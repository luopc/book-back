package com.luopc.platform.web.trading.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MarketIndex {


    private LocalDate date;
    private double close;
    private double open;
    private double high;
    private double low;
    private double percentage;

}
