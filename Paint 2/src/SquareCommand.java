
import java.awt.Graphics2D;


public class SquareCommand implements PaintCommand{
	
	private Square square;
	
	public SquareCommand(Square square){
		this.square = square;
	}
	public void execute(Graphics2D g2d){
		
		g2d.setColor(square.getColor());
		
		Point start = this.square.getStart();
		int sideLength = this.square.getSideLength();
		
		if (!this.square.isFill()) {
			g2d.drawRect(start.getX(), start.getY(), sideLength, sideLength);
		} else {
			g2d.fillRect(start.getX(), start.getY(), sideLength, sideLength);
		}
		
	}
		
	public String shapeCommands() {
		return (this.square.getColor().getRed() + "," + this.square.getColor().getGreen() + "," + 
				this.square.getColor().getBlue() + "," + this.square.getStart().x + "," + 
				this.square.getStart().y + "," + this.square.getSideLength() + "," + this.square.isFill());
	}
}
