package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	FXMLLoader fxmlLoader;
	
	public Main() {
		System.out.println("Main:constructor()");
	}

	//nejaka zmena 2
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			primaryStage.setTitle("Adventure");
			fxmlLoader = new FXMLLoader(getClass().getResource("/lab/view.fxml"));
			Pane pane = fxmlLoader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(this::exitProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exitProgram(WindowEvent evt) {
		System.exit(0);
	}

}