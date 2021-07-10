package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

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
    public void add_title_to_title_exception_test(){
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
    public void get_content_exception_test(){
        Paragraph paragraph = new Paragraph("This is a paragraph");
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: getContent");
        paragraph.getContent(10);
    }
  }
