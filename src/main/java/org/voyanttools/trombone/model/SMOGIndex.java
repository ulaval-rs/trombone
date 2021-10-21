package org.voyanttools.trombone.model;

import org.voyanttools.trombone.tool.util.TextParser;

import java.io.IOException;

public class SMOGIndex extends ReadabilityIndex {

    protected double smogIndex;

    public SMOGIndex(IndexedDocument indexedDocument) throws IOException {
        super(indexedDocument);

        smogIndex = calculateIndex(text);
    }

    /*
    Mc Laughlin, G. Harry. “SMOG Grading-a New Readability Formula.” Journal of Reading 12, no. 8 (1969): 639–46. http://www.jstor.org/stable/40011226.
     */
    @Override
    protected double calculateIndex(TextParser text) {
        double polysyllablesWordsPerSentence = (double) text.getWordsWithMoreThanTwoSyllablesCount() / text.getSentencesCount();

        double result = 1.043 * Math.sqrt(30 * polysyllablesWordsPerSentence) + 3.1291;

        if (Double.isFinite(result))
            return result;

        // When the calculation fails (result is not a finite number)
        return -999;
    }

    public double getSMOGIndex() {
        return smogIndex;
    }
}
