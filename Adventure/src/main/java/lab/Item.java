package lab;

public class Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String imageName;
	private int x;
	private int y;
	private int width;
	private int height;

	public Item(String name, String imageName, int x, int y, int width, int height) {
		this.name = name;
		this.imageName = imageName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public Item() {
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
		World.getInstance().getController().showItem(imageName, x, y, width, height);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean itemAction() {
		System.out.println("itemAction");
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
				RoomManager.getInstance().getRoom("void").setAccessible(true);
				RoomManager.getInstance().getCurrentRoom().setImageName("doorsToVoidOpened.jpg");
				RoomManager.getInstance().getCurrentRoom().showRoom();
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
