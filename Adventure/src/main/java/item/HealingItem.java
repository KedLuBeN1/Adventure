package item;

import game.World;

public class HealingItem extends Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int heal;
	
	public HealingItem() {	
	}
	
	public HealingItem(String name, String imageName, int x, int y, int width, int height, boolean collectable, int heal) {
		super(name, imageName, x, y, width, height, collectable);
		this.heal = heal;
	}
	
	public int getHeal() {
		return heal;
	}

	public void setHeal(int heal) {
		this.heal = heal;
	}

	@Override
	public boolean itemAction() {
		int hp1 = World.getInstance().getPlayer().getCurrent_HP();
		World.getInstance().getPlayer().addHP(heal);
		int hp2 = World.getInstance().getPlayer().getCurrent_HP();
		World.getInstance().getController().displayText(name+" healed you for " + (hp2-hp1) + " HP");
		return true;
	}
	
}
