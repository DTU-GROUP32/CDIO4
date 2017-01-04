package control;

import java.util.ArrayList;

import boundary.GUIBoundary;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;
import entity.fields.Field;
import entity.language.Language;
import entity.language.LanguageHandler;

/**
 *
 */
public abstract class SequenceController {

    /**
     *
     */
    public static void buildSequence(Player player, GameBoard gameBoard, GUIBoundary boundary) {
        int i = 0;
        ArrayList<Field> buildableList = gameBoard.getBuildableList(player);
        String[] buildableLabels = new String[gameBoard.getBuildableList(player).size()];
        for (Field field : buildableList) {
            buildableLabels[i++] = field.getName();
        }
        if (buildableLabels.length == 0) {
            boundary.getButtonPressed("You have no buildable properties", "Ok!");
        } else {
            boundary.getUserSelection("Choose plot to build on", buildableLabels);
        }

    }

    /**
     *
     */
    public static void demolitionSequence(Player player, GameBoard gameBoard, GUIBoundary boundary) {

    }

    /**
     *
     */
    public static void tradePropertiesSequence(Player owner, GameBoard gameBoard, GUIBoundary boundary, PlayerList playerList) {
        int i = 0;
        ArrayList<Field> sellableList = gameBoard.getPropertyList(owner);
        String[] sellableLabels = new String[sellableList.size()];
        String[] playerLabels = new String[playerList.getPlayers().length - 1];
        Field fieldToSellObject;
        Player buyerObject;

        for (Field field : sellableList) {
            sellableLabels[i++] = field.getName();
        }
        if (sellableLabels.length == 0) {
            boundary.getButtonPressed("Du har ingen felter at sælge", "Ok!");
        } else {
            i = 0;
            for (Player player : playerList.getPlayers()) {
                if (!player.getName().equals(owner.getName())) {
                    playerLabels[i] = player.getName();
                    i++;
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
                            fieldToSellObject.tradeField(owner, buyerObject, price);
                            boundary.setOwner(field.getID(), buyerObject.getName());
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
    public static boolean getMoneySequence(Player player, GameBoard gameBoard, GUIBoundary boundary, PlayerList playerList) {
        return false;
    }

    /**
     *
     */
    public static void pawnSequence(Player player, GameBoard gameBoard, GUIBoundary boundary) {

    }

    /**
     *
     */
    public static void buyPropertySequence(Player player, Field field, GUIBoundary boundary, LanguageHandler language) {
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
}
