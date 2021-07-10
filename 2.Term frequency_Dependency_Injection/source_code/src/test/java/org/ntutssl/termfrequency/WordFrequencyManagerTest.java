package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.rules.ExpectedException;

import jdk.jfr.Timestamp;

import org.junit.Rule;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SortOrder;

import org.junit.Test;

public class WordFrequencyManagerTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void word_frequency_manager_works(){
        WordFrequencyManager wfm = new WordFrequencyManager();
        wfm.incrementCount("dog");
        wfm.incrementCount("cat");
        wfm.incrementCount("dog");
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
        List<String> result_asc = wfm.getWordFrequency(SortOrder.ASCENDING);
        String key_asc = result_asc.get(0);
        assertEquals("c: 1\n", key_asc);
        List<String> result_desc = wfm.getWordFrequency(SortOrder.DESCENDING);
        String key_desc = result_desc.get(0);
        assertEquals("a: 3\n", key_desc);
    }
    @Test
    public void order_exception(){
        WordFrequencyManager wfm = new WordFrequencyManager();
        wfm.incrementCount("a");
        wfm.incrementCount("b");
        wfm.incrementCount("c");
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("The order should be \"asc\" or \"des\".");
        wfm.output("output/output.txt", "de", 2, new IOHandler());
    }
    @Test
    public void range_exception(){
        WordFrequencyManager wfm = new WordFrequencyManager();
        wfm.incrementCount("a");
        wfm.incrementCount("a");
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("Out of range! The range should be from 1 to " + String.valueOf(wfm.getNumOfWords()) + ".");
        wfm.output("output/output.txt", "desc", -1, new IOHandler());
        wfm.output("output/output.txt", "desc", 2, new IOHandler());
    }
    @Test
    public void wordNotFound_exception(){
        WordFrequencyManager wfm = new WordFrequencyManager();
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("Word not found.");
        wfm.output("output/output.txt", "desc", 2, new IOHandler());
    }
}
