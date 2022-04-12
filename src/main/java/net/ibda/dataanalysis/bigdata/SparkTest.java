package net.ibda.dataanalysis.bigdata;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;
import org.apache.spark.mllib.tree.RandomForest;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.mllib.util.MLUtils;
import scala.Tuple2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 不支持JDK 8+，由于spark版本过老
 */
public class SparkTest {
    public static void main(String[] args) {
        System.out.println("Calc Word count ===================");
        calcWordCount("dataset/txt/shakespeare.txt");

        System.out.println("K-means clustering ===================");
        kmeansCluster("dataset/spark/mllib/kmeans_data.txt");

        System.out.println("Linear Regression ===================");
        linearRegression("dataset/spark/mllib/ridge-data/lpsa.data");

        System.out.println("Random Forest ===================");
        randomForest("dataset/spark/mllib/sample_binary_classification_data.txt");
    }

    private static void randomForest(String input) {
        SparkConf configuration = new
                SparkConf().setMaster("local[4]").setAppName("Random Forest");
        JavaSparkContext sparkContext = new
                JavaSparkContext(configuration);
        //加载与分析数据文件
        JavaRDD<LabeledPoint> data =
                MLUtils.loadLibSVMFile(sparkContext.sc(), input).toJavaRDD();
        //把数据划分为训练集与测试集（抽取30%作为测试）
        JavaRDD<LabeledPoint>[] dataSplits = data.randomSplit(new
                double[]{0.7, 0.3});
        JavaRDD<LabeledPoint> trainingData = dataSplits[0];
        JavaRDD<LabeledPoint> testData = dataSplits[1];
        //训练随机森林模型
        Integer classes = 2;
        HashMap<Integer, Integer> nominalFeatures = new HashMap<Integer,
                Integer>();//空categoricalFeaturesInfo表示所有特征都是连续的   features are continuous.
        Integer trees = 3; //实际设置得更多
        String featureSubsetProcess = "auto"; //让算法自己选择
        String impurity = "gini";
        Integer maxDepth = 3;
        Integer maxBins = 20;
        Integer seed = 12345;
        final RandomForestModel rf =
                RandomForest.trainClassifier(trainingData, classes,
                        nominalFeatures, trees, featureSubsetProcess, impurity,
                        maxDepth, maxBins, seed);
        //在测试实例上评估模型，并计算测试误差
        JavaPairRDD<Double, Double> label =
                testData.mapToPair(new PairFunction<LabeledPoint, Double,
                        Double>() {
                    private static final long serialVersionUID = 1L;

                    public Tuple2<Double, Double> call(LabeledPoint p) {
                        return new Tuple2<Double, Double>
                                (rf.predict(p.features()), p.label());
                    }
                });
        Double error =
                1.0 * label.filter(new Function<Tuple2<Double, Double>,
                        Boolean>() {
                    private static final long serialVersionUID = 1L;

                    public Boolean call(Tuple2<Double, Double> pl) {
                        return !pl._1().equals(pl._2());
                    }
                }).count() / testData.count();
        System.out.println("Test Error: " + error);
        System.out.println("Learned classification forest model:\n" +
                rf.toDebugString());
        sparkContext.close();
    }

    private static void linearRegression(String inputFile) {
        SparkConf configuration = new
                SparkConf().setMaster("local[4]").setAppName("Linear  Regression");
        JavaSparkContext sparkContext = new
                JavaSparkContext(configuration);
        //加载与解析数据
        //String inputData = "data/lr-data.txt";
        JavaRDD<String> data = sparkContext.textFile(inputFile);
        JavaRDD<LabeledPoint> parsedData = data.map(
                new Function<String, LabeledPoint>() {
                    private static final long serialVersionUID = 1L;

                    public LabeledPoint call(String line) {
                        String[] parts = line.split(",");
                        String[] features = parts[1].split(" ");
                        double[] featureVector = new
                                double[features.length];
                        for (int i = 0; i < features.length - 1; i++) {
                            featureVector[i] =
                                    Double.parseDouble(features[i]);
                        }
                        return new LabeledPoint(Double.parseDouble(parts[0]),
                                Vectors.dense(featureVector));
                    }
                }
        );
        parsedData.cache();
        //创建模型
        int iterations = 10;
        final LinearRegressionModel model =
                LinearRegressionWithSGD.train(JavaRDD.toRDD(parsedData),
                        iterations);
        //评估模型在训练样本的表现并计算训练误差error
        JavaRDD<Tuple2<Double, Double>> predictions = parsedData.map(
                new Function<LabeledPoint, Tuple2<Double, Double>>() {
                    private static final long serialVersionUID = 1L;

                    public Tuple2<Double, Double> call(LabeledPoint point) {
                        double prediction = model.predict(point.features());
                        return new Tuple2<Double, Double>(prediction,
                                point.label());
                    }
                }
        );
        double mse = new JavaDoubleRDD(predictions.map(
                new Function<Tuple2<Double, Double>, Object>() {
                    private static final long serialVersionUID = 1L;

                    public Object call(Tuple2<Double, Double> pair) {
                        return Math.pow(pair._1() - pair._2(), 2.0);
                    }
                }
        ).rdd()).mean();
        System.out.println("training Mean Squared Error = " + mse);
        sparkContext.close();
    }

    private static void kmeansCluster(String inputFile) {
        SparkConf configuration = new
                SparkConf().setMaster("local[4]").setAppName("K-meansClustering");
        JavaSparkContext sparkContext = new
                JavaSparkContext(configuration);
        //加载与解析数据

        JavaRDD<String> data = sparkContext.textFile(inputFile);
        JavaRDD<Vector> parsedData = data.map(
                new Function<String, Vector>() {
                    private static final long serialVersionUID = 1L;

                    public Vector call(String s) {
                        String[] sarray = s.split(" ");
                        double[] values = new double[sarray.length];
                        for (int i = 0; i < sarray.length; i++)
                            values[i] = Double.parseDouble(sarray[i]);
                        return Vectors.dense(values);
                    }
                }
        );
        parsedData.cache();
        //使用K均值把数据聚集到两个类中
        int numClusters = 2;
        int iterations = 10;
        KMeansModel clusters = KMeans.train(parsedData.rdd(),
                numClusters, iterations);
        //通过计算集合的误差平方和评估聚类Errors
        double sse = clusters.computeCost(parsedData.rdd());
        System.out.println("Sum of Squared Errors within set = " + sse);
        sparkContext.close();
    }


    private static void calcWordCount(String inputFile) {
        SparkConf configuration = new
                SparkConf().setMaster("local[4]").setAppName("My App");
        JavaSparkContext sparkContext = new
                JavaSparkContext(configuration);
        JavaRDD<String> rdd = sparkContext.textFile(inputFile).cache();
        long emptyLines = rdd.filter(new Function<String, Boolean>() {
            private static final long serialVersionUID = 1L;

            public Boolean call(String s) {
                return s.trim().length() == 0;
            }
        }).count();

        System.out.println("Empty Lines: " + emptyLines);
        JavaPairRDD<String, Integer> wordCounts = rdd
                .flatMap(line -> Arrays.asList(line.toLowerCase().split("\\s")))//.iterator()
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);
        Map<String, Integer> wordMap = wordCounts.collectAsMap();
        for (Entry<String, Integer> entry : wordMap.entrySet()) {
            System.out.println("Word = " + entry.getKey() + ", Frequency= " + entry.getValue());
        }
        sparkContext.close();
    }
}
