package com.luopc.platform.web.trading.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketInfo {

    private LocalDateTime dateTime;
    private Double price;
    private Double high;
    private Double low;
    private Double flu;
    private List<Double> lastPrices;

    @Override
    public String toString() {
        return "MarketInfo{" +
                "dateTime=" + dateTime +
                ", price=" + price +
                ", high=" + high +
                ", low=" + low +
                ", flu=" + String.format("%.4f%%", flu * 100) +
                ", lastPrices=" + lastPrices +
                '}';
    }
}
