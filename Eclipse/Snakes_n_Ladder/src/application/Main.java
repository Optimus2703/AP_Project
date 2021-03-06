package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



import java.util.Objects;


public class Main extends Application {
	@Override
	public void start(Stage Stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
			Scene scene = new Scene(root,392,568);
			
			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
			Stage.setScene(scene);
			Stage.setTitle("Snakes & Ladders");
			//Stage.setResizable(false);
			Image logo = new Image("game.png");
			Stage.getIcons().add(logo);
			//Stage.setFullScreen(true);

			Stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		int turn = 0;    //1 for green and 0 for blue

	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}
