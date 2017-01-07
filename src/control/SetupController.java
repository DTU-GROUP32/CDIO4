package control;

import boundary.GUIBoundary;
import entity.GameBoard;
import entity.PlayerList;
import entity.language.LanguageHandler;

public class SetupController {

	private GUIBoundary boundary;
	private GameBoard gameBoard;
	private LanguageHandler language;

	/**
	 * Constructor that creates the logic of the gameBoard from our games default language.
	 */
	public SetupController() {
		boundary = GUIBoundary.getInstance();
		language = LanguageHandler.getInstance();
		gameBoard = new GameBoard(language);
	}

	/**
	 * Sets up the GUI game board and returns the gameBoard instance.
	 * @return gameBoard
	 */
	public GameBoard setupGameBoard() {
		boundary.createGameBoard(gameBoard);
		return gameBoard;
	}

	/**
	 * Method to ask for each players name and generate a player object from it.
	 * @return playerList
	 */
	public PlayerList setupPlayers() {
		PlayerList playerList = new PlayerList(Integer.parseInt(boundary.getUserButtonPressed(language.askForNumberOfPlayers(), "2", "3", "4", "5", "6")));
		for(int i = 0; i < playerList.getPlayers().length; i++)
		{
			String name;
			do {
				name = boundary.getString(language.askForPlayerName(i+1));
			} while (playerList.isNameTaken(name));
			playerList.addPlayer(i, name);
			boundary.addPlayer(playerList.getPlayer(i));
		}
		return playerList;
	}
}