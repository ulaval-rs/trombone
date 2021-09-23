package org.voyanttools.trombone.tool.corpus;

import org.junit.Test;
import org.voyanttools.trombone.model.FOGIndex;
import org.voyanttools.trombone.model.FOGIndexTest;
import org.voyanttools.trombone.storage.Storage;
import org.voyanttools.trombone.util.FlexibleParameters;
import org.voyanttools.trombone.util.TestHelper;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.fail;


public class DocumentFOGIndexTest {

    private static final String FILE_PATH_FR = "udhr/udhr-fr.txt";
    private static final double EXPECTED_FR_FOG_INDEX = 15.627728400992874;

    private static final String FILE_PATH_EN = "udhr/udhr-en.txt";
    private static final double EXPECTED_EN_FOG_INDEX = 11.036356849570737;

    @Test
    public void testWithEnglishTexts() throws IOException {
        for (Storage storage : TestHelper.getDefaultTestStorages()) {
            System.out.println("Testing with "+storage.getClass().getSimpleName()+": "+storage.getLuceneManager().getClass().getSimpleName());

            testWithGivenParameters(storage, new FlexibleParameters(new String[]{"string="+FOGIndexTest.TEXT_EN}), FOGIndexTest.EXPECTED_FOG_INDEX_EN);
            testWithGivenParameters(storage, new FlexibleParameters(new String[]{"file="+TestHelper.getResource(FILE_PATH_EN)}), EXPECTED_EN_FOG_INDEX);
        }
    }


    @Test
    public void testWithNonEnglishTexts() throws IOException {
        for (Storage storage : TestHelper.getDefaultTestStorages()) {
            System.out.println("Testing with "+storage.getClass().getSimpleName()+": "+storage.getLuceneManager().getClass().getSimpleName());

            try {
                testWithGivenParameters(storage, new FlexibleParameters(new String[]{"string=" + FOGIndexTest.TEXT_FR}), FOGIndexTest.EXPECTED_FOG_INDEX_FR);
                fail("Expected exception was not thrown.");
            } catch (RuntimeException ignored) {}

            try {
                testWithGivenParameters(storage, new FlexibleParameters(new String[]{"file="+TestHelper.getResource(FILE_PATH_FR)}), EXPECTED_FR_FOG_INDEX);
                fail("Expected exception was not thrown.");
            } catch (RuntimeException ignored) {}
        }
    }

    private void testWithGivenParameters(Storage storage, FlexibleParameters parameters, double expectedFOGIndex) throws IOException {
        CorpusCreator creator = new CorpusCreator(storage, parameters);
        creator.run();

        DocumentFOGIndex documentFOGIndex = new DocumentFOGIndex(storage, parameters);
        documentFOGIndex.run();

        List<FOGIndex> fogIndexes = documentFOGIndex.getFOGIndexes();

        for (FOGIndex fogIndex : fogIndexes) {
            assert fogIndex.getFOGIndex() == expectedFOGIndex;
        }
    }
}
