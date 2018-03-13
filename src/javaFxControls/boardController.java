package javaFxControls;

import java.io.IOException;

import application.GameManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Game;
import models.NormalProp;
import models.Player;
import models.Property;
import models.Railroad;
import models.SpecialSpace;
import models.Utility;

public class boardController
{

	public static int playerPlaying = 0;
	private Stage primaryStage;
	@FXML
	private ImageView PieceFour;

	@FXML
	private ImageView PieceThree;

	@FXML
	private ImageView PieceTwo;

	@FXML
	private ImageView PieceOne;
	@FXML
	private GridPane boardPane;

	@FXML
	private ChoiceBox<String> onePlayerProp;

	@FXML
	private ChoiceBox<String> twoPlayerProp;

	@FXML
	private ChoiceBox<String> threePlayerProp;

	@FXML
	private ChoiceBox<String> playerFourProp;

	@FXML
	private TextField whatPlayer;

	@FXML
	private ImageView rolledOne;

	@FXML
	private ImageView rolledTwo;

	@FXML
	private Button rollButton;

	@FXML
	private Button endButton;

	@FXML
	private Button buyYesButton;

	@FXML
	private Button buyNoButton;

	@FXML
	private TextField buyText;

	@FXML
	private TextField infoBox;

	// could figure out how to make it readable
	// @FXML
	// private TextField playerOneMoney;
	// @FXML
	// private TextField playerTwoMoney;
	// @FXML
	// private TextField playerThreeMoney;
	// @FXML
	// private TextField playerFourMoney;

	@FXML
	public StackPane stack1 = new StackPane();

	@FXML
	public StackPane stack2 = new StackPane();

	@FXML
	public StackPane stack3 = new StackPane();

	@FXML
	public StackPane stack4 = new StackPane();

	@FXML
	void handleBuyYesButton(ActionEvent event)
	{
		GameManager.willBuy = true;
		Player[] players = GameManager.game.getPlayers();
		GameManager.buyProperty(players[playerPlaying],
				GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
		buyYesButton.setVisible(false);
		buyNoButton.setVisible(false);
		buyText.setVisible(false);
		infoBoxText("You bought " + GameManager.game.board.spaces[players[playerPlaying].getIndex()].getName());
		// onePlayerProp.set(players[playerPlaying].getProperties().keySet());

		switch(playerPlaying)
		{
		case 0:
			onePlayerProp.getItems().add(GameManager.game.board.spaces[players[playerPlaying].getIndex()].getName());
			break;
		case 1:
			twoPlayerProp.getItems().add(GameManager.game.board.spaces[players[playerPlaying].getIndex()].getName());
			break;
		case 2:
			threePlayerProp.getItems().add(GameManager.game.board.spaces[players[playerPlaying].getIndex()].getName());
			break;
		case 3:
			playerFourProp.getItems().add(GameManager.game.board.spaces[players[playerPlaying].getIndex()].getName());
			break;
		}
	}

	@FXML
	void handleBuyNoButton(ActionEvent event)
	{
		GameManager.willBuy = false;
		Player[] players = GameManager.game.getPlayers();
		GameManager.buyProperty(players[playerPlaying],
				GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
		buyYesButton.setVisible(false);
		buyNoButton.setVisible(false);
		buyText.setVisible(false);
	}

	@FXML
	void handleBuyText(ActionEvent event)
	{

	}

	@FXML
	void handleEndButton(ActionEvent event)
	{
		Player[] players = GameManager.game.getPlayers();
		playerPlaying++;
		if(playerPlaying > GameManager.game.getPlayers().length - 1)
		{
			playerPlaying = 0;
		}
		rollButton.setDisable(false);
		changeText();
		endButton.setDisable(true);
		infoBoxText(players[playerPlaying-1].getName() + " ended their turn.");
	}

	@FXML
	void handleRollButton(ActionEvent event)
	{
		// boolean isOwned = false;
		// handlePlayerOneMoney();
		// handlePlayerTwoMoney();
		// handlePlayerThreeMoney();
		// handlePlayerFourMoney();

		String[] urls = {"Dice\\Dice\\Dice1.png", "Dice\\Dice\\Dice2.png", "Dice\\Dice\\Dice3.png",
				"Dice\\Dice\\Dice4.png", "Dice\\Dice\\Dice5.png", "Dice\\Dice\\Dice6.png"};
		int[] rolledNums = GameManager.rollDice();

		rolledOne.setImage(new Image(urls[rolledNums[0] - 1]));
		rolledTwo.setImage(new Image(urls[rolledNums[1] - 1]));
		Player[] players = GameManager.game.getPlayers();
		if(rolledNums[0] != rolledNums[1])
		{
			rollButton.setDisable(true);
			endButton.setDisable(false);
		}
		// for(int i = 0; i < GameManager.game.board.spaces.length; i++) {
		// if(GameManager.game.board.spaces[i].getCoordinates().equals(players[playerPlaying].getCoordinates()))
		// {

		if(players[playerPlaying].getIndex() + (rolledNums[0] + rolledNums[1]) > 39)
		{
			players[playerPlaying].setIndex(players[playerPlaying].getIndex() + rolledNums[0] + rolledNums[1] - 40);
			players[playerPlaying].setAmountOfCash(players[playerPlaying].getAmountOfCash() + 200);
		}
		else
		{
			players[playerPlaying].setIndex(players[playerPlaying].getIndex() + rolledNums[0] + rolledNums[1]);
		}
		// System.out.println(newCoordinates[0] + " " + newCoordinates[1]);

		movePlayer(players);

		// for(int i = 0; i<GameManager.game.getPlayers().length; i++) {
		// if(players[i].getProperties().containsValue(GameManager.game.board.spaces[players[playerPlaying].getIndex()]))
		// {
		// isOwned = true;
		// }
		// }

		askToBuy(players);

		// handlePlayerOneMoney();
		// handlePlayerTwoMoney();
		// handlePlayerThreeMoney();
		// handlePlayerFourMoney();

		// Popup pop = new Popup();
		//
		// FXMLLoader load = new
		// FXMLLoader(getClass().getClassLoader().getResource("QuesPain.fxml"));
		// Parent root = null;
		// try {
		// root = load.load();
		// } catch (IOException e) {
		//
		// }
		// pop.show(primaryStage);
		// pop.getContent().add(root);
	}

	private static boolean playerOneTurn = true;

	public void askToBuy(Player[] players)
	{
		try
		{
			if(!(GameManager.game.board.spaces[players[playerPlaying].getIndex()] instanceof SpecialSpace)
					&& !(((Property) GameManager.game.board.spaces[players[playerPlaying].getIndex()]).isOwned()))
			{
				buyYesButton.setVisible(true);
				buyNoButton.setVisible(true);
				buyText.setVisible(true);
				GameManager.buyProperty(players[playerPlaying],
						GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			}
		}
		catch(ClassCastException e)
		{
		}
	}

	public void movePlayer(Player[] players)
	{

		int[] newCoordinates = GameManager.game.board.spaces[players[playerPlaying].getIndex()].getCoordinates();
		switch(playerPlaying)
		{
		case 0:
			stack1.getChildren().clear();
			stack1.getChildren().add(PieceOne);
			boardPane.getChildren().remove(stack1);
			boardPane.add(stack1, newCoordinates[0], newCoordinates[1]);
			GameManager.willBuy = false;
			GameManager.buyProperty(players[playerPlaying],
					GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			break;
		case 1:
			stack2.getChildren().clear();
			stack2.getChildren().add(PieceTwo);
			boardPane.getChildren().remove(stack2);

			boardPane.add(stack2, newCoordinates[0], newCoordinates[1]);
			GameManager.willBuy = false;
			GameManager.buyProperty(players[playerPlaying],
					GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			break;
		case 2:
			stack3.getChildren().clear();
			stack3.getChildren().add(PieceThree);
			boardPane.getChildren().remove(stack3);
			boardPane.add(stack3, newCoordinates[0], newCoordinates[1]);
			GameManager.willBuy = false;
			GameManager.buyProperty(players[playerPlaying],
					GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			break;
		case 3:
			stack4.getChildren().clear();
			stack4.getChildren().add(PieceFour);
			boardPane.getChildren().remove(stack4);
			boardPane.add(stack4, newCoordinates[0], newCoordinates[1]);
			GameManager.willBuy = false;
			GameManager.buyProperty(players[playerPlaying],
					GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			break;
		}
		infoBoxText("You landed on " + GameManager.game.board.spaces[players[playerPlaying].getIndex()].getName());
	}

	public static void setPlayerOneTurn(boolean playerOneTurn)
	{
		boardController.playerOneTurn = playerOneTurn;
	}

	// updates the money
	// public void handlePlayerOneMoney()
	// {
	// Player[] players = GameManager.game.getPlayers();
	// playerOneMoney.setText("Player1: " + players[0].getAmountOfCash());
	//
	// }
	//
	// public void handlePlayerTwoMoney()
	// {
	// Player[] players = GameManager.game.getPlayers();
	// playerTwoMoney.setText("Player2: /n" + players[1].getAmountOfCash());
	// }
	//
	// public void handlePlayerThreeMoney()
	// {
	// Player[] players = GameManager.game.getPlayers();
	// if(players.length != 2)
	// {
	// playerThreeMoney.setText("Player3: /n" + players[2].getAmountOfCash());
	// }
	// }
	//
	// public void handlePlayerFourMoney()
	// {
	// Player[] players = GameManager.game.getPlayers();
	// if(players.length == 4)
	// {
	// playerFourMoney.setText("Player4: /n" + players[3].getAmountOfCash());
	// }
	// }

	public void initialize()
	{
		if(whatPlayer != null)
		{
			changeText();
		}
		else if(whatPlayer == null)
		{

		}

	}

	public void infoBoxText(String text)
	{
		infoBox.setText(text);
	}

	public void changeText()
	{
		// if(GameManager.game.getPlayers() != null)
		// {
		// handlePlayerOneMoney();
		// handlePlayerTwoMoney();
		// handlePlayerThreeMoney();
		// handlePlayerFourMoney();
		// }
		if(playerPlaying == 0)
		{
			whatPlayer.setText("It is Player 1's turn.");
		}
		else if(playerPlaying == 1)
		{
			whatPlayer.setText("It is Player 2's turn.");
		}
		else if(playerPlaying == 2)
		{
			whatPlayer.setText("It is Player 3's turn.");
		}
		else if(playerPlaying == 3)
		{
			whatPlayer.setText("It is Player 4's turn.");
		}
		else
		{
			whatPlayer.setText("It is the end of the word, I worked hard but failed!");
		}
	}

	public void setStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;

	}

	public static int getCurrentPlayer()
	{
		return playerPlaying;
	}

}
