package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.Random;

import models.Game;
import models.NormalProp;
import models.Player;
import models.Property;
import models.Space;
import models.SpecialCard;

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
			System.out.println("Could not open the instructions");
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

	public static void buyProperty(Player player, Property prop) {
		//this should be what is called when a player lands on a buyable property
		for (int i = 0; i < game.getPlayers().length; i++) {
			if (game.getPlayers()[i].getProperties().containsValue(prop)) {
				break;
			}
			if (i == game.getPlayers().length - 1) {
				boolean willBuy = false;
				//asks the player if they want to buy the property
				if (willBuy) {
					player.setAmountOfCash(player.getAmountOfCash() - prop.getCostToPurchase());
					giveProperties(player, prop);
				}
			}
		}
	}

	public static void giveCards(Player player, SpecialCard card)
	{
		// adds the card to the players collection.
		player.setSpecialCards(card);
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
		
		if (!player.getSpecialCards().isEmpty()) {
			boolean getOutOfJail = false;
			// asks the user if they want to use their get out of jail free card
			if (getOutOfJail) {
				player.getSpecialCards().remove(0);
				player.setInJail(false);
			}
		} else {

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

	public static void drawCard(Player player, Property prop)
	{
		// stores The card as a special card and adds it to the players card list
		SpecialCard card = Game.drawCard(prop);
		player.setSpecialCards(card);
	}

	public static void buyHouse(Player player, NormalProp prop)
	{
		int numberOfProps = 0;
		for(String i : player.getNormalProps().keySet())
		{
			if(player.getNormalProps().get(i).getColor() == prop.getColor())
			{
				numberOfProps++;
			}
		}
		if(numberOfProps == 3 && !(prop.getColor().equals("blue") && !(prop.getColor().equals("brown"))))
		{

		}
		// Blue and brown only have two props
		else if(numberOfProps == 2 && !(prop.getColor().equals("blue") && !(prop.getColor().equals("brown"))))
		{

		}
		else
		{
			// cant buy house
		}
	}

	public static void chargeRent(Player player, Property prop)
	{
		for (int i = 0; i < game.getPlayers().length; i++) {
			if (game.getPlayers()[i].getProperties().containsValue(prop)) {
				if (!game.getPlayers()[i].getProperties().get(prop.getName()).isMortgaged()) {
					game.getPlayers()[i].setAmountOfCash(game.getPlayers()[i].getAmountOfCash() + prop.getRent());
					player.setAmountOfCash(player.getAmountOfCash() - prop.getRent());
				}
			}
		}
	}
}
