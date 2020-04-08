package utilities;

import utilities.doc.INodeData;

public class NodeData implements INodeData {
	
	private String component;
	private int count;
	private int total;

	public void setComponent(String comp){
		component = comp;
	}
	public void setCount(int cnt){
		count = cnt;
	}
	public void setTotal(int ttl){
		total = ttl;
	}
	
	public String getComponent(){
		return component;
	}
	public int getCount(){
		return count;
	}
	public int getTotal(){
		return total;
	}
	
}
