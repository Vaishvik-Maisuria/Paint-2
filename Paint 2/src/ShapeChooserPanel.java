

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class ShapeChooserPanel extends JPanel implements ActionListener {
	private View a_View; // So we can talk to our parent or other components of the view
	
	//private static final long serialVersionUID = -2308804917821899439L;
	private ShapeManipulatorStrategy strategy;
	private Paint paint;
	private PaintPanel panel;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private String[] buttonLabels = { "Circle", "Rectangle", "Square", "Line", "RightTriangle", "Squiggle", "Polyline", "Polygon", "Triangle" };
	final JFrame frame = new JFrame();
	
	public ShapeChooserPanel(View a_View) {	
		this.a_View = a_View;
		this.panel = a_View.getPaintPanel();
		
		
		this.setLayout(new GridLayout(buttonLabels.length, 2));
		for (String label : buttonLabels) {		
			// pics/label.png is the location of the images
			ImageIcon start = new ImageIcon("pics/"+label+".png");
			Image img = start.getImage() ;
			Image newimg = img.getScaledInstance(55, 60, java.awt.Image.SCALE_SMOOTH ) ;
			start = new ImageIcon(newimg);
			JButton button = new JButton();
			button.setIcon(start);
			this.add(button);
			button.addActionListener(this);
			buttons.add(button);
			button.setFocusPainted(false);
			button.setContentAreaFilled(true);
			if (label == "Squiggle")
				button.setContentAreaFilled(false);	
		}
 		this.reset();
	}
	
	public void reset(){
		this.setStrategy("Circle");
	}
	
	private void setStrategy(String strategyName){
		if(this.strategy!=null){
			this.strategy.uninstall();
		}
		this.strategy = ShapeManipulatorFactory.create(strategyName);
		this.strategy.install(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		if (buttons.indexOf(e.getSource()) == 5) {

			String name = JOptionPane.showInputDialog("Number of Sides of the Polygon? (between 3 and 50)");
			try {
		        if (name != "" && Integer.parseInt(name) < 3 || Integer.parseInt(name) > 51) {

	        		JOptionPane.showMessageDialog(frame, "You entered something invalid. We have set the number equal to 3." );
	        		this.a_View.getPaintPanel().SetSide(3);
		        } else {
		        	this.a_View.getPaintPanel().SetSide(Integer.parseInt(name));	
		        }
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(frame, "You entered something invalid. We have set the number equal to 3." );
        		this.a_View.getPaintPanel().SetSide(3);
			}
		} 
		
		
		int index = buttons.indexOf(e.getSource());
		this.setStrategy(buttonLabels[index]);
		JButton b = (JButton)e.getSource();
		b.setContentAreaFilled(false);
		for (JButton button: buttons) {
			if (button != b) {
				button.setContentAreaFilled(true);
			}
			
		}
	}
}