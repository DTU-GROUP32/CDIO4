package control;

import java.util.ArrayList;
import boundary.GUIBoundary;
import entity.GameBoard;
import entity.Player;
import entity.fields.Field;

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
    public static void tradePropertiesSequence(Player player, GameBoard gameBoard, GUIBoundary boundary){
        int i = 0;
        ArrayList<Field> sellableList = gameBoard.getSellableList(player);
        String[] sellableLabels = new String[40];
        for (Field field: sellableList) {
            sellableLabels[i++] = field.getName();
        }
        boundary.getUserSelection("Choose plot to trade", sellableLabels);
    }

    /**
     *
     */
    public static void pawnSequence(Player player, GameBoard gameBoard, GUIBoundary boundary){

    }

    /**
     *
     */
    public static void buyPropertySequence(Player player, Field field, GUIBoundary boundary){

    }
}
