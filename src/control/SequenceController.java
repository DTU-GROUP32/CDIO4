package control;

import java.util.ArrayList;

import boundary.GUIBoundary;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;
import entity.fields.Field;
import entity.language.LanguageHandler;

public abstract class SequenceController {

	/**
	 * Method that handles the sequence of constructing a building on a property.
	 * @param player
	 * @param gameBoard
	 */
	public static void buildSequence(Player player, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> buildableList = gameBoard.getBuildableList(player);
		String[] buildableLabels = getFieldNamesFromListOfFields(buildableList);

		// lets the player choose a property to construct on, executes in logic followed by updating the GUI
		if (buildableLabels.length == 0) {
			boundary.getButtonPressed(language.notBuildable());
		} else {
			Field fieldToBuildOn = gameBoard.getField(gameBoard.getIndexByName(boundary.getUserSelection(language.choosePlotToBuildOn(), buildableLabels)));
			fieldToBuildOn.buildConstruction();
			boundary.updateGUI(gameBoard, playerList);
		}
	}

	/**
	 * Method that handles the sequence of demolishing a building on a property.
	 * @param player
	 * @param gameBoard
	 */
	public static void demolitionSequence(Player player, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> demolitionableList = gameBoard.getDemolitionableList(player);
		String[] demolitionableLabels = getFieldNamesFromListOfFields(demolitionableList);

		// lets the player choose a property to demolish on, executes in logic followed by updating the GUI
		if (demolitionableLabels.length == 0)
		{
			boundary.getButtonPressed(language.noDemolitionableProperties());
		} else
		{
			String fieldToDemolishOn = boundary.getUserSelection(language.choosePropertyToDemolishOn(), demolitionableLabels);
			gameBoard.getField(gameBoard.getIndexByName(fieldToDemolishOn)).sellConstruction();
			boundary.updateGUI(gameBoard, playerList);
		}
	}

	/**
	 * Method that handles the sequence of trading a property.
	 * @param owner
	 * @param gameBoard
	 * @param playerList
	 */
	public static void tradePropertiesSequence(Player owner, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> sellableList = gameBoard.getPropertyList(owner);
		String[] sellableLabels = getFieldNamesFromListOfFields(sellableList);
		String[] playerLabels = getPlayerNamesExceptSpecificPlayer(playerList, owner);
		Field fieldToSellObject = null;
		Player buyerObject = null;

		if (sellableLabels.length == 0) {
			boundary.getButtonPressed(language.noTradeableProperties());
		} else {
			// gets user choices
			String fieldToSell = boundary.getUserSelection(language.choosePlotTrade(), sellableLabels);
			String buyer = boundary.getUserSelection(language.choosePropertyBuyer(), playerLabels);

			// finds the field object by the name
			for (Field field : sellableList) {
				if (fieldToSell.equals(field.getName())) {
					fieldToSellObject = field;
				}
			}

			// finds the buyer object by name
			for (Player player : playerList.getPlayers()) {
				if (buyer.equals(player.getName())) {
					buyerObject = player;
				}
			}

			// gets the trade price
			int price = boundary.getInteger(language.enterTradePrice(), 0, buyerObject.getBankAccount().getBalance());
			// gets confirmation on the trade and executes actions if confirmed
			if(boundary.getBoolean(language.confirmTrade(fieldToSell, buyer, price), language.yes(), language.no())){
				if(fieldToSellObject.tradeField(owner, buyerObject, price)) {
					boundary.updateGUI(gameBoard, playerList);
					boundary.getButtonPressed(language.purchaseConfirmation());
				} else {
					boundary.getButtonPressed(language.notEnoughMoney());
				}
			}
		}
	}

	/**
	 * Method that handles the sequence of pawning a property.
	 * @param player
	 * @param gameBoard
	 */
	public static void pawnSequence(Player player, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> pawnableList = gameBoard.getPawnableList(player);
		String[] pawnableLabels = getFieldNamesFromListOfFields(pawnableList);

		if (pawnableLabels.length == 0)
		{
			boundary.getButtonPressed(language.noPawnableFields());
		} else
		{
			// gets user choice
			String fieldToPawn = boundary.getUserSelection(language.choosePropertyToPawn(), pawnableLabels);
			// gets the field object by the name and pawns it, if its possible
			if(gameBoard.getField(gameBoard.getIndexByName(fieldToPawn)).pawnField())
			{
				boundary.updateGUI(gameBoard, playerList);
				boundary.getButtonPressed(language.pawnSuccessful());
			} else
			{
				boundary.getButtonPressed(language.pawnUnsuccessful());
			}
		}
	}

	/**
	 * Method that handles the sequence of undoing the pawn of a property.
	 * @param player
	 * @param gameBoard
	 */
	public static void undoPawnSequence(Player player, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> undoPawnableList = gameBoard.getAlreadyPawnedList(player);
		String[] alreadyPawnedLabels = getFieldNamesFromListOfFields(undoPawnableList);

		if (alreadyPawnedLabels.length == 0)
		{
			boundary.getButtonPressed(language.noPawnedProperties());
		} else
		{
			// gets user choice
			String fieldToUndoPawn = boundary.getUserSelection(language.choosePropertyToUndoPawn(), alreadyPawnedLabels);
			// gets the field object by the name and undoes the pawn, if its possible
			if(gameBoard.getField(gameBoard.getIndexByName(fieldToUndoPawn)).undoPawnField())
			{
				boundary.updateGUI(gameBoard, playerList);
				boundary.getButtonPressed(language.undoPawnSuccessful());
			} else
			{
				boundary.getButtonPressed(language.undoPawnUnsuccessful());
			}
		}
	}

	/**
	 * Method that handles the sequence of buying a property.
	 * @param player
	 * @param field
	 */
	public static void buyPropertySequence(Player player, Field field, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		int priceOfField = field.getPrice();

		// gets user choice
		if (boundary.getBoolean(language.buyingOfferMsg(priceOfField), language.yes(), language.no())) {
			// carries out the buy if possible and updates the GUI
			if (field.buyField(player)) {
				boundary.updateGUI(gameBoard, playerList);
				boundary.getButtonPressed(language.purchaseConfirmation());
			} else {
				boundary.getButtonPressed(language.notEnoughMoney());
			}
		} else {
			// if the playing landing on the field doesn't want to buy it, it gets offered to the other players
			auctionSequence(player, field, gameBoard, playerList);
		}
	}

	/**
	 * Method that handles the sequence of auctioning off a property.
	 * @param playerOnField
	 * @param playerList
	 * @param field
	 */
	public static void auctionSequence(Player playerOnField, Field field, GameBoard gameBoard, PlayerList playerList){

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		String[] playerLabels = getPlayerNamesExceptSpecificPlayer(playerList, playerOnField);
		Player buyerObject = null;

		// asks if anyone wants to buy the field
		if(boundary.getBoolean(language.auctionNotification(), language.yes(), language.no())) {
			// gets user choice
			String buyer = boundary.getUserSelection(language.choosePropertyBuyer(), playerLabels);

			// gets the player object by the name
			for (Player player : playerList.getPlayers()) {
				if (buyer.equals(player.getName()))
				{
					buyerObject = player;
				}
			}

			//gets user choice
			int price = boundary.getInteger(language.enterAuctionPrice(), 0, buyerObject.getBankAccount().getBalance());
			// gets confirmation on the purchase and executes actions if confirmed
			if(boundary.getBoolean(language.confirmPurchase(field.getName(), price), language.yes(), language.no())){
				if(field.buyField(buyerObject, price)) {
					boundary.updateGUI(gameBoard, playerList);
					boundary.getButtonPressed(language.purchaseConfirmation());
				} else {
					boundary.getButtonPressed(language.notEnoughMoney());
				}
			} else {
				auctionSequence(playerOnField, field, gameBoard, playerList);
			}
		}
	}

	/**
	 * Method that handles the sequence of getting money if a player doesn't have enough money.
	 * @param debitor
	 * @param creditor
	 * @param gameBoard
	 * @param playerList
	 * @param targetAmount
	 */
	public static void getMoneySequence(Player debitor, Player creditor, GameBoard gameBoard, PlayerList playerList, int targetAmount) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		String[] options = {language.pawn(), language.demolish(), language.trade(), language.bankrupt()};

		// loop that continues until the player has enough money to pay his debt
		getMoneySeq: while(debitor.getBankAccount().getBalance() < targetAmount) {

			// gets user choice
			String choice = boundary.getUserSelection(language.toPay(targetAmount), options);

			// handles which other sequence to run by the users choice
			if(choice.equals(language.pawn())) {
				pawnSequence(debitor, gameBoard, playerList);
			} else if (choice.equals(language.demolish())) {
				demolitionSequence(debitor, gameBoard, playerList);
			} else if (choice.equals(language.trade())) {
				tradePropertiesSequence(debitor, gameBoard, playerList);
			} else if (choice.equals(language.bankrupt())) {
				// only lets the player declare bankruptcy if his total assets amounts to less than his debt
				if(debitor.getTotalAssets(gameBoard) < targetAmount) {
					// if the creditor is another player
					if(creditor != null) {
						debitor.getBankAccount().transfer(creditor, debitor.getTotalAssets(gameBoard));
					}
					executeBankruptcy(debitor, gameBoard, playerList);
					break getMoneySeq;
				} else {
					boundary.getButtonPressed(language.canGetMoney());
				}
			}
		}
	}

	/**
	 * Help method that handles the logic and GUI events of a bankruptcy
	 * @param player
	 * @param gameBoard
	 */
	public static void executeBankruptcy(Player player, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();

		// sets players onField and bank account to -1 to indicate he is eliminated from the game
		player.setOnField(-1);
		player.getBankAccount().setBalance(-1);
		// returns his fields to the bank in the logic
		gameBoard.releasePlayersFields(player);
		// updates GUI, after the bankrupt player and his fields has been reset in the logic
		boundary.updateGUI(gameBoard, playerList);
	}

	/**
	 * Methods that converts a list of fields to an array of field names.
	 * @param fieldList
	 * @return fieldLabels
	 */
	private static String[] getFieldNamesFromListOfFields(ArrayList<Field> fieldList) {
		String[] fieldLabels = new String[fieldList.size()];
		for (int i = 0; i < fieldList.size(); i++) {
			fieldLabels[i] = fieldList.get(i).getName();
		}
		return fieldLabels;
	}

	/**
	 * Methods that removes one player from a list and converts the remaining players to an array of player names.
	 * @param playerList
	 * @param playerToExclude
	 * @return
	 */
	private static String[] getPlayerNamesExceptSpecificPlayer(PlayerList playerList, Player playerToExclude) {
		String[] playerLabels = new String[playerList.getPlayers().length - 1];
		int i = 0;
		for(Player player : playerList.getPlayers()) {
			if (player.getName().equals(playerToExclude.getName()) == false) {
				playerLabels[i] = player.getName();
				i++;
			}
		}
		return playerLabels;
	}
}
