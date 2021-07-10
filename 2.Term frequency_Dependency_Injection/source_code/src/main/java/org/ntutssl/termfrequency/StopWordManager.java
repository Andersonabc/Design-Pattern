package org.ntutssl.termfrequency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import com.google.common.base.Stopwatch;

public class StopWordManager implements IStopWordManager{

    private List<String> stopWords;
    private Set<String> set_stopWords;

    public StopWordManager(String filePath, IOHandler ioHandler){
        this.stopWords = ioHandler.handleInputAsList(filePath, ",");
        this.set_stopWords = ioHandler.handleInputAsSet(filePath, ",");
        for (char c = 'a'; c <= 'z'; c++) {
            set_stopWords.add("" + c);
            stopWords.add("" + c);
        }
     }

    public boolean isStopWordList(String word){ 
        return this.stopWords.contains(word);
    }

    public boolean isStopWordSet(String word){ 
        return this.set_stopWords.contains(word);
    }
}