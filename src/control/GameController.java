package control;

import boundary.GUIBoundary;
import entity.DiceCup;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;
import entity.fields.Chance;
import entity.fields.Field;
import entity.fields.Ownable;
import entity.language.LanguageHandler;

public class GameController {

    private GUIBoundary boundary;
    private GameBoard gameBoard;
    private LanguageHandler language;
    private PlayerList playerList;
    private DiceCup diceCup;

    public GameController() {
        SetupController setupController = new SetupController();
        gameBoard = setupController.setupGameBoard();
        language = LanguageHandler.getInstance();
        playerList = setupController.setupPlayers();
        boundary = GUIBoundary.getInstance();
        diceCup = new DiceCup();
    }

    /**
     * Method to start playing the game
     */
    public void runGame() {
        boundary.getButtonPressed(language.readyToBegin());
        while (!playerList.isThereAWinner()) {
            for (int i = 0; i < playerList.getPlayers().length; i++)
                if (!playerList.isThereAWinner() && !playerList.isPlayerBroke(i))
                    playTurn(playerList.getPlayer(i));
        }
        boundary.getButtonPressed(language.winnerMsg(playerList.whoIsTheWinner()));
    }


    /**
     * Method that receives a player object and posts a message with instructions for the player.
     * After the player has pressed "enter" the method will roll the dices, print the result of the roll,
     * enforce the landOnField method for the given field and do a win condition check. The method keeps
     * running that sequence until the player has no more extra turns or has won the game. If the players
     * turn ends and he hasn't won, the method will print a message with the players current score,
     * if the player has won, the method will post a message saying that.
     *
     * @param player Player
     */
    public void playTurn(Player player) {
        String turnChoice = boundary.getUserButtonPressed(language.preMsg(player), language.throwDices(), language.build(), language.trade());
        if (turnChoice.equals(language.throwDices())) {
            diceCup.rollDices();
            boundary.setDices(diceCup);
            boundary.removeCar(player.getOnField(), player.getName());
            player.movePlayer(diceCup.getSum());
            int fieldNumber = player.getOnField();
            Field field = gameBoard.getField(fieldNumber);
            boundary.setCar(fieldNumber, player.getName());
            if(field instanceof Chance) {
            	boundary.setChanceCard(language.getChanceCardMsg(field.getTopCardNumber()));
                boundary.getButtonPressed(language.fieldMsg(fieldNumber));
                boundary.setChanceCard(language.getChanceCardMsg(-1));
            } else {
            	boundary.getButtonPressed(language.fieldMsg(fieldNumber));
            }
            if (field instanceof Ownable) {
                Player ownerOfField = field.getOwner();
                if (ownerOfField == null) {
                    SequenceController.buyPropertySequence(player, field);
                } else {
                    if (!field.getOwner().getName().equals(player.getName())) {
                        boundary.getButtonPressed(language.landedOnOwnedField(ownerOfField));
                        int preBalance = player.getBankAccount().getBalance();
                        field.landOnField(player, diceCup.getSum(), gameBoard, playerList, false);
                        boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
                        boundary.updateBalance(ownerOfField.getName(), ownerOfField.getBankAccount().getBalance());
                        int amountPayed = preBalance - player.getBankAccount().getBalance();
                        boundary.getButtonPressed(language.youPaidThisMuchToThisPerson(amountPayed, ownerOfField));
                    } else {
                        boundary.getButtonPressed(language.youOwnThisField());
                    }
                }
            } else {
            	if(field.getID() == 4) {
            		field.landOnField(player, diceCup.getSum(), gameBoard, playerList, boundary.getBoolean(language.getTaxChoice(), language.yes(), language.no()));
                	boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
            	} else {
                	field.landOnField(player, diceCup.getSum(), gameBoard, playerList, false);
                	boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
            	}
            	
            }
            if (player.getBankAccount().getBalance() <= 0) {
                boundary.getButtonPressed(language.youAreBroke());
                boundary.removeCar(fieldNumber, player.getName());
                boundary.releasePlayersFields(gameBoard, player);
                gameBoard.releasePlayersFields(player);
            }
        } else if (turnChoice.equals(language.build())) {
            SequenceController.buildSequence(player, gameBoard);
        } else if (turnChoice.equals(language.trade())) {
            SequenceController.tradePropertiesSequence(player, gameBoard, playerList);
        }
    }

    /**
     *
     */
    public GUIBoundary getBoundary() {
        return this.boundary;
    }
}
