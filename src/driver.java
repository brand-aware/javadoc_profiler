import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import core.Display;
import core.IO;
import core.ParseTree;
import core.Parser;
import utilities.Logging;

public class driver {
	
	private static String[] arguments;

	public static void main(String[] args) throws IOException {
		arguments = args;
						
		if(arguments.length != 1){
			System.out.println("Please launch program with syntax: \n\n"
					+ "java driver <path to project root javadoc folder>");
			System.exit(0);
		}
		String path = arguments[0];
		Logging logs = new Logging(true);
		IO io = new IO(logs);
		
		io.getComponentList(path);
		io.printDirectoryMap();
		
		Parser parser;
		try {
			
			/*parser = new Parser("core", new File("C:\\Users\\mike802\\My Code"
					+ "\\java\\eclipse\\portfolio\\javadoc_profiler\\data"
					+ "\\card_game\\core\\FlipActions.html"),
					logs);*/
			
			parser = new Parser(logs);
			
			// loop over all components AND files in respective components
			ConcurrentHashMap<String, ArrayList<String>> directory = io.getDirectory();
			Iterator<String> keys = directory.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				ArrayList<String> files = directory.get(key);
				for(int x = 0; x < files.size(); x++){
					String file = files.get(x);
					parser.loadText(key, new File(file));
					parser.findAllMethods();
				}
			}
			
			//parser.findAllMethods();
			parser.update();
			parser.printTree();
			ParseTree tree = parser.getTree();
			
			//Format results
			Display display = new Display(tree);
			display.init();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			logs.close();
		}
	}

	
}
