package game;

import java.util.ArrayList;
import java.util.HashMap;

public class SaveData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Player player;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private Room currentRoom;
	private HashMap<String, Room> rooms = new HashMap<String, Room>();
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public HashMap<String, Room> getRooms() {
		return rooms;
	}

	public void setRooms(HashMap<String, Room> rooms) {
		this.rooms = rooms;
	}

}
