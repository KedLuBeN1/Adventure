package lab;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class openedChestController {

    @FXML
    void goBackAction(ActionEvent event) {
    	try {
			World.getInstance().getController().showGui();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
