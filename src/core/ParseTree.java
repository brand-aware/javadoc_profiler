package core;

import java.util.ArrayList;

import core.doc.IParseTree;
import utilities.ComponentNode;
import utilities.Logging;
import utilities.MethodNode;
import utilities.NodeData;

public class ParseTree implements IParseTree {
	
	private SymbolTable data;
	private ArrayList<ComponentNode> tree;
	private Logging logger;
	
	public ParseTree(SymbolTable table, Logging logs){
		data = table;
		tree = new ArrayList<ComponentNode>();
		logger = logs;
	}
	
	public void buildTree(){
		logger.printLine("\n\nBUILDING PARSE TREE\n\n");
		ArrayList<String> componentList = data.sortComponents();
		for(int x = 0; x < componentList.size(); x++){
			String component = componentList.get(x);
			logger.printLine("Building component: " + component);
			ComponentNode node = new ComponentNode(component);
			ArrayList<String> methods = data.getMethods(component);
			for(int y = 0; y < methods.size(); y++){
				String method = methods.get(y);
				logger.printLine("ADDING METHOD: " + method);
				String description = data.getDescription(method);
				MethodNode methodNode = new MethodNode(
						component, method, description);
				node.addNode(methodNode);
			}
			tree.add(node);
		}
	}
	
	public void compile(){
		for(int x = 0; x < tree.size(); x++){
			ComponentNode node = tree.get(x);
			node.calculateScore();
		}
	}
	
	public ArrayList<NodeData> getTotals(){
		ArrayList<NodeData> nodeTotals = new ArrayList<NodeData>();
		for(int x = 0; x < tree.size(); x++){
			ComponentNode branch = tree.get(x);
			nodeTotals.add(branch.getAllData());
		}
		
		return nodeTotals;
	}
	
	public ArrayList<ComponentNode> getRoot(){
		return tree;
	}
	
	public void print(){
		logger.printLine("\nPRINTING PARSE TREE\n\n");
		int size = tree.size();
		logger.printLine("tree size: " + size);
		for(int x = 0; x < size; x++){
			ComponentNode node = tree.get(x);
			node.printComponentNode(logger);
		}
	}
}