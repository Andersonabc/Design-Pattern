package org.ntutssl.termfrequency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.CertPath;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import jdk.jfr.events.FileWriteEvent;

import java.util.HashSet;


public class IOHandler {
    public IOHandler() { }

    public List<String> handleInputAsList(String filePath, String pattern) { 
        List<String> listInput = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(filePath))){
            sc.useDelimiter(pattern);
            while(sc.hasNext()){
                listInput.add(sc.next().toLowerCase()); 
            }
        }catch(IOException e){
            throw new WordFrequencyException("File not found.",e);
        }
        return listInput;
    }

    public Set<String> handleInputAsSet(String filePath, String pattern) {
        Set<String> setInput = new HashSet<>();
        try(Scanner sc = new Scanner(new File(filePath))){
            sc.useDelimiter(pattern);
            while(sc.hasNext()){
                setInput.add(sc.next().toLowerCase());
            }
        }catch(IOException e){
            throw new WordFrequencyException("File not found.",e);
        }
        return setInput;
     }

    public void handleOutput(String outputPath, int range, List<String> data){ 
        File file = new File(outputPath);

        try (FileWriter fwriter = new FileWriter(file)){
            for (int i = 0; i<range; i++){
                fwriter.write(data.get(i));
                System.out.println(data.get(i));
                fwriter.flush();
            }
        }catch(IndexOutOfBoundsException | IOException error){
            error.printStackTrace();
        }
    }
}