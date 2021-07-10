package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EditorTest { 
    @Test
    public void add_and_size_test(){
        Editor editor = new Editor();
        Title t = new Title("title1",1);
        Paragraph p = new Paragraph("Paragraph");
        Article a = new Article("article", 1);
        editor.add(t);
        editor.add(p);
        editor.add(a);
        assertEquals(3,editor.size());
    }
    @Test
    public void empty_size_test(){
        Editor editor = new Editor();
        assertEquals(0,editor.size());
    }
    @Test
    public void iterator_test(){
        Editor editor = new Editor();
        Article a = new Article("article", 1);
        editor.add(a);
        assertTrue(editor.iterator().hasNext());
    }

}