package org.ntutssl.termfrequency;

public class WordFrequencyController implements EventListener {
  private EventManager em;
  public WordFrequencyController(EventManager eventManager) { 
    this.em = eventManager;
    this.em.subscribe(EventType.START, this);
  }
  
  public void onEvent(EventType eventType, String event) { 
    if(eventType.equals(EventType.START)){
      this.em.publish(EventType.LOAD, event);
      this.em.publish(eventType.RUN, "");
    }
  }
}