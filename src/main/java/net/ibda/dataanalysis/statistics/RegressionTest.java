package net.ibda.dataanalysis.statistics;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.regression.GLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class RegressionTest {
    public static void main(String[] args) {
        double[][] data = {{1, 3}, {2, 5}, {3, 7}, {4, 14}, {5, 11}};
        RegressionTest test = new RegressionTest();
        test.calculateRegression(data);
        System.out.println(test.hashCode() == new RegressionTest().hashCode());

        double[] y = new double[]{11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
        double[][] x = new double[6][];
        x[0] = new double[]{0, 0, 0, 0, 0};
        x[1] = new double[]{2.0, 0, 0, 0, 0};
        x[2] = new double[]{0, 3.0, 0, 0, 0};
        x[3] = new double[]{0, 0, 4.0, 0, 0};
        x[4] = new double[]{0, 0, 0, 5.0, 0};
        x[5] = new double[]{0, 0, 0, 0, 6.0};
        test.calculateOlsRegression(x, y);

        /*DOUBLE[] GLS_Y = NEW DOUBLE[]{11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
        DOUBLE[][] GLS_X = NEW DOUBLE[6][];
        X[0] = NEW DOUBLE[]{0, 0, 0, 0, 0};
        X[1] = NEW DOUBLE[]{2.0, 0, 0, 0, 0};
        X[2] = NEW DOUBLE[]{0, 3.0, 0, 0, 0};
        X[3] = NEW DOUBLE[]{0, 0, 4.0, 0, 0};
        X[4] = NEW DOUBLE[]{0, 0, 0, 5.0, 0};
        X[5] = NEW DOUBLE[]{0, 0, 0, 0, 6.0};*/
        double[][] omega = new double[6][];
        omega[0] = new double[]{1.1, 0, 0, 0, 0, 0};
        omega[1] = new double[]{0, 2.2, 0, 0, 0, 0};
        omega[2] = new double[]{0, 0, 3.3, 0, 0, 0};
        omega[3] = new double[]{0, 0, 0, 4.4, 0, 0};
        omega[4] = new double[]{0, 0, 0, 0, 5.5, 0};
        omega[5] = new double[]{0, 0, 0, 0, 0, 6.6};
        test.calculateGlsRegression(x, y, omega);
    }

    public void calculateGlsRegression(double[][] x, double[] y,
                                       double[][] omega) {
        GLSMultipleLinearRegression regression = new
                GLSMultipleLinearRegression();
        regression.newSampleData(y, x, omega);
        double[] beta = regression.estimateRegressionParameters();
        double[] residuals = regression.estimateResiduals();
        double[][] parametersVariance =
                regression.estimateRegressionParametersVariance();
        double regressandVariance =
                regression.estimateRegressandVariance();
        double sigma = regression.estimateRegressionStandardError();
        //在这里把这些值打印出来
        System.out.println("GLS 多元回归");
        System.out.println("beta：" + StringUtils.join(beta, ','));
        System.out.println(String.format("Y = %1$.4f + %2$.4f * X1 + %3$.4f * X2  + %4$.4f * X3  + %5$.4f * X4  + %6$.4f * X5", beta[0], beta[1], beta[2], beta[3], beta[4], beta[5]));
        System.out.println("residuals：" + StringUtils.join(residuals, ','));
        System.out.println("parametersVariance：" + StringUtils.join(parametersVariance, ','));
        System.out.println("regressandVariance：" + regressandVariance + "/" + StatUtils.variance(y));

        System.out.println("sigma：" + sigma);
    }

    public void calculateRegression(double[][] data) {
        SimpleRegression regression = new SimpleRegression();
        regression.addData(data);

        System.out.println(String.format("回归结果：Y = %1$.4f + %2$.4f * X ", regression.getIntercept(), regression.getSlope()));
        System.out.println(regression.getIntercept());
        System.out.println(regression.getSlope());
        System.out.println(regression.getSlopeStdErr());
    }

    public void calculateOlsRegression(double[][] x, double[] y) {
        OLSMultipleLinearRegression regression = new
                OLSMultipleLinearRegression();
        regression.newSampleData(y, x);
        double[] beta = regression.estimateRegressionParameters();
        double[] residuals = regression.estimateResiduals();
        double[][] parametersVariance =
                regression.estimateRegressionParametersVariance();
        double regressandVariance =
                regression.estimateRegressandVariance();
        double rSquared = regression.calculateRSquared();
        double sigma = regression.estimateRegressionStandardError();
        //在这里把这些值打印出来
        System.out.println("OLS 多元回归");
        System.out.println("beta：" + StringUtils.join(beta, ','));
        System.out.println(String.format("Y = %1$.4f + %2$.4f * X1 + %3$.4f * X2  + %4$.4f * X3  + %5$.4f * X4  + %6$.4f * X5", beta[0], beta[1], beta[2], beta[3], beta[4], beta[5]));
        System.out.println("residuals：" + StringUtils.join(residuals, ','));
        System.out.println("parametersVariance：" + StringUtils.join(parametersVariance, ','));
        System.out.println("regressandVariance：" + regressandVariance + "/" + StatUtils.variance(y));

        System.out.println("rSquared：" + rSquared);
        System.out.println("sigma：" + sigma);
    }

    @Override
    public int hashCode() {
        return new Integer(1000000);
    }
}

