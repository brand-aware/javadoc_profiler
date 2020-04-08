package utilities.doc;

import utilities.Logging;
import utilities.MethodNode;
import utilities.NodeData;

public interface IComponentNode {
	
	/**
	 * Adds a MethodNode "branch" to the current
	 * component tree "root".
	 * 
	 * @param MethodNode methodNode
	 */
	public void addNode(MethodNode methodNode);
	
	/**
	 * Returns the name of the current component "branch".
	 * 
	 * @return String name
	 */
	public String getName();
	
	/**
	 * Returns the current count of methods documented
	 * via javadoc for the given component.
	 * 
	 * @return int total
	 */
	public int getTotal();
	
	/**
	 * Traverses all leaves of the current branch to determine
	 * the total score value used with completion percentage.
	 * 
	 */
	public void calculateScore();
	
	/**
	 * Traverses all leaves of branch and prints data to file.
	 * 
	 * @param Logging logger
	 */
	public void printComponentNode(Logging logger);
	
	/**
	 * Compiles all component totals into one object.
	 * 
	 * @return NodeData data
	 */
	public NodeData getAllData();

}
