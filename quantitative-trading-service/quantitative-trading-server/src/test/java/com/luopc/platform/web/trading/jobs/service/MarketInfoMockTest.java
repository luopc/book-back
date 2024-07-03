package com.luopc.platform.web.trading.jobs.service;

import com.luopc.platform.web.trading.domain.MarketInfo;
import com.luopc.platform.web.trading.domain.api.MarketIndex;
import com.luopc.platform.web.trading.util.LimitedSizeList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Slf4j
class MarketInfoMockTest {

    @Test
    public void testNext() {
        //"2024-5-22","63932","64031","64479","63779","","-0.11%"
        MarketIndex marketIndex = new MarketIndex(LocalDate.of(2024, 4, 30), 19299.18, 19227.84, 19339.82, 19087.66, 0.0025);

        LimitedSizeList lastPrices = new LimitedSizeList(5);
        MarketInfoMock marketInfoMock = new MarketInfoMock(marketIndex, lastPrices);

        log.info("Total size = {}", marketInfoMock.getSize());
        while (marketInfoMock.hasNext()) {
            MarketInfo marketInfo = marketInfoMock.next();
            log.info("{}", String.format("marketInfo = %s", marketInfo));
        }

    }
}