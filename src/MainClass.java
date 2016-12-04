import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

class menuItemActionListener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		JMenuItem sce =(JMenuItem) e.getSource();
		if (sce.getText() == "NEW")
		{
			Simulator sim = Simulator.getInstance();
			sim.initializeSimulator();
			JOptionPane.showMessageDialog(null, "New Simulation started\n PRESS RUN SIMULATION\nto continue","PRESS RUN SIMULATION " , JOptionPane.INFORMATION_MESSAGE);

		} else
		{
			System.out.println("Menu item selected but source was not recognised");
			System.out.println("Source: " );
		}
	}
}

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
		menuItemNew.addActionListener(new menuItemActionListener());
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
