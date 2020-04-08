package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import core.doc.IParser;
import utilities.Logging;
import utilities.ParserPatterns;

public class Parser extends ParserPatterns implements IParser{
	
	private String component;
	private File html;
	private SymbolTable symbolTable;
	private ParseTree tree;
	private Logging logger;
	
	private String text;
	private int start;
	private int end;
	
	@Deprecated
	public Parser(String comp, File page, Logging logs) throws IOException{
		component = comp;
		html = page;
		symbolTable = new SymbolTable();
		logger = logs;
		
		loadText();
	}
	
	public Parser(Logging logs){
		symbolTable = new SymbolTable();
		logger = logs;
	}
	
	public ParseTree getTree(){
		return tree;
	}
	
	public void loadText(String comp, File file) throws IOException{
		component = comp;
		html = file;
		loadText();
	}
	
	private void loadText() throws IOException{
		FileInputStream fsi = new FileInputStream(html);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fsi));
		while(reader.ready()){
			text += reader.readLine();
		}
		reader.close();
	}
	
	private void findMethodSummary(){
		int index = text.indexOf(METHOD_SUMMARY_MARKER);
		start = index;
	}
	
	public int getStartPosition(){
		return start;
	}
	
	private void findEndMethodSummary(){
		int index = text.indexOf(SUMMARY_END_MARKER);
		logger.printLine("end: " + index);
		end = index;
	}
	
	public int getEndMethodSummary(){
		return end;
	}
	
	public int findMethod(int startMethod){
		int startIndex = text.indexOf(START_MARKER, startMethod);
		startIndex += START_MARKER.length();
		if(startIndex < startMethod || startIndex > end){
			return end + 1;
		}
		logger.printLine("start index: " + startIndex);
		
		int startName = text.indexOf(START_NAME_MARKER, startIndex);
		startName += START_NAME_MARKER.length();
		logger.printLine("startName: " + startName);
		
		int endName = text.indexOf(END_NAME_MARKER, startName);
		logger.printLine("endName: " + endName);
		String name = text.substring(startName, endName);
		logger.printLine("name: " + name);
		
		int startDescription = text.indexOf(START_DESCRIPTION_MARKER, endName);
		startDescription += START_DESCRIPTION_MARKER.length();
		logger.printLine("startDescription: " + startDescription);
		
		int endDescription = text.indexOf(END_DESCRIPTION_MARKER, startDescription);
		logger.printLine("endDescription: " + endDescription);
		String description = text.substring(startDescription, endDescription);
		
		logger.printLine("description: " + description);
		
		int methodEnd = text.indexOf(METHOD_END_MARKER, endDescription);
		logger.printLine("method end: " + methodEnd);
		
		symbolTable.putMethod(component, name, description);
		
		return methodEnd;
	}
	
	public int findAllMethods(){
		findMethodSummary();
		findEndMethodSummary();
		
		int current = findMethod(start);
		while(!isFinished(current)){
			current = findMethod(current);
		}
		
		return current;
	}
	
	public boolean isFinished(int current){
		if(current < end){
			return false;
		}else{
			return true;
		}
	}
	
	public void update(){
		tree = new ParseTree(symbolTable, logger);
		tree.buildTree();
		tree.compile();
	}
	
	public void printTree(){
		tree.print();
	}
	
	public SymbolTable getSymbolTable(){
		return symbolTable;
	}
}
