package org.ntutssl.shop;

public class RateDiscountDecorator extends Decorator {

  /**
   * constructor
   * @param goods goods to be decorated
   * @param rate discount rate, which should be [0, 1)
   */
  private double rate;
  public RateDiscountDecorator(Goods goods, double rate) { 
    super(goods);
    this.rate = rate;
    if(rate < 0 || rate >= 1){
      throw new ShopException("Discount rate should be [0, 1).");
    }
  }

  public int id() {
    return this.goods.id();
   }

  public double price() { 
    return this.goods.price()*this.rate;
  }

  public String name() {
    return this.goods.name();
   }

  public String description() { 
    return this.goods.description();
  }
}
