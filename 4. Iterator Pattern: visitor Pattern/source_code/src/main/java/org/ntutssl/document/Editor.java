package org.ntutssl.document;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;;
//Editor.java helps you to generate a text file

public class Editor {
  private List<Document> documents;
  public Editor(){
    this.documents = new ArrayList<Document>();
  }

  public void add(Document document) {
    if(document!=null){
      this.documents.add(document);
    }
  }

  public int size() {
    return this.documents.size();
  }

  public Iterator<Document> iterator() {
    return documents.iterator();
  }
}
