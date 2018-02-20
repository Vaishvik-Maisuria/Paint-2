
import java.awt.Graphics2D;



/**
 * DrawLineCommand takes care of drawing a given Line object on the panel.
 * This is because the Line object does not know how to draw itself.
 *
 */
public class LineCommand implements PaintCommand {
	private Line line;
	

	/**
	 * Initializing DrawLineCommand. This is the constructor.
	 * @param line			The Line to be drawn
	 */
	public LineCommand(Line line){
		this.line = line;
	}
	
	public void execute(Graphics2D g2d){

		g2d.drawLine(this.line.getstart().getX(), this.line.getstart().getY(), this.line.getEnd().getX(), this.line.getEnd().getY());
       
	}


	public String shapeCommands() {
		return (this.line.getColor().getRed() + "," + this.line.getColor().getGreen() + "," + 
				this.line.getColor().getBlue() + "," + this.line.getstart().x + "," + 
				this.line.getstart().y + "," + this.line.getEnd().x + "," + this.line.getEnd().y + "," + this.line.isFill());
	}
}
