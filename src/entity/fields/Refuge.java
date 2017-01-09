package entity.fields;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Refuge extends Field {

	private int bonus;

	/**
	 * Constructor. Refuge, which is not ownable, that has a name and a bonus
	 * @param name
	 * @param bonus
	 */
	public Refuge(String name, int bonus) {
		super(name);
		this.bonus = bonus;
	}

	@Override
	public void landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		player.getBankAccount().deposit(bonus);
	}
	
	@Override
	public int getBonus() {
		return bonus;
	}
}
