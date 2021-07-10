package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;
import java.io.File;

public class DataStorageManager implements EventListener {
  private List<String> data;
  private EventManager em;
  public DataStorageManager(EventManager eventManager) { 
    this.em = eventManager;
    this.data = new ArrayList<>();
    this.em.subscribe(EventType.LOAD, this);
    this.em.subscribe(EventType.RUN, this);
  }

  private void handleInput(String dataPath){
    List<String> listInput = new ArrayList<>();
    String pattern = "[\\W_]+";
    try(Scanner sc = new Scanner(new File(dataPath))){
        sc.useDelimiter(pattern);
        while(sc.hasNext()){
            listInput.add(sc.next().toLowerCase()); 
        }
    }catch(IOException e){
        throw new WordFrequencyException("File not found.",e);
    }
    this.data = listInput;
  }



  public List<String> getWords() { 
    return this.data;
  }

  public void onEvent(EventType eventType, String event) { 
    if (eventType.equals(EventType.LOAD) ){
      handleInput(event);
    }else if(eventType.equals(EventType.RUN)) {
      for(int i = 0; i < this.data.size() ; i++){
        this.em.publish(EventType.VALIDATE, data.get(i));
      }
      this.em.publish(eventType.EOF, "");
    }
  }
}