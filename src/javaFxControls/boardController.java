package javaFxControls;

import application.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Game;
import models.Player;

public class boardController {
	public int playerPlaying = 1;
	private Stage primaryStage;
	private static boolean cat = true;
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
	void handleEndButton(ActionEvent event) {
		playerPlaying++;
		if (playerPlaying > 4) {
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
		Player[] players = Game.getPlayers();
		if (rolledNums[0] != rolledNums[1]) {
			rollButton.setDisable(true);
		}
		for(int i = 0; i < GameManager.game.board.spaces.length; i++) {
			if(GameManager.game.board.spaces[i].getCoordinates() == players[playerPlaying].getCoordinates())
				players[playerPlaying].setCoordinates(GameManager.game.board.spaces[i+(rolledNums[0]+rolledNums[1])].getCoordinates());
		}
		
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
		if (playerPlaying == 1) {
			whatPlayer.setText("It is Player 1's turn.");
		} else if (playerPlaying == 2) {
			whatPlayer.setText("It is Player 2's turn.");
		} else if (playerPlaying == 3) {
			whatPlayer.setText("It is Player 3's turn.");
		} else if (playerPlaying == 4) {
			whatPlayer.setText("It is Player 4's turn.");
		} else {
			whatPlayer.setText("It is the end of the word, I worked hard but failed");
		}
	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}
}
