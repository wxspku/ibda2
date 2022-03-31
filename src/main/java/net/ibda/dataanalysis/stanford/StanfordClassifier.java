package net.ibda.dataanalysis.stanford;

import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.ling.Datum;
import edu.stanford.nlp.objectbank.ObjectBank;

public class StanfordClassifier {
    public static void main(String[] args) throws Exception {
        ColumnDataClassifier cdc = new ColumnDataClassifier("dataset/stanford/iris2007.prop");
        Classifier<String, String> classifier =
                cdc.makeClassifier(cdc.readTrainingExamples("dataset/stanford/iris.train"));
        for (String line : ObjectBank.getLineIterator("dataset/stanford/iris.test", "utf-8")) {
            Datum<String, String> datum = cdc.makeDatumFromLine(line);
            System.out.println(line + " ==> " + classifier.classOf(datum));
        }
    }
}