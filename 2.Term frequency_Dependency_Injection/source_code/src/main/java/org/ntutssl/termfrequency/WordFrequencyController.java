package org.ntutssl.termfrequency;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SortOrder;
import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch;

public class WordFrequencyController{

    private IOHandler ioh;
    private IStopWordManager iswm;
    private IDataStorageManager idsm;
    private IWordFrequencyManager iwfm;
    public WordFrequencyController(
        IOHandler handler, 
        IStopWordManager swm, 
        IDataStorageManager dsm, 
        IWordFrequencyManager wfm
    ){ 
        ioh = handler;
        iswm = swm;
        idsm = dsm;
        iwfm = wfm;
    }

    public void run(String order, Integer range, String outputPath) { 

        List<String> data = idsm.getWords();
        if(range > data.size() || range <= 0) {
            throw new WordFrequencyException("Out of range! The range should be from 1 to "+String.valueOf(data.size())+".");
        }

        
        System.out.println("===================");
        Stopwatch watch = Stopwatch.createStarted();
        for (int i = 0 ; i < data.size() ; i++){
            if (!iswm.isStopWordList(data.get(i))){
                iwfm.incrementCount(data.get(i));
            }
        }
        System.out.println("List compute time(milli second): ");
        System.out.println(watch.elapsed(TimeUnit.MILLISECONDS));

        iwfm.output(outputPath, order, range, ioh);
        System.out.println("===================");

    }

}
