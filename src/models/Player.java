package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Player
{
	HashMap<String, Property> properties =  new HashMap<>();
	ArrayList<SpecialCard> lootBoxCards = new ArrayList<>();
	String name;
	int amountOfCash;
	boolean inJail;
	int x;
	int y;
	
	public Player(String name) {
		setName(name);
	}

	public HashMap<String, Property> getProperties() {
		return properties;
	}

	public void setProperties(Property property) {
		properties.put(property.getName(), property);
	}

	public ArrayList<SpecialCard> getLootBoxCards() {
		return lootBoxCards;
	}

	public void setLootBoxCards(ArrayList<SpecialCard> lootBoxCards) {
		this.lootBoxCards = lootBoxCards;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountOfCash() {
		return amountOfCash;
	}

	public void setAmountOfCash(int amountOfCash) {
		this.amountOfCash = amountOfCash;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void useGetOutOfJail(){
		
	}
}
