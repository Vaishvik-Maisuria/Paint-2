

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about
 * its effort to parse a file. After a successful parse, an instance
 * will have an ArrayList of PaintCommand suitable for rendering.
 * If there is an error in the parse, the instance stores information
 * about the error. For more on the format of Version 1.0 of the paint 
 * save file format, see the associated documentation.
 * 
 * @author 
 *
 */
public class PaintSaveFileParser {
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage =""; // error encountered during parse
	private ArrayList<PaintCommand> commands; // created as a result of the parse
	
	/**
	 * Below are Patterns used in parsing 
	 */
	private Pattern pFileStart=Pattern.compile("^PaintSaveFileVersion1.0$");
	private Pattern pFileEnd=Pattern.compile("^EndPaintSaveFile$");

	private Pattern pCircleStart=Pattern.compile("^Circle$");
	private Pattern pColor=Pattern.compile("^\\s*color:((\\d{1,3}),(\\d{1,3}),(\\d{1,3}))$");
	private Pattern pFilled=Pattern.compile("^\\s*filled:(true|false)$");
	private Pattern pCenter=Pattern.compile("^\\s*center:\\(((\\d{1,3}),(\\d{1,3}))\\)$");
	private Pattern pRadius=Pattern.compile("^\\s*radius:(\\d{1,3})$");
	private Pattern pCircleEnd=Pattern.compile("^EndCircle$");
	
	private Pattern pRectangleStart=Pattern.compile("^Rectangle$");
	private Pattern pPoint1=Pattern.compile("^\\s*p1:\\(((\\d{1,3}),(\\d{1,3}))\\)$");
	private Pattern pPoint2=Pattern.compile("^\\s*p2:\\(((\\d{1,3}),(\\d{1,3}))\\)$");
	private Pattern pRectangleEnd=Pattern.compile("^EndRectangle$");
	
	private Pattern pSquiggleStart=Pattern.compile("^Squiggle$");
	private Pattern pSquiggleEnd=Pattern.compile("^EndSquiggle$");
	private Pattern pPointStart=Pattern.compile("^\\s*points$");
	private Pattern pPoints=Pattern.compile("^\\s*point:\\(((\\d{1,3}),(\\d{1,3}))\\)$");
	private Pattern pPointEnd=Pattern.compile("^\\s*end\\spoints$");
	private Pattern pRandom=Pattern.compile("^MoreExtraContent$");
	
	// ADD MORE!!	
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg){
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
	}
	/**
	 * 
	 * @return the PaintCommands resulting from the parse
	 */
	public ArrayList<PaintCommand> getCommands(){
		return this.commands;
	}
	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	/**
	 * Parse the inputStream as a Paint Save File Format file.
	 * The result of the parse is stored as an ArrayList of Paint command.
	 * If the parse was not successful, this.errorMessage is appropriately
	 * set, with a useful error message.
	 * 
	 * @param inputStream the open file to parse
	 * @return whether the complete file was successfully parsed
	 */
	public boolean parse(BufferedReader inputStream) {
		this.commands = new ArrayList<PaintCommand>();
		this.errorMessage="";
		
		// During the parse, we will be building one of the 
		// following shapes. As we parse the file, we modify 
		// the appropriate shape.
		
		Circle circle = null; 
		Rectangle rectangle = null;
		Squiggle squiggle = null;
	
		try {	
			int state=0,color,points; Matcher m; String l, filled;
			
			this.lineNumber=0;
			while ((l = inputStream.readLine()) != null) {
				l = l.replaceAll("\\s+","");
				this.lineNumber++;
				System.out.println(lineNumber+" "+l+" "+state);
				
				switch(state){
					case 0:
						m=pFileStart.matcher(l);
						if(m.matches()){
							state=1;
							break;
						}
						error("Expected Start of Paint Save File");
						return false;
					case 1: // Looking for the start of a new object or end of the save file
						m=pCircleStart.matcher(l);
						if(m.matches()){
							circle = new Circle();
							// ADD CODE!!!
							state=2; 
							break;
						}
						m=pRectangleStart.matcher(l);
						if(m.matches()){
							rectangle = new Rectangle();
							// ADD CODE!!!
							state=7; 
							break;
						}
						m=pSquiggleStart.matcher(l);
						if(m.matches()){
							squiggle = new Squiggle();
							// ADD CODE!!!
							state= 12; 
							break;
						} else {
							state = 17;
							break;
						}
				case 2:
						m=pColor.matcher(l);
						if(m.matches()){
							int color1=Integer.parseInt(m.group(2));
							int color2=Integer.parseInt(m.group(3));
							int color3=Integer.parseInt(m.group(4));
							
							if ((-1 < color1) && (color1 < 256) && (-1 < color2) && (color2 < 256) && (-1 < color3) && (color3 < 256)) {
								circle.setColor(new Color(color1,color2,color3));
							} else {
								error("Error wrong color input");
								return false;
							}
							
							state= 3;
						}else {
							error("Expecting color line");
							return false;
						}						
						break;
						
					case 3:
						m=pFilled.matcher(l);
						if(m.matches()){	
							filled = m.group(1);
							
							if (filled.equals("true")){
								circle.setFill(true);	
							}else {
								circle.setFill(false);
							}
							state = 4;
						}
						else {
							error("Expecting filled line");
							return false;
						}
						break;
					case 4:
						m=pCenter.matcher(l);
						if(m.matches()){
							int points1=Integer.parseInt(m.group(2));
							int points2=Integer.parseInt(m.group(3));
							circle.setCentre(new Point(points1, points2));
							state = 5;
						}else {
							error("Expecting Center line");
							return false;
						}
						break;
					case 5:
						m=pRadius.matcher(l);
						if(m.matches()){
							int radius=Integer.parseInt(m.group(1));
							circle.setRadius(radius);
							state = 6;
						}else {
							error("Expecting Radius line");
							return false;
						}
						break;
					case 6:
						m=pCircleEnd.matcher(l);
						if(m.matches()){
							CircleCommand circleCommand = new CircleCommand(circle);
							this.commands.add(circleCommand);
							state = 1;
							
						}else {
							error("Expecting End Circle line");
							return false;
						}
						break;
					case 7:
						m=pColor.matcher(l);
						if(m.matches()){
							int color1=Integer.parseInt(m.group(2));
							int color2=Integer.parseInt(m.group(3));
							int color3=Integer.parseInt(m.group(4));
							rectangle.setColor(new Color(color1,color2,color3));
							state = 8;
						}else {
							error("Expecting color line");
							return false;
						}						
						break;
						
					case 8:
						m=pFilled.matcher(l);
						if(m.matches()){
							filled = m.group(1);
							if (filled.equals("true")){
								rectangle.setFill(true);	
							}else {
								rectangle.setFill(false);
							}
							state = 9;
						}
						else {
							error("Expecting filled line");
							return false;
						}
						break;
					
					case 9:
						m=pPoint1.matcher(l);
						if(m.matches()){
							int points1=Integer.parseInt(m.group(2));
							int points2=Integer.parseInt(m.group(3));
							rectangle.setP1(new Point(points1, points2));
							state = 10;
						}else {
							error("Expecting first point line");
							return false;
						}
						break;
					case 10:
						m=pPoint2.matcher(l);
						if(m.matches()){
							int points1=Integer.parseInt(m.group(2));
							int points2=Integer.parseInt(m.group(3));
							rectangle.setP2(new Point(points1, points2));
							state = 11;
						}else {
							error("Expecting second point line");
							return false;
						}
						break;
					
					case 11:
						m=pRectangleEnd.matcher(l);
						if(m.matches()){
							RectangleCommand rectangleCommand = new RectangleCommand(rectangle);
							this.commands.add(rectangleCommand);
							state = 1;
						}else {
							error("Expecting End Rectangle line");
							return false;
						}
						break;
					case 12:
						m=pColor.matcher(l);
						if(m.matches()){
							int color1=Integer.parseInt(m.group(2));
							int color2=Integer.parseInt(m.group(3));
							int color3=Integer.parseInt(m.group(4));
							squiggle.setColor(new Color(color1,color2,color3));
							state= 13;
						}else {
							error("Expecting color line");
							return false;
						}						
						break;
					case 13:
						m=pFilled.matcher(l);
						if(m.matches()){
							filled = m.group(1);
							if (filled.equals("true")){
								squiggle.setFill(true);	
							}else {
								squiggle.setFill(false);
							}
							state = 14;
						}
						else {
							error("Expecting filled line");
							return false;
						}
						break;
					case 14:
						m=pPointStart.matcher(l);
						if(m.matches()){
							state = 15;
						}
						else {
							error("Expecting filled line");
							return false;
						}
						break;	
					
					
					case 15:
						
						m=pPoints.matcher(l);
						if(m.matches()){
							int points1=Integer.parseInt(m.group(2));
							int points2=Integer.parseInt(m.group(3));
							squiggle.add(new Point(points1, points2));
							state = 15;
						}else {
							state = 16;
						}
						break;	
				

					case 16:
						m=pSquiggleEnd.matcher(l);
						if(m.matches()){
							SquiggleCommand squiggleCommand = new SquiggleCommand(squiggle);
							this.commands.add(squiggleCommand);
							state = 1;
						}
						else {
							error("Expecting filled line");
							return false;
						}
						break;
					case 17:
						m=pFileEnd.matcher(l);
						if(m.matches()){
							state = 17;
						}
						else {
							error("Expecting endline line");
							return false;
							
					}	
				
				}
				
			}
		}  catch (Exception e){
			
		}
		return true;
	}
}
