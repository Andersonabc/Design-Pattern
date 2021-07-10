package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Output implements EventListener{
  private int range;
  private String order;
  private Map<String, Integer> words;
  private List<String> finalData;
  public Output(EventManager eventManager, int range, String order) { 
    this.range = range;
    this.order = order;
    this.finalData = new ArrayList();
    eventManager.subscribe(EventType.OUTPUT, this);
    this.words = new HashMap<>();
  }

  private void handleOutput(String result){
    List<String> data = Arrays.asList(result.split("\n"));
    List<String> tmp;
    int testRange = data.size();
    //System.out.print(testRange);
    if(testRange < this.range || this.range < 0) throw new WordFrequencyException("Out of range! The range should be from 1 to " + testRange + ".");
    for(int i = 0; i < testRange ; i ++){
      tmp = Arrays.asList(data.get(i).split(": "));
      //System.out.print(tmp);
      this.words.put(tmp.get(0), Integer.valueOf(tmp.get(1)));
    }

    List<Map.Entry<String, Integer>> mylist = new LinkedList<Map.Entry<String, Integer>>(this.words.entrySet());  
    //sorting the list elements
    String tmpKey = "";
    Integer tmpVal = null;
    Collections.sort(mylist, new Comparator<Entry<String, Integer>>()   
    {  
        public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)   
        { 
        if (order.equals("asc")){
            return o1.getValue().compareTo(o2.getValue());
        }else{
            return o2.getValue().compareTo(o1.getValue()); 
        }
        }
      });
      for (Entry<String, Integer> entry : mylist)   
      {  
          tmpKey = entry.getKey();
          tmpVal = entry.getValue();
          this.finalData.add(tmpKey+": "+String.valueOf(tmpVal)+"\n");
      }
  }

  public void onEvent(EventType eventType, String event) { 
    if(eventType.equals(EventType.OUTPUT)){
      handleOutput(event);
      for(int count = 0; count <this.range ; count++){
        System.out.print(this.finalData.get(count));
      }
    }
  }
}
