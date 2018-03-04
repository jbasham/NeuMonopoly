package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Player
{
	HashMap<String, Property> properties = new HashMap<>();
	ArrayList<SpecialCard> specialCards = new ArrayList<>();
	String name;
	int amountOfCash, jailTurns;
	boolean inJail;
	int[] coordinates;
	
	public Player(String name) {
		setName(name);
	}

	public HashMap<String, Property> getProperties()
	{
		return properties;
	}

	public void setProperties(Property property) {
		properties.put(property.getName(), property);
	}

	public ArrayList<SpecialCard> getSpecialCards()
	{
		return specialCards;
	}

	public void setSpecialCards(SpecialCard card)
	{
		specialCards.add(card);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAmountOfCash()
	{
		return amountOfCash;
	}

	public void setAmountOfCash(int amountOfCash)
	{
		this.amountOfCash = amountOfCash;
	}

	public boolean isInJail()
	{
		return inJail;
	}

	public void setInJail(boolean inJail)
	{
		this.inJail = inJail;
	}
	
	public void useGetOutOfJail(){
		
	}

	public int[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	public int getJailTurns() {
		return jailTurns;
	}

	public void setJailTurns(int jailTurns) {
		this.jailTurns = jailTurns;
	}
	
}
