package lab;

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

	
    
}
