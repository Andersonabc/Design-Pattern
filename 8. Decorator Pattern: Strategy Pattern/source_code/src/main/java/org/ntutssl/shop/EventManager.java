package org.ntutssl.shop;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
  private static volatile EventManager instance = null;
  private Map<EventType, List<EventListener>> subscriptions;

  public void subscribe(EventType eventType, EventListener listener) {
    this.subscriptions.get(eventType).add(listener);
   }

  public <T> void publish(Event<T> event) {
    List<EventListener> users =  this.subscriptions.get(event.type());
    for (EventListener listener : users){
      listener.onEvent(event);
    }
  }

  public static EventManager getInstance() {
    if (instance == null) {
      synchronized(EventManager.class) {
        if (instance == null) {
          instance = new EventManager();
        }
      }
    }
    return instance;
  }

  private EventManager() { 
    this.subscriptions = new HashMap<>();
    for (EventType eventType : EventType.values()){
      this.subscriptions.put(eventType, new ArrayList<>());
    }
  }

  public void reset() {
    this.subscriptions.clear();
    this.instance = null;
  }
  // SINGLETON implementation below
  
}
