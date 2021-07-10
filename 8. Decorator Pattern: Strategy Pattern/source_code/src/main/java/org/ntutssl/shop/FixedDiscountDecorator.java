package org.ntutssl.shop;

import java.util.Dictionary;

public class FixedDiscountDecorator extends Decorator {

  /**
   * counstructor
   * @param goods goods to be decorated
   * @param discount fixed discount, which should be lower than the price of goods
   */
  private double discount;
  public FixedDiscountDecorator(Goods goods, double discount) {
    super(goods);
    this.discount = discount;
    if(discount > goods.price()){
      throw new ShopException("Fixed discount should be lower than the price of goods.");
    }
   }

  public int id() { 
    return this.goods.id();
  }

  public double price() { 
    return this.goods.price() - this.discount;
  }

  public String name() {
    return this.goods.name();
   }

  public String description() { 
    return this.goods.description();
  }
}
