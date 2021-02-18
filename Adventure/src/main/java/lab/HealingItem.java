package lab;

public class HealingItem extends Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	public HealingItem() {	
	}
	
	public HealingItem(String name, String imageName, int x, int y, int width, int height, boolean collectable) {
		super(name, imageName, x, y, width, height, collectable);
	}
	
	@Override
	public boolean itemAction() {
		int hp1 = World.getInstance().getPlayer().getHP();
		World.getInstance().getPlayer().addHP(25);
		int hp2 = World.getInstance().getPlayer().getHP();
		World.getInstance().getController().displayText(name+" healed you for " + (hp2-hp1) + " HP");
		return true;
	}
	
}
