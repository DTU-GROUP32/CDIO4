package control;

import boundary.GUIBoundary;
import entity.DiceCup;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;
import entity.fields.Field;
import entity.language.LanguageHandler;

public class GameController {

	private GUIBoundary boundary;
	private SetupController setupController;
	private GameBoard gameBoard;
	private LanguageHandler language;
	private PlayerList playerList;
	private DiceCup diceCup;

	public GameController() {
		setupController = new SetupController();
		gameBoard = setupController.setupGameBoard();
		language = setupController.chooseLanguage();
		playerList = setupController.setupPlayers();
		boundary = setupController.getBoundary();
		diceCup = new DiceCup();
	}

	/**
	 * Method to start playing the game
	 */
	public void runGame() {
		boundary.getButtonPressed(language.readyToBegin());
		while(!playerList.isThereAWinner()) {
			for(int i = 0; i < playerList.getPlayers().length; i++)
				if(!playerList.isThereAWinner() && !playerList.isPlayerBroke(i))
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
	 * @param player Player
	 */
	public void playTurn(Player player) {
		boundary.getButtonPressed(language.preMsg(player));
		diceCup.rollDices();
		boundary.setDices(diceCup);
		boundary.removeCar(player.getOnField(), player.getName());
		player.setLastRoll(diceCup.getSum());
		player.movePlayer(diceCup.getSum());
		int fieldNumber = player.getOnField();
		Field field = gameBoard.getField(fieldNumber);
		boundary.setCar(fieldNumber, player.getName());
		boundary.getButtonPressed(language.fieldMsg(fieldNumber));
		if(field.isOwnable())
		{
			Player ownerOfField = field.getOwner();
			if(ownerOfField == null)
			{
				int priceOfField = field.getPrice();
				if(boundary.getBoolean(language.buyingOfferMsg(priceOfField), language.yes(), language.no()))
				{
					if(player.getBankAccount().getBalance() > priceOfField)
					{
						field.buyField(player);
						boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
						boundary.setOwner(fieldNumber, player.getName());
						boundary.getButtonPressed(language.purchaseConfirmation());
					} else
					{
						boundary.getButtonPressed(language.notEnoughMoney());
					}
				}
			} else
			{
				if(!field.getOwner().getName().equals(player.getName()))
				{
				boundary.getButtonPressed(language.landedOnOwnedField(ownerOfField));
				int preBalance = player.getBankAccount().getBalance();
				field.landOnField(player);
				boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
				boundary.updateBalance(ownerOfField.getName(), ownerOfField.getBankAccount().getBalance());
				int amountPayed = preBalance - player.getBankAccount().getBalance();
				boundary.getButtonPressed(language.youPaidThisMuchToThisPerson(amountPayed, ownerOfField));
				} else
				{
				boundary.getButtonPressed(language.youOwnThisField());
				}
			}
		} else
		{
			if(fieldNumber == 18)
				player.setTaxChoice(boundary.getBoolean(language.getTaxChoice(), language.yes(), language.no()));
			else boundary.getButtonPressed(language.nonOwnableFieldEffectMsg(fieldNumber));
			field.landOnField(player);
			boundary.updateBalance(player.getName(), player.getBankAccount().getBalance());
		}
		if (player.getBankAccount().getBalance() <= 0)
		{
			boundary.getButtonPressed(language.youAreBroke());
			boundary.removeCar(fieldNumber, player.getName());
			boundary.releasePlayersFields(gameBoard, player);
			gameBoard.releasePlayersFields(player);
		}
	}

	/**
	 * Gamemenu shown before the start of each turn. Lets player end game, continue or switch entity.language
	 * @return
	 */
	//	public void gameMenu() {
	//		String choice = this.getInput(entity.language.menu());
	//		switch (choice) {
	//		// Change dice sides
	//		case "1":
	//			String subchoice = this.getInput(entity.language.changeDices());
	//			if(subchoice.length() > 2) {
	//				if(diceCup.setDiceSides(Character.getNumericValue(subchoice.charAt(0)), Character.getNumericValue(subchoice.charAt(2))))
	//					System.out.println(entity.language.printDiceChangeSucces());
	//				break;
	//			}
	//			else System.out.println(entity.language.printDiceChangeNotExecuted());
	//			break;
	//			// Change Language
	//		case "2":
	//			this.chooseLanguage();
	//			break;
	//			// Show Score
	//		case "3":
	//			System.out.println(entity.language.printScore(this.players));
	//			break;
	//			// End Game
	//		case "4":
	//			System.exit(1);
	//			// Continue game
	//		case "5":
	//			break;
	//			// Default
	//		default: break;
	//		}
	//	}
}
