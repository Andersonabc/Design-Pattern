package org.ntutssl.document;

import javax.print.Doc;

public interface Document { 
    public String getText();

    public default void add(Document document){
        throw new DocumentException("Invalid action: add");
    }
    public default int getLevel(){
        throw new DocumentException("Invalid action: getLevel");
    }
    public default Document getContent(int index){
        throw new DocumentException("Invalid action: getContent");
    }
 }
