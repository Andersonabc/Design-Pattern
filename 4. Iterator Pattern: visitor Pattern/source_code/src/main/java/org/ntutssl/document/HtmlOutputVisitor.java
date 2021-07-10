package org.ntutssl.document;
import java.util.Iterator;
public class HtmlOutputVisitor implements Visitor{
  private String result;
  private String prefix;
  public HtmlOutputVisitor() {
    this.result = "";
    this.prefix = "";
  }

  public void visitParagraph(Paragraph paragraph) {
    result += String.format("%s<p>%s</p>\n",this.prefix, paragraph.getText());
  }

  public void visitTitle(Title title) {
    result += String.format("%s<h%d>%s</h%d>\n",this.prefix,title.getSize(),title.getText(),title.getSize());
  }

  public void visitArticle(Article article) {
    String buf = this.prefix;
    result += String.format("%s<article topic='%s'>\n",buf,article.getText());
    this.prefix+="  ";
    Iterator<Document>itr = article.iterator();
    while(itr.hasNext()){
      itr.next().accept(this);
    }
    this.prefix = buf;
    result += String.format("%s</article>\n",buf);
  }

  public String getResult() {
    return this.result;
  }
}
