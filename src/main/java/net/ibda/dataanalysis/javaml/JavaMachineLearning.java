package net.ibda.dataanalysis.javaml;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.classification.evaluation.CrossValidation;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.distance.PearsonCorrelationCoefficient;
import net.sf.javaml.featureselection.ranking.RecursiveFeatureEliminationSVM;
import net.sf.javaml.featureselection.scoring.GainRatio;
import net.sf.javaml.featureselection.subset.GreedyForwardSelection;
import net.sf.javaml.tools.data.FileHandler;

/**
 * 使用java-ml类库实现自动分类、聚类
 */
public class JavaMachineLearning {

    public static void main(String[] args) throws IOException {
        String irisPathname = "./dataset/UCI/iris/iris.data";
        Dataset data = FileHandler.loadDataset(new File(irisPathname), 4, ",");
        System.out.println(data);
        File out = new File("./out/javaml");
        if (!out.exists()){
            out.mkdirs();
        }
        FileHandler.exportDataset(data, new File("./out/javaml/javamloutput.txt"));
        data = FileHandler.loadDataset(new File("./out/javaml/javamloutput.txt"), 0, "\t");
        System.out.println(data);
        //聚类
        Clusterer km = new KMeans();
        Dataset[] clusters = km.cluster(data);
        for (Dataset cluster : clusters) {
            System.out.println("Cluster: " + cluster);
        }
        ClusterEvaluation sse = new SumOfSquaredErrors();
        double score = sse.score(clusters);
        System.out.println(score);
        //分类
        Classifier knn = new KNearestNeighbors(5);
        knn.buildClassifier(data);
        //交叉验证
        CrossValidation cv = new CrossValidation(knn);
        Map<Object, PerformanceMeasure> cvEvaluation = cv.crossValidation(data);
        System.out.println(cvEvaluation);
        //Held-out测试
        Dataset testData = FileHandler.loadDataset(new File(irisPathname), 4, ",");
        Map<Object, PerformanceMeasure> testEvaluation =
                EvaluateDataset.testDataset(knn, testData);
        for (Object classVariable : testEvaluation.keySet()) {
            System.out.println(classVariable + " class has " + testEvaluation.get(classVariable).getAccuracy());
        }
        //特征打分
        System.out.println("特征打分 ---------");
        GainRatio gainRatio = new GainRatio();
        gainRatio.build(data);
        for (int i = 0; i < gainRatio.noAttributes(); i++) {
            System.out.println(gainRatio.toString() + ":" + gainRatio.score(i));
        }
        //特征排序
        System.out.println("特征排序 ---------");
        RecursiveFeatureEliminationSVM featureRank = new RecursiveFeatureEliminationSVM(0.2);
        featureRank.build(data);
        for (int i = 0; i < featureRank.noAttributes(); i++) {
            System.out.println(featureRank.toString() + ":" + featureRank.rank(i));
        }
        //特征子集选择
        GreedyForwardSelection featureSelection = new GreedyForwardSelection(5, new PearsonCorrelationCoefficient());
        featureSelection.build(data);
        System.out.println(featureSelection.selectedAttributes());
    }
}
