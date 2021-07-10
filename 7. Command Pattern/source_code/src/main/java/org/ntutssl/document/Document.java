package org.ntutssl.document;

import java.util.Iterator;
import javax.print.Doc;

public interface Document { 
    public default Iterator<Document> iterator() {
        return new NullIterator(); 
    }
    
    public default int getSize() {
        throw new DocumentException("Invalid action: getSize");
    }

    public default int getLevel(){
        throw new DocumentException("Invalid action: getLevel");
    }

    public default void add(Document document){
        throw new DocumentException("Invalid action: add");
    }

    public default void remove(Document document) {
        throw new DocumentException("Invalid action: remove");
    }

    public String getText();
    public String toString();

 }
