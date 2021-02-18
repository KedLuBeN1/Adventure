package lab;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.stream.JsonGenerationException;

public class ItemManager {

	private static ItemManager instance;
	private HashMap<String, Item> items = new HashMap<String, Item>();
	private HashMap<String, HealingItem> healingItems = new HashMap<String, HealingItem>();
	private HashMap<String, Key> keys = new HashMap<String, Key>();
	private HashMap<String, OpenableItem> openableItems = new HashMap<String, OpenableItem>();
	
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
		/*healingItems.put("sword",new HealingItem("sword", "sword.png", 450, 500, 50, 50, true));
		healingItems.put("apple",new HealingItem("apple", "apple.png", 50, 500, 32, 32,true));
		keys.put("key to void",new Key("key to void", "keyToVoid.jpg", 300, 500, 80, 45, "void", "doors to void", "doorsToVoidOpened.jpg",true));
		openableItems.put("chest",new OpenableItem("chest", "closedChest.png","openedChest.png", 15, 35, 32, 32,false));
		items.putAll(healingItems);
		items.putAll(keys);
		items.putAll(openableItems);
		
		
		
		try {
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("keys.json"), keys);
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("healingItems.json"), healingItems);
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("openableItems.json"), openableItems);
		System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(keys));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		ObjectMapper objectMapper = new ObjectMapper();	
		
		
		System.out.println("tadyyyy0");
		try {	
			System.out.println("tadyyyy1");
			items.putAll(objectMapper.readValue(new File("keys.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Key.class)));
			items.putAll(objectMapper.readValue(new File("healingItems.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, HealingItem.class)));
			items.putAll(objectMapper.readValue(new File("openableItems.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, OpenableItem.class)));
			
			System.out.println("tadyyyy2");
			for(Item item : items.values()) {
				System.out.println(item.getName());	
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public HashMap<String, Item> getItems() {
		return items;
	}
	
	public Item getItem(String item) {
		return items.get(item);
	}
}
