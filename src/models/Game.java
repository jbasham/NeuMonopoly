package models;

public class Game
{
	Board board = new Board();
	Player[] players;
	SpecialCard[] specialCards = new SpecialCard[10];
	
	public Game (int numberOfPlayers) {
		players = new Player[numberOfPlayers];
	}
}
