package org.voyanttools.trombone.model;

import org.voyanttools.trombone.tool.util.TextParser;
import ucar.ma2.Index;

import java.io.IOException;

public class ColemanLiauIndex extends ReadabilityIndex {

    protected double colemanLiauIndex;

    public ColemanLiauIndex(IndexedDocument indexedDocument) throws IOException {
        super(indexedDocument);

        colemanLiauIndex = calculateIndex(text);
    }

    @Override
    protected double calculateIndex(TextParser text) {
        double l = (double) text.getLettersCount() / text.getWordsCount() * 100;
        double s = (double) text.getSentencesCount() / text.getWordsCount() * 100;

        double result = 0.0588 * l - 0.296 * s - 15.8;

        if (Double.isFinite(result))
            return result;

        // When the calculation fails (result is not a finite number)
        return -999;
    }

    public double getColemanLiauIndex() {
        return colemanLiauIndex;
    }
}
