package org.ntutssl.document;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
public class AddCommandToEditorTest {

    @Test
    public void execute_test(){
        Editor editor = new Editor();
        Title t = new Title("title",1);
        AddCommandToEditor cmd = new AddCommandToEditor(editor,t);
        cmd.execute();
        assertEquals(t,editor.iterator().next());
    }

    @Test
    public void undo_test(){
        Editor editor = new Editor();
        Title t = new Title("title",1);
        AddCommandToEditor cmd = new AddCommandToEditor(editor,t);
        cmd.execute();
        cmd.undo();
        assertFalse(editor.iterator().hasNext());
    }

    @Test
    public void redo_test(){
        Editor editor = new Editor();
        Title t = new Title("title",1);
        AddCommandToEditor cmd = new AddCommandToEditor(editor,t);
        cmd.execute();
        cmd.undo();
        cmd.redo();
        assertEquals(t,editor.iterator().next());
    }
}