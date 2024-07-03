package com.luopc.platform.web.trading.jobs.service;

import com.luopc.platform.base.util.SmartNumberUtil;
import com.luopc.platform.web.trading.domain.MarketInfo;
import com.luopc.platform.web.trading.domain.api.MarketIndex;
import com.luopc.platform.web.trading.util.LimitedSizeList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Slf4j
public class MarketInfoMock {

    @Getter
    private final LocalDate date;
    @Getter
    private final MarketIndex marketIndex;
    private final Deque<MarketInfo> marketQueue;


    public MarketInfoMock(MarketIndex marketIndex, LimitedSizeList pricesCache) {
        this.date = marketIndex.getDate();
        this.marketIndex = marketIndex;
        int dayIndexes = 12 * 60 / 5; //(int) TimeUnit.DAYS.toMinutes(1) / 5; // 1440/5
        marketQueue = new ArrayDeque<>(dayIndexes);
        double high, low, open = marketIndex.getOpen();
        double close = marketIndex.getClose();
        high = open;
        low = open;
        double percentage = marketIndex.getPercentage();
        boolean isUp = open < close;
        double previous = 0;
        double current = open;

        int hour = 8;
        int minute = 0;
        for (int i = 0; i < dayIndexes; i++) {
            previous = current;
            List<Double> lastPrices = new ArrayList<>(pricesCache.getList());
            if (minute >= 60) {
                hour++;
                minute = 0;
            }
            LocalDateTime localDateTime = LocalDateTime.of(date, LocalTime.of(hour, minute));
            if (i > 0) {
                isUp = isUp(i, dayIndexes, current, close, isUp);
                double flu = getFlu(isUp, percentage);
                current = current + (current * flu);

                if (current < marketIndex.getLow()) {
                    current = marketIndex.getLow();
                    isUp = true;
                }
                if (current > marketIndex.getHigh()) {
                    current = marketIndex.getHigh();
                    isUp = false;
                }
            }
            current = BigDecimal.valueOf(current).setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (current > high) {
                high = current;
            }
            if (current < low) {
                low = current;
            }
            pricesCache.add(current);
            marketQueue.add(new MarketInfo(localDateTime, current, high, low, (current - previous) / previous, lastPrices));

            minute = (i % 12 * 5) + 5;
        }
        double flu = (close - current) / current;
        marketQueue.add(new MarketInfo(LocalDateTime.of(date, LocalTime.of(20, 59)), close, high, low, flu, new ArrayList<>(pricesCache.getList())));
    }

    public MarketInfo next() {
        return marketQueue.pollFirst();
    }

    public boolean hasNext() {
        return !marketQueue.isEmpty();
    }

    public double getFlu(boolean isUp, double percentage) {
        long maxFlu = (long) (percentage * 1000000);
        long randomNumber = SmartNumberUtil.randomNumber(0, Math.abs(maxFlu));
        double flu = (double) randomNumber / 1000000;
        return isUp ? flu : flu * -1;
    }

    private boolean isUp(int index, int total, double current, double close, boolean oldValue) {
        if (index >= total * 0.9) {
            return current < close;
        } else {
            if (index % 6 == 0) {
                return SmartNumberUtil.randomNumber(0, 9) % 2 == 0;
            }
            return oldValue;
        }
    }

    public int getSize() {
        return marketQueue.size();
    }
}
