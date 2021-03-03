package game;

import java.util.ArrayList;

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
		System.out.println("Player:constructor()");
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
	
	public void setCurrent_HP(int current_HP) {
		this.currentHP = current_HP;
	}
	
	public int getCurrent_HP() {
		return currentHP;
	}

	public int getMax_HP() {
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
	}
	
	public void addItemToInventory(Item item) {
		inventory.add(item);
		System.out.println("Item "+item.getName()+" was added to player inventory");
		World.getInstance().getController().addItem(item);
	}
	
	public void removeItemFromInventory(Item item) {
		inventory.remove(item);
	}

	public ArrayList<Item> sgetInventory() {
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
