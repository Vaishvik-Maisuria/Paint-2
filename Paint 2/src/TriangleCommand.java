

import java.awt.Graphics2D;

public class TriangleCommand implements PaintCommand {
	private Triangle triangle;
	public TriangleCommand(Triangle triangle){
		this.triangle=triangle;
	}
	@Override
	public void execute(Graphics2D g2d) {
		// TODO Auto-generated method stub
		int[] pointsX = this.triangle.getPointsX();
		int[] pointsY = this.triangle.getPointsY();
		if (!this.triangle.isFill()) {
			g2d.drawPolygon(pointsX, pointsY, 3);
		} else {
			g2d.fillPolygon(pointsX, pointsY, 3);
		}		
	}

	@Override
	public String shapeCommands() {
		// TODO Auto-generated method stub
		return null;
	} 
	

}
