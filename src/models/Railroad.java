package models;

public class Railroad extends Property
{

	public Railroad(String name, int costToPurchase, int mortgage)
	{
		setName(name);
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
