package org.voyanttools.trombone.model;

import org.junit.Test;
import org.voyanttools.trombone.storage.Storage;
import org.voyanttools.trombone.storage.memory.MemoryStorage;
import org.voyanttools.trombone.tool.corpus.CorpusCreator;
import org.voyanttools.trombone.tool.corpus.CorpusManager;
import org.voyanttools.trombone.util.FlexibleParameters;

import java.io.IOException;

public class ColemanLiauIndexTest {

    public static final String TEXT = "Existing computer programs that measure readability are based largely upon subroutines which estimate number of syllables, usually by counting vowels. The shortcoming in estimating syllables is that it necessitates keypunching the prose into the computer. There is no need to estimate syllables since word length in letters is a better predictor of readability than word length in syllables. Therefore, a new readability formula was computed that has for its predictors letters per 100 words and sentences per 100 words. Both predictors can be counted by an optical scanning device, and thus the formula makes it economically feasible for an organization such as the U.S. Office of Education to calibrate the readability of all textbooks for the public school system.";

    public static final double EXPECTED_COLEMAN_LIAU_INDEX = 14.53042016806722;

    @Test
    public void test() throws IOException {
        IndexedDocument indexedDocument = makeIndexedDocument(TEXT);

        ColemanLiauIndex colemanLiauIndex = new ColemanLiauIndex(indexedDocument);

        assert colemanLiauIndex.getColemanLiauIndex() == EXPECTED_COLEMAN_LIAU_INDEX;
    }

    public static IndexedDocument makeIndexedDocument(String text) throws IOException {
        Storage storage = new MemoryStorage();
        FlexibleParameters parameters = new FlexibleParameters(new String[]{"string="+text});
        CorpusCreator creator = new CorpusCreator(storage, parameters);
        creator.run();

        Corpus corpus = CorpusManager.getCorpus(storage, parameters);

        return corpus.getDocument(corpus.getDocumentIds().get(0));
    }
}
