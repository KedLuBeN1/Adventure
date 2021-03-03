package game;

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
			World.getInstance().getController().loadGameWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
