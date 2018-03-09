package models;

public class NormalProp extends Property
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String color;
	private int numOfHouses;
	private int housePrice;

	public NormalProp(String name, int xCoordinate, int yCoordinate, String color, int costToPurchase, int costOfRent,
			int mortgage, int housePrice)
	{
		super(name, xCoordinate, yCoordinate);
		this.color = color;
		setCostToPurchase(costToPurchase);
		setCostOfRent(costOfRent);
		setMortgageValue(mortgage);
		setHousePrice(housePrice);

	}

	public int getNumOfHouses()
	{
		return numOfHouses;
	}

	public void setNumOfHouses(int numOfHouses)
	{
		this.numOfHouses = numOfHouses;
	}

	public int getHousePrice()
	{
		return housePrice;
	}

	public void setHousePrice(int housePrice)
	{
		this.housePrice = housePrice;
	}

	public int getHouseAdditionalRent()
	{
		int houseAdditionalRent = 0;
		
		//calculates extra rent to be charged based off of how many houses are on the property
		//5 houses would be the equivalent of a hotel
		if(getNumOfHouses() == 1) {
			houseAdditionalRent = ((getCostOfRent()/2)*10)-getCostOfRent();
		} else if (getNumOfHouses() == 2) {
			houseAdditionalRent = (((getCostOfRent()/2)*10)*3)-getCostOfRent();
		} else if (getNumOfHouses() == 3) {
			houseAdditionalRent = (((getCostOfRent()/2)*10)*6)-getCostOfRent();
		} else if (getNumOfHouses() == 4) {
			houseAdditionalRent = ((((getCostOfRent()/2)*10)*6)+150)-getCostOfRent();
		} else if (getNumOfHouses() == 5) {
			houseAdditionalRent = ((((getCostOfRent()/2)*10)*6)+300)-getCostOfRent();
		}
		return houseAdditionalRent;
	}

	public String getColor()
	{
		return color;
	}

	@Override
	public int getRent()
	{
		
		if(!this.isMortgaged()) {
			//adds the cost of rent to the additional rent to be charged by the houses on the property
			return (getCostOfRent() + getHouseAdditionalRent());
		} else {
			return 0;
		}
	}

	@Override
	public void receiveMessage()
	{
		// TODO Auto-generated method stub

	}

}
