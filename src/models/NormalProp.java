package models;

public class NormalProp extends Property
{
	private final String color;
	private int numOfHouses;
	private int housePrice;
	private int houseAdditionalRent;

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
		return houseAdditionalRent;
	}

	public void setHouseAdditionalRent(int houseAdditionalRent)
	{
		this.houseAdditionalRent = houseAdditionalRent;
	}

	public String getColor()
	{
		return color;
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
