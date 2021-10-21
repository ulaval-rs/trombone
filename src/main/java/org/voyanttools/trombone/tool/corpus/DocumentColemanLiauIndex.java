package org.voyanttools.trombone.tool.corpus;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.voyanttools.trombone.lucene.CorpusMapper;
import org.voyanttools.trombone.model.ColemanLiauIndex;
import org.voyanttools.trombone.model.Corpus;
import org.voyanttools.trombone.model.IndexedDocument;
import org.voyanttools.trombone.storage.Storage;
import org.voyanttools.trombone.util.FlexibleParameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@XStreamAlias("documentColemanLiauIndex")
public class DocumentColemanLiauIndex extends AbstractCorpusTool {

    private List<ColemanLiauIndex> colemanLiauIndexes;

    public DocumentColemanLiauIndex(Storage storage, FlexibleParameters parameters) {
        super(storage, parameters);

        colemanLiauIndexes = new ArrayList<>();
    }

    @Override
    public void run(CorpusMapper corpusMapper) throws IOException {
        Corpus corpus = corpusMapper.getCorpus();

        for (String documentId : corpus.getDocumentIds()) {
            IndexedDocument indexedDocument = corpus.getDocument(documentId);

            colemanLiauIndexes.add(new ColemanLiauIndex(indexedDocument));
        }
    }

    public List<ColemanLiauIndex> getColemanLiauIndexes() {
        return colemanLiauIndexes;
    }
}
