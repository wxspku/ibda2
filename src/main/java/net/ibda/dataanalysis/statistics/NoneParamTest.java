package net.ibda.dataanalysis.statistics;


import org.apache.commons.math3.stat.inference.TestUtils;

public class NoneParamTest {
    public static void main(String[] args) {
        long[] observed = {43, 21, 25, 42, 57, 59};
        double[] expected = {99, 65, 79, 75, 87, 81};
        NoneParamTest test = new NoneParamTest();
        test.getChiSquare(observed, expected);

        test.gTest(observed, expected);

        double[] x = {43, 21, 25, 42, 57, 59};
        double[] y = {99, 65, 79, 75, 87, 81};
        test.calculateKs(x, y);
    }

    public void getChiSquare(long[] observed, double[] expected) {
        System.out.println("ChiSquare Test ..........");
        System.out.println(TestUtils.chiSquare(expected, observed));//chiSquare statistics
        System.out.println(TestUtils.chiSquareTest(expected,
                observed));//p value
        System.out.println(TestUtils.chiSquareTest(expected, observed,
                0.05));
    }

    public void gTest(long[] observed, double[] expected) {
        System.out.println("G Test .......... G = 2*Σ(o*ln(o/e))");
        /*double g = 2*IntStream.range(0, observed.length).asDoubleStream().
                map(i->observed[(int)i] * Math.log(observed[(int)i]/expected[(int)i])).sum();
        System.out.println("g calc:" + g);*/

        System.out.println(TestUtils.g(expected, observed));//g statistics
        System.out.println(TestUtils.gTest(expected,
                observed));//p value
        System.out.println(TestUtils.gTest(expected, observed,
                0.05));
    }

    public void calculateKs(double[] x, double[] y) {
        System.out.println("K-S Test");
        double d = TestUtils.kolmogorovSmirnovStatistic(x, y);
        System.out.println(TestUtils.kolmogorovSmirnovTest(x, y, false));
        System.out.println(TestUtils.exactP(d, x.length, y.length,
                false));
    }
}

