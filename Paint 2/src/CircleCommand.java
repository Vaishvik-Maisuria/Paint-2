
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.PrintWriter;

public class CircleCommand implements PaintCommand {
	private Circle circle;
	
	
	public CircleCommand(Circle circle){
		this.circle=circle;
	}
	public void execute(Graphics2D g2d){
		g2d.setColor(circle.getColor());
		int x = this.circle.getCentre().x;
		int y = this.circle.getCentre().y;
		int radius = this.circle.getRadius();
		if(circle.isFill()){
			g2d.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		} else {
			g2d.drawOval(x-radius, y-radius, 2*radius, 2*radius);
		}
	}
	
	public String shapeCommands() {
		return (this.circle.getColor().getRed() + "," + this.circle.getColor().getGreen() + "," + 
				this.circle.getColor().getBlue() + "," + this.circle.getCentre().x + "," + 
				this.circle.getCentre().y + "," + this.circle.getRadius() + "," + this.circle.isFill());
	}
}
