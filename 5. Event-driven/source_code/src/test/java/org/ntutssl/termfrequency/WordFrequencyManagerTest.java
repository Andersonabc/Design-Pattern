package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.io.FileNotFoundException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

public class WordFrequencyManagerTest implements EventListener{
    private int callCount = 0;
    private int callOutput = 0;
    private String data = "";
    public void onEvent(EventType eventType, String event) {
        if(eventType == EventType.COUNT){
            this.callCount++;
        }else if(eventType == EventType.OUTPUT){
            this.callOutput++;
            this.data = event;
        }
    }
    @Test
    public void test_increase_word(){
        EventManager em = new EventManager();
        WordFrequencyManager wfm = new WordFrequencyManager(em);
        em.subscribe(EventType.COUNT, this);
        em.publish(EventType.COUNT, "apple");
        em.publish(EventType.COUNT, "passion fruit");
        em.publish(EventType.COUNT, "guava");
        assertEquals(3,this.callCount);
        assertEquals(3,wfm.getNumOfWords());
    }
    @Test
    public void test_publish_output_data(){
        EventManager em = new EventManager();
        WordFrequencyManager wfm = new WordFrequencyManager(em);
        em.subscribe(EventType.OUTPUT, this);
        em.publish(EventType.COUNT, "apple");
        em.publish(EventType.COUNT, "passion fruit");
        em.publish(EventType.EOF, "");
        assertEquals("apple: 1\npassion fruit: 1\n",this.data);
    }
    @Test
    public void test_publish_output(){
        EventManager em = new EventManager();
        WordFrequencyManager wfm = new WordFrequencyManager(em);
        em.subscribe(EventType.OUTPUT, this);
        em.publish(EventType.EOF, "");
        assertEquals(1,this.callOutput);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_invalid_range_over(){
        EventManager em = new EventManager();
        WordFrequencyManager wfm = new WordFrequencyManager(em);
        Output output = new Output(em, 100, "des");
        em.publish(EventType.COUNT, "apple");
        em.publish(EventType.COUNT, "passion fruit");
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("Out of range! The range should be from 1 to 2.");
        em.publish(EventType.EOF, "");
    }   
    @Test
    public void test_invalid_range_negative(){
        EventManager em = new EventManager();
        WordFrequencyManager wfm = new WordFrequencyManager(em);
        Output output = new Output(em, -1, "des");
        em.publish(EventType.COUNT, "apple");
        em.publish(EventType.COUNT, "passion fruit");
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("Out of range! The range should be from 1 to 2.");
        em.publish(EventType.EOF, "");
    }
 }
