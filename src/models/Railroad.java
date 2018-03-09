package models;

public class Railroad extends Property
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Railroad(String name, int xCoordinate, int yCoordinate, int costToPurchase, int mortgage)
	{
		super(name, xCoordinate, yCoordinate);
		setCostToPurchase(costToPurchase);
		setMortgageValue(mortgage);
	}

	public int getRent(Player player)
	{
		int amountOfRailsOwned = 0;
		int rent = 0;
		
		//check player hashmap for multiple, available railroads and increments counter
		for(Property prop : player.properties.values()) {
			if(prop instanceof Railroad && !prop.isMortgaged()) {
				amountOfRailsOwned++;
			}
		}
		
		//sets rent based on the amount of available railroads
		switch(amountOfRailsOwned) {
		case 1:
			rent = 25;
			break;
		case 2:
			rent = 50;
			break;
		case 3:
			rent = 100;
			break;
		case 4:
			rent = 200;
			break;
		}
		
		return rent;
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
