package javaFxControls;

import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

public class FirstController {

    @FXML
    private Button newGameButton;

    @FXML
    private Button loadGameButton;

	private Stage primaryStage;
    
    @FXML
    void handleLoadGameButton(ActionEvent event) {
        switchPageL();
    }

    @FXML
    void handleNewGameButton(ActionEvent event) {
        switchPage();
    }
    @FXML
    public void switchPage(){
        Random rando = new Random();
         playersController.setPlayerOneTurn(rando.nextBoolean());
        try {
        	FXMLLoader load = new FXMLLoader(getClass().getClassLoader().getResource("Players.fxml"));
			Parent root = load.load();
            playersController controlsIN = (playersController)load.getController(); 
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

