package lab;

import javafx.scene.image.Image;

public class Item {

	private String name;
	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;

	public Item(String name, Image image, int x, int y, int width, int height) {
		this.name = name;
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean clickedOnItem(double posX, double posY) {
		if (posX >= x && posX <= (x + width) && posY >= y && posY <= (y + height)) {
			World.getInstance().getController().displayText("You have picked up "+name);
			return true;
		} else {
			return false;
		}
	}

	public void showItem() {
		World.getInstance().getController().showItem(image, x, y, width, height);
	}

	public String getName() {
		return name;
	}

	public boolean itemAction() {
		if (name == "sword") {
			System.out.println("Nothing for now");	
			return true;
		}
		else if (name == "apple") {
			int hp1 = World.getInstance().getPlayer().getHP();
			World.getInstance().getPlayer().addHP(25);
			int hp2 = World.getInstance().getPlayer().getHP();
			World.getInstance().getController().displayText("Apple healed you for " + (hp2-hp1) + " HP");
			return true;
		}
		else if (name == "key to void") {
			if(RoomManager.getInstance().getCurrentRoom().getName() == "doors to void") {
				RoomManager.getInstance().getRoom(5).setAccessible(true);
				World.getInstance().getController().displayText("Path to void has been unlocked");
				return true;
			}
			else {
				World.getInstance().getController().displayText("This key should open some doors");
				return false;
			}
				
		}
		return false;
	}

}
