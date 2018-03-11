package javaFxControls;

import java.io.IOException;

import application.GameManager;
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
import models.Player;

public class boardController {

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
	private ChoiceBox<?> onePlayerProp;

	@FXML
	private ChoiceBox<?> twoPlayerProp;

	@FXML
	private ChoiceBox<?> threePlayerProp;

	@FXML
	private ChoiceBox<?> playerFourProp;

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
	public StackPane stack1 = new StackPane();
	
	@FXML
	public StackPane stack2 = new StackPane();
	
	@FXML
	public StackPane stack3 = new StackPane();
	
	@FXML
	public StackPane stack4 = new StackPane();
	

	@FXML
	void handleEndButton(ActionEvent event) {
		playerPlaying++;
		if (playerPlaying > GameManager.game.getPlayers().length - 1) {
			playerPlaying = 0;
		}
		rollButton.setDisable(false);
		changeText();
	}

	@FXML
	void handleRollButton(ActionEvent event) {
		String[] urls = { "Dice\\Dice\\Dice1.png", "Dice\\Dice\\Dice2.png", "Dice\\Dice\\Dice3.png",
				"Dice\\Dice\\Dice4.png", "Dice\\Dice\\Dice5.png", "Dice\\Dice\\Dice6.png" };
		int[] rolledNums = GameManager.rollDice();

		rolledOne.setImage(new Image(urls[rolledNums[0] - 1]));
		rolledTwo.setImage(new Image(urls[rolledNums[1] - 1]));
		Player[] players = GameManager.game.getPlayers();
		if (rolledNums[0] != rolledNums[1]) {
			rollButton.setDisable(true);
		}
		// for(int i = 0; i < GameManager.game.board.spaces.length; i++) {
		// if(GameManager.game.board.spaces[i].getCoordinates().equals(players[playerPlaying].getCoordinates()))
		// {

		if (players[playerPlaying].getIndex() + (rolledNums[0] + rolledNums[1]) > 39) {
			players[playerPlaying].setIndex(players[playerPlaying].getIndex() + rolledNums[0] + rolledNums[1] - 40);
			players[playerPlaying].setAmountOfCash(players[playerPlaying].getAmountOfCash() + 200);
		} else {
			players[playerPlaying].setIndex(players[playerPlaying].getIndex() + rolledNums[0] + rolledNums[1]);
		}
		// System.out.println(newCoordinates[0] + " " + newCoordinates[1]);
		int[] newCoordinates = GameManager.game.board.spaces[players[playerPlaying].getIndex()].getCoordinates();
		switch (playerPlaying) {
		case 0:
			stack1.getChildren().clear();
			stack1.getChildren().add(PieceOne);
			boardPane.add(stack1, newCoordinates[0], newCoordinates[1]);
			GameManager.buyProperty(players[playerPlaying],
					GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			break;
		case 1:
			stack2.getChildren().clear();
			stack2.getChildren().add(PieceTwo);

			boardPane.add(stack2, newCoordinates[0], newCoordinates[1]);
			GameManager.buyProperty(players[playerPlaying],
					GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			break;
		case 2:
			stack3.getChildren().clear();
			stack3.getChildren().add(PieceThree);
			boardPane.add(stack3, newCoordinates[0], newCoordinates[1]);
			GameManager.buyProperty(players[playerPlaying],
					GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			break;
		case 3:
			stack4.getChildren().clear();
			stack4.getChildren().add(PieceFour);
			boardPane.add(stack4, newCoordinates[0], newCoordinates[1]);
			GameManager.buyProperty(players[playerPlaying],
					GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
			break;
		}
		GameManager.buyProperty(players[playerPlaying],
				GameManager.game.board.spaces[players[playerPlaying].getIndex()]);
//		Popup pop = new Popup();
//		
//		FXMLLoader load = new FXMLLoader(getClass().getClassLoader().getResource("QuesPain.fxml"));
//		Parent root = null;
//		try {
//			root = load.load();
//		} catch (IOException e) {
//		
//		}
//		pop.show(primaryStage);
//		pop.getContent().add(root);
	}

	private static boolean playerOneTurn = true;

	public static void setPlayerOneTurn(boolean playerOneTurn) {
		boardController.playerOneTurn = playerOneTurn;
	}

	public void initialize() {
		if (whatPlayer != null) {
			changeText();
		} else if (whatPlayer == null) {

		}

	}

	public void changeText() {
		if (playerPlaying == 0) {
			whatPlayer.setText("It is Player 1's turn.");
		} else if (playerPlaying == 1) {
			whatPlayer.setText("It is Player 2's turn.");
		} else if (playerPlaying == 2) {
			whatPlayer.setText("It is Player 3's turn.");
		} else if (playerPlaying == 3) {
			whatPlayer.setText("It is Player 4's turn.");
		} else {
			whatPlayer.setText("It is the end of the word, I worked hard but failed!");
		}
	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}
	
	public static int getCurrentPlayer() {
		return playerPlaying;
	}
}
