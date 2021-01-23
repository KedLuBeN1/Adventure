package lab;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class ItemManager {

	private static ItemManager instance;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	private ItemManager() {
		System.out.println("ItemManager:constructor()");
	}
	
	public static synchronized ItemManager getInstance() {
		if (instance == null) {
			instance = new ItemManager();
		}
		return instance;
	}
	
	public void initItems() {
		items.add(new Item("sword", new Image(getClass().getResource("/lab/sword.png").toString()), 450, 500, 50, 50));
		items.add(new Item("apple", new Image(getClass().getResource("/lab/apple.png").toString()), 50, 500, 32, 32));
		items.add(new Item("key to void", new Image(getClass().getResource("/lab/keyToVoid.jpg").toString()), 300, 500, 80, 45));
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public Item getItem(int i) {
		return items.get(i);
	}
}
