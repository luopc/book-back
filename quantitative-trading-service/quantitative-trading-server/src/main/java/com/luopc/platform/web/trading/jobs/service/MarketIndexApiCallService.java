package com.luopc.platform.web.trading.jobs.service;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import com.luopc.platform.web.trading.domain.api.MarketIndex;
import com.luopc.platform.web.trading.domain.api.MarketIndexResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
public class MarketIndexApiCallService {


    public List<MarketIndex> getHistoryData() {
        return getLocalData("static/indexes_from_2018_2024.csv");
    }

    private List<MarketIndex> getLocalData(String path) {
        CsvReader reader = CsvUtil.getReader();
        List<MarketIndexResponse> responseList = reader.read(
                ResourceUtil.getUtf8Reader(path), MarketIndexResponse.class);
        return responseList.stream().map(MarketIndexResponse::getMarketIndex).sorted(Comparator.comparing(MarketIndex::getDate)).toList();
    }

    public static void main(String[] args) {
//        List<MarketIndex> indexList1 = getLocalData("D:\\workspace\\java\\personal-projects\\trade-exchange-platfrom\\quantitative-trading-service\\quantitative-trading-server\\src\\main\\resources\\indexes\\indexes_from_2018_2024.csv");
//        System.out.println(indexList1);
    }
}
