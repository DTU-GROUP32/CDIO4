package entity.fields;

import entity.Player;

public abstract class Field {

	protected boolean ownable;

	/**
	 * Returns boolean for a field being ownable or not
	 * @return Boolean
	 */
	public boolean isOwnable() {
		return ownable;
		}

	/**
	 * Returns the price of the field
	 * @return price
	 */
	public abstract int getPrice();

	/**
	 * Returns the owner of the field
	 * @return owner
	 */
	public abstract Player getOwner();

	/**
	 * Sets the owner of the field
	 * @param newOwner
	 */
	public abstract void setOwner(Player newOwner);

	/**
	 * Method that will evaluate what is going to happen to the player that lands on the field
	 * @param player
	 */
	public abstract void landOnField(Player player);

	/**
	 * Returns rent of the field
	 * @return rent
	 */
	public abstract int getRent();

	/**
	 * Makes you able to buy an ownable field. Will set you to the owner if you want to buy it for certain price
	 * @param player
	 */
	public abstract void buyField(Player player);
}
