package models;

public abstract class Space
{
	private final String name;
	private final int xCoordinate, yCoordinate;
	
	public abstract void receiveMessage();
	
	public Space(String name, int xCoordinate, int yCoordinate) {
		this.name = name;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public String getName()
	{
		return name;
	}

	public int[] getCoordinates() {
		int[] coordinates = {this.xCoordinate, this.yCoordinate};
		
		return coordinates;
	}
}
