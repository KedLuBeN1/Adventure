package game;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class OpenableItem extends Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String image2Name;
	
	public OpenableItem() {
	}

	public OpenableItem(String name, String imageName,String image2Name, int x, int y, int width, int height, boolean collectable) {
		super(name, imageName, x, y, width, height, collectable);
		this.image2Name = image2Name;
	}
	
	
	public String getImage2Name() {
		return image2Name;
	}

	public void setImage2Name(String image2Name) {
		this.image2Name = image2Name;
	}

	@Override
	public boolean itemAction() {
		this.setImageName(image2Name);
		BorderPane mainBorderPane = World.getInstance().getController().getMainBorderPane();
		try {
			World.getInstance().getController().getMainBorderPane().setCenter(FXMLLoader.load(getClass().getResource("/game/openedChestView.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
