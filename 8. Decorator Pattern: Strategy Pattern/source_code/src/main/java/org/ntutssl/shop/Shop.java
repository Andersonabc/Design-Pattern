package org.ntutssl.shop;

import java.util.Iterator;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class Shop implements EventListener {
  private EventManager eventManager;
  private List<Event<Goods>> goodsEvent; 
  public Shop() { 
    this.eventManager = EventManager.getInstance();
    this.goodsEvent = new ArrayList<Event<Goods>>();
    this.eventManager.subscribe(EventType.LIST_SHOP, this);
    this.eventManager.subscribe(EventType.PURCHASE, this);
    this.eventManager.subscribe(EventType.CHECK_STOCK, this);
    this.eventManager.subscribe(EventType.REPLENISH, this);
  }

  public void onEvent(Event event) { 
    switch (event.type()) {
      case LIST_SHOP:
        if(this.goodsEvent.size() == 0){
          System.out.print("This shop does not sell anything.\n");
        }else{
          this.listShop();
        }
        break;
      case PURCHASE:
        this.purchase(event);
        break;
      case CHECK_STOCK:
        this.checkStock(event);
        break;
      case REPLENISH:
        this.replenish(event);
        break;
      default:
        break;
      }
  }

  /**
   * private methods are not necessary, but you can takce them as reference.
   */
  /**
   * replenish stock
   * @param event Event of Goods to replenish
   */
  private void replenish(Event<Goods> event) {
    this.goodsEvent.add(event);
  }

  /**
   * check if the specific goods is in stock, if so, publish an 
   * event ADD_TO_CART
   * @param event Event of Goods to check
   */
  private void checkStock(Event<Goods> event) { 
    Iterator<Event<Goods>> itr = this.goodsEvent.iterator();
    while(itr.hasNext()){
      Event<Goods> tempEvent = itr.next();
      Goods temp = tempEvent.data();
      if(temp.id() == event.data().id()){
        if(tempEvent.count() >= event.count()){
          this.eventManager.publish(new GoodsEvent(EventType.ADD_TO_CART, temp, event.count()));
          return;
        }else{
          System.out.print(String.format("out of stock. goods ID: %d\n", temp.id()));
          return;
        }
      }
    }
    System.out.print("The store doesn't have this goods.\n");
  }

  /**
   * deduct stock after user complete purchase
   * @param event Event of Goods to be deducted
   */
  private void purchase(Event<Goods> event) {
    int index = 0;
    Iterator<Event<Goods>> itr = this.goodsEvent.iterator();
    while(itr.hasNext()){
      Event<Goods> tempEvent = itr.next();
      if(tempEvent.data().id() == event.data().id()){
        if(tempEvent.count() >= event.count()){
          this.goodsEvent.set(index, new GoodsEvent(EventType.PAY, tempEvent.data(), tempEvent.count()-event.count()));
          return;
        }else{
          System.out.print(String.format("out of stock. goods ID: %d\n", tempEvent.data().id()));
          return;
        }
      }else{
        index += 1;
      }
    }
    System.out.print("The store doesn't have this goods.\n");
   }

  /**
   * show stocks of this shop
   */
  private void listShop() { 
    System.out.print("================================================================================\n");
    System.out.print(String.format("%-4s%-22s%-40s%-8s%-6s\n", "ID","name", "description", "price", "count"));
    System.out.print("--------------------------------------------------------------------------------\n");

    Iterator<Event<Goods>> itr = this.goodsEvent.iterator();
    while(itr.hasNext()){
      Event<Goods> tempEvent = itr.next();
      System.out.printf("%-4s%-22s%-40s%-8s%-6s\n",String.valueOf(tempEvent.data().id()), tempEvent.data().name(), tempEvent.data().description(), tempEvent.data().price(), String.valueOf(tempEvent.count()));
    }
    System.out.print("================================================================================\n");
  }
}
