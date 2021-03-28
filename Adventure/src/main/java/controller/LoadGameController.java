package controller;

import game.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoadGameController {

	@FXML
	private TextField playerName;
	
    @FXML
    private Label nameError;
    
	@FXML
	void setInfoAction(ActionEvent event) {
		nameError.setVisible(true);
		World.getInstance().getPlayer().setName(playerName.getText());
		World.getInstance().getController().getGuiMenu().setDisable(false);
		World.getInstance().getController().loadGame();
	}

}
