package org.ntutssl.document;

public class Paragraph implements Document {
  private String text;
  public Paragraph(String text) {
    this.text = text;  
  }

  public String getText() { 
    return text;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitParagraph(this);
  }

  @Override
  public String toString() {
    return String.format("Paragraph\ttext: %s\n", this.text);
  }
}
