package item;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemManager {

	private static ItemManager instance;
	private HashMap<String, Item> items = new HashMap<String, Item>();
	
	private ItemManager() {
	}
	
	public static synchronized ItemManager getInstance() {
		if (instance == null) {
			instance = new ItemManager();
		}
		return instance;
	}
	
	public void initItems() {
		ObjectMapper objectMapper = new ObjectMapper();	
		
		try {	
			items.putAll(objectMapper.readValue(new File("keys.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Key.class)));
			items.putAll(objectMapper.readValue(new File("healingItems.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, HealingItem.class)));
			items.putAll(objectMapper.readValue(new File("damageItems.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, DamageItem.class)));
			items.putAll(objectMapper.readValue(new File("dragon.json"),
					objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Dragon.class)));
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
