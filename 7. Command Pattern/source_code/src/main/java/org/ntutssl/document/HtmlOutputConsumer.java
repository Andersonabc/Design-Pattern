package org.ntutssl.document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.lang.NullPointerException;
import java.util.Iterator;

public class HtmlOutputConsumer implements Consumer<Document> {

  /**
   * @param result you should add the result into here.
   * 
   * example:
   * this.result.get(0) = "<article topic='topic1'>\n";
   * this.result.get(1) = "  <h1>title1</h1>\n";
   * this.result.get(2) = "  <p>paragraph1</p>\n";
   * this.result.get(3) = "  <h2>title2</h2>\n";
   * this.result.get(4) = "</article>\n";
   */
  private List<String> result;
  private String prefix;
  public HtmlOutputConsumer(List<String> result) {
    this.result = result;
    this.prefix = "";
   }

  public void accept(Document document) {
    if(document.getClass().getSimpleName().equals("Title")){
      this.result.add(String.format("%s<h%d>%s</h%d>\n",this.prefix,document.getSize(),document.getText(),document.getSize()));
    }else if(document.getClass().getSimpleName().equals("Paragraph")){
      this.result.add(String.format("%s<p>%s</p>\n",this.prefix, document.getText()));
    }else if(document.getClass().getSimpleName().equals("Article")){
      Iterator<Document> itr= document.iterator();
      this.result.add(String.format("%s<article topic='%s'>\n",this.prefix,document.getText()));
      String buf = this.prefix;
      this.prefix += "  ";
      while(itr.hasNext()){
        this.accept(itr.next());
      }
      this.prefix = buf;
      this.result.add(String.format("%s</article>\n",this.prefix));
    }
  }

}
