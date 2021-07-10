package org.ntutssl.shop;

import java.util.Stack;

public class GoodsBuilder {
  private Stack<Goods> stack;
  private Goods gd;
  public GoodsBuilder() {
    this.stack = new Stack<>();
   }

  public void buildMerchandise(int id, String name, String desc, double price) { 
    if (this.stack.empty()){
      this.gd = new Merchandise(id, name, desc, price);
    }else{
      this.stack.peek().add(new Merchandise(id, name, desc, price));
    }
  }

  public void startBuildCollection(int id, String name, String desc) { 
    Collection col = new Collection(id, name, desc);
    this.stack.push(col);
  }

  public void endBuildCollection() { 
    if (stack.size() == 1) {
      this.gd = this.stack.pop();
    } else {
        Goods temp = stack.pop();
        this.stack.peek().add(temp);
    }
  }
  
  public Goods getResult() { 
    return this.gd;
  }
}
