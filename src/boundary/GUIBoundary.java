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

	public static GUIBoundary getInstance() {
		if(instance == null) {
			instance = new GUIBoundary();
		}
		return instance;
	}

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
						.setSubText(language.fieldPrices(i))
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.Chance){
				fields[i] = new Chance.Builder()
						.setFgColor(Color.WHITE)
						.setBgColor(Color.BLACK)
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.Plot){
				fields[i] = new Street.Builder()
						.setTitle(language.fieldNames(i))
						.setSubText(language.fieldPrices(i))
						.setBgColor(getPropertyGroupColor(gameBoard.getField(i).getPropertyGroup()))
						.setDescription(
								" - Kun grund: " + gameBoard.getField(i).getRent()[0] + " | " +
										"1 hus: " + gameBoard.getField(i).getRent()[1] + " | " +
										"2 huse: " + gameBoard.getField(i).getRent()[2] + " | " +
										"3 huse: " + gameBoard.getField(i).getRent()[3] + " | " +
										"4 huse: " + gameBoard.getField(i).getRent()[4] + " | " +
										"Hotel: " + gameBoard.getField(i).getRent()[5])
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.Refuge){
				fields[i] = new Refuge.Builder()
						.setTitle(language.fieldNames(i))
						.setSubText(language.fieldDescription(i))
						.build();
			}else if(gameBoard.getField(i) instanceof entity.fields.ShippingLine){
				fields[i] = new Shipping.Builder()
						.setTitle(language.fieldNames(i))
						.setSubText(language.fieldPrices(i))
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

	public String getLanguage() {
		return GUI.getUserSelection("Select entity.language. \nVælg sprog.", "Dansk", "English");
	}

	public String getUserSelection(String message, String... options) {
		return GUI.getUserSelection(message, options);
	}

	public void setDices(DiceCup diceCup) {
		GUI.setDice(diceCup.getDices()[0].getFaceValue(), diceCup.getDices()[1].getFaceValue());
	}

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

	public void setCar(int fieldNumber, String playerName) {
		GUI.setCar(convertFieldNumber(fieldNumber), playerName);
	}

	public void removeCar(int fieldNumber, String playerName) {
		GUI.removeCar(convertFieldNumber(fieldNumber), playerName);
	}

	public void updateBalance(String playerName, int newBalance) {
		GUI.setBalance(playerName, newBalance);
	}

	public void setOwner(int fieldNumber, String playerName) {
		GUI.setOwner(convertFieldNumber(fieldNumber), playerName);
	}

	public int getInteger(String message) {
		return GUI.getUserInteger(message);
	}

	public int getInteger(String message, int min, int max) {
		return GUI.getUserInteger(message, min, max);
	}

	public String getString(String message) {
		return GUI.getUserString(message);
	}

	public boolean getBoolean(String message, String optionTrue, String optionFalse) {
		String response = GUI.getUserButtonPressed(message, optionTrue, optionFalse);
		return response.equals(optionTrue);
	}

	public boolean getButtonPressed(String message) {
		GUI.getUserButtonPressed(message, "OK!");
		return true;
	}

	public String getUserButtonPressed(String msg, String... buttons) {
		return GUI.getUserButtonPressed(msg, buttons);
	}

	private int convertFieldNumber(int fieldNumber) {
		int convertedFieldNumber = fieldNumber + 1;
		while(convertedFieldNumber > 40)
			convertedFieldNumber -= 40;
		return convertedFieldNumber;
	}

	public void releasePlayersFields(GameBoard gameBoard, Player player) {
		for(int i = 0; i < gameBoard.getFields().length; i++)
			if(gameBoard.getField(i) instanceof Ownable)
				if(gameBoard.getField(i).getOwner().getName().equals(player.getName())) {
					GUI.removeOwner(convertFieldNumber(i));
				}
	}

	public void updateConstructionRate(entity.fields.Field field){
		if(field.getConstructionRate() == 5){
			GUI.setHouses(field.getID(), 0);
			GUI.setHotel(field.getID(), true);
		}else{
			GUI.setHouses(field.getID(), field.getConstructionRate());
		}
	}

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

}
