package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;

public class HtmlOutputVisitorTest {
    @Test
    public void accept_article_test(){
        Article a1 = new Article("apple",1);
        HtmlOutputVisitor hov = new HtmlOutputVisitor();
        a1.accept(hov);
        assertEquals("<article topic='apple'>\n</article>\n", hov.getResult());
    }
    @Test
    public void accept_title_test(){
        Title t1 = new Title("berry",1);
        HtmlOutputVisitor hov = new HtmlOutputVisitor();
        t1.accept(hov);
        assertEquals("<h1>berry</h1>\n", hov.getResult());
    }
    @Test
    public void accept_paragraph_test(){
        Paragraph p1 = new Paragraph("passionfruit");
        HtmlOutputVisitor hov = new HtmlOutputVisitor();
        p1.accept(hov);
        assertEquals("<p>passionfruit</p>\n", hov.getResult());
    }
    @Test
    public void visit_result_test(){
        Editor e1 = new Editor();
        Article a1 = new Article("apple",1);
        Paragraph p1 = new Paragraph("passionfruit");
        Title t1 = new Title("guava juice",3);
        e1.add(a1);
        e1.add(p1);
        e1.add(t1);
        Iterator<Document> itr = e1.iterator();
        HtmlOutputVisitor hov = new HtmlOutputVisitor();
        while (itr.hasNext()){
            itr.next().accept(hov);
        }
        assertEquals("<article topic='apple'>\n</article>\n<p>passionfruit</p>\n<h3>guava juice</h3>\n", hov.getResult());
    }
 }
