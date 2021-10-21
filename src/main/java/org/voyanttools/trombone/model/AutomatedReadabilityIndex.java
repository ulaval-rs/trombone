package org.voyanttools.trombone.model;

import org.voyanttools.trombone.tool.util.TextParser;

import java.io.IOException;

public class AutomatedReadabilityIndex extends ReadabilityIndex {

    protected double automatedReadabilityIndex;

    public AutomatedReadabilityIndex(IndexedDocument indexedDocument) throws IOException {
        super(indexedDocument);

        automatedReadabilityIndex = calculateIndex(text);
    }

    @Override
    protected double calculateIndex(TextParser text) {
        double lettersPerWord = (double) text.getLettersCount() / text.getWordsCount();
        double wordsPerSentence = (double) text.getWordsCount() / text.getSentencesCount();

        double result = 4.71 * lettersPerWord + 0.5 * wordsPerSentence - 21.43;

        if (Double.isFinite(result))
            return result;

        // When the calculation fails (result is not a finite number)
        return -999;
    }

    public double getAutomatedReadabilityIndex() {
        return automatedReadabilityIndex;
    }
}
