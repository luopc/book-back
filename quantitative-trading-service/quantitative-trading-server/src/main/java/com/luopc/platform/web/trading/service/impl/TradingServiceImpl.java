package com.luopc.platform.web.trading.service.impl;

import com.luopc.platform.base.util.SmartNumIDUtil;
import com.luopc.platform.base.util.SmartNumberUtil;
import com.luopc.platform.web.trading.domain.MarketInfo;
import com.luopc.platform.web.trading.domain.OrderRequest;
import com.luopc.platform.web.trading.domain.analysis.AnalysisAction;
import com.luopc.platform.web.trading.domain.analysis.AnalysisResult;
import com.luopc.platform.web.trading.service.AnalysisService;
import com.luopc.platform.web.trading.service.OrderService;
import com.luopc.platform.web.trading.service.TradingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TradingServiceImpl implements TradingService {

    @Resource
    private AnalysisService analysisService;

    @Resource
    private OrderService orderService;

    @Override
    public void process(MarketInfo marketInfo) {
        AnalysisResult result = analysisService.analysis(marketInfo);
        if (result.action().equals(AnalysisAction.BUY)) {
            OrderRequest orderRequest = new OrderRequest(String.valueOf(SmartNumIDUtil.shortId()), "Buy", SmartNumberUtil.nextInt(20));
            orderService.order(orderRequest);
        } else if (result.action().equals(AnalysisAction.SELL)) {
            OrderRequest orderRequest = new OrderRequest(String.valueOf(SmartNumIDUtil.shortId()), "Sell", SmartNumberUtil.nextInt(20));
            orderService.order(orderRequest);
        }
    }
}
