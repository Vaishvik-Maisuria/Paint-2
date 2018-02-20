

import javax.swing.*;  
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

class PaintPanel extends JPanel {
	private static final long serialVersionUID = 3277442988868869424L;
	private ArrayList<PaintCommand> commands = new ArrayList<PaintCommand>();
	private PaintSaveFileParser parser = new PaintSaveFileParser();
	private Color lastColor; 
	
	public PaintPanel(){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300,300));
	}
	
	public ArrayList<PaintCommand> getShapes(){
		return commands;
	}
	
	/**
	 * Removes the last shape within shapes
	 */
	public void removeDrawingCommand() {
		this.commands.remove(this.commands.size() - 1);
	}
	
	
	/**
	 * Change the last color used for the shapes
	 * @param color	New last color
	 */
	public void changeLastColor(Color color) {
		this.lastColor = (color); 
	
	}
	
	public void setCommands(ArrayList<PaintCommand> commands){
		this.commands=commands;
	}
	public void reset(){
		this.commands.clear();
		this.repaint();
	}
	
	public void addCommand(PaintCommand command){
		this.commands.add(command);
	}
	public void save(PrintWriter writer){
		/**
		Need to start file with title to signify file initializing.
		Need to access the command ArrayList, look at commands from start to end, and print shapes and their properties.
		Need to end file with text to signify file ending.
		*/
//		writer.println(java.time.LocalDateTime.now()); // Testing
		
		
		writer.println("Paint Save File Version 1.0");
		
		for (PaintCommand printCommand : this.commands) {
			if (printCommand.toString().contains("CircleCommand")) {
				writer.println("Circle");
				
				ArrayList<String> shapeValues = new ArrayList<String> (Arrays.asList(printCommand.shapeCommands().split(",")));
				writer.println("    color:" + shapeValues.get(0) + "," + shapeValues.get(1) + "," + shapeValues.get(2));
				writer.println("    filled:" + shapeValues.get(6));
				writer.println("    center:(" + shapeValues.get(3) + "," + shapeValues.get(4) + ")");
				writer.println("    radius:" + shapeValues.get(5));
				writer.println("End Circle");
				System.out.println(printCommand);
				
			} else if (printCommand.toString().contains("SquiggleCommand")) {
				writer.println("Squiggle");
				
				ArrayList<String> shapeValues = new ArrayList<String> (Arrays.asList(printCommand.shapeCommands().split(",")));
				writer.println("    color:" + shapeValues.get(0) + "," + shapeValues.get(1) + "," + shapeValues.get(2));
				writer.println("    filled:true");
				writer.println("    points");
				
				for (int i=4;i<shapeValues.size()-1;i++) {
					String p1 = shapeValues.get(i);
					String p2 = shapeValues.get(i+1);
					writer.println("\tpoint:(" + p1 + "," + p2 + ")");
					i++;
				}
				writer.println("    end points");
				writer.println("End Squiggle");
//				System.out.println(printCommand);
				
			} else if (printCommand.toString().contains("RectangleCommand")) {
				writer.println("Rectangle");
				
				ArrayList<String> shapeValues = new ArrayList<String> (Arrays.asList(printCommand.shapeCommands().split(",")));
				writer.println("    color:" + shapeValues.get(0) + "," + shapeValues.get(1) + "," + shapeValues.get(2));
				writer.println("    filled:" + shapeValues.get(7));
				writer.println("    p1:(" + shapeValues.get(3) + "," + shapeValues.get(4) + ")");
				writer.println("    p2:(" + shapeValues.get(5) + "," + shapeValues.get(6) + ")");
				writer.println("End Rectangle");
//				System.out.println(printCommand);
			}
		}
		
		writer.println("End Paint Save File");
		writer.close();
	}
	public void open(FileReader  reader){
		BufferedReader in = new BufferedReader(reader);
		boolean retVal = parser.parse(in);
		//System.out.println(parser.parse(in) + "cool ass please work");
		this.reset();
		if (retVal) {
			this.setCommands(parser.getCommands());
		}
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g;		
		for(PaintCommand c: this.commands){
			c.execute(g2d);
		}
		g2d.dispose();
	}
}
