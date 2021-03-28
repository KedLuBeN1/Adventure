package game;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
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
		World.getInstance().getController().updatePlayerStats(player.getCurrentHP(), player.getMaxHP(), player.getDamage());
	}

	private World(){
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
			player = objectMapper.readValue(new File("player.json"),Player.class);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		World.getInstance().getController().updatePlayerStats(player.getCurrentHP(), player.getMaxHP(), player.getDamage());
	}

	
    
}
