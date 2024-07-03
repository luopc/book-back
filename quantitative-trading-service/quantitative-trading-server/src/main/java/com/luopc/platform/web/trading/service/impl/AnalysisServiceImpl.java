package com.luopc.platform.web.trading.service.impl;

import com.luopc.platform.web.trading.domain.MarketInfo;
import com.luopc.platform.web.trading.domain.analysis.AnalysisAction;
import com.luopc.platform.web.trading.domain.analysis.AnalysisResult;
import com.luopc.platform.web.trading.service.AnalysisService;
import org.springframework.stereotype.Service;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Override
    public AnalysisResult analysis(MarketInfo marketInfo) {
        AnalysisResult analysisResult = new AnalysisResult(AnalysisAction.HOLD, 10);


        return analysisResult;
    }

}
