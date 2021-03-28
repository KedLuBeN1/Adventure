package controller;

import java.io.IOException;
import java.util.HashMap;

import game.Command;
import game.ImageManager;
import game.World;
import item.Item;
import item.ItemManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import room.Room;
import room.RoomManager;
import saveLoad.SaveData;
import saveLoad.SaveLoadManager;

public class Controller {

	private Command command;
	private HashMap<String, Command> commands = new HashMap<String, Command>();
	private Stage startingWindow;
	private Stage newGameWindow;
	private int GUI = 1;

	@FXML
	private Canvas canvas;

	@FXML
	private BorderPane mainBorderPane;

	@FXML
	private HBox bottomHBox;

	@FXML
	private VBox rightVBox;

	@FXML
	private VBox leftVBox;

	@FXML
	private Button enterButton;

	@FXML
	private VBox inventory;

	@FXML
	private Menu guiMenu;

	@FXML
	private MenuItem menuItemSave;

	@FXML
	private Text playerName;

	@FXML
	private Button endGameButton;

	@FXML
	private TextField commandEntry;

	@FXML
	private TextArea output;

	@FXML
	private Text damage;

	@FXML
	private Text max_HP;

	@FXML
	private Text current_HP;

	@FXML
	private VBox dragonStats;

	@FXML
	private Text dragonHP;

	@FXML
	private Text dragonMaxHP;

	@FXML
	private Text dragonDamage;

	@FXML
	private Button westButton;

	@FXML
	private Button eastButton;

	@FXML
	private Button northButton;

	@FXML
	private Button southButton;

	@FXML
	void enterButtonAction(ActionEvent event) {
		System.out.println("enterButton " + commandEntry.getText());
		checkCommand(commandEntry.getText().toLowerCase());
		commandEntry.clear();
	}

	@FXML
	void setGUI1(ActionEvent event) {
		GUI = 1;
		try {
			showGui();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void setGUI2(ActionEvent event) {
		GUI = 2;
		try {
			showGui();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void setGUI3(ActionEvent event) {
		GUI = 3;
		try {
			showGui();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showGui() throws IOException {
		switch (GUI) {
		case 1:
			mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/game/GUI1.fxml")));
			break;
		case 2:
			mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/game/GUI2.fxml")));
			break;
		case 3:
			mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/game/GUI3.fxml")));
			break;
		}
		canvasAction();
		leftVBox.setVisible(true);
		rightVBox.setVisible(true);
		bottomHBox.setVisible(true);
	}

	@FXML
	void setAboutView(ActionEvent event) throws IOException {
		mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/game/aboutView.fxml")));
		World.getInstance().getController().getLeftVBox().setVisible(false);
		World.getInstance().getController().getRightVBox().setVisible(false);
		World.getInstance().getController().getBottomHBox().setVisible(false);
		World.getInstance().getController().getMenuItemSave().setVisible(false);
	}

	@FXML
	void endGameAction(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void newGameAction(ActionEvent event) {
		guiMenu.setDisable(true);
		leftVBox.setVisible(false);
		rightVBox.setVisible(false);
		bottomHBox.setVisible(false);
		menuItemSave.setVisible(false);
		dragonStats.setVisible(false);
		initialize();
		clearInventory();
		try {
			newGameWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void loadGameAction(ActionEvent event) {
		menuItemSave.setVisible(false);
		guiMenu.setDisable(true);
		leftVBox.setVisible(false);
		rightVBox.setVisible(false);
		bottomHBox.setVisible(false);
		dragonStats.setVisible(false);
		try {
			loadGameWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadGame() {
		try {
			// menuItemNew.setVisible(false);
			menuItemSave.setVisible(true);
			guiMenu.setDisable(false);
			showGui();
			System.out.println("tak co je 0");
			SaveData data = (SaveData) SaveLoadManager.getInstance().load(playerName.getText() + ".save");
			System.out.println("tak co je 1");
			World.getInstance().setPlayer(data.getPlayer());
			RoomManager.getInstance().setRooms(data.getRooms());
			World.getInstance().getPlayer().accessInventory(data.getInventory());
			RoomManager.getInstance().setCurrentRoom(data.getCurrentRoom());
			System.out.println("Game has been loaded");
			displayText("Game has been loaded");
		} catch (Exception e) {
			// e.printStackTrace();
			loadGameAction(null);
		}
	}

	@FXML
	void saveGameAction(ActionEvent event) {
		SaveData data = new SaveData();
		data.setPlayer(World.getInstance().getPlayer());
		data.setCurrentRoom(RoomManager.getInstance().getCurrentRoom());
		data.setInventory(World.getInstance().getPlayer().getInventory());
		data.setRooms(RoomManager.getInstance().getRooms());
		try {
			SaveLoadManager.getInstance().save(data, playerName.getText() + ".save");
			System.out.println("Game has been saved");
			displayText("Game has been saved");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	public void initialize() {
		System.out.println("initialize ");
		initConroller();
		initPlayer();
		initCommands();
		initItems();
		initRooms();
		try {
			startingWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startingWindow() throws Exception {
		mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/game/startingView.fxml")));
	}

	public void newGameWindow() throws Exception {
		mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/game/newGameView.fxml")));
	}

	public void loadGameWindow() throws Exception {
		mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource("/game/loadGameView.fxml")));
	}

	private void initItems() {
		ItemManager.getInstance().initItems();
	}

	private void initRooms() {
		RoomManager.getInstance().initRooms();
	}

	private void initConroller() {
		World.getInstance().setController(this);
	}

	private void initPlayer() {
		World.getInstance().initPlayer();
	}

	private void canvasAction() {
		canvas.setOnMouseClicked(event -> {
			System.out.println("X " + event.getX() + " Y " + event.getY());
			for (Item item : RoomManager.getInstance().getCurrentRoom().returnItems()) {
				if (item.clickedOnItem(event.getX(), event.getY())) {
					System.out.println("You have clicked on " + item.getName());
					if (item.isCollectable()) {
						World.getInstance().getPlayer().addItemToInventory(item);
						RoomManager.getInstance().getCurrentRoom().removeItem(item);
						World.getInstance().getController().displayText("You have picked up " + item.getName());
					} else {
						item.itemAction();
					}
					break;
				}
			}
		});
	}

	private void initCommands() {
		commands.put("go north", new Command("go north", "The player will go north if possible"));
		commands.put("go south", new Command("go south", "The player will go south if possible"));
		commands.put("go west", new Command("go west", "The player will go west if possible"));
		commands.put("go east", new Command("go east", "The player will go east if possible"));
		commands.put("help", new Command("help", "Show all available commands"));
		commands.put("get rooms", new Command("get rooms", "Show all accessible rooms"));
	}

	public void checkCommand(String c) {
		if (commands.containsKey(c)) {
			command = commands.get(c);
			if (command.isAvailable()) {
				command.run();
				if (command.getPhrase() != null) {
					output.setText(command.getPhrase() + "\n");
				}
			} else if (!command.isAvailable()) {
				output.setText("This command is not available\n");
			}
		} else {
			output.setText("Unknown command \n");
		}
	}

	public Controller() {
		System.out.println("Controller:constructor()");
	}

	public void goEastAction() {
		checkCommand("go east");
	}

	public void goNorthAction() {
		checkCommand("go north");
	}

	public void goSouthAction() {
		checkCommand("go south");
	}

	public void goWestAction() {
		checkCommand("go west");
	}

	public Menu getGuiMenu() {
		return guiMenu;
	}

	public MenuItem getMenuItemSave() {
		return menuItemSave;
	}

	public HBox getBottomHBox() {
		return bottomHBox;
	}

	public VBox getRightVBox() {
		return rightVBox;
	}

	public VBox getLeftVBox() {
		return leftVBox;
	}

	public void showRoom(String imageName) {
		if (ImageManager.getInstance().getImage(imageName) == null) {
			System.out.println("Tenhle obrazek jeste neznam, vytvorim ho");
			ImageManager.getInstance().addImage(imageName);
		}
		Image image = ImageManager.getInstance().getImage(imageName);
		canvas.getGraphicsContext2D().drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());

		showArrows();
	}

	public void showArrows() {
		Room currentRoom = RoomManager.getInstance().getCurrentRoom();

		if (currentRoom.getNorthExit() != null && currentRoom.northExitObject().isAccessible()) {
			northButton.setVisible(true);
		} else {
			northButton.setVisible(false);
		}

		if (currentRoom.getSouthExit() != null && currentRoom.southExitObject().isAccessible()) {
			southButton.setVisible(true);
		} else {
			southButton.setVisible(false);
		}

		if (currentRoom.getEastExit() != null && currentRoom.eastExitObject().isAccessible()) {
			eastButton.setVisible(true);
		} else {
			eastButton.setVisible(false);
		}

		if (currentRoom.getWestExit() != null && currentRoom.westExitObject().isAccessible()) {
			westButton.setVisible(true);
		} else {
			westButton.setVisible(false);
		}
	}

	public void showItem(String imageName, int x, int y, int w, int h) {
		if (ImageManager.getInstance().getImage(imageName) == null) {
			System.out.println("Tenhle obrazek itemu jeste neznam, vytvorim ho");
			ImageManager.getInstance().addImage(imageName);
		}
		Image image = ImageManager.getInstance().getImage(imageName);
		canvas.getGraphicsContext2D().drawImage(image, x, y, w, h);
	}

	public void setCurrent_HP(int current_HP) {
		this.current_HP.setText(Integer.toString(current_HP));
	}

	public void setMax_HP(int max_HP) {
		this.max_HP.setText(Integer.toString(max_HP));
	}

	public void setDamage(int damage) {
		this.damage.setText(Integer.toString(damage));
	}

	public VBox getDragonStats() {
		return dragonStats;
	}

	public void updateDragonStats(int damage, int hp, int maxHp) {
		dragonDamage.setText(Integer.toString(damage));
		dragonHP.setText(Integer.toString(hp));
		dragonMaxHP.setText(Integer.toString(maxHp));
	}
	
	public void updatePlayerStats(int currentHP, int maxHP, int damage) {
		setCurrent_HP(currentHP);
		setMax_HP(maxHP);
		setDamage(damage);
	}

	public void addItem(Item item) {
		System.out.println("Nacitam item");
		System.out.println(item.getName() + " sirka: " + inventory.getWidth());
		Button itemButton = new Button(item.getName());
		itemButton.setPrefWidth(inventory.getWidth());
		inventory.getChildren().add(itemButton);
		itemButton.setOnAction(event -> {
			if (item.itemAction()) {
				System.out.println("removeItem");
				inventory.getChildren().remove(itemButton);
				World.getInstance().getPlayer().removeItemFromInventory(item);
			}
		});
		System.out.println("Item " + item.getName() + " was displayed");
	}

	public void clearInventory() {
		inventory.getChildren().clear();
	}

	public void hideImage() {
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void showCommands() {
		output.setText(null);
		output.appendText("All available commands\n");
		for (Command command : commands.values()) {
			output.appendText(command.getInfo());
		}
	}

	public void displayText(String s) {
		output.setText(s + "\n");
	}

	public void setName(String name) {
		playerName.setText(name);
	}

	public Stage getStartingWindow() {
		return startingWindow;
	}

	public Stage getNewGameWindow() {
		return newGameWindow;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public BorderPane getMainBorderPane() {
		return mainBorderPane;
	}

	public Button getWestButton() {
		return westButton;
	}

	public void setWestButton(Button westButton) {
		this.westButton = westButton;
	}

	public Button getEastButton() {
		return eastButton;
	}

	public void setEastButton(Button eastButton) {
		this.eastButton = eastButton;
	}

	public Button getNorthButton() {
		return northButton;
	}

	public void setNorthButton(Button northButton) {
		this.northButton = northButton;
	}

	public Button getSouthButton() {
		return southButton;
	}

	public void setSouthButton(Button southButton) {
		this.southButton = southButton;
	}
}