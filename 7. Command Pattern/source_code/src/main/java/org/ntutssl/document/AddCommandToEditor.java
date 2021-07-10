package org.ntutssl.document;

public class AddCommandToEditor implements Command {
  /**
   * @param target   the target editor
   * @param document the document to be added
   */
  private Editor target;
  private Document document;
  public AddCommandToEditor(Editor target, Document document) {
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
