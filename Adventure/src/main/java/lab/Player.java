package lab;

import java.util.ArrayList;

public class Player {
	
	private int current_HP = 0;
	private int max_HP = 0;
	private String name = "";
	private ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Player(int max_HP) {
		System.out.println("Player:constructor()");
		setHP(max_HP);
		setMaxHP(max_HP);
	}
	
	public void addHP(int i) {
		if(current_HP + i <= max_HP) {
			current_HP += i;
		}
		else {
			current_HP = max_HP;
		}
		World.getInstance().getController().setCurrent_HP(current_HP);
	}
	
	public void takeHP(int i) {
		current_HP -= i;
		World.getInstance().getController().setCurrent_HP(current_HP);
	}
	
	public int getHP() {
		return current_HP;
	}
	
	public void setHP(int current_HP) {
		this.current_HP = current_HP;
		World.getInstance().getController().setCurrent_HP(current_HP);
	}
	
	public void setMaxHP(int i) {
		max_HP = i;
		World.getInstance().getController().setMax_HP(max_HP);
	}
	
	public int getMaxHP() {
		return max_HP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		World.getInstance().getController().setName(name);
	}
	
	public void addItemToInventory(Item item) {
		inventory.add(item);
		System.out.println("Item "+item.getName()+" was added to player inventory");
		World.getInstance().getController().addItem(item);
	}
	
	public void removeItemFromInventory(Item item) {
		inventory.remove(item);
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
		World.getInstance().getController().clearInventory();
		for(Item item : inventory) {
			World.getInstance().getController().addItem(item);
		}
	}
}
