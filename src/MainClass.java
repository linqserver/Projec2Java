import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainClass
{
	private static JMenuBar menuBar;
	private static JMenu menuItemFile;
	private static JMenuItem menuItemQuit;
	private static JMenuItem menuItemSaveToFile;
	private static JMenuItem menuItemReadFromFile;
	private static JMenuItem menuItemStopSimulation;
	private static JMenuItem menuItemStartSimulation;
	
	private static JMenuItem menuItemNew;

	public static void main(String[] args)
	{

		JFrame frame = new JFrame("A00224300 Assignment 2");
		frame.setResizable(false);
		frame.setMinimumSize(new Dimension(500, 420));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuItemFile = new JMenu("File");
		menuBar.add(menuItemFile);

		menuItemNew = new JMenuItem("NEW");
		menuItemFile.add(menuItemNew);

		menuItemStartSimulation = new JMenuItem("Start Simulation");
		menuItemFile.add(menuItemStartSimulation);

		menuItemStopSimulation = new JMenuItem("Stop Simulation");
		menuItemFile.add(menuItemStopSimulation);

		menuItemReadFromFile = new JMenuItem("Read from file");
		menuItemFile.add(menuItemReadFromFile);

		menuItemSaveToFile = new JMenuItem("Save to file");
		menuItemFile.add(menuItemSaveToFile);

		menuItemQuit = new JMenuItem("Quit");
		menuItemFile.add(menuItemQuit);

		Simulator s1 = new Simulator();
		RafaTable newContentPane = new RafaTable(s1.mapOfCreatures);

		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		frame.setVisible(true);

	}

}
