package models;

import java.io.Serializable;

public abstract class Space implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String name;
	private final int xCoordinate, yCoordinate;

	public abstract void receiveMessage();

	public Space(String name, int xCoordinate, int yCoordinate)
	{
		this.name = name;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public String getName()
	{
		return name;
	}

	public int[] getCoordinates()
	{
		int[] coordinates = {this.xCoordinate, this.yCoordinate};

		return coordinates;
	}
}
