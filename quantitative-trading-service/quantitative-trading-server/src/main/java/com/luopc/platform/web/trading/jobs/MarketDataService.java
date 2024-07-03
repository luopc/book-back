package com.luopc.platform.web.trading.jobs;

import com.luopc.platform.web.trading.domain.MarketInfo;
import com.luopc.platform.web.trading.domain.api.MarketIndex;

import java.time.LocalDate;
import java.util.List;

public interface MarketDataService {

    void initial();

    MarketInfo getCurrentData();

    MarketIndex getMarketIndex(LocalDate date);

    List<MarketIndex> getHistoryData(LocalDate start, LocalDate end);


}
