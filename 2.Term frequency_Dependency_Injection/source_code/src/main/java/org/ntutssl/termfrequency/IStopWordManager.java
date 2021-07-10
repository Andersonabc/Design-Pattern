package org.ntutssl.termfrequency;

import java.util.List;


public interface IStopWordManager { 
    public boolean isStopWordList(String word);
    public boolean isStopWordSet(String word);
}