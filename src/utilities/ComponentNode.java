package utilities;

import java.util.ArrayList;

import utilities.doc.IComponentNode;

public class ComponentNode implements IComponentNode{
	
	private String name;
	private ArrayList<MethodNode> nodes;
	private int total = -1;
	
	public ComponentNode(String component){
		name = component;
		nodes = new ArrayList<MethodNode>();
	}
	
	public void addNode(MethodNode methodNode){
		nodes.add(methodNode);
	}
	
	public String getName(){
		return name;
	}
	public int getTotal(){
		return total;
	}
	
	public void calculateScore(){
		total = 0;
		for(int x = 0; x < nodes.size(); x++){
			MethodNode node = nodes.get(x);
			boolean score = node.getScore();
			if(score){
				total++;
			}
		}
	}
	
	public void printComponentNode(Logging logger){
		logger.printLine("Printing Node: " + name + " total :" + total);
	}
	
	public NodeData getAllData(){
		NodeData data = new NodeData();
		data.setComponent(name);
		data.setCount(total);
		data.setTotal(nodes.size());
		
		return data;
	}
}
