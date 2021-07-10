package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;

public class FindContentVisitorTest {
    @Test
    public void accept_article_test(){
        Article a1 = new Article("apple",1);
        Article a2 = new Article("apple pie",2);
        Title a3 = new Title("banana",3);
        Paragraph a4 = new Paragraph("apple juice");
        a1.add(a2);
        a1.add(a3);
        a1.add(a4);
        FindContentVisitor fcv = new FindContentVisitor("apple");
        a1.accept(fcv);
        List<Document> doc = fcv.getResult();
        assertEquals(3,doc.size());
    }
    @Test
    public void accept_title_test(){
        Editor e1 = new Editor();
        Title t1 = new Title("apple",3);
        Title t2 = new Title("apple juice",3);
        Title t3 = new Title("guava juice",3);
        e1.add(t1);
        e1.add(t2);
        e1.add(t3);
        Iterator<Document> itr = e1.iterator();
        FindContentVisitor fcv = new FindContentVisitor("apple");
        while (itr.hasNext()){
            itr.next().accept(fcv);
        }
        List<Document> doc = fcv.getResult();
        assertEquals(2,doc.size());
    }
    @Test
    public void accept_paragraph_test(){
        Editor e1 = new Editor();
        Paragraph t1 = new Paragraph("banana");
        Paragraph t2 = new Paragraph("apple juice");
        Paragraph t3 = new Paragraph("guava juice");
        e1.add(t1);
        e1.add(t2);
        e1.add(t3);
        Iterator<Document> itr = e1.iterator();
        FindContentVisitor fcv = new FindContentVisitor("apple");
        while (itr.hasNext()){
            itr.next().accept(fcv);
        }
        List<Document> doc = fcv.getResult();
        assertEquals(1,doc.size()); 
    }
    @Test 
    public void visit_result_test(){
        Article a1 = new Article("apple",1);
        Article a2 = new Article("apple pie",2);
        Title a3 = new Title("banana",3);
        Paragraph a4 = new Paragraph("apple juice");
        a1.add(a2);
        a1.add(a3);
        a1.add(a4);
        FindContentVisitor fcv = new FindContentVisitor("apple");
        a1.accept(fcv);
        Iterator<Document> doc = fcv.getResult().iterator();
        assertEquals("apple",doc.next().getText());
        assertEquals("apple pie",doc.next().getText());
    }
    @Test 
    public void find_empty_but_not_exist_test(){
        Article a1 = new Article("apple",1);
        Article a2 = new Article("apple pie",2);
        Title a3 = new Title("banana",3);
        Paragraph a4 = new Paragraph("apple juice");
        a1.add(a2);
        a1.add(a3);
        a1.add(a4);
        FindContentVisitor fcv = new FindContentVisitor("");
        a1.accept(fcv);
        Iterator<Document> doc = fcv.getResult().iterator();
        assertFalse(doc.hasNext());
    }
    @Test 
    public void find_empty_and_exist_test(){
        Article a1 = new Article("apple",1);
        Article a2 = new Article("apple pie",2);
        Title a3 = new Title("",3);
        Paragraph a4 = new Paragraph("apple juice");
        a1.add(a2);
        a1.add(a3);
        a1.add(a4);
        FindContentVisitor fcv = new FindContentVisitor("");
        a1.accept(fcv);
        Iterator<Document> doc = fcv.getResult().iterator();
        assertEquals("",doc.next().getText());
    }
 }
