package org.ntutssl.document;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;

public class HtmlOutputConsumerTest {
    private List<String> result;
    @Test
    public void output_article_test(){
        this.result = new ArrayList<String>();
        Article a1 = new Article("apple",1);
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(this.result);
        hoc.accept(a1);
        assertEquals("<article topic='apple'>\n", this.result.get(0));
        assertEquals("</article>\n", this.result.get(1));
    }
    @Test
    public void output_title_test(){
        this.result = new ArrayList<String>();
        Title t1 = new Title("berry",1);
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(this.result);
        hoc.accept(t1);
        assertEquals("<h1>berry</h1>\n", this.result.get(0));
    }
    @Test
    public void output_paragraph_test(){
        this.result = new ArrayList<String>();
        Paragraph p1 = new Paragraph("passionfruit");
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(this.result);
        hoc.accept(p1);
        assertEquals("<p>passionfruit</p>\n", this.result.get(0));
    }
    @Test
    public void output_result_test(){
        this.result = new ArrayList<String>();
        Editor e1 = new Editor();
        Article a1 = new Article("apple",1);
        Paragraph p1 = new Paragraph("passionfruit");
        Title t1 = new Title("guava juice",3);
        e1.add(a1);
        e1.add(p1);
        e1.add(t1);
        Iterator<Document> itr = e1.iterator();
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(this.result);
        while (itr.hasNext()){
            hoc.accept(itr.next());
        }
        assertEquals("<article topic='apple'>\n", this.result.get(0));
        assertEquals("</article>\n", this.result.get(1));
        assertEquals("<p>passionfruit</p>\n", this.result.get(2));
        assertEquals("<h3>guava juice</h3>\n", this.result.get(3));
    }
    @Test
    public void output_result_withindentation_test(){
        this.result = new ArrayList<String>();
        Article a1 = new Article("apple",1);
        Paragraph p1 = new Paragraph("passionfruit");
        Title t1 = new Title("guava juice",3);
        a1.add(p1);
        a1.add(t1);
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(this.result);
        hoc.accept(a1);
        assertEquals("<article topic='apple'>\n", this.result.get(0));
        assertEquals("  <p>passionfruit</p>\n", this.result.get(1));
        assertEquals("  <h3>guava juice</h3>\n", this.result.get(2));
        assertEquals("</article>\n", this.result.get(3));
    }
 }