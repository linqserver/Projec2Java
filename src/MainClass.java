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
		Simulator sim = Simulator.getInstance();
		Serializer readWrite = Serializer.getInstance();
		
		JMenuItem sce =(JMenuItem) e.getSource();
		if (sce.getText() == "NEW")
		{
			
			sim.initializeSimulator();
			JOptionPane.showMessageDialog(null, "New Simulation started\n PRESS RUN SIMULATION\nto continue","PRESS RUN SIMULATION " , JOptionPane.INFORMATION_MESSAGE);

		}
		else if (sce.getText() == "Read from file")
		{
			sim.vectorDataInputFile = readWrite.readfromFile();
			sim.newRead = true;
			
		}
		else if (sce.getText() == "Save to file")
		{
			readWrite.saveToFile(sim.vectorDataInputFile);
		}
		else
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
	
	private static JMenuItem menuItemSaveToFile;
	private static JMenuItem menuItemReadFromFile;
	
	
	private static JMenuItem menuItemNew;

	public static void main(String[] args)
	{

		JFrame frame = new JFrame("A00224300 Assignment 2");
		frame.setResizable(true);
		frame.setMinimumSize(new Dimension(500, 450));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuItemFile = new JMenu("File");		
		
		menuItemNew = new JMenuItem("NEW");
		
		menuItemNew.addActionListener(new menuItemActionListener());		
		
		menuItemReadFromFile = new JMenuItem("Read from file");	
		menuItemReadFromFile.addActionListener(new menuItemActionListener());
		menuItemSaveToFile = new JMenuItem("Save to file");
		menuItemSaveToFile.addActionListener(new menuItemActionListener());
		
		menuBar.add(menuItemFile);
		menuItemFile.add(menuItemNew);
		menuItemFile.add(menuItemReadFromFile);
		menuItemFile.add(menuItemSaveToFile);

		
		Simulator s1 = new Simulator();
		RafaTable newContentPane = new RafaTable(s1.mapOfCreatures);

		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		frame.setVisible(true);

	}
	
	
}
