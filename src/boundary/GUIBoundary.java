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
						.setDescription(
								" - Kun grund: " + gameBoard.getField(i).getRentArray()[0] + " | " +
										"1 hus: " + gameBoard.getField(i).getRentArray()[1] + " | " +
										"2 huse: " + gameBoard.getField(i).getRentArray()[2] + " | " +
										"3 huse: " + gameBoard.getField(i).getRentArray()[3] + " | " +
										"4 huse: " + gameBoard.getField(i).getRentArray()[4] + " | " +
										"Hotel: " + gameBoard.getField(i).getRentArray()[5])
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
		GUI.setDice(1, 1);
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
			.typeUfo()
			.patternCheckered()
			.primaryColor(Color.RED)
			.secondaryColor(Color.GRAY);
			break;
		case 1:
			carBuilder
			.typeRacecar()
			.patternDiagonalDualColor()
			.primaryColor(Color.GREEN)
			.secondaryColor(Color.ORANGE);
			break;
		case 2:
			carBuilder
			.typeTractor()
			.patternDotted()
			.primaryColor(Color.BLUE)
			.secondaryColor(Color.CYAN);
			break;
		case 3:
			carBuilder
			.typeUfo()
			.patternZebra()
			.primaryColor(Color.YELLOW)
			.secondaryColor(Color.MAGENTA);
			break;
		case 4:
			carBuilder
			.typeRacecar()
			.patternHorizontalGradiant()
			.primaryColor(Color.BLACK)
			.secondaryColor(Color.WHITE);
			break;
		case 5:
			carBuilder
			.typeTractor()
			.patternHorizontalDualColor()
			.primaryColor(Color.WHITE)
			.secondaryColor(Color.PINK);
			break;
		default:
			break;
		}
		Car car = carBuilder.build();
		GUI.addPlayer(player.getName(), player.getBankAccount().getBalance(), car);
		this.setCar(player.getOnField(), player.getName());
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
	 * Removes ownership from every field that a player owns
	 * @param gameBoard
	 * @param player to release fields from
	 */
	public void releasePlayersFields(GameBoard gameBoard, Player player) {
		for(int i = 0; i < gameBoard.getFields().length; i++)
			if(gameBoard.getField(i) instanceof Ownable)
				if(gameBoard.getField(i).getOwner().getName().equals(player.getName())) {
					GUI.removeOwner(convertFieldNumber(i));
				}
	}

	/**
	 * Updates the construction rate on a specific field
	 * @param field that is updated
	 */
	public void updateConstructionRate(entity.fields.Field field){
		if(field.getConstructionRate() == 5){
			GUI.setHouses(field.getID(), 0);
			GUI.setHotel(field.getID(), true);
		}else{
			GUI.setHouses(field.getID(), field.getConstructionRate());
		}
	}

	/**
	 * Updates the pawn status on field
	 * @param field that is updated
	 */
	public void updatePawnStatus(entity.fields.Field field) {
		if(field.getIsPawned()) {
			GUI.setSubText(field.getID(), "PANTSAT");
		} else {
			if(field.getOwner() == null)
				GUI.setSubText(field.getID(), LanguageHandler.getInstance().fieldPrices(field.getID()));
			else
				GUI.setSubText(field.getID(), field.getOwner().getName());
		}
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
	 * Shows two dice on the board. The dice will have specified values, but placement is random
	 * @param diceCup which contains two dice
	 */
	public void setDices(DiceCup diceCup) {
		GUI.setDice(diceCup.getDices()[0].getFaceValue(), diceCup.getDices()[1].getFaceValue());
	}

	/**
	 * Places a player's car on a specific field on the board
	 * @param fieldNumber of the specific field
	 * @param playerName of the player
	 */
	public void setCar(int fieldNumber, String playerName) {
		GUI.setCar(convertFieldNumber(fieldNumber), playerName);
	}

	/**
	 * Removes a player's car from a specific field on the board
	 * @param fieldNumber of the specific field
	 * @param playerName of the player
	 */
	public void removeCar(int fieldNumber, String playerName) {
		GUI.removeCar(convertFieldNumber(fieldNumber), playerName);
	}

	/**
	 * Updates a player's balance
	 * @param playerName of the player
	 * @param newBalance to be updated
	 */
	public void updateBalance(String playerName, int newBalance) {
		GUI.setBalance(playerName, newBalance);
	}

	/**
	 * Sets an owner of a field. Field border and subText will be changed and now indicate who's the owner
	 * @param fieldNumber
	 * @param playerName
	 */
	public void setOwner(int fieldNumber, String playerName) {
		GUI.setOwner(convertFieldNumber(fieldNumber), playerName);
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
	 * @param message - the message that promts the user.
	 * @param min - the minimum value the user is allowed to enter
	 * @param max - the maximum value the user is allowed to enter
	 * @return int - the integer that the user selected
	 */
	public int getInteger(String message, int min, int max) {
		return GUI.getUserInteger(message, min, max);
	}

	/**
	 * Displays a message to the user and awaits the response
	 * @param message - the message that promts the user
	 * @return String - the string that the user has entered
	 */
	public String getString(String message) {
		return GUI.getUserString(message);
	}

	/**
	 * Displays a message to the user and awaits the response from the button that the user pressed
	 * @param message - the message that promts the user
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
	 * @param message - the message that promts the user
	 * @return true - whenever the button is pressed
	 */
	public boolean getButtonPressed(String message) {
		GUI.getUserButtonPressed(message, "OK!");
		return true;
	}

	/**
	 * Displays a message to the user and awaits the button pressed response
	 * @param msg - the message that promts the user
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
