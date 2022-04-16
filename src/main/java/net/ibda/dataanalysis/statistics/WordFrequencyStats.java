package net.ibda.dataanalysis.statistics;

import org.apache.commons.math3.stat.Frequency;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyStats {
    public static void main(String[] args) {
        String str = "Horatio says 'tis but our fantasy, "
                + "And will not let belief take hold of him "
                + "Touching this dreaded sight, twice seen of us. "
                + "Therefore I have entreated him along, 35 "
                + "With us to watch the minutes of this night, "
                + "That, if again this apparition come, "
                + "He may approve our eyes and speak to it.";
        String[] words = str.toLowerCase().split("\\W+");
        WordFrequencyStats freqTest = new WordFrequencyStats();
        freqTest.getFreqStats(words);
    }

    public void getFreqStats(String[] words) {
        Frequency freq = new Frequency();
        for (int i = 0; i < words.length; i++) {
            freq.addValue(words[i].trim());
        }
        System.out.println(freq);

        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i] + "=" + freq.getCount(words[i]));
        }
    }

    public void getFreqStats(String str) {
        Stream<String> stream =
                Stream.of(str.toLowerCase().split("\\W+")).parallel();
        Map<String, Long> wordFreq = stream
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        wordFreq.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}

