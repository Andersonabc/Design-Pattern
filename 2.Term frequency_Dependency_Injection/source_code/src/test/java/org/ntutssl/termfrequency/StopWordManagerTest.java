package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;

import org.junit.Test;
import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch;

public class StopWordManagerTest {
    @Test
    public void test_is_stop_word(){
        IOHandler ioHandler = new IOHandler(); 
        StopWordManager swm = new StopWordManager("input/stop_words.txt", ioHandler);
        assertTrue(swm.isStopWordList("the"));
        assertTrue(swm.isStopWordSet("the"));
        assertTrue(swm.isStopWordList("z"));
        assertTrue(swm.isStopWordSet("z"));
        assertTrue(swm.isStopWordList("f"));
        assertTrue(swm.isStopWordSet("f"));
    }
    @Test
    public void test_is_stop_word_first_and_last(){
        IOHandler ioHandler = new IOHandler();
        StopWordManager swm = new StopWordManager("input/stop_words.txt",ioHandler);
        assertTrue(swm.isStopWordList("a"));
        assertTrue(swm.isStopWordList("either"));
        assertTrue(swm.isStopWordList("your"));
        assertTrue(swm.isStopWordSet("a"));
        assertTrue(swm.isStopWordSet("either"));
        assertTrue(swm.isStopWordSet("your"));   
    }
    @Test
    public void test_not_stop_word_first_and_last(){
        IOHandler ioHandler = new IOHandler();
        StopWordManager swm = new StopWordManager("input/stop_words.txt",ioHandler);
        assertFalse(swm.isStopWordList("Anderson"));
        assertFalse(swm.isStopWordList("Cheng"));
        assertFalse(swm.isStopWordSet("Anderson"));
        assertFalse(swm.isStopWordSet("Cheng"));
    }
    @Test
    public void test_compute_time(){
        IOHandler ioHandler = new IOHandler();
        Stopwatch watch = Stopwatch.createStarted();
        StopWordManager swm = new StopWordManager("input/stop_words.txt",ioHandler);
        assertTrue(swm.isStopWordList("a"));
        assertTrue(swm.isStopWordList("either"));
        assertTrue(swm.isStopWordList("your"));   
        long list_time = watch.elapsed(TimeUnit.MILLISECONDS);
        watch.reset();
        watch.start();
        assertTrue(swm.isStopWordSet("a"));
        assertTrue(swm.isStopWordSet("either"));
        assertTrue(swm.isStopWordSet("your"));
        long set_time = watch.elapsed(TimeUnit.MILLISECONDS);
        assertTrue(list_time>set_time);    
    }
}
