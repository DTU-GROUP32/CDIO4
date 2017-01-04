package control;

import boundary.GUIBoundary;
import entity.GameBoard;
import entity.PlayerList;
import entity.language.LanguageHandler;

public class SetupController {

	private GUIBoundary boundary = GUIBoundary.getInstance();
	private GameBoard gameBoard;
	private LanguageHandler language = LanguageHandler.getInstance();

	public SetupController() {
		gameBoard = new GameBoard(language);
	}

	public GameBoard setupGameBoard() {
		boundary.createGameBoard(gameBoard, language);
		return gameBoard;
	}

	/**
	 * Method to change & set entity.language for the gameboard
	 */
	public LanguageHandler chooseLanguage() {
		boundary.getButtonPressed(language.notifyLangChange());
		return language;
	}

	/**
	 * Method to ask for each players name and generate a player object from it.
	 */
	public PlayerList setupPlayers() {
		PlayerList playerList = new PlayerList(boundary.getInteger(language.askForNumberOfPlayers(), 2, 6));
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