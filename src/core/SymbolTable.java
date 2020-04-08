package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import core.doc.ISymbolTable;

public class SymbolTable implements ISymbolTable{
	
	private ConcurrentHashMap<String, ArrayList<String>> components;
	private ConcurrentHashMap<String, String> methods;
	
	public SymbolTable(){
		components = new ConcurrentHashMap<String, ArrayList<String>>();
		methods = new ConcurrentHashMap<String, String>();
	}
	
	public void putMethod(String component, String method, String description){
		System.out.println("Adding method: " + component + method + description);
		
		ArrayList<String> componentList = components.get(component);
		if(componentList == null){
			componentList = new ArrayList<String>();
		}
		componentList.add(method);
		components.put(component, componentList);
		
		methods.put(method, description);
	}
	
	public ArrayList<String> sortComponents(){
		Iterator<String> list = components.keySet().iterator();
		ArrayList<String> componentList = new ArrayList<String>();
		while(list.hasNext()){
			componentList.add(list.next());
		}
		
		for(int x = 0; x < componentList.size(); x++){
			for(int y = 0; y < componentList.size(); y++){
				String first = componentList.get(x);
				String second = componentList.get(y);
				if(first.compareTo(second) < 0){
					componentList.set(y, first);
					componentList.set(x, second);
				}
			}
		}
		
		return componentList;
	}
	
	public ArrayList<String> getMethods(String component){
		return components.get(component);		
	}
	
	public String getDescription(String method){
		return methods.get(method);
	}
}
