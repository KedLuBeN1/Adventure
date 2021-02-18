package lab;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewGameWindowController {
	@FXML
    private TextField playerName;

    @FXML
    void setInfoAction(ActionEvent event) {
    	World.getInstance().getPlayer().setName(playerName.getText());
    	try {
			World.getInstance().getController().showGui();
			World.getInstance().getController().disableGuiMenu(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


}

