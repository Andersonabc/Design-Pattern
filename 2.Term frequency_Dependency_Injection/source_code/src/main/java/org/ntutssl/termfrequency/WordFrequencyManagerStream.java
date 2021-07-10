package org.ntutssl.termfrequency;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.SortOrder;
import javax.swing.event.ListDataEvent;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class WordFrequencyManagerStream implements IWordFrequencyManager {
    private Map<String, Integer> words;
    public WordFrequencyManagerStream() { 
        this.words = new HashMap<>();
    }

    public void incrementCount(String word) { 
        if(this.words.containsKey(word)){
            this.words.put(word, this.words.get(word) + 1);
        }else{
            this.words.put(word, 1);
        }
    }

    public int getNumOfWords() {
        return words.size();
     }

    public List<String> getWordFrequency(SortOrder order) { 
        List<String> finalData = new ArrayList<>();
        String tmpKey = "";
        Integer tmpVal = null;
        Map<String, Integer> temp = null;

        if (order.equals(SortOrder.ASCENDING)){
            temp = words.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        }else if(order.equals(SortOrder.DESCENDING)){
            temp = words.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        }


        
        for (Map.Entry<String, Integer> entry : temp.entrySet())   
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
        SortOrder sOrder = SortOrder.ASCENDING;
        List<String> result = new ArrayList<>();
        if (this.getWordFrequency(sOrder).isEmpty()) throw new WordFrequencyException("Word not found.");
        if(range < 1 || range > this.getNumOfWords()) throw new WordFrequencyException("Out of range! The range should be from 1 to " + String.valueOf(this.getNumOfWords()) + ".");
        if(order.equals("asc")) sOrder = SortOrder.ASCENDING;
        else if (order.equals("desc")) sOrder = SortOrder.DESCENDING;
        else{
            throw new WordFrequencyException("The order should be \"asc\" or \"des\".");
        }
        
        result = getWordFrequency(sOrder);
        handler.handleOutput(outputPath, range, result);
    }
}