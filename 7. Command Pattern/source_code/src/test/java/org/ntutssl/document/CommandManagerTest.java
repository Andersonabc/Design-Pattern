package org.ntutssl.document;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
public class CommandManagerTest {

    @Test
    public void AddCommandToEditor_test(){
        CommandManager manager = new CommandManager();
        Editor editor = new Editor();
        Title title = new Title("title",1);
        AddCommandToEditor command = new AddCommandToEditor(editor,title);
        manager.executeCmd(command);
        assertEquals(title,editor.iterator().next());
    }

    @Test
    public void AddCommandToArticle_test(){
        CommandManager manager = new CommandManager();
        Article article = new Article("article",1);
        Title title = new Title("title",1);
        AddCommandToArticle command = new AddCommandToArticle(article,title);
        manager.executeCmd(command);
        assertEquals(title,article.iterator().next());
    }

    @Test
    public void undo_test(){
        CommandManager manager = new CommandManager();
        Editor editor = new Editor();
        Title title = new Title("title",1);
        AddCommandToEditor command = new AddCommandToEditor(editor,title);
        manager.executeCmd(command);
        manager.undoCmd();
        assertFalse(editor.iterator().hasNext());
    }
    @Test
    public void undo_twoTimes_test(){
        CommandManager manager = new CommandManager();
        Editor editor = new Editor();
        Title title = new Title("title",1);
        Title title2 = new Title("title2",2);
        AddCommandToEditor command = new AddCommandToEditor(editor,title);
        AddCommandToEditor command2 = new AddCommandToEditor(editor,title2);
        manager.executeCmd(command);
        manager.executeCmd(command2);
        manager.undoCmd();
        manager.undoCmd();
        assertFalse(editor.iterator().hasNext());
    }
    @Test
    public void redo_test(){
        CommandManager manager = new CommandManager();
        Editor editor = new Editor();
        Title title = new Title("title",1);
        AddCommandToEditor command = new AddCommandToEditor(editor,title);
        manager.executeCmd(command);
        manager.undoCmd();
        manager.redoCmd();
        assertEquals(title,editor.iterator().next());
    }

    @Test
    public void redo_twoTimes_test(){
        CommandManager manager = new CommandManager();
        Editor editor = new Editor();
        Title title = new Title("title",1);
        Title title2 = new Title("title2",2);
        AddCommandToEditor command = new AddCommandToEditor(editor,title);
        AddCommandToEditor command2 = new AddCommandToEditor(editor,title2);
        manager.executeCmd(command);
        manager.executeCmd(command2);
        manager.undoCmd();
        manager.undoCmd();
        manager.redoCmd();
        manager.redoCmd();
        Iterator<Document> it = editor.iterator();
        assertEquals(title,it.next());
        assertEquals(title2,it.next());
    }
    @Test
    public void undo_redo_undo_redo_test(){
        CommandManager manager = new CommandManager();
        Editor editor = new Editor();
        Title title = new Title("title",1);
        AddCommandToEditor command = new AddCommandToEditor(editor,title);
        manager.executeCmd(command);
        manager.undoCmd();
        manager.redoCmd();
        manager.undoCmd();
        manager.redoCmd();
        assertEquals(title, editor.iterator().next());
    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void undo_error_test(){
        CommandManager manager = new CommandManager();
        exceptionRule.expect(DocumentException.class);
        exceptionRule.expectMessage("No command can be undid.");
        manager.undoCmd();
    }
    @Test
    public void undo_twoTimes_error_test(){
        CommandManager manager = new CommandManager();
        Editor editor = new Editor();
        Title title = new Title("title",1);
        Title title2 = new Title("title2",2);
        AddCommandToEditor command = new AddCommandToEditor(editor,title);
        AddCommandToEditor command2 = new AddCommandToEditor(editor,title2);
        manager.executeCmd(command);
        manager.undoCmd();
        exceptionRule.expect(DocumentException.class);
        exceptionRule.expectMessage("No command can be undid.");
        manager.undoCmd();
    }


    @Test
    public void redo_error_test(){
        CommandManager manager = new CommandManager();
        exceptionRule.expect(DocumentException.class);
        exceptionRule.expectMessage("No command can be redid.");
        manager.redoCmd();
    }
    @Test
    public void redo_twoTimes_error_test(){
        CommandManager manager = new CommandManager();
        Editor editor = new Editor();
        Title title = new Title("title",1);
        AddCommandToEditor command = new AddCommandToEditor(editor,title);
        manager.executeCmd(command);
        manager.undoCmd();
        manager.redoCmd();
        exceptionRule.expect(DocumentException.class);
        exceptionRule.expectMessage("No command can be redid.");
        manager.redoCmd();
    }

}