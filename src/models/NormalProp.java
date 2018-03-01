package models;

public class NormalProp extends Property
{
	int numOfHouses = 0, housePrice, houseAdditionalRent;
	
	public NormalProp(String name, String color, int costToPurchase, int costOfRent, int mortgage, int housePrice, int additionalRent) {
		
	}

	public int getNumOfHouses() {
		return numOfHouses;
	}

	public void setNumOfHouses(int numOfHouses) {
		this.numOfHouses = numOfHouses;
	}

	public int getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	public int getHouseAdditionalRent() {
		return houseAdditionalRent;
	}

	public void setHouseAdditionalRent(int houseAdditionalRent) {
		this.houseAdditionalRent = houseAdditionalRent;
	}

	@Override
	public int getRent() {
		
		return 0;
	}

	@Override
	public void receiveMessage() {
		// TODO Auto-generated method stub
		
	}
	
}
