package net.ibda.dataanalysis.statistics;

import org.apache.commons.math.stat.StatUtils;
import org.apache.commons.math3.stat.inference.TestUtils;

public class ParamTest {
    public static void main(String[] args) {
        double[] sample1 = {43, 21, 25, 42, 57, 59};
        double[] sample2 = {99, 65, 79, 75, 87, 81};
        //double[] sample2 = {45, 28, 30, 40, 67, 61};
        ParamTest test = new ParamTest();
        test.getTtest(sample1, sample2);
    }

    public void getTtest(double[] sample1, double[] sample2) {
        System.out.println("均值：" + StatUtils.mean(sample1) + "/" + StatUtils.mean(sample2) );

        System.out.println("Paired TTest");
        System.out.println(TestUtils.pairedT(sample1, sample2));//t statistics
        System.out.println(TestUtils.pairedTTest(sample1, sample2));//p value
        System.out.println(TestUtils.pairedTTest(sample1, sample2, 0.05));

        System.out.println("方差齐 None Paired TTest");
        System.out.println(TestUtils.homoscedasticT(sample1, sample2));//t statistics
        System.out.println(TestUtils.homoscedasticTTest(sample1, sample2));//p value
        System.out.println(TestUtils.homoscedasticTTest(sample1, sample2, 0.05));

        System.out.println("方差不齐 None Paired TTest");
        System.out.println(TestUtils.t(sample1, sample2));//t statistics
        System.out.println(TestUtils.tTest(sample1, sample2));//p value
        System.out.println(TestUtils.tTest(sample1, sample2, 0.05));

    }
}