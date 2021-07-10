package org.ntutssl.termfrequency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class StopWordManager{

    private List<String> stopWords;
    private Set<String> set_stopWords;

    public StopWordManager(String filePath){
        this.stopWords = new ArrayList<>();
        this.set_stopWords = new HashSet<>();
        String temp = "";
        try(Scanner sc = new Scanner(new File(filePath))){
            sc.useDelimiter(",");
            while(sc.hasNext()){
                temp = sc.next();
                this.stopWords.add(temp.toLowerCase());
                this.set_stopWords.add(temp.toLowerCase());
            }
        }catch(IOException e){
            System.out.println("cannot find the file.");
        }
        for (char c = 'a'; c <= 'z'; c++) {
            this.stopWords.add("" + c);
            this.set_stopWords.add("" + c);
        }
     }

    public boolean isStopWordList(String word){ 
        return stopWords.contains(word);
    }

    public boolean isStopWordSet(String word){ 
        return set_stopWords.contains(word);
    }
}