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
			boundary.getButtonPressed("You have no buildable properties");
		} else {
			Field fieldToBuildOn = gameBoard.getField(gameBoard.getIndexByName(boundary.getUserSelection("Choose plot to build on", buildableLabels)));
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
			boundary.getButtonPressed("You have no demolitionable properties");
		} else
		{
			String fieldToDemolishOn = boundary.getUserSelection("Choose plot to demolish on", demolitionableLabels);
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
			boundary.getButtonPressed("Du har ingen felter at sælge");
		} else {
			for (int i = 1; i < playerList.getPlayers().length; i++) {
				if (!playerList.getPlayers()[i].getName().equals(owner.getName())) {
					playerLabels[i] = playerList.getPlayers()[i].getName();
				}
			}

			String fieldToSell = boundary.getUserSelection("Choose plot to trade", sellableLabels);
			String buyer = boundary.getUserSelection("Choose who is buying", playerLabels);

            fieldLoop:
            for (Field field : sellableList) {
                if (fieldToSell.equals(field.getName())) {
                    fieldToSellObject = field;
                    for (Player player : playerList.getPlayers()) {
                        if (buyer.equals(player.getName())) {
                            buyerObject = player;
                            int price = boundary.getInteger("Which price?", 0, 30000);
                            if(boundary.getBoolean("Do you want to trade?", "Yes", "No")){
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
			boundary.getButtonPressed("Du har ingen felter at patnsætte");
		} else
		{
			String fieldToPawn = boundary.getUserSelection("Choose property to pawn", pawnableLabels);
			if(gameBoard.getField(gameBoard.getIndexByName(fieldToPawn)).pawnField())
			{
				boundary.getButtonPressed("Din ejendom er blevet pantsat");
			} else
			{
				boundary.getButtonPressed("Din ejendom kunne ikke pantsættes");
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
		String buyer = boundary.getUserSelection("Choose who is buying", playerLabels);
		for (Player player : playerList.getPlayers()) {
			if (buyer.equals(player.getName()))
			{
				buyerObject = player;
			}
		}
		int price = boundary.getInteger("Which price?", 0, buyerObject.getBankAccount().getBalance());
		field.buyField(buyerObject, price);
		boundary.setOwner(field.getID(), buyer);
		boundary.updateBalance(buyer, buyerObject.getBankAccount().getBalance());
	}



	public static void getMoneySequence(Player player, GameBoard gameBoard, PlayerList playerList) {
		GUIBoundary boundary = GUIBoundary.getInstance();
		LanguageHandler language = LanguageHandler.getInstance();
		
		
	}
}
