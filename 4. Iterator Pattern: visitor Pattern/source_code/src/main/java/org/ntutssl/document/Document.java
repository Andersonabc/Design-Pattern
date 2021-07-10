package org.ntutssl.document;

import java.util.Iterator;
import javax.print.Doc;

public interface Document { 

    public default void add(Document document){
        throw new DocumentException("Invalid action: add");
    }
    public default int getLevel(){
        throw new DocumentException("Invalid action: getLevel");
    }
    public default Document getContent(int index){
        throw new DocumentException("Invalid action: getContent");
    }

    public default Iterator<Document> iterator() {
        return new NullIterator(); 
    }
    
    public default int getSize() {
        throw new DocumentException("Invalid action: getSize");
    }

    public String getText();
    public void accept(Visitor visitor);
    public String toString();
 }
