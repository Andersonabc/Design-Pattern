package org.ntutssl.document;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
public class FindContentVisitor implements Visitor {  
  private List result;
  private String target;
  public FindContentVisitor(String target) {
    this.result = new ArrayList<Document>();
    this.target = target;
  }

  public void visitParagraph(Paragraph paragraph) {
    String content = paragraph.getText();
    if(content.toLowerCase().contains(target.toLowerCase()) && (this.target.length()>0 || paragraph.getText().length() == 0)){
      result.add(paragraph);
    }
  }

  public void visitTitle(Title title) {
    String content = title.getText();
    if(content.toLowerCase().contains(target.toLowerCase())&& (this.target.length()>0 || title.getText().length() == 0)){
      result.add(title);
    }
  }

  public void visitArticle(Article article) {
    String content = article.getText();
    if(content.toLowerCase().contains(target.toLowerCase())&& (this.target.length()>0 || article.getText().length() == 0)){
      result.add(article);
    }   
    Iterator<Document>itr = article.iterator();
    while(itr.hasNext()){
      itr.next().accept(this);
    }
  }
  
  public List<Document> getResult() {
    return this.result;
  }
}
//add title
//Design Patterns
//1
//add paragraph
//This course discusses issues in principles of object-oriented design through design patterns.
//add article
//Course Design Pattern
//1
//add title
//Information
//2
//add paragraph
//Professor: YC Cheng
//add article
//Introduction
//2
//add title
//What is design pattern?
//3
//add paragraph
//Design pattern are solutions to general problems that appear repeatedly in software engineering.
//exit
//add title
//References
//2
//add paragraph
//Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.
//exit
//find content
//design pattern