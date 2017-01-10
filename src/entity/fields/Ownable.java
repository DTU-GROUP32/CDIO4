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

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
	}

	@Override
	public int getPawnValue() {
		return pawnValue;
	}

	@Override
	public boolean isPawned() {
		return this.pawned;
	}

	@Override
	public void releasePawnField() {
		this.pawned = false;
	}

	@Override
	public void landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		// if the owner isn't in jail and the property isn't pawned
		if(this.owner.isPlayerInJail() == false && this.pawned == false) {
			// if the player can't pay a sequence to get money is executed
			if(player.getBankAccount().transfer(owner, this.getRent(gameBoard, roll)) == false) {
				SequenceController.getMoneySequence(player, this.owner, true, gameBoard, playerList, this.getRent(gameBoard, roll), false);
			}
		}
	}

	@Override
	public boolean buyField(Player player, GameBoard gameBoard, PlayerList playerList) {
		return buyField(player, this.price, gameBoard, playerList);
	}

	@Override
	public boolean buyField(Player player, int price, GameBoard gameBoard, PlayerList playerList) {
		if(player.getBankAccount().withdraw(price)) {
			this.setOwner(player);
			return true;
		} else {
			SequenceController.getMoneySequence(player, null, false, gameBoard, playerList, price, true);
			// request is only executed if the player got enough money
			if(player.getBankAccount().withdraw(price)) {
				this.setOwner(player);
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean tradeField(Player seller, Player buyer, int price, GameBoard gameBoard, PlayerList playerList) {
		if(buyer.getBankAccount().transfer(seller, price)) {
			this.setOwner(buyer);
			return true;
		} else {
			SequenceController.getMoneySequence(buyer, null, false, gameBoard, playerList, price, true);
			// request is only executed if the player got enough money
			if(buyer.getBankAccount().transfer(seller, price)) {
				this.setOwner(buyer);
				return true;
			} else {
				return false;
			}

		}
	}

	@Override
	public boolean pawnField() {
		if (this.getConstructionRate() == 0) {
			this.owner.getBankAccount().deposit(pawnValue);
			this.pawned = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean undoPawnField(GameBoard gameBoard, PlayerList playerList) {
		if (this.pawned) {
			if(this.owner.getBankAccount().withdraw(pawnValue * 110 / 100)) {
				this.pawned = false;
				return true;
			} else {
				// if it was because the player didn't have enough money
				SequenceController.getMoneySequence(owner, null, false, gameBoard, playerList, pawnValue * 110 / 100, true);
				// request is only executed if the player got enough money
				if(this.owner.getBankAccount().withdraw(pawnValue * 110 / 100)) {
					this.pawned = false;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public boolean undoPawnFieldWithoutInterest(GameBoard gameBoard, PlayerList playerList) {
		if (this.pawned) {
			if(this.owner.getBankAccount().withdraw(pawnValue)) {
				this.pawned = false;
				return true;
			} else {
				// if it was because the player didn't have enough money
				SequenceController.getMoneySequence(owner, null, false, gameBoard, playerList, pawnValue, true);
				// request is only executed if the player got enough money
				if(this.owner.getBankAccount().withdraw(pawnValue)) {
					this.pawned = false;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
