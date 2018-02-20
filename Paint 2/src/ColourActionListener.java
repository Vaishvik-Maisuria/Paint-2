import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ColourActionListener extends Shape implements ActionListener{

	private ColourChooserView view;
	private PaintPanel panel;
	private Shape shape;
	
	/**
	 * Initializing OKActionListener. This is the constructor.
	 * @param view	The color chooser view in which the RGB sliders are in
	 * @param model	The paint model which holds the shape methods
	 */
	public ColourActionListener(ColourChooserView view, PaintPanel panel) {
		this.view = view;
		this.panel = panel;
	}
	
	/**
	 * The required method for ActionListener. Sends the color to the 
	 * paint model and closes the current window if clicked.
	 * @param e	The button that was clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Color currentColour = this.view.getCurrentColour();
		System.out.println(currentColour);
		// Sends the color in the color chooser to the paint model
		this.panel.changeLastColor(currentColour);
		//this.shape.setColor(currentColour);
		// Closes the color window but keeps the main frame open
		this.view.dispose();
	}

	
}
