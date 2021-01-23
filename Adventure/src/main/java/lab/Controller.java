package lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller {

	private Command command;
	private HashMap<String, Command> commands = new HashMap<String, Command>();
	private Stage startingWindow = new Stage(); 	

	@FXML
	private Canvas canvas;

	@FXML
	private Button enterButton;

	@FXML
	private VBox inventory;

	@FXML
	private Text playerName;

	@FXML
	private Button endGameButton;

	@FXML
	private TextField commandEntry;

	@FXML
	private TextArea output;

	@FXML
	private Text max_HP;

	@FXML
	private Text current_HP;

	@FXML
	void enterButtonAction(ActionEvent event) {
		System.out.println("enterButton " + commandEntry.getText());
		checkCommand(commandEntry.getText().toLowerCase());
		commandEntry.clear();
	}

	@FXML
	void setPlayerNameAction(ActionEvent event) {
	
	}

	@FXML
	void endGameAction(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void loadGameAction(ActionEvent event) {
		try {
			System.out.println("tak co je 0");
			SaveData data = (SaveData) SaveLoadManager.getInstance().load("first.save");
			System.out.println("tak co je 1");
			World.getInstance().getPlayer().setHP(data.getCurrentHP());
			World.getInstance().getPlayer().setMaxHP(data.getMaxHP());
			World.getInstance().getPlayer().setName(data.getPlayerName());
			// RoomManager.getInstance().setCurrentRoom(data.getCurrentRoom()); 	
			// World.getInstance().getPlayer().setInventory(data.getInventory());
			// RoomManager.getInstance().setRooms(data.getRooms());

			System.out.println("Game has been loaded");
			displayText("Game has been loaded");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("yasay");
		}
	}

	@FXML
	void saveGameAction(ActionEvent event) {
		SaveData data = new SaveData();
		data.setCurrentHP(World.getInstance().getPlayer().getHP());
		data.setMaxHP(World.getInstance().getPlayer().getMaxHP());
		data.setPlayerName(World.getInstance().getPlayer().getName());
		// data.setCurrentRoom(RoomManager.getInstance().getCurrentRoom());
		// data.setInventory(World.getInstance().getPlayer().getInventory());
		// data.setRooms(RoomManager.getInstance().getRooms());
		try {
			SaveLoadManager.getInstance().save(data, "first.save");
			System.out.println("Game has been saved");
			displayText("Game has been saved");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	public void initialize() {
		System.out.println("initialize ");
		System.out.println(javafx.scene.text.Font.getFamilies());
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
		initCanvas();
	}
		
	private void startingWindow() throws Exception{
		try {
			startingWindow.setTitle("Create player");
			startingWindow.initModality(Modality.APPLICATION_MODAL);
			startingWindow.setOnCloseRequest(this::exitProgram);
			Pane pane = new FXMLLoader(getClass().getResource("/lab/startingWindowView.fxml")).load();
			Scene scene = new Scene(pane);
			startingWindow.setScene(scene);
			startingWindow.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void exitProgram(WindowEvent evt) {
		System.exit(0);
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
		World.getInstance().setPlayer(new Player(150));
	}

	private void initCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		try {
			gc.setFont(Font.loadFont(new FileInputStream(new File("BlackChancery.ttf")), 45));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gc.setFill(Color.ORANGERED);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Welcome to my adventure game\n"+playerName.getText()+"\nType in help to get available commands",
				canvas.getWidth() / 2, canvas.getHeight() / 2.5);
		canvas.setOnMouseClicked(event -> {
			System.out.println("X " + event.getX() + " Y " + event.getY());
			for (Item item : RoomManager.getInstance().getCurrentRoom().getItems()) {
				if (item.clickedOnItem(event.getX(), event.getY())) {
					System.out.println("You have clicked on " + item.getName());
					World.getInstance().getPlayer().addItemToInventory(item);
					RoomManager.getInstance().getCurrentRoom().removeItem(item);
					break;
				}
			}
		});
	}

	private void initCommands() {
		commands.put("damage", new Command("damage","Deal damage to a player"));
		commands.put("go north", new Command("go north","The player will go north if possible"));
		commands.put("go south", new Command("go south","The player will go south if possible"));
		commands.put("go west", new Command("go west","The player will go west if possible"));
		commands.put("go east", new Command("go east","The player will go east if possible"));
		commands.put("help", new Command("help","Show all available commands"));
		commands.put("get rooms", new Command("get rooms","Show all accessible rooms"));
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

	public void showImage(Image image) {
		canvas.getGraphicsContext2D().drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void setCurrent_HP(int current_HP2) {
		current_HP.setText(Integer.toString(current_HP2));
	}

	public void setMax_HP(int max_HP2) {
		max_HP.setText(Integer.toString(max_HP2));
	}

	public void addItem(Item item) {
		Button itemButton = new Button(item.getName());
		itemButton.setPrefWidth(inventory.getWidth());
		inventory.getChildren().add(itemButton);
		itemButton.setOnAction(event -> {
			if(item.itemAction()){
			inventory.getChildren().remove(itemButton);
			World.getInstance().getPlayer().removeItemFromInventory(item);
			}
		});
		System.out.println("Item " + item.getName() + " was displayed");
	}

	public void hideImage() {
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void showCommands() {
		output.setText(null);
		output.appendText("All available commands\n");
		for(Command command : commands.values()) {
			output.appendText(command.getInfo());
		}
	}
	
	public void showItem(Image image, int x, int y, int w, int h) {
		canvas.getGraphicsContext2D().drawImage(image, x, y, w, h);
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
}