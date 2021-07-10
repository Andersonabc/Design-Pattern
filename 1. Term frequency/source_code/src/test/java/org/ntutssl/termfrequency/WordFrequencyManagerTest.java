package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class WordFrequencyManagerTest {
    @Test
    public void word_frequency_manager_works(){
        WordFrequencyManager wfm = new WordFrequencyManager();
        wfm.incrementCount("dog");
        wfm.incrementCount("cat");
        wfm.incrementCount("dog");
        assertEquals(Integer.valueOf(2), wfm.getCount("dog"));
        assertEquals(Integer.valueOf(1), wfm.getCount("cat"));
        assertEquals(Integer.valueOf(0), wfm.getCount("mouse"));
        assertEquals(2, wfm.getNumOfWords());
    }
    @Test
    public void sorting_works(){
        WordFrequencyManager wfm = new WordFrequencyManager();
        wfm.incrementCount("a");
        wfm.incrementCount("a");
        wfm.incrementCount("a");
        wfm.incrementCount("b");
        wfm.incrementCount("b");
        wfm.incrementCount("c");
        Map<String, Integer> result_asc = wfm.getWordFrequencyAscending();
        String key_asc = (String)result_asc.keySet().toArray()[0];
        assertEquals("c", key_asc);
        Map<String, Integer> result_desc = wfm.getWordFrequencyDescending();
        String key_desc = (String)result_desc.keySet().toArray()[0];
        assertEquals("a", key_desc);
    }
}
