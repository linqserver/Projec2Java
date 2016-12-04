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
					String type = ((Creature) arrayData2D[i][j]).getType();
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
				if (map[i][j].getType() != null)
				{
					if (map[i][j].getType().equals("Herbivore") || map[i][j].getType().equals("Carnivore"))
					{
						addRow(map[i][j]);
					}
				}
			}
		}
	}
	public void deleteRow(int index)
	{
		if(index < vectorData2D.size())
		vectorData2D.remove(index);
	}
	/** 
	 * @param c Creature
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

	public void dataFillerFromArray2D(Creature[][] map)
	{
		int counter = 0;

		arrayData2D = new Object[getNumberOfCreatuesFromArray2D(map)][6];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
			{
				if (map[i][j].getType() != null)
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

	public String getColumnName(int col)
	{
		return headers[col];
	}

	public Object getValueAt(int row, int col)
	{
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

	/*******************************************************************/

	/*
	 * public void deleteRow(int rowIndex) { for(int rowIndex =
	 * arrayData2D.size() - 1; rowIndex >= 0; rowIndex--) {
	 * if(arrayData2D.get(rowIndex).isSelect()) { arrayData2D.remove(rowIndex);
	 * } } fireTableDataChanged(); }
	 */
}
