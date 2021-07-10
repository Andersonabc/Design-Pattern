package org.ntutssl.termfrequency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class DataStorageManager{

    private List<String> data;

    public DataStorageManager(String filePath){ 
        String temp = "";
        this.data = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filePath))){
            sc.useDelimiter("[\\W_]+");
            while(sc.hasNext()){
                temp = sc.next();
                this.data.add(temp.toLowerCase()); 
            }
        }catch(IOException e){
            System.out.println("cannot find the file.");
        }
    }

    public List<String> getWords(){ 
        return this.data;
    }

}