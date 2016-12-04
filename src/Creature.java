public class Creature
{
	private String type;
	private int age;
	private int fitness;
	private int generation;
	private int[] position = new int[2];
	public boolean moved;

	/**
	 * @param pType
	 *            String "Herbivore" or "Carnivore"
	 * @param pAge
	 *            integer grater than zero
	 * @param pFitness
	 *            integer grater than zero
	 * @param pPosition[2]
	 *            array of integers of size 2
	 */
	public Creature(){};
	@SuppressWarnings("null")
	public Creature(String pType, int pAge, int pFitness,int pGen, int[] pPosition)
	{
		try {
			setType(pType);
			setAge(pAge);
			setFitness(pFitness);
			setGeneration(pGen);
			setPosition(pPosition);
			moved = false;
		} catch (Exception e) {
			setType(null);
			setAge((Integer) null);
			setFitness((Integer) null);
			setGeneration((Integer) null);
			setPosition(null);
			moved = false;
		}
	}
	public Creature getCreature()
	{
		return this;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @return the age
	 */
	public int getAge()
	{
		return age;
	}

	/**
	 * @return the fitness
	 */
	public int getFitness()
	{
		return fitness;
	}

	/**
	 * @return the generation
	 */
	public int getGeneration()
	{
		return generation;
	}

	/**
	 * @return the position
	 */
	public int[] getPosition()
	{
		return position;
	}
	public int getPositionX()
	{
		return this.position[0];
	}
	public int getPositionY()
	{
		return this.position[1];
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		if (type.equals("Herbivore") || type.equals("Carnivore"))
			this.type = type;
		else
			System.out.println("Incorrect type of creature, type is: " + type);
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age)
	{
		if (age >= 0)
			this.age = age;
		else
			System.out.println("Age can not be a negative value");
	}

	/**
	 * @param fitness
	 *            the fitness to set
	 */
	public void setFitness(int fitness)
	{
		if (fitness >= 0)
			this.fitness = fitness;
		else
			System.out.println("fitness can not be a negative value");
	}
	/**
	 * @param fitness
	 *            the fitness is increased by param
	 */
	public void modifyFitness(int fitness)
	{
		if (fitness >= 0)
			this.fitness += fitness;
		else
			System.out.println("fitness can not be a negative value");
	}

	/**
	 * @param generation
	 *            the generation to set
	 */
	public void setGeneration(int generation)
	{
		this.generation = generation;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int[] position)
	{
		this.position = position;
	}
	public void setPositionX(int x)
	{
		this.position[0] = x;
	}
	public void setPositionY(int y)
	{
		this.position[1] = y;
	}
	
}
