package com.luopc.platform.web.trading.jobs.service;

import com.luopc.platform.web.trading.domain.MarketInfo;
import com.luopc.platform.web.trading.domain.api.MarketIndex;
import com.luopc.platform.web.trading.jobs.MarketDataService;
import com.luopc.platform.web.trading.util.LimitedSizeList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class MarketDataMockService implements MarketDataService {


    @Resource
    private MarketIndexApiCallService marketIndexApiCallService;

    private final Map<LocalDate, MarketIndex> marketDataRepository = new ConcurrentHashMap<>();
    private final LocalDate startDate = LocalDate.of(2024, 4, 30);
    private final Deque<LocalDate> nextDateQueue = new ArrayDeque<>();
    private final LimitedSizeList lastPrices = new LimitedSizeList(5);
    private MarketInfoMock marketInfoMock;


    public void initial() {
        List<MarketIndex> marketIndexList = marketIndexApiCallService.getHistoryData();
        marketIndexList.forEach(marketIndex -> marketDataRepository.put(marketIndex.getDate(), marketIndex));
        log.info("MarketDataMockService initial success, size = {}", marketDataRepository.size());

        MarketIndex marketIndex = marketDataRepository.get(startDate);
        marketInfoMock = new MarketInfoMock(marketIndex, lastPrices);
        marketIndexList.forEach(index -> {
            if (index.getDate().isAfter(startDate)
                    && index.getDate().isBefore(startDate.plusWeeks(1))) {
                nextDateQueue.add(index.getDate());
            }
        });
        log.info("MarketDataMockService initial success, currentData is {}, nextDateQueue size = {}", startDate, nextDateQueue.size());
    }

    public void updateMarketData(MarketIndex marketIndex) {
        log.info("updateMarketData： {}", marketIndex);
        if(Objects.nonNull(marketIndex)){
            marketDataRepository.put(marketIndex.getDate(), marketIndex);
        }
    }

    @Override
    public MarketIndex getMarketIndex(LocalDate date) {
        return marketDataRepository.get(LocalDate.now());
    }

    @Override
    public List<MarketIndex> getHistoryData(LocalDate start, LocalDate end) {
        List<MarketIndex> marketIndexList = new ArrayList<>();
        marketDataRepository.forEach((date, marketIndex) -> {
            if (date.isAfter(start) && date.isBefore(end)) {
                marketIndexList.add(marketIndex);
            }
        });
        return marketIndexList;
    }


    @Override
    public MarketInfo getCurrentData() {
        if (Objects.nonNull(marketInfoMock)) {
            if (marketInfoMock.hasNext()) {
                return marketInfoMock.next();
            } else {
                log.info("MarketData is = {}", marketInfoMock.getMarketIndex());
                LocalDate nextDate =  getNextDate();
                updateMarketData(marketInfoMock.getMarketIndex());
                if (Objects.nonNull(nextDate)) {
                    MarketIndex marketIndex = marketDataRepository.get(nextDate);
                    if(Objects.nonNull(marketIndex)) {
                        marketInfoMock = new MarketInfoMock(marketIndex, lastPrices);
                        log.info("MarketDataMockService currentData is = {}", marketInfoMock.getDate());
                        return marketInfoMock.next();
                    }
                }
            }
        }
        return null;
    }

    private LocalDate getNextDate() {
        return nextDateQueue.pollFirst();
    }


}
