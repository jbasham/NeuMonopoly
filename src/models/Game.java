package models;

public class Game
{
	Board board = new Board();
	Player[] players;
	SpecialCard[] specialCards;
	
	public Game (int numberOfPlayers) {
		players = new Player[numberOfPlayers];
	}
}
