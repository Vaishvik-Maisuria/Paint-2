

import java.awt.event.MouseEvent;

public class TriangleManipulatorStrategy extends ShapeManipulatorStrategy {
	
	private Triangle triangle;
	private int counter = 0;
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		if (counter == 0 || counter == 3){
			this.triangle= new Triangle();
			Point starting = new Point(e.getX(), e.getY());
			this.triangle.SetStart(starting);
			counter = 0;
			
		}else if(counter == 1) {
			Point starting1 = new Point(e.getX(), e.getY());
			this.triangle.SetPoint2(starting1);
			
		}else if (counter == 2) {
			Point starting2 = new Point(e.getX(), e.getY());
			this.triangle.SetPoint3(starting2);
			
			TriangleCommand triangleCommand = new TriangleCommand(this.triangle);
			this.paintPanel.addCommand(triangleCommand);
			this.paintPanel.repaint();
		} 
		
		counter++;
	}

}
