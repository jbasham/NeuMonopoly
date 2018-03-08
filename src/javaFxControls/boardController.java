package javaFxControls;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class boardController {
	private Stage primaryStage;
	private static boolean cat = true;
	@FXML
	private GridPane boardPane;
	private static boolean playerOneTurn = true;
	
	public static void setPlayerOneTurn(boolean playerOneTurn) {
		boardController.playerOneTurn = playerOneTurn;
	}

//	public void initialize() {
//		//if (whatPlayer != null) {
//			cat = true;
//			changeText();
//		} else if (whatPlayer == null) {
//			//winText();
//		}
//
//	}

//	public void changeText() {
//		if (playerOneTurn) {
//			whatPlayer.setText("It is Player 1's turn.");
//		} else {
//			whatPlayer.setText("It is Player 2's turn.");
//		}
//	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}
}
