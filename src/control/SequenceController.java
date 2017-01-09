package control;

import java.util.ArrayList;

import boundary.GUIBoundary;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;
import entity.fields.Chance;
import entity.fields.Field;
import entity.fields.Ownable;
import entity.language.LanguageHandler;

public abstract class SequenceController {

	/**
	 * Method that handles the sequence of landing on a field. All you need to do before calling this method, is to make sure you have "moved" the player
	 * to the correct onField, in the logic, then this method will handle the rest.
	 * Sometimes this method will even get called recursively, if a chance card changes the player position, and then recalls this method.
	 * @param player
	 * @param roll
	 * @param gameBoard
	 * @param playerList
	 */
	public static void landOnFieldSequence(Player player, int roll, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		int fieldNumber = player.getOnField();
		Field field = gameBoard.getField(fieldNumber);

		// updating the GUI to reflect the players position on the game board, and in the case "start" was passed, the new balance will also be reflected
		boundary.updateGUI(gameBoard, playerList);
		// if the player landed on a chance field, a card is made ready in the GUI
		if(field instanceof Chance) {
			boundary.setChanceCard(language.getChanceCardMsg(field.getTopCardNumber()));
			boundary.getButtonPressed(language.fieldMsg(fieldNumber));
			boundary.setChanceCard(language.getChanceCardMsg(-1));
		} else {
			boundary.getButtonPressed(language.fieldMsg(fieldNumber));
		}

		if (field instanceof Ownable) {
			Player ownerOfField = field.getOwner();

			// check if the field is owned
			if (ownerOfField == null) {
				SequenceController.buyPropertySequence(player, field, gameBoard, playerList);
			} else {
				// if the field has an owner and the player landing on the field, is not the owner, the player landing on the field pays rent to the owner
				if (!field.getOwner().getName().equals(player.getName())) {
					boundary.getButtonPressed(language.landedOnOwnedField(ownerOfField));
					field.landOnField(player, roll, gameBoard, playerList, false);
					boundary.updateGUI(gameBoard, playerList);
					// if the player didn't declare bankruptcy, a message will display how much was paid to who
					if(!player.isPlayerBroke()) {
						boundary.getButtonPressed(language.youPaidThisMuchToThisPerson(field.getRent(gameBoard, roll), ownerOfField));
					}
				} else {
					boundary.getButtonPressed(language.youOwnThisField());
				}
			}
		} else {

			// if the field is the tax field with a choice, the landOnField method is run with that choice
			if(field.getID() == 4) {
				field.landOnField(player, roll, gameBoard, playerList, boundary.getBoolean(language.getTaxChoice(), language.yes(), language.no()));
				boundary.updateGUI(gameBoard, playerList);
				// otherwise just a standard landOnField call
			} else {
				field.landOnField(player, roll, gameBoard, playerList, false);
				boundary.updateGUI(gameBoard, playerList);
			}
		}
	}

	/**
	 * Withdraws 1000 from the players bank account and sets him to be out of jail.
	 * @param player
	 * @param gameBoard
	 * @param playerList
	 */
	public static void payToGetOutOfJailSequence(Player player, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();

		// tries to withdraw 1000 from the player, while it returns false, the player will be asked to get more money
		while(player.getBankAccount().withdraw(1000) == false) {
			SequenceController.getMoneySequence(player, null, gameBoard, playerList, 1000);
		}
		player.setPlayerInJail(false);
		boundary.updateGUI(gameBoard, playerList);
	}

	/**
	 * Uses on the players "get out of jail" cards and sets him to be out of jail.
	 * @param player
	 */
	public static void useCardToGetOutOfJailSequence(Player player) {
		player.setGetOutOfJailCardCount(player.getGetOutOfJailCardCount()-1);
		player.setPlayerInJail(false);
	}

	/**
	 * Method that handles the sequence of constructing a building on a property.
	 * @param player
	 * @param gameBoard
	 * @param playerList
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
	 * @param playerList
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
		ArrayList<Field> tradeableList = gameBoard.getTradeableList(owner);
		String[] tradeableLabels = getFieldNamesFromListOfFields(tradeableList);
		String[] playerLabels = getPlayerNamesExceptSpecificPlayer(playerList, owner);
		Field fieldToSellObject = null;
		Player buyerObject = null;

		if (tradeableLabels.length == 0) {
			boundary.getButtonPressed(language.noTradeableProperties());
		} else {
			// gets user choices
			String fieldToSell = boundary.getUserSelection(language.choosePropertyToTrade(), tradeableLabels);
			String buyer = boundary.getUserSelection(language.choosePropertyBuyer(), playerLabels);

			// finds the field object by the name
			for (Field field : tradeableList) {
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
			int price = boundary.getInteger(language.enterPropertyTradePrice(), 0, buyerObject.getBankAccount().getBalance());
			// gets confirmation on the trade and executes actions if confirmed
			if(boundary.getBoolean(language.confirmPropertyTrade(fieldToSell, buyer, price), language.yes(), language.no())){
				if(fieldToSellObject.tradeField(owner, buyerObject, price)) {
					boundary.updateGUI(gameBoard, playerList);
					boundary.getButtonPressed(language.propertyTradeConfirmation(buyer, price));
				} else {
					boundary.getButtonPressed(language.notEnoughMoney());
				}
			}
		}
	}

	public static void tradeGetOutOfJailCardSequence(Player owner, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		String[] playerLabels = getPlayerNamesExceptSpecificPlayer(playerList, owner);
		Player buyerObject = null;

		// gets user choice
		String buyer = boundary.getUserSelection(language.chooseGetOutOfJailCardBuyer(), playerLabels);

		// finds the buyer object by name
		for (Player player : playerList.getPlayers()) {
			if (buyer.equals(player.getName())) {
				buyerObject = player;
			}
		}

		// gets the trade price
		int price = boundary.getInteger(language.enterGetOutOfJailCardTradePrice(), 0, buyerObject.getBankAccount().getBalance());
		// gets confirmation on the trade and executes actions if confirmed
		if(boundary.getBoolean(language.confirmGetOutOfJailCardTrade(buyer, price), language.yes(), language.no())){
			if(buyerObject.getBankAccount().transfer(owner, price)) {
				// transfers the "get out of jail" card
				owner.setGetOutOfJailCardCount(owner.getGetOutOfJailCardCount()-1);
				buyerObject.setGetOutOfJailCardCount(buyerObject.getGetOutOfJailCardCount()+1);
				// updates GUI after money was transfered
				boundary.updateGUI(gameBoard, playerList);
				boundary.getButtonPressed(language.getOutOfJailCardPurchaseConfirmation());
			} else {
				boundary.getButtonPressed(language.notEnoughMoney());
			}
		}
	}

	/**
	 * Method that handles the sequence of pawning a property.
	 * @param player
	 * @param gameBoard
	 * @param playerList
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
	 * @param playerList
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
	 * @param gameBoard
	 * @param playerList
	 */
	public static void buyPropertySequence(Player player, Field field, GameBoard gameBoard, PlayerList playerList) {

		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		int priceOfField = field.getPrice();

		// updates the GUI in case the player has been moved by a chance card
		boundary.updateGUI(gameBoard, playerList);
		// gets user choice
		if (boundary.getBoolean(language.buyingOfferMsg(priceOfField), language.yes(), language.no())) {
			// carries out the buy if possible and updates the GUI
			if (field.buyField(player)) {
				boundary.updateGUI(gameBoard, playerList);
				boundary.getButtonPressed(language.propertyPurchaseConfirmation());
			} else {
				boundary.getButtonPressed(language.notEnoughMoney());
				// if the playing landing on the field wanted to buy it, but couldn't afford it, it gets offered to the other players
				auctionSequence(player, field, gameBoard, playerList);
			}
		} else {
			// if the playing landing on the field doesn't want to buy it, it gets offered to the other players
			auctionSequence(player, field, gameBoard, playerList);
		}
	}

	/**
	 * Method that handles the sequence of auctioning off a property.
	 * @param playerOnField
	 * @param field
	 * @param gameBoard
	 * @param playerList
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
			int price = boundary.getInteger(language.enterAuctionPrice(), field.getPrice(), buyerObject.getBankAccount().getBalance());
			// gets confirmation on the purchase and executes actions if confirmed
			if(boundary.getBoolean(language.confirmPropertyPurchase(field.getName(), price), language.yes(), language.no())){
				if(field.buyField(buyerObject, price)) {
					boundary.updateGUI(gameBoard, playerList);
					boundary.getButtonPressed(language.propertyPurchaseConfirmation());
				} else {
					boundary.getButtonPressed(language.notEnoughMoney());
				}
			}
			// asks if the auction sequence should run again, since the purchase wasn't confirmed
			else if(boundary.getBoolean(language.wantToRunAuctionSequenceAgain(), language.yes(), language.no())) {
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

		getMoneySeq:
			// loop that continues until the player has enough money to pay his debt
			while(debitor.getBankAccount().getBalance() < targetAmount) {

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
					// only lets the player declare bankruptcy if his total releasable assets amounts to less than his debt
					if(debitor.getTotalReleasableAssets(gameBoard) < targetAmount) {
						// if the creditor is another player
						if(creditor != null) {
							debitor.getBankAccount().transfer(creditor, debitor.getTotalReleasableAssets(gameBoard));
							boundary.getButtonPressed(language.youPaidThisMuchToThisPerson(debitor.getTotalReleasableAssets(gameBoard), creditor));
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
		LanguageHandler language = LanguageHandler.getInstance();

		// sets players onField and bank account to -1 to indicate he is eliminated from the game
		player.setOnField(-1);
		player.getBankAccount().setBalance(-1);
		// returns his fields to the bank in the logic
		gameBoard.releasePlayersFields(player);
		// updates GUI, after the bankrupt player and his fields has been reset in the logic
		boundary.updateGUI(gameBoard, playerList);
		boundary.getButtonPressed(language.bankruptcyConcluded());
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
