package core;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import utilities.NodeData;

public class Display {
	
	private ArrayList<JProgressBar> progressList;
	private ArrayList<JPanel> panelList;
	private ArrayList<JLabel> labelList;
	private ArrayList<JLabel> ratioList;
	
	private ParseTree tree;
	
	public Display(ParseTree parseTree){
		tree = parseTree;
		
		progressList = new ArrayList<JProgressBar>();
		panelList = new ArrayList<JPanel>();
		labelList = new ArrayList<JLabel>();
		ratioList = new ArrayList<JLabel>();
	}
		
	public void init(){		
		JFrame frame = new JFrame("javadoc_profiler");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<NodeData> nodeTotals = tree.getTotals();
		
		for(int x = 0; x < nodeTotals.size(); x++){
			NodeData data = nodeTotals.get(x);
			
			JProgressBar progress = new JProgressBar(0, data.getTotal());
			progress.setValue(data.getCount());
			progress.setStringPainted(true);
			progressList.add(progress);
			
			String name = data.getComponent();
			name = formatName(name);
			JLabel title = new JLabel(name);
			title.setPreferredSize(new Dimension(250, 30));
			labelList.add(title);
			
			JLabel ratio = new JLabel(data.getCount() + "//" + data.getTotal());
			ratio.setPreferredSize(new Dimension(100, 30));
			ratioList.add(ratio);
			
			JPanel panel = new JPanel();
			panel.add(Box.createGlue());
			panel.add(title);
			panel.add(progress);
			panel.add(ratio);	
			panelList.add(panel);
		}
		
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalGlue());
		
		for(int y = 0; y < panelList.size(); y++){
			box.add(panelList.get(y));
		}
		
		frame.add(box);
		frame.pack();
		frame.setVisible(true);
	}
	
	private String formatName(String name){
		int end = name.lastIndexOf(File.separator);
		if(end > 0){
			end++;
			name = name.substring(end, name.length());
		}
		return name;
	}
}
