package net.ibda.dataanalysis.statistics;


import org.apache.commons.math3.stat.inference.TestUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

public class NoneParamTest {
    public static void main(String[] args) {
        long[] observed = {43, 21, 25, 42, 57, 59};
        double[] expected = {99, 65, 79, 75, 87, 81};
        NoneParamTest test = new NoneParamTest();
        //卡方检验、G-Test均是基于频次，common-math计算这两个统计量时都是需要进行观测总数rescale
        //即观测值和期望值需要总数相同，如不相同，则需要根据实际观测次数进行进行同比调整期望值
        test.getChiSquare(observed, expected);

        test.gTest(observed, expected);

        double[] x = {43, 21, 25, 42, 57, 59};
        double[] y = {99, 65, 79, 75, 87, 81};
        test.calculateKs(x, y);
    }

    public void getChiSquare(long[] observed, double[] expected) {
        System.out.println("ChiSquare Test: sum((expected[i] - observed[i])^2 / expected[i])..........");

        double observedTotal = Arrays.stream(observed).sum();
        double expectedTotal = Arrays.stream(expected).sum();
        double ratio =  observedTotal / expectedTotal;
        double chi = IntStream.range(0, observed.length)
                .mapToDouble(i->Math.pow(ratio * expected[i]-observed[i],2)/ (ratio * expected[i])).sum();
        System.out.println("calc by stream:" + chi);
        System.out.println(TestUtils.chiSquare(expected, observed));//chiSquare statistics
        System.out.println(TestUtils.chiSquareTest(expected,observed));//p value
        System.out.println(TestUtils.chiSquareTest(expected, observed,0.05));
    }

    public void gTest(long[] observed, double[] expected) {
        System.out.println("G Test .......... G = 2 * sum(observed[i]) * log(observed[i]/expected[i])");
        double observedTotal = Arrays.stream(observed).sum();
        double expectedTotal = Arrays.stream(expected).sum();
        double ratio =  observedTotal / expectedTotal;
        double g2 = 2* IntStream.range(0, observed.length)
                .mapToDouble(i->observed[i] * Math.log(observed[i]/(ratio * expected[i]))).sum();
        System.out.println("g calc by stream:" + g2);

        System.out.println(TestUtils.g(expected, observed));//g statistics
        System.out.println(TestUtils.gTest(expected, observed));//p value
        System.out.println(TestUtils.gTest(expected, observed,
                0.05));
    }

    public void calculateKs(double[] x, double[] y) {
        System.out.println("K-S Test");
        double d = TestUtils.kolmogorovSmirnovStatistic(x, y);
        System.out.println(TestUtils.kolmogorovSmirnovTest(x, y, false));
        System.out.println(TestUtils.exactP(d, x.length, y.length,false));
    }
}

