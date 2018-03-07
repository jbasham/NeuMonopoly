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

public class GameManager {
	public static int freeParkingSpaceMoney;

	public static Game game;

	public static void newGame(int numOfPlayers) {
		Game newGame = new Game(numOfPlayers);
		game = newGame;
	}

	public static void instructions() {
		// opens a webpage with the instructions. ezpz
		try {
			java.awt.Desktop.getDesktop().browse(new URI("http://richard_wilding.tripod.com/monorules.htm"));
		} catch (Exception e) {
			// Nick make it tell the person that the instructions could not be opened
		}
	}

	public static void save() {
		// saves the current game to a file
		try {
			FileOutputStream fout = new FileOutputStream("SavedGame.txt");
			ObjectOutputStream out = new ObjectOutputStream(fout);

			out.writeObject(game);
			out.close();
		} catch (Exception e) {
			System.out.println("Could not save to file");
		}
	}

	public static void load() {
		// loads a game from a file
		// RETURNS A GAME OBJECT THAT WE SHOULD USE INSTEAD OF MAKING A NEW ONE WITH
		// newGame()
		try {
			FileInputStream fin = new FileInputStream("SavedGame.txt");
			ObjectInputStream oin = new ObjectInputStream(fin);

			Game g = (Game) oin.readObject();
			oin.close();
			game = g;
		} catch (Exception e) {
			System.out.println("No saved game found");
		}
	}

	public static int[] rollDice() {
		// Creates two dice and gets a random number 1-6 and returns the array.
		Random rng = new Random();
		int[] dice = new int[2];
		dice[0] = rng.nextInt(5) + 1;
		dice[1] = rng.nextInt(5) + 1;

		return dice;
	}

	public static void updateFunds(Player player, int money) {
		// Set the cash amount to the current plus money passed in.
		player.setAmountOfCash(player.getAmountOfCash() + money);
	}

	public static void giveProperties(Player player, Property prop) {
		// add prop to the players prop collection
		player.setProperties(prop);
	}

	public static void buyProperty(Player player, Space space) {
		//this should be what is called when a player lands on a buyable property
		try {
			Property thisProp = (Property)space;
			for (int i = 0; i < game.getPlayers().length; i++) {
				if (game.getPlayers()[i].getProperties().containsValue(thisProp)) {
					chargeRent(player, thisProp);
					break;
				}
				if (i == game.getPlayers().length - 1) {
					boolean willBuy = false;
					//asks the player if they want to buy the property
					if (willBuy) {
						player.setAmountOfCash(player.getAmountOfCash() - thisProp.getCostToPurchase());
						giveProperties(player, thisProp);
					}
				}
			}
		} catch (Exception e) {
			
		}
		try {
			SpecialSpace thisSpecialSpace = (SpecialSpace)space;
			switch (thisSpecialSpace.getName()) {
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
		} catch (Exception e) {
			
		}
	}

	public static void giveCards(Player player, SpecialCard card) {
		// adds the card to the players collection.
		player.setSpecialCards(card);
	}

	public static int addFreeParkingMoney(int money) {
		// Adds the money to parking space.
		freeParkingSpaceMoney += money;
		// not sure if we need a return.
		return 0;
	}

	public static void jail(Player player) {
		int[] escapeAttempt;
		escapeAttempt = rollDice();
		// Temp so no error
		boolean payBail = false;
		// asks the user if they want to pay $50 bail

		if (!player.getSpecialCards().isEmpty()) {

		if(!player.getSpecialCards().isEmpty())
		{
			boolean getOutOfJail = false;
			// asks the user if they want to use their get out of jail free card
			if(getOutOfJail)
			{
				player.getSpecialCards().remove(0);
				player.setInJail(false);
			}
		}
		else
		{

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

	public static void goToJail(Player player) {
		player.setInJail(true);
		int[] jailCords = { 0, 10 };
		// sets the player coordinates to jail.
		player.setCoordinates(jailCords);
	}

	public static void giveFreeParkingGiveMoney(Player player) {
		// Set the cash amount to the current plus the Free parking.
		player.setAmountOfCash(player.getAmountOfCash() + freeParkingSpaceMoney);
		// Set the free parking to 0.
		freeParkingSpaceMoney = 0;
	}

	public static void drawCard(Player player, SpecialSpace sp) {
		// stores The card as a special card and adds it to the players card list
		SpecialCard card = Game.drawCard(sp);
		useCards(player, card);
	}

	public static void buyHouse(Player player, NormalProp prop)
	{
		boolean ownsAllColors = false;
		int numberOfProps = 0;
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

		// Check if they have even number of houses on each prop.
		if(ownsAllColors)
		{
			if(checkIfHousesAreEven(ownedProps, prop))
			{
				prop.setNumOfHouses(prop.getNumOfHouses() + 1);
				player.setAmountOfCash(player.getAmountOfCash() - prop.getHousePrice());
			}
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
				//if the have three props. The logic decides if the can buy a house.
				if(p1HouseCount == prop.getNumOfHouses() || p2HouseCount == prop.getNumOfHouses())
				{
					return true;
				}
			}
			//they can't buy the house
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
}
