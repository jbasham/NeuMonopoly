package models;

public class Railroad extends Property
{

	public Railroad(String name, int xCoordinate, int yCoordinate, int costToPurchase, int mortgage)
	{
		super(name, xCoordinate, yCoordinate);
		setCostToPurchase(costToPurchase);
		setMortgageValue(mortgage);
	}

	@Override
	public int getRent()
	{
		return getCostOfRent();
	}


	@Override
	public void receiveMessage()
	{
		// TODO Auto-generated method stub
		
	}

}
