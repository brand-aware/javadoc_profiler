package core.doc;

import java.util.ArrayList;

public interface ISymbolTable {
	
	/**
	 * Associates method with given component and associates 
	 * description with given methods.  Components have many
	 * methods.  Methods have just one description.
	 * 
	 * @param String component
	 * @param String method
	 * @param String description
	 */
	public void putMethod(String component, String method, String description);
	
	/**
	 * Gets component map keys and returns then in the form of
	 * a sorted list which can they be used to lookup further
	 * information in a linear manner.
	 * 
	 * @return ArrayList<String> componentList
	 */
	public ArrayList<String> sortComponents();
	
	/**
	 * Returns all methods associated with given component, which
	 * can be used to further lookup method descriptions.
	 * 
	 * @param String component
	 * @return ArrayList<String> componentMethods
	 */
	public ArrayList<String> getMethods(String component);
	
	/**
	 * Returns the description for the given method, typically
	 * a key indicator of a completed javadoc entry.
	 * 
	 * @param String method
	 * @return String description
	 */
	public String getDescription(String method);

}
