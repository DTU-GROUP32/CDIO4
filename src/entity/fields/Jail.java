package entity.fields;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Jail extends Field {

	/**
	 * Constructor for the Jail field, only takes a name.
	 * @param name
	 */
	public Jail(String name) {
		super(name);
	}

	@Override
	public void landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		player.setOnField(10);
		player.setPlayerInJail(true);
	}
}
