package com.luopc.platform.web.trading.domain.api;

import lombok.Data;

@Data
public class PointContribution {

    private String type;
    private int index;
    private double contribute;

}
