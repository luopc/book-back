package com.luopc.platform.web.trading.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {

    public String orderId;
    public String orderType;
    public double amount;

}
