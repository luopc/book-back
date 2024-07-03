package com.luopc.platform.web.trading.util;

import ai.djl.ndarray.NDManager;
import com.luopc.trade.api.tools.RateCalculator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MathUtil {

    /**
     * 一般情况下，短线交易员用5、10、15、30日历史波动率较多；中长线用60日、180日、360日居多
     * @param preClose
     * @param nextClose
     * @return
     */
    public static double ln(double preClose, double nextClose) {
        double result = RateCalculator.div(nextClose, preClose, 10);
        return Math.log(result);
    }

    /**
     * STDEV.S() 是 Excel 中的一个函数，用于计算给定数据集的总体标准偏差（sample standard deviation）。在 Java 中，你可以通过编写一个方法来模拟这个函数的行为。
     * 总体标准偏差的计算公式如下：
     * [ \text{STDEV.S} = \sqrt{\frac{1}{N}\sum_{i=1}^{N}(x_i - \bar{x})^2} ]
     * 其中，( N ) 是数据点的数量，( x_i ) 是每个数据点，( \bar{x} ) 是数据点的平均值。
     *
     * @param values 数据集
     * @return 总体标准偏差
     */
    public static double stdevS(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int n = values.length;
        double mean = calculateMean(values);
        double sumOfSquares = 0.0;

        // 计算平方和（并减去平均值的平方）
        for (double value : values) {
            sumOfSquares += Math.pow(value - mean, 2);
        }
        // 计算标准偏差
        return Math.sqrt(sumOfSquares / n);
    }

    public static double calculateMean(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    public static double[] calculateDeviations(double[] data, double mean) {
        double[] deviations = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            deviations[i] = data[i] - mean;
        }
        return deviations;
    }


    public static void main(String[] args) {
        log.info("volatility: {}", String.format("%.4f%%", ln(59727, 59707) * 100));
        double[] values = {-0.000335,
                0.019570,
                -0.001002,
                -0.002650,
                0.000049,
                0.016868,
                0.005173,
                -0.028357,
                0.000680,
                0.000116,
                0.009337,
                -0.010631,
                0.011058,
                0.009930,
                0.004831,
                -0.004279,
                0.000520,
                -0.002357,
                -0.017730,
                0.008083,
                0.013431
        };
        log.info("stdevS: {}", String.format("%.4f%%", stdevS(values) * 100));

        log.info("stdevS: {}", String.format("%.4f", stdevS(new double[]{1, 3, 5, 7, 9})));

        double volatility = Math.sqrt(250) * 0.011148908;
        log.info("volatility: {}", String.format("%.4f%%", volatility * 100));

        try (NDManager manager = NDManager.newBaseManager()) {
//            manager.stdevS(manager.create(values));
        }
    }

}
