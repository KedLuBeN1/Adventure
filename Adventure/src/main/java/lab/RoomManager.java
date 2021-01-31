package lab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.HashMap;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;


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
		/*JsonParser jsonParser = null;
		try {
			jsonParser = Json.createParser(new FileInputStream(getClass().getResource("/lab/rooms.json").getFile()));
		} catch (FileNotFoundException e) {
			System.out.println("CHYBA: Nenacetl jsem soubor s daty");
			e.printStackTrace();
			return;
		}
		Room r = null;
		while (jsonParser.hasNext()) {
			Event event = jsonParser.next();
			switch (event) {
			case KEY_NAME:
				if (jsonParser.getString().equals("name")) {
					jsonParser.next();
					r.setName(jsonParser.getString());
				} else if (jsonParser.getString().equals("description")) {
					jsonParser.next();
					r.setDescription(jsonParser.getString());
				} else if (jsonParser.getString().equals("imageName")) {
					jsonParser.next();
					r.setImageName(jsonParser.getString());
				} else if (jsonParser.getString().equals("accessible")) {
					jsonParser.next();
					r.setAccessible(Boolean.parseBoolean(jsonParser.getString()));
				}
				break;
			case VALUE_STRING:

			case VALUE_NUMBER:
				//
				break;
			case START_ARRAY:
				System.out.println("Zacatek pole");
				break;
			case END_ARRAY:
				System.out.println("Konec pole");
				break;
			case START_OBJECT:
				System.out.println(" Novy objekt");
				r = new Room();
				break;
			case END_OBJECT:
				System.out.println(" Konci objekt pridam do listu ");
				rooms.put(r.getName(),r);
				break;
			default:
				System.out.println("Neco se pokazilo pri nacitani");
			}
		}
		jsonParser.close();*/
		
		rooms.put("forest", new Room("forest", "Beautifull forest", "forest.jpg", true));
		rooms.put("well", new Room("well", "Very deep well", "well.jpg", true));
		rooms.put("cratch", new Room("cratch", "Big cratch", "cratch.jpg", true));
		rooms.put("cave", new Room("cave", "Beautifull cave", "cave.jpeg", true));
		rooms.put("doors to void", new Room("doors to void", "Some weird doors", "doorsToVoid.jpg", true));
		rooms.put("void",new Room("void", "Just void", "void.jpg", false));
		
		/*jsonParser = null;
		try {
			jsonParser = Json.createParser(new FileInputStream(getClass().getResource("/lab/roomExits.json").getFile()));
		} catch (FileNotFoundException e) {
			System.out.println("CHYBA: Nenacetl jsem soubor s daty");
			e.printStackTrace();
			return;
		}
		r = null;
		while (jsonParser.hasNext()) {
			Event event = jsonParser.next();
			switch (event) {
			case KEY_NAME:
				if (jsonParser.getString().equals("name")) {
					jsonParser.next();
					r = rooms.get(jsonParser.getString());
				} else if (jsonParser.getString().equals("northExit")) {
					jsonParser.next();
					if(jsonParser.getString()!=null)
						r.setNorthExit(rooms.get(jsonParser.getString()));
				} else if (jsonParser.getString().equals("southExit")) {
					jsonParser.next();
					if(jsonParser.getString()!=null)
						r.setSouthExit(rooms.get(jsonParser.getString()));
				} else if (jsonParser.getString().equals("westExit")) {
					jsonParser.next();
					if(jsonParser.getString()!=null)
						r.setWestExit(rooms.get(jsonParser.getString()));
				} else if (jsonParser.getString().equals("eastExit")) {
					jsonParser.next();
					if(jsonParser.getString()!=null)
						r.setEastExit(rooms.get(jsonParser.getString()));
				}
				break;
			case VALUE_STRING:	
			case VALUE_NUMBER:
				//
				break;
			case START_ARRAY:
				System.out.println("Zacatek pole");
				break;
			case END_ARRAY:
				System.out.println("Konec pole");
				break;
			case START_OBJECT:
				System.out.println(" Novy objekt");
				break;
			case END_OBJECT:
				System.out.println(" Konci objekt pridam do listu ");
				break;
			default:
				System.out.println("Neco se pokazilo pri nacitani");
			}
		}
		jsonParser.close();*/
		rooms.get("forest").setNorthExit(rooms.get("well"));
		rooms.get("forest").setSouthExit(rooms.get("cratch"));
		rooms.get("forest").setEastExit(rooms.get("cave"));
		rooms.get("forest").setWestExit(rooms.get("doors to void"));

		rooms.get("well").setSouthExit(rooms.get("forest"));

		rooms.get("cratch").setNorthExit(rooms.get("forest"));

		rooms.get("cave").setWestExit(rooms.get("forest"));

		rooms.get("doors to void").setEastExit(rooms.get("forest"));
		rooms.get("doors to void").setWestExit(rooms.get("void"));
		
		rooms.get("void").setEastExit(rooms.get("doors to void"));

		rooms.get("well").addItem(ItemManager.getInstance().getItem("sword"));

		rooms.get("cratch").addItem(ItemManager.getInstance().getItem("apple"));

		rooms.get("cave").addItem(ItemManager.getInstance().getItem("key to void"));

		setCurrentRoom(rooms.get("forest"));
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
