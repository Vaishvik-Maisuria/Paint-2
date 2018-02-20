package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;

/**
 * TriangleManipulatorStrategy extends ShapeManipulatorStrategy and takes care of
 * what happens to the shape and panel when the user modifies the mouse position or status.
 *
 */
public class TriangleManipulatorStrategy extends ShapeManipulatorStrategy {

	private Triangle triangle;

	@Override
	public void mousePressed(MouseEvent e) {
		// starting point for the triangle 
		Point starting = new Point(e.getX(), e.getY());
		// create new triangle
		this.triangle = new Triangle(starting, panel.getIsFilled());
		panel.getPaintModel().addDrawingCommand(new DrawTriangleCommand(this.triangle, panel.getPaintModel().getLastColor(), panel.getView().getSlider().getValue()));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Starting point for the triangle 
		Point starting = new Point(e.getX(), e.getY());
		// changing the third point of the triangle 
		this.triangle.addPoint3(starting);
		
		// changing the y coordinates of the second point of the triangle 
		Point starting1 = new Point(e.getX(), e.getY());
		this.triangle.addPoint2(starting1);
		
		// adding to the model so that the you can see the traingle

		panel.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
