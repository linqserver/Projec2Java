import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Serializer implements Serializable
{

	private static Serializer instance = null;

	protected Serializer()
	{
		System.out.println("Serializer singleton initialized Simulator");
	}

	public static Serializer getInstance()
	{
		if (instance == null)
		{
			instance = new Serializer();
		}
		System.out.println("Serializer singleton reference passesd");
		return instance;
	}

	public void saveToFile(Vector<Vector<Object>> vectorData)
	{
		try
		{
			FileOutputStream out = new FileOutputStream("save.ser");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(vectorData);
			oos.close();
		} catch (Exception e)
		{

		}

	}

	public Vector<Vector<Object>> readfromFile()
	{
		Vector<Vector<Object>> vectorData = new Vector<Vector<Object>>();
		try
		{
			FileInputStream in = new FileInputStream("save.ser");
			ObjectInputStream ois = new ObjectInputStream(in);
			vectorData = (Vector<Vector<Object>>) ois.readObject();
			ois.close();
		} catch (Exception c)
		{
		}
		return vectorData;
	}
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

}
