package application;

import javaFxControls.FirstController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Initializer extends Application
{
	private Stage primary;
	
	@Override
	public void start(Stage primaryStage)
	{
		primary = primaryStage;
		try
		{
			FXMLLoader load = new FXMLLoader(getClass().getClassLoader().getResource("First.fxml"));
			Parent root = load.load();
			FirstController controlsIN = (FirstController)load.getController(); 
			controlsIN.setStage(primaryStage);
			Scene scene = new Scene(root,1200, 1200);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

	public Stage getPrimary() {
		return primary;
	}
}
