package core.doc;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface IIO {
	
	/**
	 * Loops through all project root folders, passing the directory
	 * file along for further inspection.  Initial loop makes the
	 * distinction for root "components".
	 * 
	 * @param String path
	 */
	public void getComponentList(String path);
	
	/**
	 * Returns a list of just the component directory folders.
	 * 
	 * @return File[] rootFiles
	 */
	public File[] getRootFiles();
	
	/**
	 * Recursively finds all relevant HTML files to be associated
	 * with the given root component.  (Currently) private filters
	 * remove index files, formatting, and other unnessecary HTML.
	 * 
	 * @param String path
	 */
	public void mapDirectory(String path);
	
	/**
	 * Returns a map of all relevant HTML files associated with their
	 * root components.
	 * 
	 * @return ConcurrentHashMap<String, ArrayList<String>> directory
	 */
	public ConcurrentHashMap<String, ArrayList<String>> getDirectory();
	
	/**
	 * Formats all component and associated HTML data into a tree
	 * with a depth of 2, that is printed to file.
	 */
	public void printDirectoryMap();

}
