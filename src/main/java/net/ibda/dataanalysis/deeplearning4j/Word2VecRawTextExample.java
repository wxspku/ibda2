package net.ibda.dataanalysis.deeplearning4j;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.InMemoryLookupCache;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class Word2VecRawTextExample {
    private static Logger logger =
            LoggerFactory.getLogger(Word2VecRawTextExample.class);

    public static void main(String[] args) throws Exception {
        String stopWordsFilePath = "dataset/txt/stopwords.txt";
        List<String> stopWords = FileUtils.readLines(new File(stopWordsFilePath), Charsets.UTF_8);
        //获取文本文件路径
        String filePath = "dataset/txt/shakespeare.txt";
        logger.info("Load & Vectorized Sentences....");
        //去除每行前后的空格
        //SentenceIterator iter = UimaSentenceIterator.createWithPath(filePath);
        SentenceIterator iter = new BasicLineIterator(filePath);
        //,
        //                AnalysisEngineFactory.createEngine(AnalysisEngineFactory.createEngineDescription(
        //                                                                        TokenizerAnnotator.getDescription(),
        //                                                                        SentenceAnnotator.getDescription()))
        //根据数据行中的空格进行切分，获取单词
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());
        InMemoryLookupCache cache = new InMemoryLookupCache();
        WeightLookupTable table = new InMemoryLookupTable.Builder()
                .vectorLength(100)
                .useAdaGrad(false)
                .cache(cache)
                .lr(0.025f).build();
        logger.info("Building model....");
        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(5).iterations(1)
                .layerSize(100).lookupTable(table)
                .stopWords(stopWords)
                .vocabCache(cache).seed(42)
                .windowSize(5).iterate(iter).tokenizerFactory(t).build();
        logger.info("Fitting Word2Vec model....");
        vec.fit();
        logger.info("Writing word vectors to text file....");
        //写单词
        WordVectorSerializer.writeWordVectors(vec, "model/word2vec/word2vec.txt");
        logger.info("Closest Words:");
        Collection<String> lst = vec.wordsNearest("man", 5);
        System.out.println(lst);
        double cosSim = vec.similarity("cruise", "voyage");
        System.out.println(cosSim);
    }
}

