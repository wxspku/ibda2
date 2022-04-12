package net.ibda.dataanalysis.bigdata;

import com.google.common.base.Charsets;
import org.apache.mahout.classifier.evaluation.Auc;
import org.apache.mahout.classifier.sgd.CsvRecordFactory;
import org.apache.mahout.classifier.sgd.LogisticModelParameters;
import org.apache.mahout.classifier.sgd.OnlineLogisticRegression;
import org.apache.mahout.math.Matrix;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.SequentialAccessSparseVector;
import org.apache.mahout.math.Vector;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MahoutLogisticRegression {
    public static void main(String[] args) throws IOException {
        String inputFile = "dataset/weka/weather.numeric.csv";
        String modelFile = "model/bigdata/mahout_lr.model";
        System.out.println("Model Training ======================== ");
        LRTrain(inputFile, modelFile);
        System.out.println("\nModel Testing ========================= ");
        LRTest("dataset/weka/weather.numeric.test.csv",modelFile);
    }

    public static void LRTest(String inputFile, String modelFile) throws IOException {
        Auc auc = new Auc();
        LogisticModelParameters params =
                LogisticModelParameters.loadFrom(new File(modelFile));
        CsvRecordFactory csv = params.getCsvRecordFactory();
        OnlineLogisticRegression olr = params.createRegression();
        InputStream in = new FileInputStream(new File(inputFile));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charsets.UTF_8));
        String line = reader.readLine();
        csv.firstLine(line);
        line = reader.readLine();
        PrintWriter output = new PrintWriter(new
                OutputStreamWriter(System.out, Charsets.UTF_8), true);
        output.println("\"class\",\"model-output\",\"log-likelihood\"");
        while (line != null) {
            Vector vector = new
                    SequentialAccessSparseVector(params.getNumFeatures());
            int classValue = csv.processLine(line, vector);
            double score = olr.classifyScalarNoLink(vector);
            output.printf(Locale.ENGLISH, "%d,%.3f,%.6f%n", classValue,
                    score, olr.logLikelihood(classValue, vector));
            auc.add(classValue, score);
            line = reader.readLine();
        }
        reader.close();
        output.printf(Locale.ENGLISH, "AUC = %.2f%n", auc.auc());
        Matrix matrix = auc.confusion();
        output.printf(Locale.ENGLISH, "confusion: [[%.1f, %.1f], [%.1f, %.1f]]%n",
                matrix.get(0, 0), matrix.get(1, 0), matrix.get(0,1), matrix.get(1, 1));
        matrix = auc.entropy();
        output.printf(Locale.ENGLISH, "entropy: [[%.1f, %.1f], [%.1f,%.1f]]%n",
                matrix.get(0, 0), matrix.get(1, 0), matrix.get(0,1), matrix.get(1, 1));
    }

    private static void LRTrain(String inputFile, String modelFile) throws IOException {
        List<String> features = Arrays.asList("outlook", "temperature",
                "humidity", "windy", "play");
        List<String> featureType = Arrays.asList("w", "n", "n", "w", "w");
        LogisticModelParameters params = new LogisticModelParameters();
        params.setTargetVariable("play");
        params.setMaxTargetCategories(2);
        params.setNumFeatures(4);
        params.setUseBias(false);
        params.setTypeMap(features, featureType);
        params.setLearningRate(0.5);
        int passes = 10;
        OnlineLogisticRegression olr;
        CsvRecordFactory csv = params.getCsvRecordFactory();
        olr = params.createRegression();
        for (int pass = 0; pass < passes; pass++) {
            BufferedReader in = new BufferedReader(new
                    FileReader(inputFile));
            csv.firstLine(in.readLine());
            String row = in.readLine();
            while (row != null) {
                System.out.println(row);
                Vector input = new
                        RandomAccessSparseVector(params.getNumFeatures());
                int targetValue = csv.processLine(row, input);
                olr.train(targetValue, input);
                row = in.readLine();
            }
            in.close();
        }
        OutputStream modelOutput = new FileOutputStream(modelFile);
        try {
            params.saveTo(modelOutput);
        } finally {
            modelOutput.close();
        }
    }
}
        

