package com.luopc.platform.web.trading.jobs;

import com.luopc.platform.web.trading.domain.MarketInfo;
import com.luopc.platform.web.trading.domain.mapper.MarketMockDataMapper;
import com.luopc.platform.web.trading.handler.MarketPricesReceiver;
import com.luopc.platform.web.trading.util.TopKElements;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Component
public class MarketDataGenerator {

    @Resource
    private MarketDataService marketDataService;
    @Resource
    private MarketPricesReceiver marketPricesReceiver;

    private boolean isStopped = false;
    private final Set<Double> allPrices = new HashSet<>();


    @Async
    @Scheduled(initialDelay = 5 * 1000, fixedDelay = 5000)
    public void updateMarketQuotations() {
        if (!isStopped) {
            MarketInfo marketInfo = marketDataService.getCurrentData();
            if (Objects.nonNull(marketInfo)) {
                log.info("marketInfo: {}", marketInfo);
                marketPricesReceiver.handle(marketInfo);
                allPrices.add(marketInfo.getPrice());
            } else {
                List<Double> maxPrices = TopKElements.findTopKElements(new ArrayList<>(allPrices), 200);
                // 因为是从最大堆中取出的元素，所以结果列表是降序的。如果需要升序，可以反转列表。
//            Collections.reverse(result);
                isStopped = true;

                log.info("--------------------Total Size = [{}]--------------------", maxPrices.size());
                log.info("--------------------Max Prices = [{}]--------------------", maxPrices.stream().max(Double::compareTo).get());
                log.info("--------------------Min Prices = [{}]--------------------", maxPrices.stream().min(Double::compareTo).get());
                log.info("--------------------------Done----------------------------");
            }
        }

    }

}


