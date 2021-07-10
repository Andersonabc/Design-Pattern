package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.junit.Test;

public class ArticleTest {
    @Test
    public void get_text_test(){
        Article article = new Article("Pui",99);
        assertEquals("Pui",article.getText());
    }
    @Test
    public void get_level_test(){
        Article article = new Article("Pui",99);
        assertEquals(99,article.getLevel());
    }
    @Test
    public void add_and_get_content_test(){
        Title title = new Title("Berkeley");
        Paragraph paragraph = new Paragraph("JackInTheBox");
        Paragraph paragraph2 = new Paragraph("InNOut");
        Article article = new Article("US",11);
        article.add(title);
        article.add(paragraph);
        article.add(paragraph2);
        assertEquals("Berkeley",article.getContent(0).getText());
        assertEquals("JackInTheBox",article.getContent(1).getText());
        assertEquals("InNOut",article.getContent(2).getText());
    }
    @Rule
    public ExpectedException expected = ExpectedException.none();
    @Test
    public void add_lower_level_exception_test(){
        Article article = new Article("art1",10);
        Article article2 = new Article("art2",15);
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: Adding lower level article");
        article2.add(article);
    }
  }
