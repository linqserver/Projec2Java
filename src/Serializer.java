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
}
