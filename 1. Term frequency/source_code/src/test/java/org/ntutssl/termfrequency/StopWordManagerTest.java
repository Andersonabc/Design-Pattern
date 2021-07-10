package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

public class StopWordManagerTest {
    @Test
    public void test_is_stop_word(){
        StopWordManager swm = new StopWordManager("input/stop_words.txt");
        assertTrue(swm.isStopWordList("the"));
        assertTrue(swm.isStopWordSet("the"));
    }
    @Test
    public void test_is_stop_word_first_and_last(){
        StopWordManager swm = new StopWordManager("input/stop_words.txt");
        assertTrue(swm.isStopWordList("a"));
        assertTrue(swm.isStopWordList("either"));
        assertTrue(swm.isStopWordList("your"));
        assertTrue(swm.isStopWordSet("a"));
        assertTrue(swm.isStopWordSet("either"));
        assertTrue(swm.isStopWordSet("your"));   
    }
    @Test
    public void test_is_compute_time(){
        long startTime = System.nanoTime();
        StopWordManager swm = new StopWordManager("input/stop_words.txt");
        assertTrue(swm.isStopWordList("a"));
        assertTrue(swm.isStopWordList("either"));
        assertTrue(swm.isStopWordList("your"));   
        long stopTime = System.nanoTime();
        long list_time = (stopTime - startTime);
        startTime = System.nanoTime();
        assertTrue(swm.isStopWordSet("a"));
        assertTrue(swm.isStopWordSet("either"));
        assertTrue(swm.isStopWordSet("your"));
        stopTime = System.nanoTime();
        long set_time = (stopTime - startTime);  
        assertTrue(list_time>set_time);    
    }
}
