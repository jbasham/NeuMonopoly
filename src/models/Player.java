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
	// added a normal properties so i can get the colors.
	HashMap<String, NormalProp> normalProps = new HashMap<>();
	ArrayList<SpecialCard> specialCards = new ArrayList<>();
	String name;
	int amountOfCash, jailTurns;
	boolean inJail;
	int[] coordinates;

	public Player(String name)
	{
		setName(name);
	}

	public HashMap<String, Property> getProperties()
	{
		return properties;
	}

	public void setProperties(Property property)
	{
		properties.put(property.getName(), property);
	}

	public HashMap<String, NormalProp> getNormalProps()
	{
		return normalProps;
	}

	public void setNormalProps(NormalProp property)
	{
		normalProps.put(property.getName(), property);
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
		if(inJail)
		{
			setJailTurns(0);
		}
	}

	public int[] getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates(int[] coordinates)
	{
		this.coordinates = coordinates;
	}

	public int getJailTurns()
	{
		return jailTurns;
	}

	public void setJailTurns(int jailTurns)
	{
		this.jailTurns = jailTurns;
	}

}
