package org.ntutssl.document;

public class AddCommandToArticle implements Command {
  /**
   * @param target   the target document
   * @param document the document to be added
   */
  private Article target;
  private Document document;
  public AddCommandToArticle(Article target, Document document) {
    this.target = target;
    this.document = document;
  }
  

  public void execute() {
    this.target.add(this.document);
  }

  public void undo() {
    this.target.remove(this.document);
  }

  public void redo() {
    this.target.add(this.document);
  }
}
