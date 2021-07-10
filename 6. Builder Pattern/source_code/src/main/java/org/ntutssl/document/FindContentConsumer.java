package org.ntutssl.document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.lang.NullPointerException;
import java.util.Iterator;

public class FindContentConsumer implements Consumer<Document> {
  
  /**
   * @param result you should add the document which contains `target` into here.
   */
  private List<Document> result;
  private String target;
  public FindContentConsumer(List<Document> result, String target) {
    this.result = result;
    this.target = target;
   }

  public void accept(Document document) {
    try{
      Iterator<Document> itr= document.iterator();
      String content = document.getText();
      if(content.toLowerCase().contains(this.target.toLowerCase())&& (this.target.length()>0 || document.getText().length() == 0)){
        this.result.add(document);
      }
      while(itr.hasNext()){
        this.accept(itr.next());
      }
    }catch(NullPointerException e){
      String content = document.getText();
      if(content.toLowerCase().contains(this.target.toLowerCase())&& (this.target.length()>0 || document.getText().length() == 0)){
        this.result.add(document);
      }
    }

   }
}