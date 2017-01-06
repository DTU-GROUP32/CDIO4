package control;

import java.util.ArrayList;

import boundary.GUIBoundary;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;
import entity.fields.Field;
import entity.language.LanguageHandler;

/**
 *
 */
public abstract class SequenceController {

	/**
	 *
	 */
	public static void buildSequence(Player player, GameBoard gameBoard) {
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> buildableList = gameBoard.getBuildableList(player);
		String[] buildableLabels = new String[gameBoard.getBuildableList(player).size()];
		for (int i = 0; i < buildableLabels.length; i++) {
			buildableLabels[i] = buildableList.get(i).getName();
		}
		if (buildableLabels.length == 0) {
			boundary.getButtonPressed(language.notBuildable());
		} else {
			Field fieldToBuildOn = gameBoard.getField(gameBoard.getIndexByName(boundary.getUserSelection(language.choosePlotToBuildOn(), buildableLabels)));
			fieldToBuildOn.buildConstruction();
			boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
		}
	}

	/**
	 *
	 */
	public static void demolitionSequence(Player player, GameBoard gameBoard) {
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> demolitionableList = gameBoard.getDemolitionableList(player);
		String[] demolitionableLabels = new String[gameBoard.getDemolitionableList(player).size()];
		for (int i = 0; i < demolitionableLabels.length; i++) {
			demolitionableLabels[i] = demolitionableList.get(i).getName();
		}
		if (demolitionableLabels.length == 0)
		{
			boundary.getButtonPressed(language.noDemolitionableProperties());
		} else
		{
			String fieldToDemolishOn = boundary.getUserSelection(language.choosePropertyToDemolishOn(), demolitionableLabels);
			gameBoard.getField(gameBoard.getIndexByName(fieldToDemolishOn)).sellConstruction();
			boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
		}
	}

	/**
	 *
	 */
	public static void tradePropertiesSequence(Player owner, GameBoard gameBoard, PlayerList playerList) {
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> sellableList = gameBoard.getPropertyList(owner);
		String[] sellableLabels = new String[sellableList.size()];
		String[] playerLabels = new String[playerList.getPlayers().length - 1];
		Field fieldToSellObject;
		Player buyerObject;

		for (int i = 0; i < sellableList.size(); i++) {
			sellableLabels[i] = sellableList.get(i).getName();
		}
		if (sellableLabels.length == 0) {
			boundary.getButtonPressed(language.noTradeableProperties());
		} else {
			int j = 0;
			for (int i = 0; i < playerList.getPlayers().length; i++) {
				if (playerList.getPlayers()[i].getName().equals(owner.getName()) == false) {
					playerLabels[j] = playerList.getPlayers()[i].getName();
					j++;
				}
			}

			String fieldToSell = boundary.getUserSelection(language.choosePlotTrade(), sellableLabels);
			String buyer = boundary.getUserSelection(language.choosePropertyBuyer(), playerLabels);

			fieldLoop:
				for (Field field : sellableList) {
					if (fieldToSell.equals(field.getName())) {
						fieldToSellObject = field;
						for (Player player : playerList.getPlayers()) {
							if (buyer.equals(player.getName())) {
								buyerObject = player;
								int price = boundary.getInteger(language.enterTradePrice(), 0, 30000);
								if(boundary.getBoolean(language.confirmTrade(), language.yes(), language.no())){
									fieldToSellObject.tradeField(owner, buyerObject, price);
									boundary.setOwner(field.getID(), buyerObject.getName());
									boundary.updateBalance(owner.getName(), owner.getBankAccount().getBalance());
									boundary.updateBalance(buyerObject.getName(), buyerObject.getBankAccount().getBalance());
								}
								break fieldLoop;
							}
						}
					}
				}
		}
	}

	/**
	 *
	 */
	public static void pawnSequence(Player player, GameBoard gameBoard) {
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> pawnableList = gameBoard.getPawnableList(player);
		String[] pawnableLabels = new String[gameBoard.getPawnableList(player).size()];
		for (int i = 0; i < pawnableLabels.length; i++)
			pawnableLabels[i] = pawnableList.get(i).getName();

		if (pawnableLabels.length == 0)
		{
			boundary.getButtonPressed(language.noPawnableFields());
		} else
		{
			String fieldToPawn = boundary.getUserSelection(language.choosePropertyToPawn(), pawnableLabels);
			if(gameBoard.getField(gameBoard.getIndexByName(fieldToPawn)).pawnField())
			{
				boundary.updatePawnStatus(gameBoard.getField(gameBoard.getIndexByName(fieldToPawn)));
				boundary.getButtonPressed(language.pawnSuccessful());
			} else
			{
				boundary.getButtonPressed(language.pawnUnsuccessful());
			}
		}
	}

	public static void undoPawnSequence(Player player, GameBoard gameBoard) {
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		ArrayList<Field> undoPawnableList = gameBoard.getAlreadyPawnedList(player);
		String[] alreadyPawnedLabels = new String[gameBoard.getAlreadyPawnedList(player).size()];
		for (int i = 0; i < alreadyPawnedLabels.length; i++)
			alreadyPawnedLabels[i] = undoPawnableList.get(i).getName();

		if (alreadyPawnedLabels.length == 0)
		{
			boundary.getButtonPressed(language.noPawnedProperties());
		} else
		{
			String fieldToUndoPawn = boundary.getUserSelection(language.choosePropertyToUndoPawn(), alreadyPawnedLabels);
			if(gameBoard.getField(gameBoard.getIndexByName(fieldToUndoPawn)).undoPawnField())
			{
				boundary.updatePawnStatus(gameBoard.getField(gameBoard.getIndexByName(fieldToUndoPawn)));
				boundary.getButtonPressed(language.undoPawnSuccesful());
			} else
			{
				boundary.getButtonPressed(language.undoPawnUnsuccesful());
			}
		}
	}

	/**
	 *
	 */
	public static void buyPropertySequence(Player player, Field field) {
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		int priceOfField = field.getPrice();
		if (boundary.getBoolean(language.buyingOfferMsg(priceOfField), language.yes(), language.no())) {
			if (player.getBankAccount().getBalance() > priceOfField) {
				field.buyField(player);
				boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
				boundary.setOwner(player.getOnField(), player.getName());
				boundary.getButtonPressed(language.purchaseConfirmation());
			} else {
				boundary.getButtonPressed(language.notEnoughMoney());
			}
		}
	}

	public static void auctionSequence(Player playerOnField, PlayerList playerList, Field field){
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		Player buyerObject = null;

		String[] playerLabels = new String[playerList.getPlayers().length - 1];
		for (int i = 1; i < playerList.getPlayers().length; i++) {
			if (!playerList.getPlayers()[i].getName().equals(playerOnField.getName())) {
				playerLabels[i] = playerList.getPlayers()[i].getName();
			}
		}
		String buyer = boundary.getUserSelection(language.choosePropertyBuyer(), playerLabels);
		for (Player player : playerList.getPlayers()) {
			if (buyer.equals(player.getName()))
			{
				buyerObject = player;
			}
		}
		int price = boundary.getInteger(language.enterAuctionPrice(), 0, buyerObject.getBankAccount().getBalance());
		field.buyField(buyerObject, price);
		boundary.setOwner(field.getID(), buyer);
		boundary.updateBalance(buyer, buyerObject.getBankAccount().getBalance());
	}



	public static void getMoneySequence(Player debitor, Player creditor, GameBoard gameBoard, PlayerList playerList, int targetAmount) {
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		String[] options = {language.pawn(), language.demolish(), language.trade(), language.bankrupt()};

		getMoneySeq: while(debitor.getBankAccount().getBalance() < targetAmount) {

			String choice = boundary.getUserSelection(language.toPay(targetAmount), options);

			if(choice.equals(language.pawn())) {
				pawnSequence(debitor, gameBoard);
			} else if (choice.equals(language.demolish())) {
				demolitionSequence(debitor, gameBoard);
			} else if (choice.equals(language.trade())) {
				tradePropertiesSequence(debitor, gameBoard, playerList);
			} else if (choice.equals(language.bankrupt())) {
				if(debitor.getTotalAssets(gameBoard) < targetAmount) {
					if(creditor != null) {
						debitor.getBankAccount().transfer(creditor, debitor.getTotalAssets(gameBoard));
						debitor.getBankAccount().setBalance(-1);
					} else {
						debitor.getBankAccount().withdraw(debitor.getTotalAssets(gameBoard));
						debitor.getBankAccount().setBalance(-1);
					}
					boundary.releasePlayersFields(gameBoard, debitor);
					gameBoard.releasePlayersFields(debitor);
					for(int i = 0; i < gameBoard.getFields().length; i++){
						boundary.updateConstructionRate(gameBoard.getField(i));
						boundary.updatePawnStatus(gameBoard.getField(i));
					}
					break getMoneySeq;
				} else {
					boundary.getButtonPressed(language.canGetMoney());
				}
			}
		}
	}
}
