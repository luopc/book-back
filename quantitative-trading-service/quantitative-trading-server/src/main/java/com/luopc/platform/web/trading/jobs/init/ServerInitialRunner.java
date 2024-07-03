package com.luopc.platform.web.trading.jobs.init;

import com.luopc.platform.web.trading.jobs.MarketDataService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Setter
@Component
public class ServerInitialRunner implements ApplicationRunner {

    @Resource
    private MarketDataService marketDataService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ServerInitial start");
        marketDataService.initial();
        log.info("ServerInitial end");
    }

}
