package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class StopWordManager implements EventListener {
  private EventManager em;
  private List<String> stopWords;
  private String filePath = "input/stop_words.txt";

  public StopWordManager(EventManager eventManager) { 
    this.em = eventManager;
    this.em.subscribe(EventType.LOAD, this);
    this.em.subscribe(EventType.VALIDATE, this);
    this.stopWords = new ArrayList<>();
  }

  private void handleInput(){
    List<String> listInput = new ArrayList<>();
    String pattern = ",";
    try(Scanner sc = new Scanner(new File(this.filePath))){
        sc.useDelimiter(pattern);
        while(sc.hasNext()){
            listInput.add(sc.next().toLowerCase()); 
        }
    }catch(IOException e){
        throw new WordFrequencyException("File not found.",e);
    }
    //System.out.print(listInput.size());
    this.stopWords = listInput;
    for (char c = 'a'; c <= 'z'; c++) {
      if(!this.stopWords.contains("" + c)) this.stopWords.add("" + c);
    }
  }

  public List<String> getStopWords() { 
    return this.stopWords;
  }

  public void onEvent(EventType eventType, String event) {
    if(eventType.equals(EventType.LOAD)){
      handleInput();
    }else if(eventType.equals(EventType.VALIDATE)){
      if(!this.stopWords.contains(event)){
        this.em.publish(EventType.COUNT, event);
      }
   }
  }
}