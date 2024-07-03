package com.luopc.platform.web.trading.service.impl;

import com.luopc.platform.web.trading.domain.OrderRequest;
import com.luopc.platform.web.trading.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public void order(OrderRequest order) {
        log.info("{}", order);
    }
}
