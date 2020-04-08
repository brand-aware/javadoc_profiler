package core.doc;

import java.util.ArrayList;

import utilities.ComponentNode;
import utilities.NodeData;

public interface IParseTree {
	
	/**
	 * Builds a set-depth tree that functions without a single
	 * proper root element.  All branches contain leaves, leaves
	 * contain data.  Traversal is linear from the ParseTree
	 * object to the branches and their data.
	 * 
	 * ComponentNode objects are used at the "branches".  The
	 * root ParseTree object contains all ComponentNodes in a
	 * linear and sortable list.  ComponentNodes do not contain
	 * a "back" link as all of their data can be compiled and
	 * retrieved from the ParseTree object.
	 * 
	 * MethodNode objects are used as the branch "leaves".  They
	 * contain all information required to determine a branch
	 * score.  Output does not currently need to know more than
	 * this about a particular branch to generate a functional
	 * output response.
	 */
	public void buildTree();
	
	/**
	 * Traverses ParseTree objects and calls each branch's method
	 * to generate it's respective branch score.  This may only be
	 * called once the ParseTree has been built.
	 */
	public void compile();
	
	/**
	 * After the ParseTree object has been built and compiled,
	 * this will traverse the ParseTree again and retrieve all
	 * useable output data in a sortable list.
	 * 
	 * @return ArrayList<NodeData> data
	 */
	public ArrayList<NodeData> getTotals();
	
	/**
	 * All tree branches are returned in a linear object as the
	 * ParseTree is of a set depth of 2.  The current structure
	 * of the data does not require a greater complexity than 2.
	 * 
	 * @return ArrayList<ComponentNode> tree
	 */
	public ArrayList<ComponentNode> getRoot();
	
	/**
	 * Formats all ParseTree data to be printed to file in a
	 * human-readable form. 
	 */
	public void print();

}
