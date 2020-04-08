package core;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import core.doc.IIO;
import utilities.Logging;

public class IO implements IIO {
	
	private ConcurrentHashMap<String, ArrayList<String>> directory;
	private String component;
	private Logging logger;
	private File[] rootFiles;
	
	public IO(Logging logs){
		directory = new ConcurrentHashMap<String, ArrayList<String>>();
		logger = logs;
	}
	
	public void getComponentList(String path){
		File componentRoot = new File(path);
		rootFiles = componentRoot.listFiles();
		
		for(int x = 0; x < rootFiles.length; x++){
			File current = rootFiles[x];
			if(current.isDirectory()){
				component = current.getAbsolutePath();
				logger.printLine(component);
				mapDirectory(component);
			}
		}
		
	}
	
	public File[] getRootFiles(){
		return rootFiles;
	}
	
	public void mapDirectory(String path){
		File root = new File(path);
		if(root.exists()){
			String[] rootFiles = root.list();
			int size = rootFiles.length;
			for(int x = 0; x < size; x++){
				String newPath = rootFiles[x];
				logger.printLine(newPath);
				if(isDirectory(newPath)){
					mapDirectory(newPath);
				}else{
					if(!skipped(newPath)){
						newPath = root.getAbsolutePath() + File.separator + newPath;
						System.out.println(newPath);
						addToComponent(newPath);
					}
				}
			}
			
		}
	}
	
	private void addToComponent(String path){
		ArrayList<String> list = directory.get(component);
		if(list == null){
			list = new ArrayList<String>();
		}
		list.add(path);
		directory.put(component, list);
	}
	
	private boolean isDirectory(String path){
		File file = new File(path);
		if(file.isDirectory()){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean skipped(String path){
		if(!path.contains(".html")){
			return true;
		}else if(path.contains("package-")){
			return true;
		}
		return false;
	}
	
	public ConcurrentHashMap<String, ArrayList<String>> getDirectory(){
		return directory;
	}
	
	public void printDirectoryMap(){
		
		logger.printLine("\n\n##PRINTING MAP####\n\n");
		Iterator<String> keySet = directory.keySet().iterator();			
		ArrayList<String> keys = new ArrayList<String>();
		String output = "";
		while(keySet.hasNext()){
			keys.add(keySet.next());
		}
		
		for(int x = 0; x < keys.size(); x++){
			for(int y = 0; y < keys.size(); y++){
				String key1 = keys.get(x);
				String key2 = keys.get(y);
				if(key1.compareTo(key2) < 0){
					keys.set(x, key2);
					keys.set(y, key1);
				}
			}
		}
		
		
		int size = keys.size();
		for(int z = 0; z < size; z++){
			String key = keys.get(z);
			//logger.printLine(": " + key + ":: \n");
			output += (z + 1) + ": " + key.replace("C:\\Users\\miked\\My Code\\java\\eclipse\\javadocprofiler\\", "") + ":: \n";
			ArrayList<String> compList = directory.get(key);
			for(int z1 = 0; z1 < compList.size(); z1++){
				//logger.printLine("--- " + compList.get(z1) + "\n");
				output += "--- " + compList.get(z1) + "\n";
			}
			output += "\n";
		}
		logger.printLine(output);
	}
}