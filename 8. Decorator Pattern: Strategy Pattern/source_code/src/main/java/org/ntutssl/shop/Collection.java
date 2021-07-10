package org.ntutssl.shop;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Collection implements Goods {
  
  /**
   * consturctor
   * @param id goods id which should not be negative
   * @param name
   * @param desc
   */
  private int id;
  private String name;
  private String desc;
  private List<Goods> products;

  public Collection(int id, String name, String desc) { 
    if(id < 0){
      throw new ShopException("Goods id should not be negative.");
    }
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.products = new ArrayList<Goods>();
  }

  public int id(){
    return this.id;
  }
  public double price(){
    double total = 0;
    Iterator<Goods> itr = this.products.iterator();
    while(itr.hasNext()){
      total = total+itr.next().price();
    }
    return total;
  }
  public String name(){
    return this.name;
  }
  public String description(){
    return this.desc;
  }
  @Override
  public void add(Goods goods) { 
    this.products.add(goods);
  }
  @Override
  public Iterator<Goods> iterator() { 
    return this.products.iterator();
  }
}