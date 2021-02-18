package lab;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartingWindowController {
	
	@FXML
	void endGameAction(ActionEvent event) {
		System.exit(0);
	}

    @FXML
    void newGameAction(ActionEvent event) {
    	try {
			World.getInstance().getController().newGameWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void loadGameAction(ActionEvent event) {
    	try {
			World.getInstance().getController().showGui();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	World.getInstance().getController().loadGameAction(event);
    }
}
