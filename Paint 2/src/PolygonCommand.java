import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;



public class PolygonCommand implements PaintCommand{

	private Polygon polygon; // the polygon we are creating
	
	/**
	 * Initializing DrawPolygonCommand. This is the constructor.
	 * @param polygon	The polygon to be drawn
	 */
	public PolygonCommand(Polygon polygon) {
		this.polygon = polygon;	
	}
	
	public void execute(Graphics2D g2d){
		
		
		int[] pointsX = this.polygon.getPointsX();
		int[] pointsY = this.polygon.getPointsY();
				
		if (!this.polygon.isFill()) {
			g2d.drawPolygon(pointsX, pointsY, this.polygon.getSide());
		} else {
			g2d.fillPolygon(pointsX, pointsY, this.polygon.getSide());
		}
	}
	
	public String shapeCommands() {
		return (this.polygon.getColor().getRed() + "," + this.polygon.getColor().getGreen() + "," + 
				this.polygon.getColor().getBlue() + "," + this.polygon.getCentre().x + "," + 
				this.polygon.getCentre().y + "," + this.polygon.getSide() + "," + this.polygon.isFill());
	}
	
}
