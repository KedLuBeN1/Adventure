package controller;

import game.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import room.RoomManager;

public class GUIController {

    @FXML
    private Canvas canvas;

    @FXML
    private Button westButton;

    @FXML
    private Button southButton;

    @FXML
    private Button eastButton;

    @FXML
    private Button northButton;
    
    @FXML
	public void initialize() {
    	World.getInstance().getController().setCanvas(canvas);
    	World.getInstance().getController().setNorthButton(northButton);
    	World.getInstance().getController().setSouthButton(southButton);
    	World.getInstance().getController().setWestButton(westButton);
    	World.getInstance().getController().setEastButton(eastButton);
    	RoomManager.getInstance().getCurrentRoom().showRoom();
    }

    @FXML
    void goEastAction(ActionEvent event) {
    	World.getInstance().getController().goEastAction();
    }

    @FXML
    void goNorthAction(ActionEvent event) {
    	World.getInstance().getController().goNorthAction();
    }

    @FXML
    void goSouthAction(ActionEvent event) {
    	World.getInstance().getController().goSouthAction();
    }

    @FXML
    void goWestAction(ActionEvent event) {
    	World.getInstance().getController().goWestAction();
    }

}
