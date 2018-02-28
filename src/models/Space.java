package models;

public abstract class Space
{
	private String name;

	public abstract void recieveMessage();

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
