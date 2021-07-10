package org.ntutssl.document;
import java.util.Stack;
/**
 * Document builder helps you build a Document object.
 * 
 * Please note that the return value of each build procedure is 
 * DocumentBuilder itself, so you can build a large object using
 * a single statement.
 */

class DocumentBuilder {
  private Stack<Document> stack;
  private Document doc;
  public DocumentBuilder() {
    this.stack = new Stack<>();
   }

  public DocumentBuilder buildTitle(String text, int size) {
    if (this.stack.empty()){
      this.doc = new Title(text, size);
    }else{
      this.stack.peek().add(new Title(text, size));
    }
    return this;
   }

  public DocumentBuilder buildParagraph(String text) { 
    if (this.stack.empty()){
      this.doc = new Paragraph(text);
    }else{
      this.stack.peek().add(new Paragraph(text));
    }
    return this;
  }

  public DocumentBuilder startBuildArticle(String topic, int level) { 
    Article art = new Article(topic, level);
    this.stack.push(art);
    return this;
  }

  public DocumentBuilder endBuildArticle() {
    if (stack.size() == 1) {
      this.doc = this.stack.pop();
    } else {
        Document temp = stack.pop();
        this.stack.peek().add(temp);
    }
    return this;
  }

  public Document getResult() { 
    return this.doc;
  }
}