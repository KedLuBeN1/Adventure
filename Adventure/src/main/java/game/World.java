package game;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.stream.JsonGenerationException;

public class World {

    private static World instance;
    private Player player;
    private Controller controller;

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		World.getInstance().getController().setCurrent_HP(player.getCurrent_HP());
		World.getInstance().getController().setMax_HP(player.getMax_HP());
		World.getInstance().getController().setDamage(player.getDamage());
	}

	private World(){
		System.out.println("World:constructor()");
	}
    
    public static synchronized World getInstance(){
        if(instance == null){
            instance = new World();
        }
        return instance;
    }

	public void initPlayer() {
		ObjectMapper objectMapper = new ObjectMapper();	
		try {
			
			//player = objectMapper.readValue(new File("player.json"),Player.class);
			objectMapper.writeValue(new File("player.json"),player);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
	}

	
    
}
