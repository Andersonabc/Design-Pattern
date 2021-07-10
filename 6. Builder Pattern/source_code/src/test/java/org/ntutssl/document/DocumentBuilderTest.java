package org.ntutssl.document;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Test;

public class DocumentBuilderTest { 
    private Iterator<Document> itr;
    @Test
    public void build_title_test(){
        DocumentBuilder db = new DocumentBuilder();
        db = db.buildTitle("title", 1);
        assertEquals("title", db.getResult().getText());
        assertEquals(1,db.getResult().getSize());
    }
    @Test
    public void build_paragraph_test(){
        DocumentBuilder db = new DocumentBuilder();
        db = db.buildParagraph("paragraph");
        assertEquals("paragraph", db.getResult().getText());
    }
    @Test
    public void build_onelevel_article_test(){
        DocumentBuilder db = new DocumentBuilder();
        db = db.startBuildArticle("topic", 1);
        db = db.endBuildArticle();
        assertEquals("topic", db.getResult().getText());
    }
    @Test
    public void build_onelevel_article_with_title_test(){
        DocumentBuilder db = new DocumentBuilder();
        db = db.startBuildArticle("topic", 1);
        db = db.buildTitle("title", 2);
        db = db.endBuildArticle();
        this.itr = db.getResult().iterator();
        assertEquals("topic", db.getResult().getText());
        assertEquals(1,db.getResult().getLevel());
        assertTrue(this.itr.hasNext());
        Document doc = itr.next();
        assertEquals("title", doc.getText());
        assertEquals(2, doc.getSize());
    }
    @Test
    public void build_onelevel_article_with_paragraph_test(){
        DocumentBuilder db = new DocumentBuilder();
        db = db.startBuildArticle("topic", 1);
        db = db.buildParagraph("paragraph");
        db = db.endBuildArticle();
        this.itr = db.getResult().iterator();
        assertEquals("topic", db.getResult().getText());
        assertEquals(1,db.getResult().getLevel());
        assertTrue(this.itr.hasNext());
        Document doc = itr.next();
        assertEquals("paragraph", doc.getText());
    }
    @Test
    public void build_onelevel_article_with_titleandparagraph_test(){
        DocumentBuilder db = new DocumentBuilder();
        db = db.startBuildArticle("topic", 1);
        db = db.buildTitle("title", 2);
        db = db.buildParagraph("paragraph");
        db = db.endBuildArticle();
        this.itr = db.getResult().iterator();
        assertEquals("topic", db.getResult().getText());
        assertEquals(1,db.getResult().getLevel());
        assertTrue(this.itr.hasNext());
        Document doc = itr.next();
        assertEquals("title", doc.getText());
        assertEquals(2, doc.getSize());
        assertTrue(this.itr.hasNext());
        doc = itr.next();
        assertEquals("paragraph", doc.getText());
    }
}