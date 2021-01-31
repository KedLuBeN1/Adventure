package lab;

import java.util.ArrayList;
import java.util.HashMap;

public class SaveData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String playerName;
	private int currentHP;
	private int maxHP;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private Room currentRoom;
	private HashMap<String, Room> rooms = new HashMap<String, Room>();
	

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}
	
	public int getCurrentHP() {
		return currentHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	public int getMaxHP() {
		return maxHP;
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
