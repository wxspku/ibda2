package net.ibda.dataanalysis.nlp;

import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

public class StanfordCoreNlpLemmatizer {
    public static void main(String[] args) {
        StanfordCoreNLP pipeline;
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner");
        pipeline = new StanfordCoreNLP(props, false);
        String text = "Hamlet's mother, Queen Gertrude, says this famous line while watching The Mousetrap. "
                + "Gertrude is talking about the queen in the play. "
                + "She feels that the play-queen seems insincere because she repeats so dramatically that" +
                " she'll never remarry due to her undying love of her husband.";
        Annotation document = pipeline.process(text);
        for (CoreMap sentence : document.get(SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                String word = token.get(TextAnnotation.class);
                String lemma = token.get(LemmaAnnotation.class);
                String pos = token.get(PartOfSpeechAnnotation.class);
                String ne = token.get(NamedEntityTagAnnotation.class);
                System.out.println(word + "-->" + lemma + "-->" + pos + "-->" + ne);
            }
        }
    }
}