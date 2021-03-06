

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class Paint {
	
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Paint();
			}
		});
	}

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private View a_View; // View+Controller
	
	public Paint() {
		this.paintPanel = new PaintPanel();
		
		this.a_View = new View(paintPanel);

	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

//	private JMenuBar createMenuBar() {
//		JMenuBar menuBar = new JMenuBar();
//		JMenu menu;
//		JMenuItem menuItem;
//
//		menu = new JMenu("File");
//
//		// a group of JMenuItems
//		menuItem = new JMenuItem("New");
//		menuItem.addActionListener(this);
//		menu.add(menuItem);
//
//		menuItem = new JMenuItem("Open");
//		menuItem.addActionListener(this);
//		menu.add(menuItem);
//
//		menuItem = new JMenuItem("Save");
//		menuItem.addActionListener(this);
//		menu.add(menuItem);
//
//		menu.addSeparator();// -------------
//
//		menuItem = new JMenuItem("Exit");
//		menuItem.addActionListener(this);
//		menu.add(menuItem);
//
//		menuBar.add(menu);
//
//		return menuBar;
//	}

//	public void actionPerformed(ActionEvent e) {
//		if (e.getActionCommand() == "Open") {
//			JFileChooser fc = new JFileChooser();
//			int returnVal = fc.showOpenDialog(this);
//
//			if (returnVal == JFileChooser.APPROVE_OPTION) {
//				String txt_format = ".txt"; // I have specified a text format here for test purposes
//				File file = fc.getSelectedFile();
//				
//				try {
//					fr = new FileReader(file);
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} 
//				paintPanel.open((fr));
//				System.out.println("Opening: " + file.getName() + txt_format + "\n");
//			} else {
//				System.out.println("Open command cancelled by user." + "\n");
//			}
//		} else if (e.getActionCommand() == "Save") {
//			JFileChooser fc = new JFileChooser();
//			int returnVal = fc.showSaveDialog(this);
//
//			if (returnVal == JFileChooser.APPROVE_OPTION) {
//				String txt_format = ".txt"; // I have specified a text format here for test purposes
//				File file = fc.getSelectedFile();
//				try {
//					paintPanel.save(new PrintWriter(file + txt_format));
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				// This is where a real application would open the file.
//				System.out.println("Saving: " + file.getName() + txt_format + "\n");
//			} else {
//				System.out.println("Save command cancelled by user." + "\n");
//			}
//		} else if (e.getActionCommand() == "New") {
//			this.paintPanel.reset();
//			this.shapeChooserPanel.reset();
//		}
//		System.out.println(e.getActionCommand());
//	}
}
