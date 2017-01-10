package boundary;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_codebehind.Car.Builder;
import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Field;
import desktop_fields.Jail;
import desktop_fields.Refuge;
import desktop_fields.Shipping;
import desktop_fields.Start;
import desktop_fields.Street;
import desktop_fields.Tax;
import desktop_resources.GUI;
import entity.DiceCup;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;
import entity.fields.Ownable;
import entity.language.LanguageHandler;

public class GUIBoundary {

	private static GUIBoundary instance;

	/**
	 * Returns and initializes the instance of the GUIBoundary if it is not already initialized
	 * @return instance - 
	 */
	public static GUIBoundary getInstance() {
		if(instance == null) {
			instance = new GUIBoundary();
		}
		return instance;
	}

	/**
	 * Creates the graphical game board. Initializes every field graphically, which is presented in the GUI
	 * @param gameBoard
	 */
	public void createGameBoard(GameBoard gameBoard) {
		Field[] fields = new Field[gameBoard.getFields().length];
		LanguageHandler language = LanguageHandler.getInstance();

		for(int i = 0; i < gameBoard.getFields().length; i++){

			if(gameBoard.getField(i).getID() == 0) { // Startfelt
				fields[i] = new Start.Builder()
						.setSubText(language.fieldDescription(i))		
						.build();
			} else if(gameBoard.getField(i).getID() == 10 || gameBoard.getField(i).getID() == 30) {
				fields[i] = new Jail.Builder()
						.setSubText(language.fieldNames(i))
						.setDescription(language.fieldDescription(i))
						.build();
			} else if(gameBoard.getField(i) instanceof entity.fields.Brewery){
				fields[i] = new Brewery.Builder()
						.setTitle(language.fieldNames(i))
						.setSubText(language.fieldPrices(gameBoard.getField(i).getPrice()))
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.Chance){
				fields[i] = new Chance.Builder()
						.setFgColor(Color.WHITE)
						.setBgColor(Color.BLACK)
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.Plot){
				fields[i] = new Street.Builder()
						.setTitle(language.fieldNames(i))
						.setSubText(language.fieldPrices(gameBoard.getField(i).getPrice()))
						.setBgColor(getPropertyGroupColor(gameBoard.getField(i).getPropertyGroup()))
						.setDescription(language.fieldInfo(gameBoard.getField(i).getRentArray(), gameBoard.getField(i).getPrice(), gameBoard.getField(i).getConstructionPrice()))
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.Refuge){
				fields[i] = new Refuge.Builder()
						.setTitle(language.fieldNames(i))
						.setSubText(language.fieldDescription(i))
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.ShippingLine){
				fields[i] = new Shipping.Builder()
						.setTitle(language.fieldNames(i))
						.setSubText(language.fieldPrices(gameBoard.getField(i).getPrice()))
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.Tax){
				fields[i] = new Tax.Builder()
						.setTitle(language.fieldNames(i))
						.setDescription(language.fieldDescription(i))
						.build();
			}
		}
		GUI.create(fields);
		GUI.setDice(1, 0, 4, 3, 1, 0, 5, 3);
	}

	/**
	 * Returns the background color of the property group
	 * @param propertyGroup
	 * @return color - color for the specific property group
	 */
	private Color getPropertyGroupColor(int propertyGroup) {
		Color color = null;

		switch(propertyGroup) {
		case 0:
			color = new Color(38, 131, 212); // Blå
			break;
		case 1:
			color = new Color(240, 106, 79); // Laksefarve
			break;
		case 2:
			color = new Color(74, 222, 20); // Lysegrøn
			break;
		case 3:
			color = new Color(195, 195, 195); // Grå
			break;
		case 4:
			color = new Color(255, 0, 0); // Rød
			break;
		case 5:
			color = new Color(255, 255, 255); // Hvid
			break;
		case 6:
			color = new Color(246, 255, 0); // Gul
			break;
		case 7:
			color = new Color(111, 77, 177); //Lilla
			break;
		default:
			break;
		}

		return color;

	}

	/**
	 * Adds a player with a specific vehicle on the game board
	 * @param player
	 */
	public void addPlayer(Player player) {
		Builder carBuilder = new Car.Builder();
		switch (player.getID()) {
		case 0:
			carBuilder
			.typeCar()
			.patternFill()
			.primaryColor(Color.BLUE);
			break;
		case 1:
			carBuilder
			.typeCar()
			.patternFill()
			.primaryColor(Color.RED);
			break;
		case 2:
			carBuilder
			.typeCar()
			.patternFill()
			.primaryColor(Color.GREEN);
			break;
		case 3:
			carBuilder
			.typeCar()
			.patternFill()
			.primaryColor(Color.YELLOW);
			break;
		case 4:
			carBuilder
			.typeCar()
			.patternFill()
			.primaryColor(Color.BLACK);
			break;
		case 5:
			carBuilder
			.typeCar()
			.patternFill()
			.primaryColor(Color.WHITE);
			break;
		default:
			break;
		}
		Car car = carBuilder.build();
		GUI.addPlayer(player.getName(), player.getBankAccount().getBalance(), car);
		this.updateCar(player);
	}

	/**
	 * Changes the field number so it remains under 40
	 * @param fieldNumber - the current field number
	 * @return convertedFieldNumber - the new converted field number
	 */
	private int convertFieldNumber(int fieldNumber) {
		int convertedFieldNumber = fieldNumber + 1;
		while(convertedFieldNumber > 40)
			convertedFieldNumber -= 40;
		return convertedFieldNumber;
	}

	/**
	 * Shows two dice on the board. The dice will have specified values.
	 * @param diceCup which contains two dice
	 */
	public void setDices(DiceCup diceCup) {
		GUI.setDice(diceCup.getDices()[0].getFaceValue(), 0, 4, 3, diceCup.getDices()[1].getFaceValue(), 0, 5, 3);
	}

	/**
	 * Updates all info in the GUI that might change during the game.
	 * @param gameBoard
	 * @param playerList
	 */
	public void updateGUI(GameBoard gameBoard, PlayerList playerList) {
		// updates everything regarding any field
		for(entity.fields.Field field : gameBoard.getFields()) {
			if(field instanceof Ownable) {
				updateOwner(field);
				updateConstructionRate(field);
				updatePawnStatus(field);
			}
		}
		// updates everything regarding any player
		for(Player player : playerList.getPlayers()) {
			updateCar(player);
			updateBalance(player);
		}
	}

	/**
	 * Updates the owner of a specific field.
	 * @param field
	 */
	public void updateOwner(entity.fields.Field field) {
		if(field.getOwner() != null) {
			GUI.setOwner(convertFieldNumber(field.getID()), field.getOwner().getName());
		}
	}

	/**
	 * Updates the construction rate on a specific field.
	 * @param field that is updated
	 */
	public void updateConstructionRate(entity.fields.Field field){
		if(field.getConstructionRate() == 5){
			GUI.setHotel(convertFieldNumber(field.getID()), true);
		}else{
			GUI.setHouses(convertFieldNumber(field.getID()), field.getConstructionRate());
		}
	}

	/**
	 * Updates the pawn status on a specific field.
	 * @param field that is updated
	 */
	public void updatePawnStatus(entity.fields.Field field) {
		if(field.isPawned()) {
			GUI.setSubText(convertFieldNumber(field.getID()), "PANTSAT");
		} else {
			if(field.getOwner() == null)
				GUI.setSubText(convertFieldNumber(field.getID()), LanguageHandler.getInstance().fieldPrices(field.getPrice()));
			else
				GUI.setSubText(convertFieldNumber(field.getID()), field.getOwner().getName());
		}
	}

	/**
	 * Updates the players position on the board.
	 * @param player
	 */
	public void updateCar(Player player) { 
		GUI.removeAllCars(player.getName());
		if(player.getOnField() >= 0) {
			GUI.setCar(convertFieldNumber(player.getOnField()), player.getName());
		}
	}

	/**
	 * Updates the players balance.
	 * @param player
	 */
	public void updateBalance(Player player) {
		GUI.setBalance(player.getName(), player.getBankAccount().getBalance());
	}

	/**
	 * Returns a string containing which language is selected
	 * @return language that is selected
	 */
	public String getLanguage() {
		return GUI.getUserSelection("Select language. \nVælg sprog.", "Dansk", "English");
	}

	/**
	 * Returns a string of which is chosen
	 * @param message
	 * @param options
	 * @return String of the choice that has been made
	 */
	public String getUserSelection(String message, String... options) {
		return GUI.getUserSelection(message, options);
	}

	/**
	 * Displays a message to the user and awaits the integer response
	 * @param message - the message that prompts the user
	 * @return int - the integer that the user selected
	 */
	public int getInteger(String message) {
		return GUI.getUserInteger(message);
	}

	/**
	 * Displays a message to the user and awaits the integer response. Only values between min and max are allowed
	 * @param message - the message that prompts the user.
	 * @param min - the minimum value the user is allowed to enter
	 * @param max - the maximum value the user is allowed to enter
	 * @return int - the integer that the user selected
	 */
	public int getInteger(String message, int min, int max) {
		return GUI.getUserInteger(message, min, max);
	}

	/**
	 * Displays a message to the user and awaits the response
	 * @param message - the message that prompts the user
	 * @return String - the string that the user has entered
	 */
	public String getString(String message) {
		return GUI.getUserString(message);
	}

	/**
	 * Displays a message to the user and awaits the response from the button that the user pressed
	 * @param message - the message that prompts the user
	 * @param optionTrue - a strings that should be printed on the "true" button
	 * @param optionFalse - a strings that should be printed on the "false" button
	 * @return boolean - boolean from the button that the user pressed
	 */
	public boolean getBoolean(String message, String optionTrue, String optionFalse) {
		String response = GUI.getUserButtonPressed(message, optionTrue, optionFalse);
		return response.equals(optionTrue);
	}

	/**
	 * Displays a message to the user and awaits the "OK!" button pressed response
	 * @param message - the message that prompts the user
	 * @return true - whenever the button is pressed
	 */
	public boolean getButtonPressed(String message) {
		GUI.getUserButtonPressed(message, "OK!");
		return true;
	}

	/**
	 * Displays a message to the user and awaits the button pressed response
	 * @param msg - the message that prompts the user
	 * @param buttons - strings that are shown on the buttons
	 * @return String from the button that the user pressed
	 */
	public String getUserButtonPressed(String msg, String... buttons) {
		return GUI.getUserButtonPressed(msg, buttons);
	}

	/**
	 * Sets the text to appear in the center when calling displayChanceCard() and when the deck is pressed
	 * @param message that are shown
	 */
	public void setChanceCard(String message) {
		GUI.setChanceCard(message);
	}


}
