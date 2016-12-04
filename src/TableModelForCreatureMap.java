import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModelForCreatureMap extends AbstractTableModel
{

	String[] headers = { "Type", "Age", "Fitness", "Generation", "PosX", "PosY" };

	public Object[][] arrayData2D;// = new Object[100][6];
	public ArrayList<Creature> arrayList;
	public Vector<Vector<Object>> vectorData2D;
	private Simulator simModel;
	public JComboBox<Object> tableCellCombo;

	/*
	 * ListIterator iter2 = ((Vector) ((Vector)
	 * WordTemp.get(t)).get(i)).listIterator(); while(iter2.hasNext()){
	 */
	public void array2DtoArrayList()
	{

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
			{
				if (((Creature) arrayData2D[i][j]).getType() != null)
				{
					String type = ((Creature) arrayData2D[i][j]).getType().toString();
					int age = ((Creature) arrayData2D[i][j]).getAge();
					int fitness = ((Creature) arrayData2D[i][j]).getFitness();
					int generation = ((Creature) arrayData2D[i][j]).getGeneration();
					int[] position = ((Creature) arrayData2D[i][j]).getPosition();
					boolean mov = ((Creature) arrayData2D[i][j]).moved;
					Creature c = new Creature(type, age, fitness, generation, position);
					c.moved = mov;
					arrayList.add(c);
				}
			}

	}

	public void array2DtoVector2D(Creature[][] map)
	{
		vectorData2D = new Vector<Vector<Object>>();
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if (!(map[i][j] == null))
				{
					if (map[i][j].getType().toString().equals("Herbivore")
							|| map[i][j].getType().toString().equals("Carnivore"))
					{
						addRow(map[i][j]);
					} else
					{
						// System.out.println("array2DtoVector2D line 61:: Dead
						// Creature");
					}
				}
			}
		}

	}

	/**
	 * This function is necessary to translate information between simulator
	 * class and table model. It changes table model into creature array[10][10]
	 * used for simulation
	 * 
	 * @param v
	 *            vector of object vectors.
	 */
	public Creature[][] Vector2DtoCreatureMapArray2D(Vector<Vector<Object>> v)
	{
		Creature[][] cMap = new Creature[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				cMap[i][j] = new Creature();

		for (int i = 0; i < v.size(); i++)
		{
			Creature c = vectorToCreature(v.elementAt(i));
			cMap[c.getPositionX()][c.getPositionY()] = c;
		}

		return cMap;
	}

	public void deleteRow(int index)
	{
		if (index < vectorData2D.size() && index >= 0)
			vectorData2D.remove(index);
	}

	/**
	 * @param c
	 *            Creature
	 */
	public void addRow(Creature c)
	{
		Vector<Object> vectorOfcreatureAtributes = new Vector<Object>();
		vectorOfcreatureAtributes.add(c.getType());
		vectorOfcreatureAtributes.add(c.getAge());
		vectorOfcreatureAtributes.add(c.getFitness());
		vectorOfcreatureAtributes.add(c.getGeneration());
		vectorOfcreatureAtributes.add(c.getPositionX());
		vectorOfcreatureAtributes.add(c.getPositionY());
		vectorData2D.add(vectorOfcreatureAtributes);
	}

	public Creature vectorToCreature(Vector<Object> v)
	{
		Creature c = new Creature();

		c.setType((String) v.elementAt(0));
		c.setAge((int) v.elementAt(1));
		c.setFitness((int) v.elementAt(2));
		c.setGeneration((int) v.elementAt(3));
		c.setPositionX((int) v.elementAt(4));
		c.setPositionY((int) v.elementAt(5));
		return c;

	}

	public void dataFillerFromArray2D(Creature[][] map)
	{
		int counter = 0;

		arrayData2D = new Object[getNumberOfCreatuesFromArray2D(map)][6];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
			{
				if (!(map[i][j].getType().equals("")))
				{
					if (map[i][j].getType().equals("Herbivore") || map[i][j].getType().equals("Carnivore"))
					{
						arrayData2D[counter][0] = map[i][j].getType();
						arrayData2D[counter][1] = map[i][j].getAge();
						arrayData2D[counter][2] = map[i][j].getFitness();
						arrayData2D[counter][3] = map[i][j].getGeneration();
						arrayData2D[counter][4] = map[i][j].getPositionX();
						arrayData2D[counter][5] = map[i][j].getPositionY();

						counter++;
					}
				}
			}
	}

	public int getComboIndex(String s)
	{
		return (s.equals("Herbivore") ? 0 : 1);
	}

	public int getNumberOfCreatuesFromArray2D(Creature[][] map)
	{
		int c = 0;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if (map[i][j].getType() != null)
				{
					if (map[i][j].getType().equals("Herbivore") || map[i][j].getType().equals("Carnivore"))
					{
						c++;
					}
				}
			}
		}

		return c;
	}

	public int getColumnCount()
	{
		return headers.length;
	}

	public int getRowCount()
	{
		// return arrayData2D.length;
		return vectorData2D.size();
	}

	public void replace(int index, Creature c)
	{

	}

	public String getColumnName(int col)
	{
		return headers[col];
	}

	public Object getValueAt(int row, int col)
	{
		if (!(row >= 0 && col >= 0))
		{
			row = 0;
			col = 0;
		}

			return vectorData2D.elementAt(row).elementAt(col);
	}

	public Class<? extends Object> getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col)
	{
		// Note that the arrayData2D/cell address is constant,
		// no matter where the cell appears onscreen.
		if (col < 1)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public void setValueAt(Object value, int row, int col)
	{
		vectorData2D.elementAt(row).setElementAt(value, col);
		fireTableCellUpdated(row, col);
	}

	public void initModelSimulatorReference()
	{
		simModel = Simulator.getInstance();
	}

	public void transferDataToSimulator()
	{
		if (vectorData2D != null)
			simModel.vectorDataBackup = vectorData2D;
	}
}
