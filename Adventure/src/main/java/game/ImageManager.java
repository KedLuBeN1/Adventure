package game;

import java.util.HashMap;

import javafx.scene.image.Image;

public class ImageManager {

	private static ImageManager instance;
	private HashMap<String, Image> images = new HashMap<String, Image>();
	
	private ImageManager() {
	}
	
	public void addImage(String imageName) {
		Image image = new Image(getClass().getResource(imageName).toString());
		images.put(imageName, image);
	}
	
	public Image getImage(String imageName) {
		if(images.containsKey(imageName)) {
			return images.get(imageName);
		}
		return null;			
	}
	
	public static synchronized ImageManager getInstance() {
		if (instance == null) {
			instance = new ImageManager();
		}
		return instance;
	}

}
