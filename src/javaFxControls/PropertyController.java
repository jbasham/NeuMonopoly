package javaFxControls;

import javaFxControls.boardController;
import application.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Player;
import models.Property;

public class PropertyController {

    @FXML
    private Pane quesPain;

    @FXML
    private TextField TextQues;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    void handleNoButton(ActionEvent event) {
    	Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleYesButton(ActionEvent event) {
     	Player[] players = GameManager.game.getPlayers();
    	players[boardController.playerPlaying].setProperties((Property)GameManager.game.board.spaces[players[boardController.playerPlaying].getIndex()]);
    	Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

}

