package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String, Property> properties = new HashMap<>();
	ArrayList<SpecialCard> specialCards = new ArrayList<>();
	String name;
	int amountOfCash;
	boolean inJail;
	//position x/y
	
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
}
