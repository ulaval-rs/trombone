package org.voyanttools.trombone.tool.corpus;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.voyanttools.trombone.lucene.CorpusMapper;
import org.voyanttools.trombone.model.Corpus;
import org.voyanttools.trombone.model.IndexedDocument;
import org.voyanttools.trombone.model.SMOGIndex;
import org.voyanttools.trombone.storage.Storage;
import org.voyanttools.trombone.util.FlexibleParameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@XStreamAlias("documentSMOGIndex")
public class DocumentSMOGIndex extends AbstractCorpusTool {

    private List<SMOGIndex> smogIndexes;

    public DocumentSMOGIndex(Storage storage, FlexibleParameters parameters) {
        super(storage, parameters);

        smogIndexes = new ArrayList<>();
    }

    @Override
    public void run(CorpusMapper corpusMapper) throws IOException {
        Corpus corpus = corpusMapper.getCorpus();

        for (String documentId : corpus.getDocumentIds()) {
            IndexedDocument indexedDocument = corpus.getDocument(documentId);

            smogIndexes.add(new SMOGIndex(indexedDocument));
        }
    }

    public List<SMOGIndex> getSMOGIndexes() {
        return smogIndexes;
    }
}
