package org.ntutssl.document;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.io.FileInputStream;
import java.lang.Exception;

import org.junit.Test;
public class DocumentParserTest { 
    @Test
    public void parse_article_test(){
        JsonObject jObj = JsonParser.parseString("{\"type\": \"article\",\"text\": \"Article\",\"level\": 0,\"contents\": []}").getAsJsonObject();
        DocumentParser dp = new DocumentParser();
        Document doc = dp.parse(jObj);
        assertEquals(doc.getClass(),Article.class);
        assertEquals(doc.getText(),"Article");
        assertEquals(doc.getLevel(),0);
    }

    @Test
    public void parse_paragraph_test(){
        JsonObject jObj = JsonParser.parseString("{\"type\": \"paragraph\",\"text\": \"Paragraph\"}").getAsJsonObject();
        DocumentParser dp = new DocumentParser();
        Document doc= dp.parse(jObj);
        assertEquals(doc.getClass(),Paragraph.class);
        assertEquals(doc.getText(),"Paragraph");
    }

    @Test
    public void parse_title_test(){
        JsonObject jObj = JsonParser.parseString("{\"type\": \"title\",\"text\": \"Title\", \"size\": 1}").getAsJsonObject();
        DocumentParser dp = new DocumentParser();
        Document doc= dp.parse(jObj);
        assertEquals(doc.getClass(),Title.class);
        assertEquals(doc.getText(),"Title");
        assertEquals(doc.getSize(),1);
    }
    @Test
    public void parse_article_with_documents_test(){
        JsonObject jObj = JsonParser.parseString("{\"type\": \"article\",\"text\": \"Article\",\"level\": 0,\"contents\": [{\"type\": \"title\",\"text\": \"Title\",\"size\": 2}]}").getAsJsonObject();
        DocumentParser dp = new DocumentParser();
        Document doc = dp.parse(jObj);
        assertEquals(doc.getClass(),Article.class);
        assertEquals(doc.getText(),"Article");
        assertEquals(doc.getLevel(),0);
        assertEquals(doc.iterator().next().getClass(),Title.class);
        assertEquals(doc.iterator().next().getText(),"Title");
        assertEquals(doc.iterator().next().getSize(),2);
    }
    @Test
    public void parse_with_inputfile_test(){
        Editor ed = new Editor();
        try{
            FileInputStream fis = new FileInputStream("input/test_input.json");
            JsonReader jReader = new JsonReader(new InputStreamReader(fis,"UTF-8"));
            JsonArray jArray = JsonParser.parseReader(jReader).getAsJsonArray();
            for(JsonElement jElement : jArray){
                DocumentParser documentParser = new DocumentParser();
                ed.add(documentParser.parse(jElement.getAsJsonObject()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Iterator<Document> itr = ed.iterator();
        assertEquals("I'm a simple title", itr.next().getText());
        assertEquals("I'm a simple paragraph", itr.next().getText());
        assertEquals("I'm a simple article", itr.next().getText());
    }
}