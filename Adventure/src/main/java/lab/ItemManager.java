package lab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class ItemManager {

	private static ItemManager instance;
	private HashMap<String, Item> items = new HashMap<String, Item>();
	
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
		/*JsonParser jsonParser = null;
		try {
			jsonParser = Json.createParser(new FileInputStream(getClass().getResource("/lab/items.json").getFile()));
		} catch (FileNotFoundException e) {
			System.out.println("CHYBA: Nenacetl jsem soubor s daty");
			e.printStackTrace();
			return;
		}
		Item i = null;
		while (jsonParser.hasNext()) {
			Event event = jsonParser.next();
			switch (event) {
			case KEY_NAME:
				if (jsonParser.getString().equals("name")) {
					jsonParser.next();
					i.setName(jsonParser.getString());
				} else if (jsonParser.getString().equals("imageName")) {
					jsonParser.next();
					i.setImageName(jsonParser.getString());
				} else if (jsonParser.getString().equals("x")) {
					jsonParser.next();
					i.setX(Integer.parseInt(jsonParser.getString()));
				} else if (jsonParser.getString().equals("y")) {
					jsonParser.next();
					i.setY(Integer.parseInt(jsonParser.getString()));
				} else if (jsonParser.getString().equals("width")) {
					jsonParser.next();
					i.setWidth(Integer.parseInt(jsonParser.getString()));
				} else if (jsonParser.getString().equals("height")) {
					jsonParser.next();
					i.setHeight(Integer.parseInt(jsonParser.getString()));
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
				i = new Item();
				break;
			case END_OBJECT:
				System.out.println(" Konci objekt pridam do listu ");
				items.put(i.getName(),i);
				break;
			default:
				System.out.println("Neco se pokazilo pri nacitani");
			}
		}
		jsonParser.close();*/
		items.put("sword",new Item("sword", "sword.png", 450, 500, 50, 50));
		items.put("apple",new Item("apple", "apple.png", 50, 500, 32, 32));
		items.put("key to void",new Item("key to void", "keyToVoid.jpg", 300, 500, 80, 45));
	}
	
	public HashMap<String, Item> getItems() {
		return items;
	}
	
	public Item getItem(String item) {
		return items.get(item);
	}
}
