package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.io.FileNotFoundException;

import org.junit.Test;

public class StopWordManagerTest implements EventListener{
    private int callCount = 0;
    public void onEvent(EventType eventType, String event) {
        if(eventType == EventType.COUNT)
            this.callCount++;
    }
    @Test
    public void test_stopwords_data(){
        EventManager em = new EventManager();
        StopWordManager swm = new StopWordManager(em);
        em.publish(EventType.LOAD, "");
        assertEquals(swm.getStopWords().get(0),"a");
        assertEquals(swm.getStopWords().get(118), "your");
        assertEquals(swm.getStopWords().get(1),"able");
    }

    @Test
    public void test_publish_word_to_count(){
        this.callCount = 0;
        EventManager em = new EventManager();
        StopWordManager swm = new StopWordManager(em);
        em.publish(EventType.LOAD, "");
        em.subscribe(EventType.COUNT, this);
        em.publish(EventType.VALIDATE, "a");
        em.publish(EventType.VALIDATE, "able");
        em.publish(EventType.VALIDATE, "apple");
        assertEquals(1,this.callCount);
    }
    @Test
    public void test_publish_noword_to_count(){
        this.callCount = 0;
        EventManager em = new EventManager();
        StopWordManager swm = new StopWordManager(em);
        em.publish(EventType.LOAD, "");
        em.subscribe(EventType.COUNT, this);
        em.publish(EventType.VALIDATE, "a");
        em.publish(EventType.VALIDATE, "your");
        em.publish(EventType.VALIDATE, "about");
        assertEquals(0,this.callCount);
    }

    
 }
