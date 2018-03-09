package models;

public class Utility extends Property
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Utility(String name, int xCoordinate, int yCoordinate, int costToPurchase, int mortgage)
	{
		super(name, xCoordinate, yCoordinate);
		setCostToPurchase(costToPurchase);
		setMortgageValue(mortgage);
	}

	public int getRent(int[] diceRoll, Player player)
	{
		// This is an overloaded method rather than an overridden one now. On the fly
		// changes ftw!
		int utilitiesOwned = 0;
		int diceTotal = diceRoll[0] + diceRoll[1];
		int total = 0;

		// loops through the players properties and increments utilitiesOwned if they
		// own more than 1
		// I realized this could be done more efficiently after I finished it, but for
		// now this works
		for(Property prop : player.getProperties().values())
		{
			if(prop.getName().equals("Neumont Cafe") || prop.getName().equals("Food Court"))
			{
				utilitiesOwned++;
			}
		}

		switch(utilitiesOwned)
		{
		case 1:
			total = diceTotal * 4;
			break;
		case 2:
			total = diceTotal * 12;
			break;
		}

		return total;
	}

	@Override
	public void receiveMessage()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getRent()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
