package item;

import game.World;
import room.RoomManager;

public class Key extends Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String room;
	private String doors;
	private String openedDoorImage;
	
	public Key() {	
	}
	
	public Key(String name, String imageName, int x, int y, int width, int height,String room, String doors, String openedDoorImage, boolean collectable) {
		super(name, imageName, x, y, width, height, collectable);
		this.room = room;
		this.doors = doors;
		this.openedDoorImage = openedDoorImage;
	}
	
	@Override
	public boolean itemAction() {
		if(RoomManager.getInstance().getCurrentRoom().getName().equals(doors)) {
			RoomManager.getInstance().getRoom(room).setAccessible(true);
			RoomManager.getInstance().getCurrentRoom().setImageName(openedDoorImage);
			RoomManager.getInstance().getCurrentRoom().showRoom();
			World.getInstance().getController().displayText(room+" unlocked");
			return true;
		}
		else {
			World.getInstance().getController().displayText("This key should open some doors");
			return false;
		}
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDoors() {
		return doors;
	}

	public void setDoors(String doors) {
		this.doors = doors;
	}

	public String getOpenedDoorImage() {
		return openedDoorImage;
	}

	public void setOpenedDoorImage(String openedDoorImage) {
		this.openedDoorImage = openedDoorImage;
	}
}
