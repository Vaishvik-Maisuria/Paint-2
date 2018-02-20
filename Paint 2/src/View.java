

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This is the top level View+Controller, it contains other aspects of the
 * View+Controller.
 * 
 * 
 *
 */
public class View extends JFrame implements ActionListener {
	private FileReader fr;
	//private PaintModel model;

	// The components that make this up
	private PaintPanel PaintPanel;
	private ShapeChooserPanel ShapeChooserPanel;
	//private ModeChooserView ModeChooserView;
	private ColourView ColourView;
	private JLabel mousecordinate;
	private JSlider slider;
	private ArrayList<PaintCommand> shapesRemoved = new ArrayList<PaintCommand>();
	private ArrayList<Integer> shapeTracker = new ArrayList<Integer>();
	private int tracker = 0;

	/**
	 * Initialize view and create/add elements to the JFrame.
	 * 
	 * @param model
	 *            The paint model
	 */
	public View(PaintPanel panel) {
		super("Paint"); // set the title and do other JFrame init

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());

		Container c = this.getContentPane();

		this.PaintPanel = panel;
		
		this.ColourView = new ColourView(this);
		//this.PaintPanel = new PaintPanel(model, this);
	
		this.ShapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.PaintPanel, BorderLayout.CENTER);
		c.add(this.ShapeChooserPanel, BorderLayout.WEST);
		c.add(this.ColourView, BorderLayout.EAST);
		
		// Mouse coordinate position
		int X = 0;
		int Y = 0;
		mousecordinate = new JLabel("X: " + X + "px,  Y: " + Y + "px");
		c.add(mousecordinate, BorderLayout.SOUTH);
		
		this.pack();

		this.setMinimumSize(this.getSize());
		this.setVisible(true);
	}

	/**
	 * getter method for mouse coordinate
	 * 
	 * @return
	 */
	public JLabel getmousecordinate() {
		return mousecordinate;
	}

	/**
	 * getter method for paintPanel
	 * 
	 * @return
	 */
	public PaintPanel getPaintPanel() {
		return PaintPanel;
	}

	/**
	 * getter method for shapeChooserPanel
	 * 
	 * @return
	 */
	public ShapeChooserPanel getShapeChooserPanel() {
		return ShapeChooserPanel;
	}

	/**
	 * getter method for slider
	 * 
	 * @return
	 */
	public JSlider getSlider() {
		return slider;
	}
	
	/**
	 * getter method for slider
	 * 
	 * @return
	 */
//	public PaintModel getPaintModel() {
//		return this.model;
//	}

	
	/**
	 * Create new components in the menu bar
	 * 
	 * @return the menu bar at the top of the frame
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		// File drop down
		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		// Edit drop down
		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		KeyStroke keyStrokeUndo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
		menuItem.setAccelerator(keyStrokeUndo);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		KeyStroke keyStrokeRedo = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
		menuItem.setAccelerator(keyStrokeRedo);


		menuItem = new JMenuItem("Clear Canvas");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);
		return menuBar;
	}

	/**
	 * Remove a shape on the frame
	 */
	public PaintCommand removeShapes() {
	
		PaintCommand shapeRemoved = PaintPanel.getShapes().get(PaintPanel.getShapes().size() - 1);
		// Remove the shape
		PaintPanel.removeDrawingCommand();
		// Update the panel
		this.PaintPanel.repaint();


		return shapeRemoved;
	}

	/**
	 * The required method for actionListener. it prints which button is selected
	 * 
	 * @param e
	 *            the button clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getActionCommand() == "Undo") {
			// Only undo if there is at least one shape present
			if (PaintPanel.getShapes().size() > 0) {
				tracker++;
				shapesRemoved.add(removeShapes());
				shapeTracker.add(tracker);
			}
		} else if (e.getActionCommand() == "Clear Canvas") {
			if (PaintPanel.getShapes().size() != 0) {
				tracker++;
			}
			// Keep removing shapes until there are none left
			while (PaintPanel.getShapes().size() != 0) {
				shapesRemoved.add(removeShapes());
				shapeTracker.add(tracker);
			}
		} else if (e.getActionCommand() == "Redo") {
			// Only redo if there are shapes that were previously removed
			if (shapesRemoved.size() > 0) {
				int index = shapeTracker.size() - 1;
				while (shapeTracker.size() > 0 && shapeTracker.get(index) == tracker) {
					// Remove both the last shape and last tracker
					PaintCommand addShape = shapesRemoved.remove(shapesRemoved.size() - 1);
					shapeTracker.remove(shapeTracker.size() - 1);
					index = shapeTracker.size() - 1;
					this.PaintPanel.addCommand(addShape);
					this.PaintPanel.repaint();
				}
				// Since a shape was removed, decrease tracker by 1
				tracker--;
			}
		}
		
		
		if (e.getActionCommand() == "Open") {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String txt_format = ".txt"; // I have specified a text format here for test purposes
				File file = fc.getSelectedFile();
				
				try {
					fr = new FileReader(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				PaintPanel.open((fr));
				System.out.println("Opening: " + file.getName() + txt_format + "\n");
			} else {
				System.out.println("Open command cancelled by user." + "\n");
			}
		} else if (e.getActionCommand() == "Save") {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String txt_format = ".txt"; // I have specified a text format here for test purposes
				File file = fc.getSelectedFile();
				try {
					PaintPanel.save(new PrintWriter(file + txt_format));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// This is where a real application would open the file.
				System.out.println("Saving: " + file.getName() + txt_format + "\n");
			} else {
				System.out.println("Save command cancelled by user." + "\n");
			}
		} else if (e.getActionCommand() == "New") {
			this.PaintPanel.reset();
			this.ShapeChooserPanel.reset();
		}
		System.out.println(e.getActionCommand());
	}

	}


