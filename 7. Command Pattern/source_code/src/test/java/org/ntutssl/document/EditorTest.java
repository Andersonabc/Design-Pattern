package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.Iterator;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class EditorTest { 
    @Test
    public void add_and_size_test(){
        Editor editor = new Editor();
        Title t = new Title("title1",1);
        Paragraph p = new Paragraph("Paragraph");
        Article a = new Article("article", 1);
        editor.add(t);
        editor.add(p);
        editor.add(a);
        assertEquals(3,editor.size());
    }
    @Test
    public void empty_size_test(){
        Editor editor = new Editor();
        assertEquals(0,editor.size());
    }
    @Test
    public void empty_list_test(){
        Editor editor = new Editor();
        assertFalse(editor.iterator().hasNext());
    }
    @Test
    public void iterator_test(){
        Editor editor = new Editor();
        Article a = new Article("article", 1);
        editor.add(a);
        assertTrue(editor.iterator().hasNext());
    }
    @Test 
    public void import_json_test(){
        Editor editor = new Editor();
        editor.importDocumentFromJsonFile("input/test_input.json");
        Iterator<Document> itr = editor.iterator();
        assertEquals("I'm a simple title", itr.next().getText());
        assertEquals("I'm a simple paragraph", itr.next().getText());
        assertEquals("I'm a simple article", itr.next().getText());
    }
    @Rule
    public ExpectedException expected = ExpectedException.none();
    @Test
    public void import_jso_withwrongpath_test(){
        Editor editor = new Editor();
        expected.expect(DocumentException.class);
        expected.expectMessage("import error");
        editor.importDocumentFromJsonFile("input/error.json");
    }
    @Test
    public void find_content_test(){
        Editor editor = new Editor();
        Article a = new Article("apple", 1);
        Title t = new Title("apple juice", 2);
        Paragraph p = new Paragraph("grape");
        editor.add(a);
        editor.add(t);
        editor.add(p);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        editor.findContent("apple");
        assertEquals("Article\t\ttopic: apple\n\t\tlevel: 1\nTitle\t\ttext: apple juice\n\t\tsize: 2\n",stream.toString());
        System.setOut(originalOut);
    }
    @Test
    public void find_nothing_test(){
        Editor editor = new Editor();
        Article a = new Article("apple", 1);
        Title t = new Title("apple juice", 2);
        Paragraph p = new Paragraph("grape");
        editor.add(a);
        editor.add(t);
        editor.add(p);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        editor.findContent("banana");
        assertEquals("",stream.toString());
        System.setOut(originalOut);
    }
    @Test
    public void export_html_test(){
        Editor editor = new Editor();
        Article a = new Article("apple", 1);
        Title t = new Title("apple juice", 2);
        Paragraph p = new Paragraph("grape");
        Paragraph p2 = new Paragraph("guava");
        a.add(p2);
        editor.add(a);
        editor.add(t);
        editor.add(p);
        editor.exportDocumentAsHtmlFile("output/test.html");
        List<String> htmlResult = new ArrayList();
        File f = new File("output/test.html");
        try(Scanner sc = new Scanner(f)){
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                htmlResult.add(sc.next());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        assertEquals(htmlResult.get(0),"<article topic='apple'>");
        assertEquals(htmlResult.get(1),"  <p>guava</p>");
        assertEquals(htmlResult.get(2),"</article>");
        assertEquals(htmlResult.get(3),"<h2>apple juice</h2>");
        assertEquals(htmlResult.get(4),"<p>grape</p>");

        f.delete();
    }
}