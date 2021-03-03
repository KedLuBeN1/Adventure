package game;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewGameWindowController {
	@FXML
    private TextField playerName;

    @FXML
    void setInfoAction(ActionEvent event) {
    	try {
        	World.getInstance().getPlayer().setName(playerName.getText());
    		World.getInstance().getController().getGuiMenu().setDisable(false);
			World.getInstance().getController().getMenuItemSave().setVisible(true);
			World.getInstance().getController().showGui();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


}

