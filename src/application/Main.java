package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static String file_path;

	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,650,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		    
		        primaryStage.setTitle("VoiceNote");
		        primaryStage.setScene(scene);
		        primaryStage.show();
		} catch(Exception e) {
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
