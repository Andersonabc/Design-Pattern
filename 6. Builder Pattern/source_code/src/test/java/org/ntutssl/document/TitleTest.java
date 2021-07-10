package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.Iterator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;

import org.junit.Test;

public class TitleTest { 
    @Test
    public void get_text_test(){
        Title title = new Title("This is a title",2);
        assertEquals("This is a title", title.getText());
    }
    @Rule
    public ExpectedException expected = ExpectedException.none();
    @Test
    public void add_title_to_title_exception_test(){
        Title title = new Title("This is a title",2);
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: add");
        title.add(new Title("error",2));
    }
    @Test
    public void get_level_exception_test(){
        Title title = new Title("This is a title",2);
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: getLevel");
        title.getLevel();
    }
    @Test
    public void Itr_next_exception_test(){
        Title title = new Title("This is a title",2);
        boolean thrown = false;
        Iterator<Document> itr= title.iterator();
        try {
            itr.next();
        } catch (NoSuchElementException e) {
            if (e.getMessage().equals("Iterator don't have next()"))thrown = true;
        }     
        assertTrue(thrown);
    }

    @Test
    public void Itr_hasnext_exception_test(){
        Title title = new Title("This is a title",2);
        Iterator<Document> itr= title.iterator();
        assertFalse(itr.hasNext());
    }
    @Test
    public void to_string_test(){
        Title title = new Title("This is a title",2);
        assertEquals("Title\t\ttext: This is a title\n\t\tsize: 2\n",title.toString());
    }
 }
