package entity.fields;

import control.SequenceController;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public abstract class Ownable extends Field {

	protected int price;
	protected Player owner;
	protected int pawnValue;
	protected boolean pawned;

	/**
	 * Constructor for the ownable type fields, because the class is abstract, this constructor is only used as a super constructor.
	 * By default it sets the owner, pawnValue and isPawned.
	 * @param name
	 * @param price
	 */
	public Ownable(String name, int price) {
		super(name);
		this.price = price;
		this.owner = null;
		this.pawnValue = price / 2;
		this.pawned = false;
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

	public int getPawnValue() {
		return pawnValue;
	}

	public boolean isPawned() {
		return this.pawned;
	}

	public void releasePawnField() {
		this.pawned = false;
	}

	public void landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		if (this.owner.isPlayerInJail() == false || this.pawned == false)
			while (player.getBankAccount().transfer(owner, this.getRent(gameBoard, roll)) == false)
				SequenceController.getMoneySequence(player, this.owner, gameBoard, playerList, this.getRent(gameBoard, roll));
	}

	public boolean buyField(Player player) {
		return buyField(player, this.price);
	}

	public boolean buyField(Player player, int price) {
		if (player.getBankAccount().withdraw(price)) {
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
			this.pawned = true;
			return true;
		}
		return false;
	}

	public boolean undoPawnField() {
		if (this.pawned == true) {
			if(this.owner.getBankAccount().withdraw(pawnValue * 110 / 100)) {
				this.pawned = false;
				return true;
			}
		}
		return false;
	}
}
