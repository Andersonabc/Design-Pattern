package org.ntutssl.termfrequency;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.SortOrder;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordFrequencyManager implements IWordFrequencyManager{
    private Map<String, Integer> words;
    public WordFrequencyManager(){ 
        this.words = new HashMap<>();
    }

    public void incrementCount(String word){ 
        if(this.words.containsKey(word)){
            this.words.put(word, this.words.get(word) + 1);
        }else{
            this.words.put(word, 1);
        }
    }

    public int getNumOfWords(){ 
        return words.size();
    }

    public List<String> getWordFrequency(SortOrder order) {
        List<String> finalData = new ArrayList();
        String tmpKey = "";
        Integer tmpVal = null;
        List<Map.Entry<String, Integer>> mylist = new LinkedList<Map.Entry<String, Integer>>(this.words.entrySet());  
        //sorting the list elements

        Collections.sort(mylist, new Comparator<Entry<String, Integer>>()   
        {  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)   
            { 
            if (order.equals(SortOrder.ASCENDING)){
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
            finalData.add(tmpKey+": "+String.valueOf(tmpVal)+"\n");
        }
        return finalData;
    }

    public void output(
        String outputPath,
        String order,
        int range,
        IOHandler handler
    ){ 
        SortOrder sOrder = SortOrder.UNSORTED;
        List<String> result = new ArrayList<>();

        try{
            if(this.getWordFrequency(sOrder).isEmpty()) throw new WordFrequencyException("Word not found.");
            if( range < 1 || range > this.getNumOfWords()) throw new WordFrequencyException("Out of range! The range should be from 1 to " + String.valueOf(this.getNumOfWords()) + ".");
            if(order.equals("desc")){
                sOrder = SortOrder.DESCENDING;
            }else if (order.equals("asc")){
                sOrder = SortOrder.ASCENDING;
            }else{
                throw new WordFrequencyException("The order should be \"asc\" or \"des\".");
            }
            result=this.getWordFrequency(sOrder);
        }catch(NullPointerException | IndexOutOfBoundsException e  ){
            throw new WordFrequencyException("Out of range! The range should be from 1 to " + String.valueOf(this.getNumOfWords()) + ".",e);        
        }
        handler.handleOutput(outputPath, range, result);
        
    }

    
}