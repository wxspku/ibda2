package net.ibda.dataanalysis.nlp;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CosineSimilarity {
    public double calculateCosine(String s1, String s2) {
        //使用Java 8并行分词
        Stream<String> stream1 =
                Stream.of(s1.toLowerCase().split("\\W+")).parallel();
        Stream<String> stream2 =
                Stream.of(s2.toLowerCase().split("\\W+")).parallel();
        //两个字符串的词频Map
        Map<String, Long> wordFreq1 = stream1
                .collect(Collectors.groupingBy
                        (String::toString, Collectors.counting()));
        Map<String, Long> wordFreq2 = stream2
                .collect(Collectors.groupingBy
                        (String::toString, Collectors.counting()));
        //每个字符串的特有单词
        Set<String> wordSet1 = wordFreq1.keySet();
        Set<String> wordSet2 = wordFreq2.keySet();
        //两个字符串的共用单词
        Set<String> intersection = new HashSet<String>(wordSet1);
        intersection.retainAll(wordSet2);
        //余弦公式的分子 s1.s2
        double numerator = 0;
        for (String common : intersection) {
            numerator += wordFreq1.get(common) * wordFreq2.get(common);
        }
        //余弦公式分母的两个参数
        double param1 = 0, param2 = 0;
        //sqrt（s1词频的平方和）
        for (String w1 : wordSet1) {
            param1 += Math.pow(wordFreq1.get(w1), 2);
        }
        param1 = Math.sqrt(param1);
        // sqrt（s2词频的平方和）
        for (String w2 : wordSet2) {
            param2 += Math.pow(wordFreq2.get(w2), 2);
        }
        param2 = Math.sqrt(param2);
        //余弦公式分母 sqrt(sum(s1^2)) X sqrt(sum(s2^2))
        double denominator = param1 * param2;
        //计算余弦相似度
        double cosineSimilarity = numerator / denominator;
        return cosineSimilarity;
    }//关闭用来计算两个字符串余弦相似度的方法。

    public static void main(String[] args) {
        CosineSimilarity cos = new CosineSimilarity();
        System.out.println(cos.calculateCosine("To be, or not to be: that is the question.",
                "Frailty, thy name is woman!"));
        System.out.println(cos.calculateCosine("The lady doth protest too   much, methinks.",
                "Frailty, thy name is woman!"));
    }
}