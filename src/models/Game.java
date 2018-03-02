package models;

public class Game
{
	Board board = new Board();
	Player[] players;
	SpecialCard[] specialCards = new SpecialCard[10];

	public Game(int numberOfPlayers)
	{
		players = new Player[numberOfPlayers];
	}

	public void createCards()
	{
		String[] chanceCards = {"Get out of IT free.", "Computer was fixed leave IT", "Go back 3 spaces.",
				"You forgot something", "Advance to go.", "Go to go", "Go to IT.",
				"Your computer stops working go to IT", "Pay everyone $50.", "Pay everyone $50", "Income tax",
				"Collect $20", "Its your birthday", "Collect $50 from each player", "You inherit $100", "Collect $100",
				"Doctor's fees", "Pay $20", "Won second place.", "Collect $10"};
		for(int i = 0; i < specialCards.length; i += 2)
		{
			SpecialCard card = new SpecialCard(chanceCards[i], chanceCards[i + 1]);
			specialCards[i] = card;
		}
	}
}
