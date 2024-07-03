package com.luopc.platform.web.trading.handler;

import com.luopc.platform.base.util.SmartNumIDUtil;
import com.luopc.platform.web.trading.domain.DO.MarketMockData;
import com.luopc.platform.web.trading.domain.MarketInfo;
import com.luopc.platform.web.trading.domain.mapper.MarketMockDataMapper;
import com.luopc.platform.web.trading.service.TradingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class MarketPricesReceiver {

    @Resource
    private TradingService tradingService;
    @Resource
    private MarketMockDataMapper marketMockDataMapper;


    public void handle(MarketInfo marketInfo) {
        tradingService.process(marketInfo);
        marketMockDataMapper.insert(convertToDo(marketInfo));
    }

    public MarketMockData convertToDo(MarketInfo marketInfo) {
        MarketMockData marketMockData = new MarketMockData();
        ZonedDateTime zonedDateTime = marketInfo.getDateTime().atZone(ZoneId.systemDefault());
//        marketMockData.setTradeTime(Date.from(zonedDateTime.toInstant()));
        marketMockData.setTradeTime(new Date());
        marketMockData.setSymbol(String.valueOf(SmartNumIDUtil.shortId()));
        marketMockData.setMarketRv(marketInfo.getPrice());
        return marketMockData;
    }

}
