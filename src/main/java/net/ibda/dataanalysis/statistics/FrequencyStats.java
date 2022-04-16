package net.ibda.dataanalysis.statistics;

import org.apache.commons.math3.stat.Frequency;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrequencyStats {
    public static void main(String[] args) {
        double[] values = {32, 39, 14, 98, 45, 44, 45, 34, 89, 67, 0, 15,
                0, 56, 88, 0};
        FrequencyStats freqTest = new FrequencyStats();
        freqTest.getFreqStats(values);

    }

    public void getFreqStats(double[] values) {
        Frequency freq = new Frequency();
        for (int i = 0; i < values.length; i++) {
            freq.addValue(values[i]);
        }
        System.out.println(freq);

        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i] + "：" + freq.getCount(values[i]));
        }
    }


}
