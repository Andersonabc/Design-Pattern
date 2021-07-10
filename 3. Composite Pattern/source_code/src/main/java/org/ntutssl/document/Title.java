package org.ntutssl.document;

import org.w3c.dom.Text;

public class Title implements Document {
  private String text;
  public Title(String text) {  
    this.text = text;
  }

  public String getText() {  
    return text;
  }
}