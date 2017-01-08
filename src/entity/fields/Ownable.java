package entity.fields;

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

	public int getPawnValue() {
		return pawnValue;
	}
	
	public boolean getIsPawned() {
		return this.isPawned;
	}

	public void releasePawnField() {
		this.isPawned = false;
	}
	
	public boolean landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		if (this.owner.isPlayerInJail() == false || this.isPawned == false)
			while (player.getBankAccount().transfer(owner, this.getRent(gameBoard, roll)) == false)
				SequenceController.getMoneySequence(player, this.owner, gameBoard, playerList, this.getRent(gameBoard, roll));
		return true;
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
			this.isPawned = true;
			return true;
		}
		return false;
	}

	public boolean undoPawnField() {
		if (this.isPawned == true) {
			this.owner.getBankAccount().withdraw(pawnValue * 110 / 100);
			this.isPawned = false;
			return true;
		}
		return false;
	}
}
