package lab;

import java.util.Random;

public class Command {

	private String name;
	private String phrase;
	private String description;
	private boolean available = true;
	private Random rand = new Random();

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
		if (name == "damage") {
			int damage = rand.nextInt(20);
			World.getInstance().getPlayer().takeHP(damage);
			World.getInstance().getController().setCurrent_HP(World.getInstance().getPlayer().getHP());
			if (World.getInstance().getPlayer().getHP() <= 0) {
				setPhrase("You are dead now");
				available = false;
				return;
			}
			setPhrase("You have lost " + damage + " HP");
		}

		else if (name == "go north" && RoomManager.getInstance().getCurrentRoom().getNorthExit() != null && RoomManager.getInstance().getCurrentRoom().getNorthExit().isAccessible()) {
			RoomManager.getInstance().setCurrentRoom(RoomManager.getInstance().getCurrentRoom().getNorthExit());
			setPhrase(RoomManager.getInstance().getCurrentRoom().getDescription());
		}

		else if (name == "go south" && RoomManager.getInstance().getCurrentRoom().getSouthExit() != null && RoomManager.getInstance().getCurrentRoom().getSouthExit().isAccessible()) {
			RoomManager.getInstance().setCurrentRoom(RoomManager.getInstance().getCurrentRoom().getSouthExit());
			setPhrase(RoomManager.getInstance().getCurrentRoom().getDescription());
		}
		
		else if (name == "go west" && RoomManager.getInstance().getCurrentRoom().getWestExit() != null && RoomManager.getInstance().getCurrentRoom().getWestExit().isAccessible()) {
			RoomManager.getInstance().setCurrentRoom(RoomManager.getInstance().getCurrentRoom().getWestExit());
			setPhrase(RoomManager.getInstance().getCurrentRoom().getDescription());
		} 
		
		else if (name == "go east" && RoomManager.getInstance().getCurrentRoom().getEastExit() != null && RoomManager.getInstance().getCurrentRoom().getEastExit().isAccessible()) {
			RoomManager.getInstance().setCurrentRoom(RoomManager.getInstance().getCurrentRoom().getEastExit());
			setPhrase(RoomManager.getInstance().getCurrentRoom().getDescription());
		}
		else if(name == "get rooms") {
			String rooms = "All accessible rooms\n";
			for(Room room : RoomManager.getInstance().getRooms()) {
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
