package org.ntutssl.document;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.*;


public class Editor {
  private List<Document> documents;
  private List<String> result;
  public Editor(){
    this.documents = new ArrayList<Document>();
    this.result = new ArrayList<String>();
  }

  public void importDocumentFromJsonFile(String filePath) { 
    try{
      FileInputStream fis = new FileInputStream(filePath);
      JsonReader jReader = new JsonReader(new InputStreamReader(fis,"UTF-8"));
      JsonArray jArray = JsonParser.parseReader(jReader).getAsJsonArray();
      for(JsonElement jElement : jArray){
          DocumentParser documentParser = new DocumentParser();
          this.add(documentParser.parse(jElement.getAsJsonObject()));
        }
    }catch(Exception e){
        throw new DocumentException("import error");
    }
 }


  public void exportDocumentAsHtmlFile(String outputPath) {
    this.result = new ArrayList<String>();
    HtmlOutputConsumer hoc = new HtmlOutputConsumer(this.result);
    Iterator<Document> itr = this.documents.iterator();
		while(itr.hasNext()){
		  hoc.accept(itr.next());
		}

		try{
			File f = new File(outputPath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
	
			Iterator<String> answer = result.iterator();
			while(answer.hasNext()){
				bw.write(answer.next());
			}
			bw.close();
		}catch(IOException e){
			System.out.println("ERROR occurred.");
		}
		

   }

  public void findContent(String target) { 
    List<Document> result = new ArrayList<Document>();
		FindContentConsumer fcc = new FindContentConsumer(result, target);
		Iterator<Document> itr = this.documents.iterator();
		while(itr.hasNext()){
			fcc.accept(itr.next());
		}
		Iterator<Document> answer = result.iterator();
		while(answer.hasNext()){
			System.out.print(answer.next().toString());
		}
  }

  
  public void add(Document document) {
    if(document!=null){
      this.documents.add(document);
    }
  }

  public int size() {
    return this.documents.size();
  }

  public Iterator<Document> iterator() {
    return documents.iterator();
  }
}
