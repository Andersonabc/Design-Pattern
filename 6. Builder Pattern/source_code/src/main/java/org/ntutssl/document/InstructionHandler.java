package org.ntutssl.document;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InstructionHandler {
	Editor editor;
	Scanner scanner;
	int lastLevel;
	public InstructionHandler(Editor editor) {
		this.editor = editor;
		this.scanner = new Scanner(System.in);
		this.lastLevel = 0;
	}

	public void run() {
		String input;
		printEditorInstructions();
		input = scanner.nextLine();
		while (!input.equals("exit")){
			//System.out.println(String.format("your input is: %s", input));
			handleEditorInstructions(input);
			printEditorInstructions();
			input = scanner.nextLine();
		}
		scanner.close();
	}

	private void printEditorInstructions() {
		System.out.println("Please enter the following instruction to start editing:");
		System.out.println("1. 'add title': to add a title to the editor");
		System.out.println("2. 'add paragraph': to add a paragraph to the editor");
		System.out.println("3. 'add article': to add an article to the editor");	
		System.out.println("4. 'find content': to find the specific string in the editor");
		System.out.println("5. 'import json': to import Document into editor from json file");
		System.out.println("6. 'output html': to transfer the text to html format");
		System.out.println("7. 'exit': to exit the program");
	}
	
	private void handleEditorInstructions(String instruction) {
		if(instruction.equals("add title")){
			Document temp = addTitleInstruction();
			if(temp != null){
				this.editor.add(temp);
				System.out.println("Title added to the editor.");
			}
		}else if(instruction.equals("add paragraph")){
			this.editor.add(addParagraphInstruction());
			System.out.println("Paragraph added to the editor.");
			return;
		}else if (instruction.equals("add article")){
			Document art = addArticleInstruction(this.lastLevel);
			if(art != null){
				printArticleInstructions();
				String io = scanner.nextLine();
				while(!io.equals("exit")){
					handleArticleInstructions(io, art);
					printArticleInstructions();
					io = scanner.nextLine();
				}
				this.editor.add(art);
				System.out.println("Article added to the editor.");
			}
			return;
		}else if(instruction.equals("find content")){
			findContentInstruction();
		}else if(instruction.equals("import json")){
			importJsonInstruction();
		}else if(instruction.equals("output html")){
			outputHtmlInstruction();
		}else{
			System.out.println("Invalid Instruction");
		}

	}

	private Document addTitleInstruction() {
		System.out.println("Please enter the information of title:");
		System.out.print("Text of title: ");
		String text = "";
		text+=scanner.nextLine();
		System.out.print("Size of title: ");
		String tmp = "";
		tmp += scanner.nextLine();
		if(tmp.equals("1") ||tmp.equals("2")||tmp.equals("3")||tmp.equals("4")||tmp.equals("5")||tmp.equals("6")){
			int size = Integer.parseInt(tmp);
			return new Title(text,size);
		}else{
			System.out.println("Invalid Input: The size should be in range 1 to 6");
			return null;
		}
		
	}

	private Document addParagraphInstruction() {
		System.out.println("Please enter the information of paragraph:");
		System.out.print("Text of paragraph: ");
		String text = scanner.nextLine();
		return new Paragraph(text);
	}

	private Document addArticleInstruction(int lastLevel) {
		int level = 0;
		System.out.println("Please enter the information of article:");
		System.out.print("Topic of article: ");
		String topic = scanner.nextLine();
		System.out.print("Level of article: ");
		String tmp = scanner.nextLine();
		try {
			level = Integer.parseInt(tmp);
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid Input: The level should be positive or higher than the level of the current article");
			return null;
		}
		if(level > lastLevel){
			return new Article(topic,level);
		}else{
			System.out.println("Invalid Input: The level should be positive or higher than the level of the current article");
			return null;	
		}
	}

	private void printArticleInstructions() {
		System.out.println("Please enter the following instruction to edit the article: ");
		System.out.println("1. 'add title': to add a title to the article");
		System.out.println("2. 'add paragraph': to add a paragraph to the article");
		System.out.println("3. 'add article': to add an article to the article");
		System.out.println("4. 'exit': to exit the process");
	}

	private void handleArticleInstructions(String instruction, Document article) {
		if(instruction.equals("add title")){
			Document temp = addTitleInstruction();
			if(temp != null){
				article.add(temp);
				System.out.println("Title added to the article.");
			}
		}else if(instruction.equals("add paragraph")){
			article.add(addParagraphInstruction());
			System.out.println("Paragraph added to the article.");
		}else if (instruction.equals("add article")){
			Document art = addArticleInstruction(article.getLevel());
			if(art != null){
				printArticleInstructions();
				String io = scanner.nextLine();
				while(!io.equals("exit")){
					handleArticleInstructions(io, art);
					printArticleInstructions();
					io = scanner.nextLine();
				}
				article.add(art);
				System.out.println("Article added to the article.");
			}			
		}else{
			System.out.println("Invalid Instruction");
		}
	}

	private void findContentInstruction() {
		System.out.print("Enter the word you want to find: ");
		String target = scanner.nextLine();
		this.editor.findContent(target);
	}
	private void importJsonInstruction() { 
		System.out.print("Enter the file path you want to import: ");
		String path = scanner.nextLine();
		this.editor.importDocumentFromJsonFile(path);
		System.out.println("Documents were imported.");
	}
	private void outputHtmlInstruction() {
		System.out.print("Enter the file path you want to output: ");
		String path = scanner.nextLine();
		this.editor.exportDocumentAsHtmlFile(path);
		System.out.println("File saved.");
		
	}
}