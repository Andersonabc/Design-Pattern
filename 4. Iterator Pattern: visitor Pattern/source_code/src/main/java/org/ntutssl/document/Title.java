package org.ntutssl.document;

import org.w3c.dom.Text;

public class Title implements Document {
  private String text;
  private int size;
  public Title(String text, int size) {  
    this.text = text;
    this.size = size;
  }

  public String getText() {  
    return this.text;
  }

  public int getSize() {
    return this.size;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitTitle(this);
  }

  @Override
  public String toString() {
    return String.format("Title\t\ttext: %s\n\t\tsize: %d\n", this.text,this.size);
  }
}