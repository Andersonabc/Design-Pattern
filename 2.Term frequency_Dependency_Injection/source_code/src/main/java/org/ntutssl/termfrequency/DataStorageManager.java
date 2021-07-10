package org.ntutssl.termfrequency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class DataStorageManager implements IDataStorageManager{

    private List<String> data;

    public DataStorageManager(String filePath, IOHandler ioHandler){ 
        this.data = new ArrayList<>();
        this.data = ioHandler.handleInputAsList(filePath, "[\\W_]+");
    }

    public List<String> getWords(){ 
        return this.data;
    }

}