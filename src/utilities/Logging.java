package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class Logging {
	
	BufferedWriter writer;
	private boolean debug;
	
	public Logging(boolean logging) throws FileNotFoundException{
		
		// Create unique id for log
		String date = "";
		Calendar calendar = Calendar.getInstance();
		date += calendar.get(Calendar.YEAR);
		date += calendar.get(Calendar.MONTH);
		date += calendar.get(Calendar.DATE);
		date += calendar.get(Calendar.HOUR);
		date += calendar.get(Calendar.MINUTE);
		date += calendar.get(Calendar.SECOND);
		
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("log_" + date + ".txt"))));
		
		debug = logging;
	}
	
	public void print(String entry) {
		try {
			if(debug){
				writer.write(entry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void printLine(String entry) {
		try {
			if(debug){
				writer.write(entry + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close(){
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
