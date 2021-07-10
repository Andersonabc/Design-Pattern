package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Map.Entry;

public class WordFrequencyManager implements EventListener {
  private Map<String, Integer> words;
  private EventManager em;
  public WordFrequencyManager(EventManager eventManager) {
    this.words = new HashMap<>();
    this.em = eventManager;
    this.em.subscribe(EventType.COUNT, this);
    this.em.subscribe(EventType.EOF, this);
   }
  
  public void increaseCount(String word){
    if (!words.containsKey(word)){
      words.put(word, 1);
    }else{
      words.put(word, words.get(word)+1);
    }
  }

  public int getNumOfWords() {
    return words.size();
   }

  public void onEvent(EventType eventType, String event) {
    if(eventType.equals(EventType.COUNT)){
        this.increaseCount(event);
    }else if(eventType.equals(EventType.EOF)){
        String output = "";
        String temp;
        String tmpKey = "";
        Integer tmpVal = null;
        List<Map.Entry<String, Integer>> mylist = new LinkedList<Map.Entry<String, Integer>>(this.words.entrySet());
        for (Entry<String, Integer> entry : mylist)   
        {  
            tmpKey = entry.getKey();
            tmpVal = entry.getValue();
            temp = tmpKey+": "+String.valueOf(tmpVal)+"\n";
            output += temp;
        }
        this.em.publish(eventType.OUTPUT, output);
    }
   }
}