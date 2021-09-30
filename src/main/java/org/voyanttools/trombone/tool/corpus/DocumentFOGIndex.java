package org.voyanttools.trombone.tool.corpus;

import org.voyanttools.trombone.lucene.CorpusMapper;
import org.voyanttools.trombone.model.Corpus;
import org.voyanttools.trombone.model.FOGIndex;
import org.voyanttools.trombone.model.IndexedDocument;
import org.voyanttools.trombone.storage.Storage;
import org.voyanttools.trombone.util.FlexibleParameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentFOGIndex extends AbstractCorpusTool {

    private List<FOGIndex> fogIndexes;

    public DocumentFOGIndex(Storage storage, FlexibleParameters parameters) {
        super(storage, parameters);

        fogIndexes = new ArrayList<>();
    }

    @Override
    public void run(CorpusMapper corpusMapper) throws IOException {
        Corpus corpus = corpusMapper.getCorpus();

        for (String documentId : corpus.getDocumentIds()) {
            IndexedDocument indexedDocument = corpus.getDocument(documentId);
            int documentIndex = corpus.getDocumentPosition(documentId);

            if (!indexedDocument.getMetadata().getLanguageCode().equalsIgnoreCase("En")) {
                throw new RuntimeException("Document must be in English in order to run the Gunning FOG readability test ");
            }

            String text = indexedDocument.getDocumentString();

            fogIndexes.add(new FOGIndex(documentIndex, documentId, text));
        }
    }

    public List<FOGIndex> getFOGIndexes() {
        return fogIndexes;
    }
}
