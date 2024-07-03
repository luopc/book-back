package com.luopc.platform.web.trading.domain.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("market_mock_data")
public class MarketMockData implements Serializable, Cloneable {

    private String symbol;

    private Date tradeTime;

    private Double marketRv;

    private Double predictRv;
}
