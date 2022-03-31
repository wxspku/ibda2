package net.ibda.dataanalysis.mulan;

import mulan.classifier.lazy.MLkNN;
import mulan.classifier.meta.RAkEL;
import mulan.classifier.transformation.LabelPowerset;
import mulan.data.InvalidDataFormatException;
import mulan.data.MultiLabelInstances;
import mulan.evaluation.Evaluator;
import mulan.evaluation.MultipleEvaluation;
import weka.classifiers.trees.J48;

import java.io.File;
import java.io.IOException;

/**
 * 多分类 http://mulan.sourceforge.net/
 */
public class Mulan {
    public static void main(String[] args) throws IOException {
        String where = "";//new File(".").getAbsolutePath() ;
        if (args.length > 0) {
            where = args[0] + File.separator;
        }
        MultiLabelInstances dataset = null;
        try {
            dataset = new MultiLabelInstances(where + "dataset/mulan/emotions.arff",
                    where + "dataset/mulan/emotions.xml");
        } catch (InvalidDataFormatException e) {

        }
        RAkEL learner1 = new RAkEL(new LabelPowerset(new
                J48()));
        MLkNN learner2 = new MLkNN();
        Evaluator eval = new Evaluator();
        MultipleEvaluation results;
        int numFolds = 10;
        //weka-dev需从moa库中排除，否则会出现方法未定义错误3.7.10版本，但似乎仍然无法运行
        results = eval.crossValidate(learner1, dataset, numFolds);
        System.out.println(results);
        results = eval.crossValidate(learner2, dataset, numFolds);
        System.out.println(results);
    }
}