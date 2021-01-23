package lab;


import java.util.ArrayList;

import javafx.scene.image.Image;

public class Room implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private Image image;
	private Room northExit = null;
	private Room southExit = null;
	private Room westExit = null;
	private Room eastExit = null;
	private boolean accessible;
	private String description = "";
	private ArrayList<Item> items = new ArrayList<Item>();

	public Room(String name, String description, Image image, boolean accessible) {
		this.name = name;
		this.image = image;
		this.accessible = accessible;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void showRoom() {
		World.getInstance().getController().showImage(image);
		for(Item item: items) {
			item.showItem();
		}
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public Room getNorthExit() {
		return northExit;
	}

	public void setNorthExit(Room northExit) {
		this.northExit = northExit;
	}

	public Room getSouthExit() {
		return southExit;
	}

	public void setSouthExit(Room southExit) {
		this.southExit = southExit;
	}

	public Room getWestExit() {
		return westExit;
	}

	public void setWestExit(Room westExit) {
		this.westExit = westExit;
	}

	public Room getEastExit() {
		return eastExit;
	}

	public void setEastExit(Room eastExit) {
		this.eastExit = eastExit;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
		showRoom();
		System.out.println("Item "+item.getName()+" was removed from room");
	}
	
	public void saveRoom() {
		
	}
	
	public void loadRoom() {
		
	}

}
