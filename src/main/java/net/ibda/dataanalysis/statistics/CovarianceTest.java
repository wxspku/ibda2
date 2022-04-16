package net.ibda.dataanalysis.statistics;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class CovarianceTest {
    public static void main(String[] args) {
        double[] x = {43, 21, 25, 42, 57, 59};
        double[] y = {99, 65, 79, 75, 87, 81};
        CovarianceTest test = new CovarianceTest();
        double covariance = test.calculateCov(x, y);
        double pearson = test.calculatePearson(x,y);
        System.out.println(covariance / Math.sqrt(StatUtils.populationVariance(x) * StatUtils.populationVariance(y))/6*5);
    }

    public double calculateCov(double[] x, double[] y) {
        double covariance = new Covariance().covariance(x, y, true);//If false is removed, we get unbiased:n-1
        System.out.println(covariance);
        return covariance;
    }

    public double calculatePearson(double[] x, double[] y) {
        PearsonsCorrelation pCorrelation = new PearsonsCorrelation();
        double cor = pCorrelation.correlation(x, y);
        System.out.println(cor);
        return cor;
    }
}

