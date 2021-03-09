package game;

public class Command {

	private String name;
	private String phrase;
	private String description;
	private boolean available = true;

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Command(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	public String getInfo() {
		if(available == true) {
			return name+"..."+description+"\n";
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void run() {
		
		if (name == "go north" && RoomManager.getInstance().getCurrentRoom().getNorthExit() != null && RoomManager.getInstance().getCurrentRoom().northExitObject().isAccessible()) {
			RoomManager.getInstance().setCurrentRoom(RoomManager.getInstance().getCurrentRoom().northExitObject());
			setPhrase(RoomManager.getInstance().getCurrentRoom().getDescription());
		}

		else if (name == "go south" && RoomManager.getInstance().getCurrentRoom().getSouthExit() != null && RoomManager.getInstance().getCurrentRoom().southExitObject().isAccessible()) {
			RoomManager.getInstance().setCurrentRoom(RoomManager.getInstance().getCurrentRoom().southExitObject());
			setPhrase(RoomManager.getInstance().getCurrentRoom().getDescription());
		}
		
		else if (name == "go west" && RoomManager.getInstance().getCurrentRoom().getWestExit() != null && RoomManager.getInstance().getCurrentRoom().westExitObject().isAccessible()) {
			RoomManager.getInstance().setCurrentRoom(RoomManager.getInstance().getCurrentRoom().westExitObject());
			setPhrase(RoomManager.getInstance().getCurrentRoom().getDescription());
		} 
		
		else if (name == "go east" && RoomManager.getInstance().getCurrentRoom().getEastExit() != null && RoomManager.getInstance().getCurrentRoom().eastExitObject().isAccessible()) {
			RoomManager.getInstance().setCurrentRoom(RoomManager.getInstance().getCurrentRoom().eastExitObject());
			setPhrase(RoomManager.getInstance().getCurrentRoom().getDescription());
		}
		else if(name == "get rooms") {
			String rooms = "All accessible rooms\n";
			for(Room room : RoomManager.getInstance().getRooms().values()) {
				if(room.isAccessible()) {
				rooms = rooms + room.getName()+ "..."+room.getDescription()+ "\n";
				}
			}
			setPhrase(rooms);
		}
		else if (name == "help") {
			World.getInstance().getController().showCommands();
		}
		else {
			setPhrase("You can t go there");
		}
	}

}
