
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RectangleCommand implements PaintCommand {
	private Rectangle rectangle;
	public RectangleCommand(Rectangle rectangle){
		this.rectangle = rectangle;
	}
	public void execute(Graphics2D g2d){
		g2d.setColor(rectangle.getColor());
		Point topLeft = this.rectangle.getTopLeft();
		Point dimensions = this.rectangle.getDimensions();
		if(rectangle.isFill()){
			g2d.fillRect(topLeft.x, topLeft.y, dimensions.x, dimensions.y);
		} else {
			g2d.drawRect(topLeft.x, topLeft.y, dimensions.x, dimensions.y);
		}
	}
	
	public String shapeCommands() {
		return (this.rectangle.getColor().getRed() + "," + 	this.rectangle.getColor().getGreen() + "," + 
				this.rectangle.getColor().getBlue() + "," + this.rectangle.getTopLeft().x + "," + 
				this.rectangle.getTopLeft().y + "," + this.rectangle.getBottomRight().x + "," + 
				this.rectangle.getBottomRight().y + "," + this.rectangle.isFill());
	}
}
