package models;

public abstract class Property extends Space
{
	
	private int costToPurchase;
	private int costOfRent;
	private int mortgageValue;
	private boolean isMortgaged;
	
	public Property(String name, int xCoordinate, int yCoordinate) {
		super(name, xCoordinate, yCoordinate);
	}

	public int getCostToPurchase()
	{
		return costToPurchase;
	}

	public void setCostToPurchase(int costToPurchase)
	{
		this.costToPurchase = costToPurchase;
	}

	public int getCostOfRent()
	{
		return costOfRent;
	}

	public void setCostOfRent(int costOfRent)
	{
		this.costOfRent = costOfRent;
	}

	public int getMortgageValue()
	{
		return mortgageValue;
	}

	public void setMortgageValue(int mortgageValue)
	{
		this.mortgageValue = mortgageValue;
	}

	public boolean isMortgaged()
	{
		return isMortgaged;
	}

	public void setMortgaged(boolean isMortgaged)
	{
		this.isMortgaged = isMortgaged;
	}

	public int getRent()
	{
		return getCostOfRent();

	}
}
