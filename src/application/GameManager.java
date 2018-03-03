package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.Random;

import javax.swing.JFrame;

import models.Game;
import models.Player;
import models.Property;
import models.SpecialCard;

public class GameManager
{
	public static int freeParkingSpaceMoney;
	
	public static Game newGame(int numOfPlayers)
	{
		Game game = new Game(numOfPlayers);
		return game;
	}

	public static void instructions()
	{
		//opens a webpage with the instructions. ezpz
		try {
			java.awt.Desktop.getDesktop().browse(new URI("http://richard_wilding.tripod.com/monorules.htm"));
		} catch (Exception e) {
			System.out.println("Could not open the instructions");
		}
	}

	public static void save(Game g) {
		//saves the current game to a file
		//MUST PASS IN THE CURRENT GAME
		try {
			FileOutputStream fout = new FileOutputStream("SavedGame.txt");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			
			out.writeObject(g);
			out.close();
		} catch (Exception e) {
			System.out.println("Could not save to file");
		}
	}
	
	public static Game load()
	{
		//loads a game from a file
		//RETURNS A GAME OBJECT THAT WE SHOULD USE INSTEAD OF MAKING A NEW ONE WITH newGame()
		try {
			FileInputStream fin = new FileInputStream("SavedGame.txt");
			ObjectInputStream oin = new ObjectInputStream(fin);
			
			Game g = (Game)oin.readObject();
			oin.close();
			return g;
		} catch (Exception e) {
			System.out.println("No saved game found");
		}
		return null;
	}

	public static int[] rollDice()
	{
		//Creates two dice and gets a random number 1-6 and returns the array.
		Random rng = new Random();
		int[] dice = new int[2];
		dice[0] = rng.nextInt(5) + 1;
		dice[1] = rng.nextInt(5) + 1;

		return dice;
	}

	public static void updateFunds(Player player, int money)
	{
		//Set the cash amount to the current plus money passed in.
		player.setAmountOfCash(player.getAmountOfCash() + money);
	}

	public static void giveProperties(Player player, Property prop)
	{
		//add prop to the players prop collection
		player.setProperties(prop);
	}

	public static void giveCards(Player player, SpecialCard card)
	{
		//adds the card to the players collection.
		player.setSpecialCards(card);
	}

	public static int addFreeParkingMoney(int money)
	{ 
		//Adds the money to parking space.
		freeParkingSpaceMoney += money;
		//not sure if we need a return.
		return 0;
	}

	public static void jail(Player player)
	{

	}

	public static void goToJail(Player player)
	{

	}

	public static void giveFreeParkingGiveMoney(Player player)
	{
		//Set the cash amount to the current plus the Free parking.
		player.setAmountOfCash(player.getAmountOfCash() + freeParkingSpaceMoney);
		//Set the free parking to 0.
		freeParkingSpaceMoney = 0;
	}

	public static void drawCard()
	{
		
	}

	public static void chargeRent()
	{

	}
}
