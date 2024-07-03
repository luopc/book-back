package com.luopc.platform.web.trading.domain.api;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SockInfo {

    private LocalDateTime dateTime;
    private String symbol = "HSI";
    private double price;
    private double high;
    private double low;
    private long turnover;
    private double current;
    private double change;
    private double percent;
    private Integer constituentCount;
    private List<PointContribution> pintContributions;
    private String no;
    private String sector;
    private String status;
    private MsgType msgType;
    private String indicator;


    public enum MsgType {
        /**
         *
         */
        PC,
        /**
         *
         */
        HL,
        /**
         *
         */
        T,
        /**
         *
         */
        E,
        /**
         *
         */
        MAIN;
    }
}
