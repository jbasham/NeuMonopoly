package models;

public class Utility extends Property
{
	
	public Utility(String name, int xCoordinate, int yCoordinate, int costToPurchase, int mortgage) {
		super(name, xCoordinate, yCoordinate);
		setCostToPurchase(costToPurchase);
		setMortgageValue(mortgage);
	}
	
	@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void receiveMessage() {
		// TODO Auto-generated method stub
		
	}

	
	
}
