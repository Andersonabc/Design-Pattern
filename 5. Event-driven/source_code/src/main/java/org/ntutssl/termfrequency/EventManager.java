package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

  private Map<EventType, List<EventListener>> listeners;

  public EventManager() { 
    this.listeners = new HashMap<>();
    for (EventType eventType : EventType.values())
            this.listeners.put(eventType, new ArrayList<>());
  }

  public void subscribe(EventType eventType, EventListener listener) { 
    this.listeners.get(eventType).add(listener);
  }

  public void publish(EventType eventType, String event) { 
    List<EventListener> users =  this.listeners.get(eventType);
    for (EventListener listener : users)
        listener.onEvent(eventType, event);
  }
}