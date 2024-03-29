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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class RafaTable extends JPanel
{
	private JTable table;

	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton ADD_Button;
	private JButton UPDATE_Button;
	private JButton DELETE_Button;
	private JButton RUN_SIM_Button;
	private JPanel panel_1;
	private JComboBox<Object> typeComboBox;
	private JTextField textField_posX;
	private JTextField textField_posY;
	private JTextField textField_age;
	private JTextField textField_generation;
	private JTextField textField_fitness;
	private JComboBox<Object> combo_simulationStep;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblCreatureSimulator;
	private JLabel lbl2CreatureSimulator;
	private Simulator simRun;// = Simulator.getInstance();
	private TableModelForCreatureMap model;
	private JButton SAVE_Button;
	private JButton READ_Button;
	private JButton AUTO_Button;

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
		model.addTableModelListener(new myTableModelListener());
		model.initModelSimulatorReference();
		return model;
	}

	public void updateCreatureMapArray2D()
	{
		simRun.mapOfCreatures = model.Vector2DtoCreatureMapArray2D(model.vectorData2D);
	}

	private void initialize(Creature[][] map)
	{
		simRun = Simulator.getInstance();
		setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 470, 209);

		table = new JTable();
		table.setRowMargin(3);
		table.setMaximumSize(new Dimension(600, 300));
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

		combo_simulationStep = new JComboBox<Object>();
		combo_simulationStep.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Step", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		combo_simulationStep.setMaximumRowCount(12);
		panel.add(combo_simulationStep);

		panel_1 = new JPanel();
		panel_1.setBounds(12, 234, 455, 50);
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
		textField_generation.setEditable(true);
		panel_1.add(textField_generation);
		textField_generation.setColumns(10);

		textField_posX = new JTextField();
		textField_posX.setEditable(true);

		textField_posY = new JTextField();
		textField_posY.setEditable(true);

		panel_1.add(textField_posX);
		textField_posX.setColumns(4);
		textField_posX.setText("-");

		panel_1.add(textField_posY);
		textField_posY.setColumns(4);
		textField_posY.setText("-");

		lblNewLabel = new JLabel("Set Type");
		panel_1.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Set Age");
		panel_1.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Set Fitness ");
		panel_1.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Generation");
		panel_1.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Position X");
		lblNewLabel_4.setMinimumSize(new Dimension(200, 14));
		panel_1.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Position Y");
		lblNewLabel_5.setMinimumSize(new Dimension(200, 14));
		panel_1.add(lblNewLabel_5);

		lblCreatureSimulator = new JLabel("File Operations:");
		lblCreatureSimulator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreatureSimulator.setBounds(12, 339, 400, 14);
		add(lblCreatureSimulator);

		lbl2CreatureSimulator = new JLabel("Auto Populate Table:");
		lbl2CreatureSimulator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl2CreatureSimulator.setBounds(326, 339, 400, 14);
		add(lbl2CreatureSimulator);

		SAVE_Button = new JButton("SAVE");
		SAVE_Button.setEnabled(true);
		SAVE_Button.addActionListener(new FileSimulationButtonActionListener());
		SAVE_Button.setBounds(12, 355, 100, 25);
		add(SAVE_Button);

		READ_Button = new JButton("READ");
		READ_Button.setEnabled(true);
		READ_Button.addActionListener(new FileSimulationButtonActionListener());
		READ_Button.setBounds(120, 355, 100, 25);
		add(READ_Button);

		AUTO_Button = new JButton("AUTO");
		AUTO_Button.setEnabled(true);
		AUTO_Button.addActionListener(new FileSimulationButtonActionListener());
		AUTO_Button.setBounds(325, 355, 130, 25);
		AUTO_Button.setBackground(Color.CYAN);
		add(AUTO_Button);

	}

	public void populateTextFieldsFromSelectedRow()
	{
		Creature c = creatureFromTableRow();

		if (c != null && !(c.getType().toString().equals("none")))
			typeComboBox.setSelectedItem(c.getType().toString());
		else
			typeComboBox.setSelectedIndex(-1);

		textField_age.setText(Integer.toString(c.getAge()));
		textField_fitness.setText(Integer.toString(c.getFitness()));
		textField_generation.setText(Integer.toString(c.getGeneration()));
		textField_posX.setText(Integer.toString(c.getPositionX()));
		textField_posY.setText(Integer.toString(c.getPositionY()));
	}

	public Creature creatureFromTextFields()
	{
		Creature c = new Creature();

		c.setType(typeComboBox.getSelectedItem().toString());
		c.setAge(Integer.parseInt(textField_age.getText()));
		c.setFitness(Integer.parseInt(textField_fitness.getText()));
		c.setGeneration(Integer.parseInt(textField_generation.getText()));
		c.setPositionX(Integer.parseInt(textField_posX.getText()));
		c.setPositionY(Integer.parseInt(textField_posY.getText()));
		return c;
	}

	public void populateSelectedRowFromCreature(Creature c)
	{
		int row = table.getSelectedRow();

		table.getModel().setValueAt(c.getType().toString(), row, 0);
		table.getModel().setValueAt(c.getAge(), row, 1);
		table.getModel().setValueAt(c.getFitness(), row, 2);
		table.getModel().setValueAt(c.getGeneration(), row, 3);
		table.getModel().setValueAt(c.getPositionX(), row, 4);
		table.getModel().setValueAt(c.getPositionY(), row, 5);
	}

	public Creature creatureFromTableRow()
	{
		Creature c = new Creature();
		if (table.getSelectedRow() >= 0)
		{
			c.setType(table.getValueAt(table.getSelectedRow(), 0).toString());
			c.setAge((int) table.getValueAt(table.getSelectedRow(), 1));
			c.setFitness((int) table.getValueAt(table.getSelectedRow(), 2));
			c.setGeneration((int) table.getValueAt(table.getSelectedRow(), 3));
			c.setPositionX((int) table.getValueAt(table.getSelectedRow(), 4));
			c.setPositionY((int) table.getValueAt(table.getSelectedRow(), 5));
		}
		return c;

	}

	private class UPDATEButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (table.getSelectedRow() > -1)
			{
				populateSelectedRowFromCreature(creatureFromTextFields());
				int row = table.getSelectedRow();
				model.replace(row, creatureFromTableRow());
				updateCreatureMapArray2D();
				//model.transferDataToSimulator();

			} else
				System.out.println("No rows selected");

		}
	}

	private class RunSimulationButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int steps = combo_simulationStep.getSelectedIndex();
			if (steps < 1)
				steps = 1;

			for (int i = 0; i < steps; i++)
			{
				simRun.moveCreatures();
				table.setModel(initModel(simRun.mapOfCreatures));

			}
			table.repaint();

			updateCreatureMapArray2D();
			System.out.println("Run button Pressed");
			//model.transferDataToSimulator();
		}
	}

	private class AddSimulationButtonActionListener implements ActionListener
	{
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e)
		{
			model.addRow(new Creature());
			scrollPane.getVerticalScrollBar().setValue(0);
			scrollPane.repaint();
			table.repaint();
			updateCreatureMapArray2D();
			//model.transferDataToSimulator();
		}
	}

	private class DeleteSimulationButtonActionListener implements ActionListener
	{
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e)
		{
			scrollPane.getVerticalScrollBar().setValue(0);
			model.deleteRow(table.getSelectedRow());
			// model.fireTableDataChanged();

			scrollPane.repaint();
			table.repaint();
			updateCreatureMapArray2D();
			//model.transferDataToSimulator();

		}
	}

	private class RowSelectionActionListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent event)
		{
			populateTextFieldsFromSelectedRow();
			//model.transferDataToSimulator();
		}

	}

	private class FileSimulationButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (((JButton) e.getSource()).getText().equals("SAVE"))
			{
				// save button pressed
				Serializer serSave = Serializer.getInstance();
				serSave.saveToFile(model.vectorData2D);
				updateCreatureMapArray2D();

			} else if (((JButton) e.getSource()).getText().equals("READ"))
			{
				// read button pressed
				Serializer serRead = Serializer.getInstance();
				
				table.setModel(initModel(serRead.Vector2DtoCreatureMapArray2D(serRead.readfromFile())));
				// model.fireTableDataChanged();)
				table.repaint();
				scrollPane.repaint();
				updateCreatureMapArray2D();

			} 
			else if (((JButton) e.getSource()).getText().equals("AUTO"))
			{
				// read button pressed
				simRun.autoInitializeMap();
				initModel(simRun.mapOfCreatures);
				table.setModel(initModel(simRun.mapOfCreatures));
				table.repaint();
				scrollPane.repaint();
				updateCreatureMapArray2D();

			}else
			{
				System.out.println("File operation not recognised");
			}

		}
	}

	private class myTableModelListener implements TableModelListener
	{
		public void tableChanged(TableModelEvent evt)
		{
			int row = table.getSelectedRow();
			model.replace(row, creatureFromTableRow());
			updateCreatureMapArray2D();
			//model.transferDataToSimulator();
		}
	};
}
