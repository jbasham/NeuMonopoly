package javaFxControls;

import java.io.IOException;
import java.util.Random;

import application.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class playersController {
	private Stage primaryStage;
	@FXML
    private Button twoPlayersButton;

    @FXML
    private Button threePlayersButton;

    @FXML
    private Button fourPlayersButton;
	private static boolean playerOneTurn = true;

    public static void setPlayerOneTurn(boolean playerOneTurn) {
        playersController.playerOneTurn = playerOneTurn;
    }

    @FXML
    void handlefourPlayerButton(ActionEvent event) {
    	GameManager.newGame(4);
    	switchPageL();
    }

    @FXML
    void handleThreePlayerButton(ActionEvent event) {
    	GameManager.newGame(3);
    	switchPageL();
    }

    @FXML
    void handleTwoPlayerButton(ActionEvent event) {
    	GameManager.newGame(2);
    	switchPageL();
    }
    @FXML
    public void switchPageL(){
        Random rando = new Random();
         boardController.setPlayerOneTurn(rando.nextBoolean());
        try {
        	FXMLLoader load = new FXMLLoader(getClass().getClassLoader().getResource("board.fxml"));
			Parent root = load.load();
            boardController controlsIN = (boardController)load.getController(); 
			controlsIN.setStage(primaryStage);
            Scene scene = new Scene(root, 1200, 1200);
            primaryStage.setScene(scene);
            primaryStage.setAlwaysOnTop(true);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
	}
}

