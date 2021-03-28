package item;

import java.io.IOException;
import java.util.Random;

import game.World;
import javafx.fxml.FXMLLoader;

public class Dragon extends Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int maxHP;
	private int currentHP;
	private int damage;
	
	public Dragon() {	
	}
	
	public Dragon(String name, String imageName,int currentHP, int maxHP, int damage, int x, int y, int width, int height, boolean collectable) {
		super(name, imageName, x, y, width, height, collectable);
		this.currentHP = currentHP;
		this.maxHP = maxHP;
	}
		
	public int getMaxHP() {
		return maxHP;
	}
	
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	public int getCurrentHP() {
		return currentHP;
	}
	
	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	@Override
	public boolean itemAction() {
		Random rand = new Random();
		int damageToPlayer;
		int damageToDragon;
		
		damageToDragon = rand.nextInt(World.getInstance().getPlayer().getDamage())+0;
		currentHP -= damageToDragon;
		damageToPlayer = rand.nextInt(damage)+0;
		World.getInstance().getPlayer().takeHP(damageToPlayer);
		World.getInstance().getController().getDragonStats().setVisible(true);
		World.getInstance().getController().updateDragonStats(damage, currentHP, maxHP);
		
		World.getInstance().getController().displayText("Player took -"+damageToPlayer+" HP\nDragon took -"+damageToDragon+" HP");
		
		if (World.getInstance().getPlayer().getCurrentHP() <= 0) {
			try {
				World.getInstance().getController().getMainBorderPane().
				setCenter(FXMLLoader.load(getClass().getResource("/game/lostView.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			World.getInstance().getController().getLeftVBox().setVisible(false);
			World.getInstance().getController().getRightVBox().setVisible(false);
			World.getInstance().getController().getBottomHBox().setVisible(false);
			World.getInstance().getController().getGuiMenu().setDisable(true);
			World.getInstance().getController().getMenuItemSave().setVisible(false);
		}
		else if(currentHP<=0) {
			try {
				World.getInstance().getController().getMainBorderPane().
				setCenter(FXMLLoader.load(getClass().getResource("/game/winView.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			World.getInstance().getController().getLeftVBox().setVisible(false);
			World.getInstance().getController().getRightVBox().setVisible(false);
			World.getInstance().getController().getBottomHBox().setVisible(false);
			World.getInstance().getController().getGuiMenu().setDisable(true);
			World.getInstance().getController().getMenuItemSave().setVisible(false);
		}

		return false;
	}

}
