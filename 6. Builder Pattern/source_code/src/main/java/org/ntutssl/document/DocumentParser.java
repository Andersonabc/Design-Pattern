package org.ntutssl.document;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
class DocumentParser {
  private DocumentBuilder db;
  public DocumentParser() { 
    this.db = new DocumentBuilder();
  }
  
  /**
   * parse a JsonObject as a Document
   * @param jObj the json object to be parsed
   */
  public Document parse(JsonObject jObj) {
    Iterator<String> it = jObj.keySet().iterator();
    String key = this.getNext(it, jObj);
    if(key.equals("title")) {
      String text =this.getNext(it, jObj);
      int size =Integer.parseInt(this.getNext(it, jObj));
      this.db = this.db.buildTitle(text, size);
    }else if(key.equals("article")){
      JsonParser parser= new JsonParser();
      String text =this.getNext(it, jObj);
      int level =Integer.parseInt(this.getNext(it, jObj));
      this.db = this.db.startBuildArticle(text, level);
      JsonArray jArray = (JsonArray) parser.parse(jObj.get(it.next()).toString());
      for (JsonElement jElement : jArray){
        this.parse(jElement.getAsJsonObject());
      }
      this.db = this.db.endBuildArticle();
    }else if(key.equals("paragraph")){
      String text =this.getNext(it, jObj);
      this.db = this.db.buildParagraph(text);
    }
    return this.db.getResult();
  }

  private String getNext(Iterator<String> it,JsonObject jObj){
    return jObj.get(it.next()).getAsString();
  }
}