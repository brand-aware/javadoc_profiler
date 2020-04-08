package utilities.doc;

public interface INodeData {
	
	/**
	 * Sets the name of the component this data set is describing.
	 * 
	 * @param String comp
	 */
	public void setComponent(String comp);
	
	/**
	 * Sets the count for the total number of methods found in
	 * the component this data set is describing.
	 * 
	 * @param int cnt
	 */
	public void setCount(int cnt);
	
	/**
	 * Sets the total number of methods that have a completed
	 * javadoc entry associated with them.
	 * 
	 * @param int ttl
	 */
	public void setTotal(int ttl);
	
	/**
	 * Gets the name of the component this data set is describing.
	 * 
	 * @return String component
	 */
	public String getComponent();
	
	/**
	 * Gets the count for the total number of methods found in
	 * the component this data set is describing.
	 * 
	 * @return String count
	 */
	public int getCount();
	
	/**
	 * Returns the total number of methods that have a completed
	 * javadoc entry associated with them.
	 * 
	 * @return String total
	 */
	public int getTotal();

}
