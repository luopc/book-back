package com.luopc.platform.web.trading.service;

import com.luopc.platform.web.trading.domain.analysis.AnalysisResult;
import com.luopc.platform.web.trading.domain.MarketInfo;

public interface AnalysisService {

    AnalysisResult analysis(MarketInfo marketInfo);

}
