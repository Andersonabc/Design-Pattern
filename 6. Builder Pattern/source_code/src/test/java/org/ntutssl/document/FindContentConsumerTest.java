package org.ntutssl.document;
import static org.junit.Assert.assertEquals;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class FindContentConsumerTest {
    private List<Document> result;
    @Test
    public void find_article_test(){
        this.result = new ArrayList<Document>();
        Article a1 = new Article("apple",1);
        Article a2 = new Article("apple pie",2);
        FindContentConsumer fcc = new FindContentConsumer(result, "apple");
        fcc.accept(a1);
        fcc.accept(a2);
        assertEquals(2,this.result.size());
    }
    @Test
    public void find_article_with_title_test(){
        this.result = new ArrayList<Document>();
        Article a1 = new Article("apple",1);
        Title t1 = new Title("Apple juice",3);
        a1.add(t1);
        FindContentConsumer fcc = new FindContentConsumer(result, "apple");
        fcc.accept(a1);
        assertEquals(2,this.result.size());
    }
    @Test
    public void find_article_with_paragraph_test(){
        this.result = new ArrayList<Document>();
        Article a1 = new Article("apple",1);
        Paragraph p1 = new Paragraph("apple juice");
        Paragraph p2 = new Paragraph("banana juice");
        a1.add(p1);
        a1.add(p2);
        FindContentConsumer fcc = new FindContentConsumer(result, "apple");
        fcc.accept(a1);
        assertEquals(2,this.result.size());
    }
    @Test
    public void find_article_with_titleandparagraph_test(){
        this.result = new ArrayList<Document>();
        Article a1 = new Article("apple",1);
        Title t1 = new Title("apple pie",3);
        Paragraph p1 = new Paragraph("apple juice");
        a1.add(t1);
        a1.add(p1);
        FindContentConsumer fcc = new FindContentConsumer(result, "apple");
        fcc.accept(a1);
        assertEquals(3,this.result.size());
    }
    @Test
    public void find_twolevel_article_test(){
        this.result = new ArrayList<Document>();
        Article a1 = new Article("apple",1);
        Article a2 = new Article("apple pie",2);
        Title t1 = new Title("apple watch",3);
        Paragraph p1 = new Paragraph("apple juice");
        a2.add(t1);
        a2.add(p1);
        a1.add(a2);
        FindContentConsumer fcc = new FindContentConsumer(result, "apple");
        fcc.accept(a1);
        assertEquals(4,this.result.size());
    }
    @Test
    public void find_nothing_test(){
        this.result = new ArrayList<Document>();
        Article a1 = new Article("apple",1);
        Article a2 = new Article("apple pie",2);
        Title t1 = new Title("apple watch",3);
        Paragraph p1 = new Paragraph("apple juice");
        a2.add(t1);
        a2.add(p1);
        a1.add(a2);
        FindContentConsumer fcc = new FindContentConsumer(result, "guava");
        fcc.accept(a1);
        assertEquals(0,this.result.size());
    }
 }