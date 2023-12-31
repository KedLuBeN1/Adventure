package game;

import java.util.ArrayList;

import item.Item;


public class Player implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private int currentHP = 0;
	private int maxHP = 0;
	private int damage = 0;
	private String name = null;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Player() {
	}
	
	public Player(int max_HP, int current_HP, int damage) {
		this.currentHP = current_HP;
		this.maxHP = max_HP;
		this.damage = damage;
	}
	
	public void addHP(int i) {
		if(currentHP + i <= maxHP) {
			currentHP += i;
		}
		else {
			currentHP = maxHP;
		}
		World.getInstance().getController().setCurrent_HP(currentHP);
	}
	
	public void takeHP(int i) {
		currentHP -= i;
		World.getInstance().getController().setCurrent_HP(currentHP);
	}
	
	public void setCurrentHP(int current_HP) {
		this.currentHP = current_HP;
	}
	
	public int getCurrentHP() {
		return currentHP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int max_HP) {
		this.maxHP = max_HP;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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
		World.getInstance().getController().addItem(item);
	}
	
	public void removeItemFromInventory(Item item) {
		inventory.remove(item);
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void accessInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
		World.getInstance().getController().clearInventory();
		for(Item item : inventory) {
			World.getInstance().getController().addItem(item);
		}
	}
}
