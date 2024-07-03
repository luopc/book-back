package com.luopc.platform.web.trading.service;

import com.luopc.platform.web.trading.domain.MarketInfo;

public interface TradingService {

    void process(MarketInfo marketInfo);

}
