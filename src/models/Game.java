package models;

import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Board board = new Board();
	Player[] players;
	static SpecialCard[] specialCards = new SpecialCard[10];

	public Game(int numberOfPlayers) {
		players = new Player[numberOfPlayers];
	}

	public Player[] getPlayers() {
		return players;
	}

	public static SpecialCard drawCard(SpecialSpace sp)
	{
		if(sp.getName().equals("Chance"))
		{

			Random rng = new Random();
			return specialCards[rng.nextInt(5)];
		} else {
			Random rng = new Random();
			return specialCards[rng.nextInt(5) + 5];
		}

	}

	public void createCards() {
		String[] chanceCards = { "Get out of jail free", "You can leave now I suppose...", "Go back 3 spaces",
				"You forgot something at your apartment. Head on back!", "Advance to Start",
				"You slept for a LONG time. Go to Start.", "Go to Jail", "You got caught torrenting! Go to jail!",
				"Pay everyone $50", "It's time to pay back all of your *ahem* 'tabs.'", "Income tax return",
				"Adulting sucks, but you have money now! Collect $50.", "It's your birthday!",
				"Collect $20 from each player.", "Parent care-package", "Collect $100. Thanks, Ma!", "Doctor's fees",
				"Pay $30", "Won second place at a Smash tournament",
				"Collect $25 (it's an Amazon gift card, but hey: money is money)" };
		for (int i = 0; i < specialCards.length; i += 2) {
			SpecialCard card = new SpecialCard(chanceCards[i], chanceCards[i + 1]);
			specialCards[i] = card;
		}
	}
}
