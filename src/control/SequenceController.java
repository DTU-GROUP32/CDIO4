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
public class SequenceController {

    /**
     *
     */
    public static void buildSequence(Player player, GameBoard gameBoard, GUIBoundary boundary){
        int i = 0;
        ArrayList<Field> buildableList = gameBoard.getBuildableList(player);
        String[] buildableLabels = new String[40];
        for (Field field: buildableList) {
            buildableLabels[i++] = field.getName();
        }
        boundary.getUserSelection("Choose plot to build on", buildableLabels);
    }

    /**
     *
     */
    public static void demolitionSequence(Player player, GameBoard gameBoard, GUIBoundary boundary){

    }

    /**
     *
     */
    public static void tradePropertiesSequence(Player player, GameBoard gameBoard, GUIBoundary boundary, PlayerList playerList){
        int i = 0;
        ArrayList<Field> sellableList = gameBoard.getPropertyList(player);
        String[] sellableLabels = new String[sellableList.size()];
        for (Field field: sellableList) {
            sellableLabels[i++] = field.getName();
        }
        String fieldToSell = boundary.getUserSelection("Choose plot to trade", sellableLabels);
        String[] playerLabels = new String[playerList.getPlayers().length];
        for (Player player: playerList.getPlayers()) {
            playerLabels[i++] = player.getName();
        }
        String buyer = boundary.getUserSelection("Choose who is buying", playerLabels);
        int price = boundary.getInteger("Which price?", 0, 30000);
    }

    /**
     *
     */
    public static void pawnSequence(Player player, GameBoard gameBoard, GUIBoundary boundary){

    }

    /**
     *
     */
    public static void buyPropertySequence(Player player, Field field, GUIBoundary boundary, LanguageHandler language){
        int priceOfField = field.getPrice();
        if(boundary.getBoolean(language.buyingOfferMsg(priceOfField), language.yes(), language.no()))
        {
            if(player.getBankAccount().getBalance() > priceOfField)
            {
                field.buyField(player);
                boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
                boundary.setOwner(player.getOnField(), player.getName());
                boundary.getButtonPressed(language.purchaseConfirmation());
            } else
            {
                boundary.getButtonPressed(language.notEnoughMoney());
            }
        }
    }
}
