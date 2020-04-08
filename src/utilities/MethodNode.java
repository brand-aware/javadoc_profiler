package utilities;

import utilities.doc.IMethodNode;

public class MethodNode implements IMethodNode{
	
	private String component;
	private String name;
	private String description;
	
	public MethodNode(String comp, String nm, String dscp){
		component = comp;
		name = nm;
		description = dscp;
	}

	public String getComponent(){
		return component;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	
	public boolean getScore(){
		if(description == null){
			return false;
		}
		
		String score = description;
		score.replaceAll(" ", "");
		if(score.compareTo("") == 0){
			return false;
		}else{
			return true;
		}
	}
}
