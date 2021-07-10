package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.junit.Test;

public class TitleTest { 
    @Test
    public void get_text_test(){
        Title title = new Title("This is a title");
        assertEquals("This is a title", title.getText());
    }
    @Rule
    public ExpectedException expected = ExpectedException.none();
    @Test
    public void add_title_to_title_exception_test(){
        Title title = new Title("This is a title");
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: add");
        title.add(new Title("error"));
    }
    @Test
    public void get_level_exception_test(){
        Title title = new Title("This is a title");
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: getLevel");
        title.getLevel();
    }
    @Test
    public void get_content_exception_test(){
        Title title = new Title("This is a title");
        boolean thrown = false;
        try {
            title.getContent(10);
        } catch (DocumentException e) {
            if (e.getMessage().equals("Invalid action: getContent"))thrown = true;
        }     
        assertTrue(thrown);

    }

 }
