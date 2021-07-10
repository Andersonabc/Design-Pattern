package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import java.util.Iterator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.junit.Test;

public class ArticleTest {
    @Rule
    public ExpectedException expected = ExpectedException.none();
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
    public void get_size_exception_test(){
        Article article = new Article("art",10);
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: getSize");
        article.getSize();
    }
    @Test
    public void add_article_get_text_test(){
        Article article = new Article("UK",11);
        Article article2= new Article("US",21);
        article.add(article2);
        Iterator<Document> itr = article.iterator();
        assertEquals("US",itr.next().getText());
    }
    @Test
    public void add_morethanonelayer_article_get_text_test(){
        Article article = new Article("UK",11);
        Article article2= new Article("US",21);
        Paragraph paragraph = new Paragraph("InNOut");
        Title title = new Title("Berkeley",3);
        article2.add(paragraph);
        article2.add(title);
        article.add(article2);
        Iterator<Document> itr = article.iterator();
        Iterator<Document> itr2 = itr.next().iterator();
        assertEquals("InNOut",itr2.next().getText());
        assertEquals("Berkeley",itr2.next().getText());
    }
    @Test
    public void add_paragraph_get_text_test(){
        Paragraph paragraph = new Paragraph("InNOut");
        Article article = new Article("US",11);
        article.add(paragraph);
        Iterator<Document> itr = article.iterator();
        assertEquals("InNOut",itr.next().getText());
    }
    @Test
    public void add_title_get_text_test(){
        Title title = new Title("Berkeley",3);
        Article article = new Article("US",11);
        article.add(title);
        Iterator<Document> itr = article.iterator();
        assertEquals("Berkeley",itr.next().getText());
    }
    @Test
    public void add_lower_level_exception_test(){
        Article article = new Article("art1",10);
        Article article2 = new Article("art2",15);
        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid action: Adding lower level article");
        article2.add(article);
    }
    @Test
    public void iterator_test(){
        Article article = new Article("art1",10);
        Title title = new Title("Berkeley",3);
        Paragraph paragraph = new Paragraph("JackInTheBox");
        article.add(title);
        article.add(paragraph);
        Iterator<Document> itr = article.iterator();
        assertEquals("Berkeley",itr.next().getText());
        assertEquals("JackInTheBox",itr.next().getText());
    }    
    @Test
    public void to_string_test(){
        Article article = new Article("art1",10);
        assertEquals("Article\t\ttopic: art1\n\t\tlevel: 10\n",article.toString());
    }
  }
