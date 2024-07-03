package com.luopc.platform.web.trading.strategy;

import com.luopc.platform.web.trading.domain.MarketInfo;

public interface TradingStrategy {

    void calculate(MarketInfo marketInfo);

}
