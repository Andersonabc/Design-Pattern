package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ParagraphTest {
    @Test
    public void get_text_test(){
        Paragraph paragraph = new Paragraph("This is a paragraph");
        assertEquals("This is a paragraph", paragraph.getText());
    }
    @Rule
    public ExpectedException expected = ExpectedException.none();
    @Test
    public void add_paragraph_to_paragraph_exception_test(){
        Paragraph paragraph = new Paragraph("This is a paragraph");
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: add");
        paragraph.add(new Paragraph("error"));
    }
    @Test
    public void get_level_exception_test(){
        Paragraph paragraph = new Paragraph("This is a paragraph");
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: getLevel");
        paragraph.getLevel();
    }
    @Test
    public void get_size_exception_test(){
        Paragraph paragraph = new Paragraph("This is a paragraph");
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: getSize");
        paragraph.getSize();
    }
    @Test
    public void Itr_next_exception_test(){
        Paragraph paragraph = new Paragraph("This is a paragraph");
        expected.expect(NoSuchElementException.class);
        expected.expectMessage("Iterator don't have next()");
        paragraph.iterator().next();
    }
    @Test
    public void Itr_hasnext_test(){
        Paragraph paragraph = new Paragraph("This is a paragraph");
        assertFalse(paragraph.iterator().hasNext());
    }
    @Test
    public void to_string_test(){
        Paragraph paragraph = new Paragraph("This is a paragraph");
        assertEquals("Paragraph\ttext: This is a paragraph\n", paragraph.toString());
    }
  }
