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
		playerList = setupController.setupPlayers();
		language = LanguageHandler.getInstance();
		boundary = GUIBoundary.getInstance();
		diceCup = new DiceCup();
	}

	/**
	 * Method to run the game
	 */
	public void runGame() {
		boundary.getButtonPressed(language.readyToBegin());
		// while there is no winner the game keeps running
		while (!playerList.isThereAWinner()) {
			// runs through the player list
			for (int i = 0; i < playerList.getPlayers().length; i++)
				// if there is still no winner and the player at turn isn't broke, a turn is played for the player at turn
				if (!playerList.isThereAWinner() && !playerList.getPlayer(i).isPlayerBroke())
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
		String turnChoice;
		turnLoop:
			do {
				// gets user choice
				turnChoice = boundary.getUserButtonPressed(language.preMsg(player), language.throwDices(), language.build(), language.trade());

				// if the choice was roll dice
				if (turnChoice.equals(language.throwDices())) {

					// dice is rolled and updated in GUI
					diceCup.rollDices();
					boundary.setDices(diceCup);

					// if the roll is equal, equal counter is increased by 1
					if(diceCup.diceEvalEqual()) {
						player.setEqualsCount(player.getEqualsCount() + 1);
						// if the equal counter gets to 3, you get jailed immediately and your turn is over
						if(player.getEqualsCount() == 3) {
							boundary.getButtonPressed(language.youGetJailedForThreeTimesEqual());
							boundary.removeCar(player.getOnField(), player.getName());
							player.setOnField(10);
							player.setEqualsCount(0);
							boundary.setCar(player.getOnField(), player.getName());
							break turnLoop;
						}
					} else {
						player.setEqualsCount(0);
					}

					// normal flow of turn continues here by moving the player
					boundary.removeCar(player.getOnField(), player.getName());
					player.movePlayer(diceCup.getSum());
					int fieldNumber = player.getOnField();
					Field field = gameBoard.getField(fieldNumber);
					boundary.setCar(fieldNumber, player.getName());
					// updating the balance here in case "start" was passed and 4000 was added to the players bank account
					boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());

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
							SequenceController.buyPropertySequence(player, field);
						} else {
							// if the field has an owner and the player landing on the field, is not the owner, the player landing on the field pays rent to the owner
							if (!field.getOwner().getName().equals(player.getName())) {
								boundary.getButtonPressed(language.landedOnOwnedField(ownerOfField));
								int preBalance = player.getBankAccount().getBalance();
								field.landOnField(player, diceCup.getSum(), gameBoard, playerList, false);
								int paidAmount = preBalance - player.getBankAccount().getBalance();
								boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
								boundary.updateBalance(ownerOfField.getName(), ownerOfField.getBankAccount().getBalance());
								boundary.getButtonPressed(language.youPaidThisMuchToThisPerson(paidAmount, ownerOfField));
							} else {
								boundary.getButtonPressed(language.youOwnThisField());
							}
						}
					} else {

						// if the field is the tax field with a choice, the landOnField method is run with that choice
						if(field.getID() == 4) {
							field.landOnField(player, diceCup.getSum(), gameBoard, playerList, boundary.getBoolean(language.getTaxChoice(), language.yes(), language.no()));
							boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
							// otherwise just a standard landOnField call
						} else {
							field.landOnField(player, diceCup.getSum(), gameBoard, playerList, false);
							boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
						}

					}
					//
					if (player.isPlayerBroke()) {
						boundary.getButtonPressed(language.youAreBroke());
						break turnLoop;
					}
					// if the choice was to build
				} else if (turnChoice.equals(language.build())) {
					SequenceController.buildSequence(player, gameBoard);
					// if the choice was to trade a propety
				} else if (turnChoice.equals(language.trade())) {
					SequenceController.tradePropertiesSequence(player, gameBoard, playerList);
				}
			} while (diceCup.diceEvalEqual() || turnChoice.equals(language.build()) || turnChoice.equals(language.trade()));
	}

	/**
	 *
	 */
	public GUIBoundary getBoundary() {
		return this.boundary;
	}
}
