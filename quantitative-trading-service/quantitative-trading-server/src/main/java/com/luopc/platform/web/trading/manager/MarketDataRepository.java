package com.luopc.platform.web.trading.manager;

import com.luopc.trade.api.market.CcyPair;
import com.luopc.trade.api.market.MarketPrices;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MarketDataRepository {


    private final Map<CcyPair, MarketPrices> marketPricesMap = new ConcurrentHashMap<>();


}
