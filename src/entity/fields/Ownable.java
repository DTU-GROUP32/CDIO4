package entity.fields;

import boundary.GUIBoundary;
import control.SequenceController;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public abstract class Ownable extends Field {

	protected int price;
	protected Player owner;
	protected int pawnValue;
	protected boolean isPawned;

	/**
	 * Default constructor
	 * 
	 * @param price
	 */
	public Ownable(String name, int price) {
		super(name);
		this.price = price;
		this.owner = null;
		this.pawnValue = price / 2;
		this.isPawned = false;
	}

	public int getPrice() {
		return price;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player newOwner) {
		this.owner = newOwner;
	}

	/**
	 * @return the pawnValue
	 */
	public int getPawnValue() {
		return pawnValue;
	}

	public boolean landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		if (this.owner.isInJail() == false)
			while (player.getBankAccount().transfer(owner, this.getRent(gameBoard)) == false)
				SequenceController.getMoneySequence(player, gameBoard, GUIBoundary.getInstance, playerList);
		return true;
	}

	public abstract int getRent(GameBoard gameBoard);

	public boolean buyField(Player player) {
		if (player.getBankAccount().withdraw(this.price)) {
			this.setOwner(player);
			return true;
		}
		return false;
	}

	public boolean tradeField(Player seller, Player buyer, int price) {
		if (buyer.getBankAccount().transfer(seller, price)) {
			this.setOwner(buyer);
			return true;
		}
		return false;
	}

	public boolean pawnField() {
		if (this.getConstructionRate() == 0) {
			this.owner.getBankAccount().deposit(pawnValue);
			this.isPawned = true;
			return true;
		}
		return false;
	}

	public boolean releasePawnedField() {
		if (this.isPawned == true) {
			this.owner.getBankAccount().withdraw(pawnValue * 110 / 100);
			this.isPawned = false;
			return true;
		}
		return false;
	}
}
