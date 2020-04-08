package core.doc;

import core.ParseTree;
import core.SymbolTable;

public interface IParser {
	
	/**
	 * Returns entire parse tree object.
	 * 
	 * @return ParseTree tree
	 */
	public ParseTree getTree();
	
	/**
	 * Returns the index of the beginning of the Method Summary
	 * block of html for the current current html file.
	 * 
	 * @return int start
	 */
	public int getStartPosition();
	
	/**
	 * Finds the start index for the next method summary from
	 * the given start index.  Pattern defined in ParserPatterns.
	 * 
	 * @param int startMethod
	 * @return int position
	 */
	public int findMethod(int startMethod);
	
	/**
	 * Loops through all methods in method summary, determining
	 * method name, description, and entering all known data
	 * into SymbolTable.  Returns position pointer finished at.
	 * 
	 * @return int current
	 */
	public int findAllMethods();
	
	/**
	 * Checks to make sure pointer has not gone outside of
	 * "Method Summary" html block.
	 * 
	 * @return boolean finished
	 */
	public boolean isFinished(int current);
	
	/**
	 * Uses SymbolTable data to construct a ParseTree object and
	 * gives "values" to all ParseTree "leaves".
	 */
	public void update();
	
	/**
	 * Traverses ParseTree object and writes all relevant data
	 * to file.
	 */
	public void printTree();
	
	/**
	 * 
	 * 
	 * @return SymbolTable symbolTable
	 */
	public SymbolTable getSymbolTable();

}
