public class Simulator
{
	public Creature[][] mapOfCreatures;
	public String[][] food;

	private static Simulator instance = null;

	protected Simulator()
	{
		// singleton
		initializeSimulator();
		System.out.println("singleton initialized Simulator");
	}

	public static Simulator getInstance()
	{
		if (instance == null)
		{
			instance = new Simulator();

		}
		System.out.println("singleton reference passesd");
		return instance;
	}

	public void initializeSimulator()
	{
		initializeMap();
		initializeFood();
		System.out.println("Food and Creatures initialized");
	}

	public void initializeMap()
	{
		mapOfCreatures = new Creature[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
			{
				int randomNum = 1 + (int) (Math.random() * ((100 - 1) + 1));
				// limits number of creatures
				if (randomNum > 66 && randomNum < 88)
				{
					String type = ""; // "Herbivore" || "Carnivore"
					if (randomNum % 2 == 0)
						type = "Herbivore";
					else
						type = "Carnivore";
					int[] pos = { i, j };
					mapOfCreatures[i][j] = new Creature(type, 0, 25, 0, pos);
				} else
				{
					int[] pos = { i, j };
					mapOfCreatures[i][j] = new Creature("none", 0, 25, 0, pos);
				}
			}
	}

	public void initializeFood()
	{
		food = new String[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
			{
				String typeOfFood = "";
				int randomNum = 1 + (int) (Math.random() * ((100 - 1) + 1));
				// limits amount of food
				if (randomNum > 45 && randomNum < 88)
				{
					if (randomNum % 2 == 0)
						typeOfFood = "Herbivore";
					else
						typeOfFood = "Carnivore";
					food[i][j] = typeOfFood;
				}
			}
	}

	/**
	 * Moves creatures on the map
	 * 
	 * @return new map with new positions.
	 */
	public void moveCreatures()
	{		
		
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{				 
				if (mapOfCreatures[i][j] != null)
				{	 					
					mapOfCreatures[i][j].setPosition(newPosition(mapOfCreatures[i][j].getPosition()));					
				}
			}
		}

		
	}

	public Creature checkFood(Creature c)
	{
		return c;
	}

	public Creature checkPredators(Creature c1, Creature c2)
	{
		return c1;
	}

	public Creature Breeding(Creature c1, Creature c2)
	{
		return c2;
	}

	int[] newPosition(int[] dirPar)
	{
		int[] temp = new int[] { dirPar[0], dirPar[1] };
		int[] trg = randomDirection();
		dirPar[0] += trg[0];
		dirPar[1] += trg[1];
		if (dirPar[0] >= 0 && dirPar[0] <= 9 && dirPar[1] >= 0 && dirPar[1] <= 9)
		{
			return dirPar;
		} else
		{
			return temp;// dont move
		}
	}

	int[] randomDirection()
	{
		int randomNum = 1 + (int) (Math.random() * ((8 - 1) + 1));
		int[] direction = { 0, 0 };
		switch (randomNum) {
		case 1:
			direction = new int[] { 0, 1 };
			break;
		case 2:
			direction = new int[] { 1, 1 };
			break;
		case 3:
			direction = new int[] { 1, 0 };
			break;
		case 4:
			direction = new int[] { 1, -1 };
			break;
		case 5:
			direction = new int[] { 0, -1 };
			break;
		case 6:
			direction = new int[] { -1, -1 };
			break;
		case 7:
			direction = new int[] { 0, -1 };
			break;
		case 8:
			direction = new int[] { -1, 1 };
			break;
		default:
			direction = new int[] { 0, 0 };
			break;
		}
		return direction;
	}
}
