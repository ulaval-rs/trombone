package org.voyanttools.trombone.model;

import org.voyanttools.trombone.tool.util.TextParser;

import java.io.IOException;
import java.io.Serializable;

public abstract class ReadabilityIndex implements Serializable {

    protected int docIndex;
    protected String docId;
    protected String location;

    protected TextParser text;

    public ReadabilityIndex(IndexedDocument indexedDocument) throws IOException {
        text = new TextParser(indexedDocument.getDocumentString());

        docIndex = indexedDocument.getMetadata().getIndex();
        docId = indexedDocument.getId();
        location = indexedDocument.getMetadata().getLocation();
    }

    abstract protected double calculateIndex(TextParser text) throws IOException;
}
