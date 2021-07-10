package org.ntutssl.termfrequency;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordFrequencyController{
    public static void main(String[] args){
        // args[0] is the file path of stop word.
        // args[1] is the file path of text to be counted.
        // args[2] is the range.
        // args[3] is the sorting order.
        String stopwordPath = args[0];
        String dataPath = args[1];
        int range = 0 ;
        try {
            range = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
            return;
        }
        String sortingOrder = args[3];
        DataStorageManager dsm = new DataStorageManager(dataPath);
        WordFrequencyManager wfm = new WordFrequencyManager();
        WordFrequencyManager wfm_set = new WordFrequencyManager();
        StopWordManager swm = new StopWordManager(stopwordPath);
        List<String> data = dsm.getWords();


        if(range >= data.size()) {
            System.out.println("Invalid range");
            return;
        }
        //List
        System.out.println("===================");
        long startTime = System.nanoTime();
        for (int i = 0 ; i < data.size() ; i++){
            if (!swm.isStopWordList(data.get(i))){
                wfm.incrementCount(data.get(i));
            }
        }
        long stopTime = System.nanoTime();
        System.out.println("List compute time(nano second): ");
        System.out.println(stopTime - startTime);
        //Set
        startTime = System.nanoTime();
        for (int i = 0 ; i < data.size() ; i++){
            if (!swm.isStopWordSet(data.get(i))){
                wfm_set.incrementCount(data.get(i));
            }
        }
        stopTime = System.nanoTime();
        System.out.println("Set compute time(nano second): ");
        System.out.println(stopTime - startTime);
        
        Map<String, Integer> result = null;
        if (sortingOrder.equals("desc")){
            result = wfm.getWordFrequencyDescending();
        }else if(sortingOrder.equals("asc"))
        {
            result = wfm.getWordFrequencyAscending();
        }else{
            System.out.println("Invalid sorting order");
            return;
        }
        System.out.println("===================");
        //print out the result
        int count = 1;
        for (Map.Entry<String, Integer> pair : result.entrySet()) {
            if(count <= range){
                System.out.println(String.format("%s:  %s", pair.getKey(), pair.getValue()));
                count++;  
            }
        }

        
     }
}
// mvn exec:java -Dexec.mainClass="org.ntutssl.termfrequency.WordFrequencyController" -Dexec.args="input/stop_words.txt input/pride-and-prejudice.txt 3 desc"