
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class SquiggleCommand implements PaintCommand {
	private Squiggle squiggle;
	public SquiggleCommand(Squiggle squiggle){
		this.squiggle = squiggle;
	}
	public void execute(Graphics2D g2d){
		ArrayList<Point> points = this.squiggle.getPoints();
		g2d.setColor(squiggle.getColor());
		for(int i=0;i<points.size()-1;i++){
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
	
	public String shapeCommands() {
						
		ArrayList<Point> points = this.squiggle.getPoints();
		String pointValues = null;
		
		for (Point p: points) {
			pointValues = pointValues + "," + p.x + "," + p.y;
		}
		
		return (this.squiggle.getColor().getRed() + "," + this.squiggle.getColor().getGreen() + "," + 
				this.squiggle.getColor().getBlue() + "," + pointValues);
	}
	
	
}
