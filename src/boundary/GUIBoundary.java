package boundary;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_codebehind.Car.Builder;
import desktop_fields.Brewery;
import desktop_fields.Field;
import desktop_fields.Refuge;
import desktop_fields.Shipping;
import desktop_fields.Street;
import desktop_fields.Tax;
import desktop_resources.GUI;
import entity.DiceCup;
import entity.GameBoard;
import entity.Player;
import entity.language.LanguageHandler;

public class GUIBoundary {

	public void createGameBoard(GameBoard gameBoard, LanguageHandler language) {
		Field[] fields = new Field[gameBoard.getFields().length];
		fields[0] = new Street.Builder()
				.setTitle(language.fieldNames(0))
				.setSubText(language.fieldPrices(0))
				.setBgColor(Color.RED)
				.build();
		fields[1] = new Street.Builder()
				.setTitle(language.fieldNames(1))
				.setSubText(language.fieldPrices(1))
				.setBgColor(Color.cyan)
				.build();
		fields[2] = new Shipping.Builder()
				.setTitle(language.fieldNames(2))
				.setSubText(language.fieldPrices(2))
				.build();
		fields[3] = new Street.Builder()
				.setTitle(language.fieldNames(3))
				.setSubText(language.fieldPrices(3))
				.setBgColor(Color.cyan)
				.build();
		fields[4] = new Brewery.Builder()
				.setTitle(language.fieldNames(4))
				.setSubText(language.fieldPrices(4))
				.build();
		fields[5] = new Street.Builder()
				.setTitle(language.fieldNames(5))
				.setSubText(language.fieldPrices(5))
				.setBgColor(Color.PINK)
				.build();
		fields[6] = new Refuge.Builder()
				.setTitle(language.fieldNames(6))
				.setSubText(language.fieldDescription(6))
				.build();
		fields[7] = new Street.Builder()
				.setTitle(language.fieldNames(7))
				.setSubText(language.fieldPrices(7))
				.setBgColor(Color.PINK)
				.build();
		fields[8] = new Shipping.Builder()
				.setTitle(language.fieldNames(8))
				.setSubText(language.fieldPrices(8))
				.build();
		fields[9] = new Street.Builder()
				.setTitle(language.fieldNames(9))
				.setSubText(language.fieldPrices(9))
				.setBgColor(Color.GREEN)
				.build();
		fields[10] = new Tax.Builder()
				.setTitle(language.fieldNames(10))
				.setDescription(language.fieldDescription(10))
				.build();
		fields[11] = new Street.Builder()
				.setTitle(language.fieldNames(11))
				.setSubText(language.fieldPrices(11))
				.setBgColor(Color.GREEN)
				.build();
		fields[12] = new Brewery.Builder()
				.setTitle(language.fieldNames(12))
				.setSubText(language.fieldPrices(12))
				.build();
		fields[13] = new Street.Builder()
				.setTitle(language.fieldNames(13))
				.setSubText(language.fieldPrices(13))
				.setBgColor(Color.GRAY)
				.build();
		fields[14] = new Shipping.Builder()
				.setTitle(language.fieldNames(14))
				.setSubText(language.fieldPrices(14))
				.build();
		fields[15] = new Street.Builder()
				.setTitle(language.fieldNames(15))
				.setSubText(language.fieldPrices(15))
				.setBgColor(Color.GRAY)
				.build();
		fields[16] = new Refuge.Builder()
				.setTitle(language.fieldNames(16))
				.setSubText(language.fieldDescription(16))
				.build();
		fields[17] = new Street.Builder()
				.setTitle(language.fieldNames(17))
				.setSubText(language.fieldPrices(17))
				.setBgColor(Color.WHITE)
				.build();
		fields[18] = new Tax.Builder()
				.setTitle(language.fieldNames(18))
				.setDescription(language.fieldDescription(18))
				.build();
		fields[19] = new Street.Builder()
				.setTitle(language.fieldNames(19))
				.setSubText(language.fieldPrices(19))
				.setBgColor(Color.WHITE)
				.build();
		fields[20] = new Shipping.Builder()
				.setTitle(language.fieldNames(20))
				.setSubText(language.fieldPrices(20))
				.build();
		fields[21] = new Street.Builder()
				.setTitle(language.fieldNames(21))
				.setSubText(language.fieldPrices(21))
				.setBgColor(Color.YELLOW)
				.build();
		GUI.create(fields);
		GUI.setDice(1, 1);
	}

	public String getLanguage() {
		return GUI.getUserSelection("Select entity.language. \nVælg sprog.", "Dansk", "English");
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

	private int convertFieldNumber(int fieldNumber) {
		int convertedFieldNumber = fieldNumber + 1;
		while(convertedFieldNumber > 22)
			convertedFieldNumber -= 22;
		return convertedFieldNumber;
	}

	public void releasePlayersFields(GameBoard gameBoard, Player player) {
		for(int i = 0; i < gameBoard.getFields().length; i++)
			if(gameBoard.getField(i).isOwnable())
				if(gameBoard.getField(i).getOwner() != null)
					if(gameBoard.getField(i).getOwner().getName().equals(player.getName()))
						GUI.removeOwner(convertFieldNumber(i));
	}
}
