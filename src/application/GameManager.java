package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;

import models.Game;
import models.NormalProp;
import models.Player;
import models.Property;
import models.Space;
import models.SpecialCard;
import models.SpecialSpace;

public class GameManager
{
	public static int freeParkingSpaceMoney;

	public static Game game;

	public static void newGame(int numOfPlayers)
	{
		Game newGame = new Game(numOfPlayers);
		game = newGame;
	}

	public static void instructions()
	{
		// opens a webpage with the instructions. ezpz
		try
		{
			java.awt.Desktop.getDesktop().browse(new URI("http://richard_wilding.tripod.com/monorules.htm"));
		}
		catch(Exception e)
		{
			// Nick make it tell the person that the instructions could not be opened
		}
	}

	public static void save()
	{
		// saves the current game to a file
		try
		{
			FileOutputStream fout = new FileOutputStream("SavedGame.txt");
			ObjectOutputStream out = new ObjectOutputStream(fout);

			out.writeObject(game);
			out.close();
		}
		catch(Exception e)
		{
			System.out.println("Could not save to file");
		}
	}

	public static void load()
	{
		// loads a game from a file
		// RETURNS A GAME OBJECT THAT WE SHOULD USE INSTEAD OF MAKING A NEW ONE WITH
		// newGame()
		try
		{
			FileInputStream fin = new FileInputStream("SavedGame.txt");
			ObjectInputStream oin = new ObjectInputStream(fin);

			Game g = (Game) oin.readObject();
			oin.close();
			game = g;
		}
		catch(Exception e)
		{
			System.out.println("No saved game found");
		}
	}

	public static int[] rollDice()
	{
		// Creates two dice and gets a random number 1-6 and returns the array.
		Random rng = new Random();
		int[] dice = new int[2];
		dice[0] = rng.nextInt(5) + 1;
		dice[1] = rng.nextInt(5) + 1;

		return dice;
	}

	public static void updateFunds(Player player, int money)
	{
		// Set the cash amount to the current plus money passed in.
		player.setAmountOfCash(player.getAmountOfCash() + money);
	}

	public static void giveProperties(Player player, Property prop)
	{
		// add prop to the players prop collection
		player.setProperties(prop);
	}

	public static void buyProperty(Player player, Space space)
	{
		// this should be what is called when a player lands on a buyable property
		try
		{
			Property thisProp = (Property) space;
			for(int i = 0; i < game.getPlayers().length; i++)
			{
				if(game.getPlayers()[i].getProperties().containsValue(thisProp))
				{
					chargeRent(player, thisProp);
					break;
				}
				if(i == game.getPlayers().length - 1)
				{
					boolean willBuy = false;
					// asks the player if they want to buy the property
					if(willBuy)
					{
						player.setAmountOfCash(player.getAmountOfCash() - thisProp.getCostToPurchase());
						giveProperties(player, thisProp);
					}
				}
			}
		}
		catch(Exception e)
		{

		}
		try
		{
			SpecialSpace thisSpecialSpace = (SpecialSpace) space;
			switch(thisSpecialSpace.getName())
			{
			case ("Start"):
				updateFunds(player, 200);
				break;
			case ("Lootbox"):
				drawCard(player, thisSpecialSpace);
				break;
			case ("Chance"):
				drawCard(player, thisSpecialSpace);
				break;
			case ("Clubfees"):
				updateFunds(player, -200);
				addFreeParkingMoney(200);
				break;
			case ("Tuitionfees"):
				updateFunds(player, -100);
				addFreeParkingMoney(100);
				break;
			case ("Jail"):
				break;
			case ("Free Parking"):
				updateFunds(player, freeParkingSpaceMoney);
				freeParkingSpaceMoney = 0;
				break;
			case ("Go to Jail"):
				player.setCoordinates(new int[] {0, 10});
				player.setInJail(true);
				break;
			}
		}
		catch(Exception e)
		{

		}
		if (player.getAmountOfCash() < 0) {
			emergencyRefunding(player);
		}
	}
	
	public static void emergencyRefunding(Player player) {
		int propsWithHouses = player.getNormalProps().size();
		boolean outOfFunds = true;
		while (outOfFunds) {
			for (NormalProp prop : player.getNormalProps().values()) {
				if (prop.getNumOfHouses() > 0) {
					player.setAmountOfCash(player.getAmountOfCash() + (prop.getHousePrice()/2));
				} else {
					propsWithHouses--;
				}
				if (player.getAmountOfCash() >= 0) {
					outOfFunds = false;
					break;
				}
			}
			if (propsWithHouses == 0) {
				for (NormalProp prop : player.getNormalProps().values()) {
					player.setAmountOfCash(player.getAmountOfCash() + prop.getMortgageValue());
					prop.setMortgaged(true);
					if (player.getAmountOfCash() >= 0) {
						outOfFunds = false;
						break;
					}
				}
			}
		}
	}

	public static void useCard(Player player, SpecialCard card)
	{
		if(card.getName().equals("Get out of jail free"))
		{
			// adds the card to the players collection.
			player.setSpecialCards(card);
		}
		else if(card.getName().equals("Go back 3 spaces"))
		{
			//sets the coordinates of the player to three spaces behind where they are now
			for(int i = 0; i<game.board.spaces.length; i++) {
				
				if(player.getCoordinates() == game.board.spaces[i].getCoordinates()) {
					player.setCoordinates(game.board.spaces[i-3].getCoordinates());
					break;
				}
					
			}
		}
		else if(card.getName().equals("Advance to Start"))
		{
			// sets the player's coordinates to the (10,10) (Start coordinates)
			player.setCoordinates(new int[] {10, 10});
		}
		else if(card.getName().equals("Go to Jail"))
		{
			// sends a player to the jail space and sets the inJail boolean to 'true'
			player.setCoordinates(new int[] {0, 10});
			player.setInJail(true);
		}
		else if(card.getName().equals("Pay everyone $50"))
		{
			Player[] players = game.getPlayers();
			int otherPlayers = 0;

			// adds $50 to every player besides the one that that drew the card
			for(Player p : players)
			{
				if(!p.getName().equals(player.getName()))
				{
					p.setAmountOfCash(p.getAmountOfCash() + 50);
					otherPlayers++;
				}
			}

			// subtracts $50 for every other player there is from the player that drew the
			// card
			player.setAmountOfCash(player.getAmountOfCash() - (50 * otherPlayers));
		}
		else if(card.getName().equals("Income tax return"))
		{
			// adds $50 to the player that drew the card
			player.setAmountOfCash(player.getAmountOfCash() + 50);
		}
		else if(card.getName().equals("It's your birthday!"))
		{
			Player[] players = game.getPlayers();
			int otherPlayers = 0;

			// subtracts $20 from every player besides the one that that drew the card
			for(Player p : players)
			{
				if(!p.getName().equals(player.getName()))
				{
					p.setAmountOfCash(p.getAmountOfCash() - 20);
					otherPlayers++;
				}
			}

			// gives $20 for every other player there is to the player that drew the card
			player.setAmountOfCash(player.getAmountOfCash() + (20 * otherPlayers));
		}
		else if(card.getName().equals("Parent care-package"))
		{
			// gives $100 to the player that drew the card
			player.setAmountOfCash(player.getAmountOfCash() + 100);
		}
		else if(card.getName().equals("Doctor's fees"))
		{
			// subtracts $30 from the player that drew the card
			player.setAmountOfCash(player.getAmountOfCash() - 30);
		}
		else if(card.getName().equals("Won second place at a Smash tournament"))
		{
			// gives $25 to the player that drew the card
			player.setAmountOfCash(player.getAmountOfCash() + 25);
		}
	}

	public static int addFreeParkingMoney(int money)
	{
		// Adds the money to parking space.
		freeParkingSpaceMoney += money;
		// not sure if we need a return.
		return 0;
	}

	public static void jail(Player player)
	{
		int[] escapeAttempt;
		escapeAttempt = rollDice();
		// Temp so no error
		boolean payBail = false;
		// asks the user if they want to pay $50 bail

		if(!player.getSpecialCards().isEmpty())
		{
			boolean getOutOfJail = false;
			// asks the user if they want to use their get out of jail free card
			if(getOutOfJail)
			{
				player.getSpecialCards().remove(0);
				player.setInJail(false);
			}
		}else{

			if(payBail)
			{
				player.setAmountOfCash(player.getAmountOfCash() - 50);
				player.setInJail(false);
			}

			if(escapeAttempt[0] == escapeAttempt[1])
			{
				player.setInJail(false);
			}
			else
			{
				player.setJailTurns(player.getJailTurns() + 1);
			}

			if(player.getJailTurns() > 1)
			{
				player.setAmountOfCash(player.getAmountOfCash() - 50);
				player.setInJail(false);
			}
		}
	}

	public static void goToJail(Player player)
	{
		player.setInJail(true);
		int[] jailCords = {0, 10};
		// sets the player coordinates to jail.
		player.setCoordinates(jailCords);
	}

	public static void giveFreeParkingGiveMoney(Player player)
	{
		// Set the cash amount to the current plus the Free parking.
		player.setAmountOfCash(player.getAmountOfCash() + freeParkingSpaceMoney);
		// Set the free parking to 0.
		freeParkingSpaceMoney = 0;
	}

	public static void drawCard(Player player, SpecialSpace sp)
	{
		// stores The card as a special card and adds it to the players card list
		SpecialCard card = Game.drawCard(sp);
		useCard(player, card);
	}

	public static void buyHouse(Player player, NormalProp prop)
	{
		boolean ownsAllColors = false;
		String propColor = prop.getColor();
		ArrayList<NormalProp> ownedProps = getPropsByColor(player, propColor);

		if(ownedProps.size() == 2 && (propColor.equals("blue") || propColor.equals("brown")))
		{
			ownsAllColors = true;
		}
		else if(ownedProps.size() == 3 && (!propColor.equals("blue") && !propColor.equals("brown")))
		{
			ownsAllColors = true;
		}
		// checks of they have no mortgage props on the same color.
		boolean noMortgage = true;

		for(NormalProp property : ownedProps)
		{
			if(property.isMortgaged())
			{
				noMortgage = false;
			}
		}

		// Check if they have even number of houses on each prop.
		if(ownsAllColors && prop.getNumOfHouses() < 5 && noMortgage)
		{
			if(checkIfHousesAreEven(ownedProps, prop))
			{
				prop.setNumOfHouses(prop.getNumOfHouses() + 1);
				player.setAmountOfCash(player.getAmountOfCash() - prop.getHousePrice());
			}
		}
		else
		{
			// tell the user they can't buy a house
			// NICK HELP!!
		}

	}

	public static boolean checkIfHousesAreEven(ArrayList<NormalProp> props, NormalProp prop)
	{

		int p1HouseCount = 0;
		int p2HouseCount = 0;
		int counter = 0;
		// Adds the other props houses to the int values
		for(NormalProp ownedProp : props)
		{
			if(ownedProp != prop && counter == 0)
			{
				p1HouseCount = ownedProp.getNumOfHouses();
			}
			else if(ownedProp != prop && counter == 1)
			{
				p2HouseCount = ownedProp.getNumOfHouses();
			}
			counter++;
		}
		// checks if they can buy
		if(props.size() == 2)
		{
			if(p1HouseCount == (prop.getNumOfHouses() + 1) || p1HouseCount == prop.getNumOfHouses())
			{
				return true;
			}
			else
			{
				// if the have three props. The logic decides if the can buy a house.
				if(p1HouseCount == prop.getNumOfHouses() || p2HouseCount == prop.getNumOfHouses())
				{
					return true;
				}
			}
			// they can't buy the house
			return false;
		}

		return true;
	}

	// added the method to return all the props with the same color.
	public static ArrayList<NormalProp> getPropsByColor(Player player, String color)
	{
		ArrayList<NormalProp> properties = new ArrayList<>();
		for(Property props : player.getProperties().values())
		{
			try
			{
				if(((NormalProp) props).getColor() == color)
				{
					properties.add((NormalProp) props);
				}
			}
			catch(ClassCastException e)
			{

			}
		}
		return properties;
	}

	public static void chargeRent(Player player, Property prop)
	{
		for(int i = 0; i < game.getPlayers().length; i++)
		{
			if(game.getPlayers()[i].getProperties().containsValue(prop))
			{
				if(!game.getPlayers()[i].getProperties().get(prop.getName()).isMortgaged())
				{
					game.getPlayers()[i].setAmountOfCash(game.getPlayers()[i].getAmountOfCash() + prop.getRent());
					player.setAmountOfCash(player.getAmountOfCash() - prop.getRent());
				}
			}
		}
	}

	public static boolean checkWin()
	{
		boolean win = false;

		// checks if only one player is left
		if(game.getPlayers().length == 1)
		{
			win = true;
		}

		return win;
	}

	public static void unmortgage(Player player, Property prop)
	{
		// cost is the mortgage value of the property that is passed in plus 10%
		float cost = ((float) prop.getMortgageValue()) * 1.1f;

		// changes isMortgaged to false if the player has enough money
		if(player.getAmountOfCash() >= cost)
		{
			prop.setMortgaged(false);
			player.setAmountOfCash(player.getAmountOfCash() - (int) cost);
		}
		else
		{
			// displays a message saying the player doesn't have enough money to unmortgage
			// the property
			// NICK HELP!!
		}
	}

	public static void playerMortgage(Player player, Property prop)
	{
		if(prop instanceof NormalProp)
		{
			ArrayList<NormalProp> ownedProps = getPropsByColor(player, prop.getName());
			for(NormalProp property : ownedProps)
			{
				player.setAmountOfCash(
						player.getAmountOfCash() + ((property.getHousePrice() * property.getNumOfHouses()) / 2));
				property.setNumOfHouses(0);
			}
		}
		prop.setMortgaged(true);
		player.setAmountOfCash(player.getAmountOfCash() + prop.getMortgageValue());
	}
}
