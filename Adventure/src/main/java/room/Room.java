package room;

import java.util.ArrayList;

import game.World;
import item.Item;
import item.ItemManager;

public class Room implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String description = "";
	private String imageName;
	private boolean accessible;
	private String northExit = null;
	private String southExit = null;
	private String westExit = null;
	private String eastExit = null;
	private ArrayList<String> itemsNames = new ArrayList<String>();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Room(String name, String description, String imageName, boolean accessible) {
		this.name = name;
		this.imageName = imageName;
		this.accessible = accessible;
		this.description = description;
	}
	
	public Room() {
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
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName=imageName;
	}
	
	public void showRoom() {
		World.getInstance().getController().showRoom(imageName);
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
	
	public void setItemsNames(ArrayList<String> itemsNames) {
		this.itemsNames = itemsNames;
		for(String itemName : itemsNames) {
			items.add(ItemManager.getInstance().getItem(itemName));
		}
	}
	
	public ArrayList<String> getItemsNames() {
		return itemsNames;
	}
	
	public ArrayList<Item> returnItems() {
		return items;
	}
	
	public String getNorthExit() {
		return northExit;
	}
	
	public Room northExitObject() {
		return RoomManager.getInstance().getRoom(northExit);
	}
	
	public void setNorthExit(String northExit) {
		this.northExit = northExit;
	}

	public String getSouthExit() {
		return southExit;
	}
	
	public Room southExitObject() {
		return RoomManager.getInstance().getRoom(southExit);
	}
	
	public void setSouthExit(String southExit) {
		this.southExit = southExit;
	}

	public String getWestExit() {
		return westExit;
	}
	
	public Room westExitObject() {
		return RoomManager.getInstance().getRoom(westExit);
	}
	
	public void setWestExit(String westExit) {
		this.westExit = westExit;
	}

	public String getEastExit() {
		return eastExit;
	}
	
	public Room eastExitObject() {
		return RoomManager.getInstance().getRoom(eastExit);
	}

	public void setEastExit(String eastExit) {
		this.eastExit = eastExit;
	}
	
	public void addItem(String item) {
		itemsNames.add(item);
		items.add(ItemManager.getInstance().getItem(item));
	}
	
	public void removeItem(Item item) {
		items.remove(item);
		showRoom();
		System.out.println("Item "+item.getName()+" was removed from room");
	}
	
	

}
