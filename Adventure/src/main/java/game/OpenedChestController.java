package game;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OpenedChestController {

    @FXML
    void goBackAction(ActionEvent event) {
    	try {
			World.getInstance().getController().showGui();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
