package lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StartingWindowController {

	@FXML
	private TextField playerNameEntered;

	@FXML
	void setInfoAction(ActionEvent event) {
		World.getInstance().getPlayer().setName(playerNameEntered.getText());
		World.getInstance().getController().getStartingWindow().close();
	}

}
