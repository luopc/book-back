package com.luopc.platform.web.trading.util;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class LimitedSizeList {
    @Getter
    private final List<Double> list = new LinkedList<>();
    private final int maxSize;

    public LimitedSizeList(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(Double element) {
        list.addFirst(element);
        if (list.size() > maxSize) {
            list.removeLast();
        }
    }

}