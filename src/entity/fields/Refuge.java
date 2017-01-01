package entity.fields;

import entity.Player;

public class Refuge extends Field {

	private int bonus;

	/**
	 * Constructor. Refuge, which is not ownable, that has a bonus
	 * @param bonus
	 */
	public Refuge(int bonus) {
		this.bonus = bonus;
		this.ownable = false;
	}

	/**
	 * Returns the bonus
	 * @return
	 */
	public int getBonus() {
		return bonus;
	}

	@Override
	public void landOnField(Player player) {
		player.getBankAccount().deposit(bonus);
	}

	@Override
	public int getPrice() {
		return -1;
	}

	@Override
	public Player getOwner() {
		return null;
	}

	@Override
	public void setOwner(Player newOwner) {}

	@Override
	public int getRent() {
		return -1;
	}

	@Override
	public void buyField(Player player) {}
}
