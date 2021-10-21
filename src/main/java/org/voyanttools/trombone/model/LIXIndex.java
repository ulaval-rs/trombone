package org.voyanttools.trombone.model;

import org.voyanttools.trombone.tool.util.TextParser;

import java.io.IOException;

public class LIXIndex extends ReadabilityIndex {

    protected double lixIndex;

    public LIXIndex(IndexedDocument indexedDocument) throws IOException {
        super(indexedDocument);

        lixIndex = calculateIndex(text);
    }

    @Override
    protected double calculateIndex(TextParser text) {
        double wordsCount = text.getWordsCount();
        double sentencesCount = text.getSentencesCount();
        double longWords = text.getWordsWithMoreThanSixLettersCount();

        double result =  wordsCount / sentencesCount + longWords * 100.0 / wordsCount;

        if (Double.isFinite(result))
            return result;

        // When the calculation fails (result is not a finite number)
        return -999;
    }

    public double getLIXIndex() {
        return lixIndex;
    }
}