package org.ntutssl.document;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
public class AddCommandToArticleTest {
    
    @Test
    public void execute_test(){
        Article a = new Article("article",1);
        Title t = new Title("title",3);
        AddCommandToArticle cmd = new AddCommandToArticle(a,t);
        cmd.execute();
        assertEquals(t,a.iterator().next());
    }

    @Test
    public void undo_test(){
        Article a = new Article("article",1);
        Title t = new Title("title",3);
        AddCommandToArticle cmd = new AddCommandToArticle(a,t);
        cmd.execute();
        cmd.undo();
        assertFalse(a.iterator().hasNext());
    }

    @Test
    public void redo_test(){
        Article a = new Article("article",1);
        Title t = new Title("title",3);
        AddCommandToArticle cmd = new AddCommandToArticle(a,t);
        cmd.execute();
        cmd.undo();
        cmd.redo();
        assertEquals(t,a.iterator().next());
    }

}
