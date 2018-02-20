import java.awt.event.MouseEvent;


/**
 * LineManipulatorStrategy extends ShapeManipulatorStrategy and takes care of
 * what happens to the shape and panel when the user modifies the mouse position or status.
 *
 */
public class LineManipulatorStrategy extends ShapeManipulatorStrategy{
	
	private Line line; // line that is being crated 
	

	@Override
	public void mousePressed(MouseEvent e) {
		// starting point for the triangle 
		Point center = new Point(e.getX(), e.getY());
		Point start = new Point(e.getX() +1, e.getY() +1);
		// create new triangle
		this.line = new Line(center, start); 
		
		LineCommand lineCommand = new LineCommand(this.line);
		this.paintPanel.addCommand(lineCommand);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point starting = new Point(e.getX(), e.getY());
		this.line.SetEnd(starting);
		
		paintPanel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}


