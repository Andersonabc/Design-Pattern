package org.ntutssl.termfrequency;


import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import jdk.jfr.Timestamp;

public class DataStorageManagerTest implements EventListener {
    private int callValidate = 0;
    private int callEof= 0;
    public void onEvent(EventType eventType, String event){
        if(eventType.equals(EventType.VALIDATE)){
            this.callValidate++;
        }else if(eventType.equals(EventType.EOF)){
            this.callEof++;
        }
    }
    @Test
    public void test_is_data_length(){
        EventManager em = new EventManager();
        DataStorageManager dsm = new DataStorageManager(em);
        em.subscribe(EventType.VALIDATE, this);
        em.publish(EventType.LOAD, "input/stop_words.txt");
        assertEquals(119, dsm.getWords().size());
    }
    @Test
    public void test_call_onEvent_validate(){
        EventManager em = new EventManager();
        DataStorageManager dsm = new DataStorageManager(em);
        em.subscribe(EventType.VALIDATE, this);
        em.publish(EventType.LOAD, "input/stop_words.txt");
        em.publish(EventType.RUN, "");
        assertEquals(119, this.callValidate);
    }
    @Test
    public void test_call_onEvent_eof(){
        EventManager em = new EventManager();
        DataStorageManager dsm = new DataStorageManager(em);
        em.subscribe(EventType.EOF, this);
        em.publish(EventType.LOAD, "input/stop_words.txt");
        em.publish(EventType.RUN, "");
        assertEquals(1, this.callEof);
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_fileNotFound(){
        EventManager _em = new EventManager();
        DataStorageManager dsm = new DataStorageManager(_em);
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("File not found.");
        _em.publish(EventType.LOAD, "input/error.txt");
    }
}
