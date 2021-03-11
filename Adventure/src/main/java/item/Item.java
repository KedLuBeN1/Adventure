package item;

import game.World;

public abstract class Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	protected String name;
	private String imageName;
	private boolean collectable;
	private int x;
	private int y;
	private int width;
	private int height;

	public Item(String name, String imageName, int x, int y, int width, int height, boolean collectable) {
		this.name = name;
		this.imageName = imageName;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.collectable = collectable;
	}
	public Item() {
	}

	public boolean clickedOnItem(double posX, double posY) {
		if (posX >= x && posX <= (x + width) && posY >= y && posY <= (y + height)) {
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
	
	public boolean isCollectable() {
		return collectable;
	}
	
	public void setCollectable(boolean collectable) {
		this.collectable = collectable;
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
	
	public abstract boolean itemAction();
}
