package com.luopc.platform.web.trading.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Slf4j
@NoArgsConstructor
public class MarketIndexResponse {

    private String date;
    private double close;
    private double open;
    private double high;
    private double low;
    private String percentage;

    public MarketIndex getMarketIndex() {
        //Convert 2018-2-1 to localDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        // 使用DateTimeFormatter将字符串转换为LocalDate
        LocalDate date = LocalDate.parse(getDate(), formatter);
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        double percent = 0;
        try {
            percent = numberFormat.parse(percentage).doubleValue();
        } catch (ParseException e) {
            log.error("unable to parse percentage number: {}", getPercentage());
        }
        return new MarketIndex(date, close, open, high, low, percent);
    }

}
