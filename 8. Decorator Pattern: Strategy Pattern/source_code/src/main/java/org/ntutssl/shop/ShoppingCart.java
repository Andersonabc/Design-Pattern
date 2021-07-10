package org.ntutssl.shop;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements EventListener {
  private List<Event<Goods>> goodsEvent;
  private EventManager eventManager;
  public ShoppingCart() {
    this.goodsEvent = new ArrayList<Event<Goods>>();
    this.eventManager = EventManager.getInstance();
    this.eventManager.subscribe(EventType.ADD_TO_CART, this);
    this.eventManager.subscribe(EventType.PAY, this);
    this.eventManager.subscribe(EventType.LIST_CART, this);
    this.eventManager.subscribe(EventType.PRINT_RECEIPT, this);
   }

  public void onEvent(Event event) { 
    switch (event.type()) {
      case ADD_TO_CART:
        this.add(event);
        break;
      case PAY:
        this.pay();
        break;
      case LIST_CART:
        if(this.goodsEvent.size() == 0){
          System.out.print("Your shopping cart is empty\n");
        }else{
          this.listCart();
        }
        break;
      case PRINT_RECEIPT:
        this.printReceipt(event);
        break;
      default:
        break;
      }
  }

  /**
   * private methods are not necessary, but you can takce them as reference.
   */
  /**
   * add goods to shopping cart
   * @param goodsEvent the data of this event is the goods to be added
   */
  private void add(Event<Goods> event) { 
    int index = 0;
    Iterator<Event<Goods>> itr = this.goodsEvent.iterator();
    while(itr.hasNext()){
      Event<Goods> tempEvent = itr.next();
      if(tempEvent.data().id() == event.data().id()){
        this.goodsEvent.set(index, new GoodsEvent(EventType.PAY, tempEvent.data(), tempEvent.count()+1));
        return;
      }else{
        index += 1;
      }
    }
    this.goodsEvent.add(event);
  }

  /**
   * pay for all items in the shopping cart
   */
  private void pay() { 
    this.eventManager.publish(new StringEvent(EventType.CALCULATE, getTotal()));
  }

  private String getTotal(){
    double total = 0.0d;
    Iterator<Event<Goods>> itr = this.goodsEvent.iterator();
    while(itr.hasNext()){
      Event<Goods> tempEvent = itr.next();
      total += (tempEvent.data().price() * tempEvent.count());
    }
    return String.format("%.4f", total);
  }

   /**
   * print receipt and publish PURCHASE
   */
  private void printReceipt(Event<String> event) {
    System.out.print("================================================================================\n");
    System.out.print("Receipt:\n");
    System.out.print(String.format("%-40s%-10s%-10s\n", "name","price", "count"));
    Iterator<Event<Goods>> itr = this.goodsEvent.iterator();
    while(itr.hasNext()){
      Event<Goods> tempEvent = itr.next();
      this.eventManager.publish(new GoodsEvent(EventType.PURCHASE, tempEvent.data(), tempEvent.count()));
      System.out.printf("%-40s%-10s%-10s\n",tempEvent.data().name(), String.format("$%s", tempEvent.data().price()), String.valueOf(tempEvent.count()));
    }
    System.out.print("--------------------------------------------------------------------------------\n");
    System.out.print("Total Price: " + event.data() +"\n");
    System.out.print("================================================================================\n");
   }
  /**
   * list all items in the shopping cart
   */
  private void listCart() { 
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
