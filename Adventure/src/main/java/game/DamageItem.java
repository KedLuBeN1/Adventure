package game;

public class DamageItem extends Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int damage;
	
	public DamageItem() {	
	}
	
	public DamageItem(String name, String imageName, int x, int y, int width, int height, boolean collectable, int damage) {
		super(name, imageName, x, y, width, height, collectable);
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public boolean itemAction() {
		World.getInstance().getPlayer().setDamage(damage);
		World.getInstance().getController().setDamage(damage);
		World.getInstance().getController().displayText("This sword gave you "+damage+" bonus damage");
		return false;
	}
	
}
