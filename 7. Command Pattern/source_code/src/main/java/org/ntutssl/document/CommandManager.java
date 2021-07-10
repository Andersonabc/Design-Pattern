package org.ntutssl.document;

import java.util.Stack;

public class CommandManager {
  private Stack<Command> cmd;
  private Stack<Command> recmd;
  public CommandManager() {
    this.cmd = new Stack();
    this.recmd = new Stack();
  }

  public void executeCmd(Command cmd) {
    this.cmd.push(cmd);
    this.cmd.peek().execute();
  }
  
  public void undoCmd() {
    if(this.cmd.size() == 0){
      throw new DocumentException("No command can be undid.");
    }else{
      this.cmd.peek().undo();
      this.recmd.push(this.cmd.pop());
    }

  }

  public void redoCmd() {
    if(this.recmd.size() == 0){
      throw new DocumentException("No command can be redid.");
    }else{
      this.cmd.push(this.recmd.peek());
      this.recmd.pop().redo();
    }
  }
}
