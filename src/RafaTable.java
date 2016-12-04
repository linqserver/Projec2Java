import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class RafaTable extends JPanel
{
	private boolean DEBUG = false;
	/**
	 * 
	 */
	// private static final long serialVersionUID = -6276714266834005086L;
	private JTable table;

	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton ADD_Button;
	private JButton UPDATE_Button;
	private JButton DELETE_Button;
	private JButton RUN_SIM_Button;
	private JPanel panel_1;
	private JComboBox<Object> typeComboBox;
	private JTextField textField_position;
	private JTextField textField_age;
	private JTextField textField_generation;
	private JTextField textField_fitness;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblCreatureSimulator;
	private Simulator simRun;// = Simulator.getInstance();	
	private TableModelForCreatureMap model;
	/**
	 * Create the panel.
	 */
	public RafaTable(Creature[][] map)
	{

		initialize(map);
	}
	public AbstractTableModel initModel(Creature[][] map)
	{
		model = new TableModelForCreatureMap();// )
		// table.getModel();
		// model.dataFillerFromArray2D(map);
		model.array2DtoVector2D(map);
		return model;
	}

	private void initialize(Creature[][] map)
	{
		simRun = Simulator.getInstance();
		setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 450, 209);

		table = new JTable();
		table.setRowMargin(3);
		table.setMaximumSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);

		table.setModel(initModel(map));
		table.getSelectionModel().addListSelectionListener(new RowSelectionActionListener());
		// TableColumn typeColumn = table.getColumnModel().getColumn(0);

		scrollPane.setViewportView(table);
		add(scrollPane);

		panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 295, 477, 39);
		add(panel);

		ADD_Button = new JButton("ADD NEW");
		ADD_Button.setEnabled(true);
		ADD_Button.addActionListener(new AddSimulationButtonActionListener());
		panel.add(ADD_Button);

		UPDATE_Button = new JButton("UPDATE");
		UPDATE_Button.addActionListener(new UPDATEButtonActionListener());
		panel.add(UPDATE_Button);

		DELETE_Button = new JButton("DELETE");
		DELETE_Button.addActionListener(new DeleteSimulationButtonActionListener());
		DELETE_Button.setEnabled(true);
		panel.add(DELETE_Button);

		RUN_SIM_Button = new JButton("RUN SIMULATION");
		RUN_SIM_Button.setBackground(Color.GREEN);
		RUN_SIM_Button.addActionListener(new RunSimulationButtonActionListener());
		panel.add(RUN_SIM_Button);

		panel_1 = new JPanel();
		panel_1.setBounds(13, 234, 433, 50);
		add(panel_1);
		panel_1.setLayout(new GridLayout(2, 5, 2, -5));

		typeComboBox = new JComboBox<Object>();
		typeComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "Herbivore", "Carnivore" }));
		panel_1.add(typeComboBox);

		textField_age = new JTextField();
		textField_age.setText("0");
		panel_1.add(textField_age);
		textField_age.setColumns(10);

		textField_fitness = new JTextField();
		textField_fitness.setText("0");
		panel_1.add(textField_fitness);
		textField_fitness.setColumns(10);

		textField_generation = new JTextField();

		textField_generation.setText("0");
		textField_generation.setEditable(false);
		panel_1.add(textField_generation);
		textField_generation.setColumns(10);

		textField_position = new JTextField();
		textField_position.setEditable(false);

		panel_1.add(textField_position);
		textField_position.setColumns(10);
		textField_position.setText("X0,Y0");
		lblNewLabel = new JLabel("Set Type");
		panel_1.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Set Age");
		panel_1.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Set Fitness ");
		panel_1.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Generation");
		panel_1.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Position X,Y");
		panel_1.add(lblNewLabel_4);

		lblCreatureSimulator = new JLabel("CREATURE SIMULATOR 9000");
		lblCreatureSimulator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreatureSimulator.setBounds(12, 339, 263, 14);
		add(lblCreatureSimulator);
	}

	

	private class UPDATEButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (table.getSelectedRow() > -1)
			{
				String type = typeComboBox.getModel().getSelectedItem().toString();
				int age = Integer.parseInt(textField_age.getText());
				int fit = Integer.parseInt(textField_fitness.getText());

				int row = table.getSelectedRow();
				table.getModel().setValueAt(type, row, 0);
				table.getModel().setValueAt(age, row, 1);
				table.getModel().setValueAt(fit, row, 2);
				textField_generation.setText(table.getModel().getValueAt(row, 3).toString());
				String pos = "";
				pos +=  table.getModel().getValueAt(row, 4).toString();
				pos += ", ";
				pos +=  table.getModel().getValueAt(row, 5).toString();
				textField_position.setText(pos);
				
			} else
				System.out.println("No rows selected");

		}
	}

	private class RunSimulationButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{					
			//simRun.moveCreatures();		
			simRun.moveCreatures();
			table.setModel(initModel(simRun.mapOfCreatures));
			model.fireTableDataChanged();
			table.repaint();
			
			System.out.println("Run button Pressed");
		}
	}

	private class AddSimulationButtonActionListener implements ActionListener
	{
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e)
		{
			model.addRow(new Creature());
			model.fireTableDataChanged();
			scrollPane.getVerticalScrollBar().setValue(0);
			scrollPane.repaint();
			table.repaint();
		}
	}
	private class DeleteSimulationButtonActionListener implements ActionListener
	{
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e)
		{
			scrollPane.getVerticalScrollBar().setValue(0);
			model.deleteRow(table.getSelectedRow());
			//model.fireTableDataChanged();
			
			scrollPane.repaint();
			table.repaint();
			
		}
	}

	private class RowSelectionActionListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent event)
		{
			
			Object s = table.getValueAt(table.getSelectedRow(), 0);
			if(s != null)
			typeComboBox.setSelectedItem(table.getValueAt(table.getSelectedRow(), 0).toString());
			else
				typeComboBox.setSelectedIndex(-1);
				
			int row = table.getSelectedRow();
			String pos = "";
			pos +=  table.getModel().getValueAt(row, 4).toString();
			pos += ", ";
			pos +=  table.getModel().getValueAt(row, 5).toString();
			textField_position.setText(pos);
			textField_age.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			textField_generation.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			textField_fitness.setText(table.getValueAt(table.getSelectedRow(), 2).toString());

		}

	}
}
