package org.ntutssl.shop;

public class Merchandise implements Goods {

  /**
   * constructor
   * @param id goods id which should not be negative
   * @param name
   * @param desc
   * @param price goods price which should not be negative
   */
  private int id;
  private String name;
  private String desc;
  private double price;
  public Merchandise(int id, String name, String desc, double price) {
    if(id < 0){
      throw new ShopException("Goods id should not be negative.");
    }
    if(price < 0){
      throw new ShopException("Goods price should not be negative.");
    }
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.price = price;
  }
  public int id(){
    return this.id;
  }
  public double price(){
    return this.price;
  }
  public String name(){
    return this.name;
  }
  public String description(){
    return this.desc;
  }
}