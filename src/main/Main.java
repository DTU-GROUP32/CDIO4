package main;

import control.GameController;

/**
 * Main class created as an entry point to the game.
 */
public class Main {

	/**
	 * Entry point for the game
	 * @param args
	 */
	public static void main(String[] args) {
		GameController spil = new GameController();
		spil.runGame();
	}

}
