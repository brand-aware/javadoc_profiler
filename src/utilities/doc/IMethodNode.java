package utilities.doc;

public interface IMethodNode {
	
	/**
	 * Returns the component name that the given method is a
	 * member of.
	 * 
	 * @return String component
	 */
	public String getComponent();
	
	/**
	 * Returns the name of the method for the current leaf on
	 * the ParseTree object.
	 * 
	 * @return String name
	 */
	public String getName();
	
	/**
	 * Returns the javadoc description for the current method.
	 * Typically used as an indicator of a completed javadoc
	 * entry.
	 * 
	 * @return String description
	 */
	public String getDescription();
	
	/**
	 * Returns true if the given javadoc entry meets criteria
	 * to be considered a "completed" entry.
	 * 
	 * @return boolean complete
	 */
	public boolean getScore();
	
}
