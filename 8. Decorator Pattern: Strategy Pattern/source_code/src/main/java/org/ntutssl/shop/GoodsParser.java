package org.ntutssl.shop;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class GoodsParser implements EventListener {
  private GoodsBuilder db;
  private int count;
  private EventManager eventManager;
  public GoodsParser() {
    this.db = new GoodsBuilder();
    this.eventManager = EventManager.getInstance();
    this.eventManager.subscribe(EventType.IMPORT_REPLENISH_LIST, this);
    this.eventManager.subscribe(EventType.IMPORT_SHOPPING_LIST, this);
   }

  public void onEvent(Event event) {
    switch (event.type()) {
      case IMPORT_SHOPPING_LIST:
        this.importShoppingCartList(event);
        break;
      case IMPORT_REPLENISH_LIST:
        this.importReplenishmentList(event);
        break;
      default:
        break;
      }
  }

  /**
   * private methods are not necessary, but you can takce them as reference.
   */
  private void importShoppingCartList(Event<String> event) { 
    // System.out.println(event.data());
    String filePath = event.data();
    try{
      FileInputStream fis = new FileInputStream(filePath);
      JsonReader jReader = new JsonReader(new InputStreamReader(fis,"UTF-8"));
      JsonArray jArray = JsonParser.parseReader(jReader).getAsJsonArray();
      for(JsonElement jElement : jArray){
        Goods temp = this.parse(jElement.getAsJsonObject());
        this.eventManager.publish(new GoodsEvent(EventType.CHECK_STOCK, temp, this.count));
      }
    }catch(Exception e){
        throw new ShopException("import error");
    }
  }

  private void importReplenishmentList(Event<String> event) { 
    String filePath = event.data();
    try{
      FileInputStream fis = new FileInputStream(filePath);
      JsonReader jReader = new JsonReader(new InputStreamReader(fis,"UTF-8"));
      JsonArray jArray = JsonParser.parseReader(jReader).getAsJsonArray();
      for(JsonElement jElement : jArray){
        Goods temp = this.parse(jElement.getAsJsonObject());
        this.eventManager.publish(new GoodsEvent(EventType.REPLENISH, temp, this.count));
      }
    }catch(Exception e){
        throw new ShopException("import error");
    }
  }

  private Goods parse(JsonObject jObj) { 
    this.db = new GoodsBuilder();
    Iterator<String> it = jObj.keySet().iterator();
    String key = this.getNext(it, jObj);
    if(key.equals("merchandise")) {
      int id =Integer.parseInt(this.getNext(it, jObj));
      String name =this.getNext(it, jObj);
      String desc =this.getNext(it, jObj);
      double price = Double.parseDouble(this.getNext(it, jObj));
      this.count = Integer.parseInt(this.getNext(it, jObj));
      this.db.buildMerchandise(id, name, desc, price);
    }else if(key.equals("collection")){
      JsonParser parser= new JsonParser();
      int id =Integer.parseInt(this.getNext(it, jObj));
      String name =this.getNext(it, jObj);
      String desc =this.getNext(it, jObj);
      this.count =Integer.parseInt(this.getNext(it, jObj));
      this.db.startBuildCollection(id, name, desc);
      JsonArray jArray = (JsonArray) parser.parse(jObj.get(it.next()).toString());
      for (JsonElement jElement : jArray){
        this.secondParse(jElement.getAsJsonObject());
      }
      this.db.endBuildCollection();
    }
    return this.db.getResult();
  }

  private void secondParse(JsonObject jObj){
    Iterator<String> it = jObj.keySet().iterator();
    String key = this.getNext(it, jObj);
    if(key.equals("merchandise")) {
      int id =Integer.parseInt(this.getNext(it, jObj));
      String name =this.getNext(it, jObj);
      String desc =this.getNext(it, jObj);
      double price = Double.parseDouble(this.getNext(it, jObj));
      this.db.buildMerchandise(id, name, desc, price);
    }else if(key.equals("collection")){
      JsonParser parser= new JsonParser();
      int id =Integer.parseInt(this.getNext(it, jObj));
      String name =this.getNext(it, jObj);
      String desc =this.getNext(it, jObj);
      this.db.startBuildCollection(id, name, desc);
      JsonArray jArray = (JsonArray) parser.parse(jObj.get(it.next()).toString());
      for (JsonElement jElement : jArray){
        this.secondParse(jElement.getAsJsonObject());
      }
      this.db.endBuildCollection();
    }
  } 

  private String getNext(Iterator<String> it,JsonObject jObj){
    return jObj.get(it.next()).getAsString();
  }
}
