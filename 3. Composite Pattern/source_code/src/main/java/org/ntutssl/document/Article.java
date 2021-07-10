package org.ntutssl.document;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class Article implements Document {  
  private int level;
  private String topic;
  private List<Document> documents;
  public Article(String topic, int level) { 
    this.topic = topic;
    this.level = level;
    this.documents = new ArrayList<Document>();
   }

  public String getText() {
    return this.topic;
  }

  @Override
  public Document getContent(int index) { 
    return this.documents.get(index);
   }

  @Override
  public int getLevel() { 
    return this.level;
   }
  
  @Override
  public void add(Document document) {
    System.out.println(document.getText());
    try {
      if ( document.getLevel() < this.level ) throw new DocumentException("Invalid action: Adding lower level article"); //invalid level 
      else {
        this.documents.add(document);   //normal situation
      }
    } catch (DocumentException e) {
      if (e.getMessage().equals("Invalid action: Adding lower level article")){
        throw new DocumentException("Invalid action: Adding lower level article");
      }else{
        this.documents.add(document); //do not have level (not article)
      }
    } 
  

   }
}
