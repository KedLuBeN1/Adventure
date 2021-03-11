package room;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RoomManager {

	private static RoomManager instance;
	private Room currentRoom;
	private HashMap<String, Room> rooms = new HashMap<String, Room>();

	private RoomManager() {
		System.out.println("RoomManager:constructor()");
	}

	public static synchronized RoomManager getInstance() {
		if (instance == null) {
			instance = new RoomManager();
		}
		return instance;
	}

	public void initRooms() {		
		/*rooms.put("forest", new Room("forest", "Beautifull forest", "forest.jpg", true));
		rooms.put("well", new Room("well", "Very deep well", "well.jpg", true));
		rooms.put("cratch", new Room("cratch", "Big cratch", "cratch.jpg", true));
		rooms.put("cave", new Room("cave", "Beautifull cave", "cave.jpeg", true));
		rooms.put("doors to void", new Room("doors to void", "Some weird doors", "doorsToVoid.jpg", true));
		rooms.put("void",new Room("void", "Just void", "void.jpg", false));
		
		rooms.get("forest").setNorthExit("well");
		rooms.get("forest").setSouthExit("cratch");
		rooms.get("forest").setEastExit("cave");
		rooms.get("forest").setWestExit("doors to void");

		rooms.get("well").setSouthExit("forest");

		rooms.get("cratch").setNorthExit("forest");

		rooms.get("cave").setWestExit("forest");

		rooms.get("doors to void").setEastExit("forest");
		rooms.get("doors to void").setWestExit("void");
		
		rooms.get("void").setEastExit("doors to void");
						
		rooms.get("well").addItem("sword");

		rooms.get("cratch").addItem("apple");

		rooms.get("cave").addItem("key to void");
		
		rooms.get("void").addItem("chest");
			
		
		
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("rooms.json"), rooms);
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rooms));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	
		ObjectMapper objectMapper = new ObjectMapper();		
		
		try {		
			rooms.putAll(objectMapper.readValue(new File("rooms.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Room.class)));	
			String currentRoomName = objectMapper.readValue(new File("currentRoom.json"), String.class);;
			setCurrentRoom(rooms.get(currentRoomName));		
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setRooms(HashMap<String, Room> rooms) {
		this.rooms = rooms;
	}

	public HashMap<String, Room> getRooms() {
		return rooms;
	}

	public Room getRoom(String room) {
		return rooms.get(room);
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
		currentRoom.showRoom();		
	}

}
