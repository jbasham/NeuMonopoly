package models;

public abstract class Property extends Space
{
	String color;
	int costToPurchase, costOfRent, mortgageValue;
	boolean isMorgaged;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getCostToPurchase() {
		return costToPurchase;
	}
	public void setCostToPurchase(int costToPurchase) {
		this.costToPurchase = costToPurchase;
	}
	public int getCostOfRent() {
		return costOfRent;
	}
	public void setCostOfRent(int costOfRent) {
		this.costOfRent = costOfRent;
	}
	public int getMortgageValue() {
		return mortgageValue;
	}
	public void setMortgageValue(int mortgageValue) {
		this.mortgageValue = mortgageValue;
	}
	public boolean isMorgaged() {
		return isMorgaged;
	}
	public void setMorgaged(boolean isMorgaged) {
		this.isMorgaged = isMorgaged;
	}
	
	public abstract int getRent();
	
}
