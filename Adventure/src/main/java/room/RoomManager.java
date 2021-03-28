package room;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import game.World;

public class RoomManager {

	private static RoomManager instance;
	private Room currentRoom;
	private HashMap<String, Room> rooms = new HashMap<String, Room>();

	private RoomManager() {
	}

	public static synchronized RoomManager getInstance() {
		if (instance == null) {
			instance = new RoomManager();
		}
		return instance;
	}

	public void initRooms() {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			rooms.putAll(objectMapper.readValue(new File("rooms.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Room.class)));
			String currentRoomName = objectMapper.readValue(new File("currentRoom.json"), String.class);
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
		World.getInstance().getController().displayText(currentRoom.getDescription());
	}

}
