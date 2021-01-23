package lab;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class RoomManager {

	private static RoomManager instance;
	private Room currentRoom;
	private ArrayList<Room> rooms = new ArrayList<Room>();

	private RoomManager(){
			System.out.println("RoomManager:constructor()");
		}

	public static synchronized RoomManager getInstance() {
		if (instance == null) {
			instance = new RoomManager();
		}
		return instance;
	}
	
	public void initRooms() {
		rooms.add(new Room("forest", "Beautifull forest", new Image(getClass().getResource("/lab/forest.jpg").toString()), true));
		rooms.add(new Room("well", "Very deep well", new Image(getClass().getResource("/lab/well.jpg").toString()), true));
		rooms.add(new Room("cratch", "Big cratch", new Image(getClass().getResource("/lab/cratch.jpg").toString()), true));
		rooms.add(new Room("cave", "Beautifull cave", new Image(getClass().getResource("/lab/cave.jpeg").toString()), true));
		rooms.add(new Room("doors to void", "Some weird doors", new Image(getClass().getResource("/lab/doorsToVoid.jpg").toString()), true));
		rooms.add(new Room("void", "Just void", new Image(getClass().getResource("/lab/void.jpg").toString()), false));
		
		 
		rooms.get(0).setNorthExit(rooms.get(1));
		rooms.get(0).setSouthExit(rooms.get(2));
		rooms.get(0).setEastExit(rooms.get(3));
		rooms.get(0).setWestExit(rooms.get(4));
		
		rooms.get(1).setSouthExit(rooms.get(0));
		
		rooms.get(2).setNorthExit(rooms.get(0));
		
		rooms.get(3).setWestExit(rooms.get(0));
		
		rooms.get(4).setEastExit(rooms.get(0));
		rooms.get(4).setWestExit(rooms.get(5));
		
		rooms.get(1).addItem(ItemManager.getInstance().getItem(0));
		
		rooms.get(2).addItem(ItemManager.getInstance().getItem(1));
		
		rooms.get(3).addItem(ItemManager.getInstance().getItem(2));
		
		setCurrentRoom(rooms.get(0));
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public Room getRoom(int i) {
		return rooms.get(i);
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
		currentRoom.showRoom();
	}



}
