package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;
import java.io.FileNotFoundException;

import org.junit.Test;

public class EventManagerTest implements EventListener{ 
    private boolean isLoad = false;
    private boolean isRun = false;
    private boolean isValidate = false;
    private boolean isEof = false;
    private boolean isCount = false;
    private boolean isStart = false;
    private boolean isOutput = false;
    public void onEvent(EventType eventType, String event){
        this.isLoad = eventType == EventType.LOAD;
        this.isRun = eventType == EventType.RUN;
        this.isValidate = eventType == EventType.VALIDATE;
        this.isEof = eventType == EventType.EOF;
        this.isCount = eventType == EventType.COUNT;
        this.isStart = eventType == EventType.START;
        this.isOutput = eventType == EventType.OUTPUT;
    }
    @Test
    public void test_load_onevent(){
        EventManager em = new EventManager();
        em.subscribe(EventType.LOAD, this);
        em.publish(EventType.LOAD, "");
        assertTrue(this.isLoad);
    }
    @Test
    public void test_run_onevent(){
        EventManager em = new EventManager();
        em.subscribe(EventType.RUN, this);
        em.publish(EventType.RUN, "");
        assertTrue(this.isRun);
    }
    @Test
    public void test_validate_onevent(){
        EventManager em = new EventManager();
        em.subscribe(EventType.VALIDATE, this);
        em.publish(EventType.VALIDATE, "");
        assertTrue(this.isValidate);
    }
    @Test
    public void test_eof_onevent(){
        EventManager em = new EventManager();
        em.subscribe(EventType.EOF, this);
        em.publish(EventType.EOF, "");
        assertTrue(this.isEof);
    }
    @Test
    public void test_count_onevent(){
        EventManager em = new EventManager();
        em.subscribe(EventType.COUNT, this);
        em.publish(EventType.COUNT, "Test");
        assertTrue(this.isCount);
    }
    @Test
    public void test_start_onevent(){
        EventManager em = new EventManager();
        em.subscribe(EventType.START, this);
        em.publish(EventType.START, "Test");
        assertTrue(this.isStart);
    }
    @Test
    public void test_output_onevent(){
        EventManager em = new EventManager();
        em.subscribe(EventType.OUTPUT, this);
        em.publish(EventType.OUTPUT, "Test");
        assertTrue(this.isOutput);
    }

}