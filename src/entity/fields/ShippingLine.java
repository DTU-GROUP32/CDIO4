package entity.fields;

import control.SequenceController;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class ShippingLine extends Ownable {

	private final int[] rent = {500,1000,2000,4000};
	private static int[] numberOfShippingLinesOwnedByEachPlayer = {0,0,0,0,0,0};

	/**
	 * Constructor for the shipping line field. Only takes a name.
	 * By default it sets the price of the field to 4000, because all shipping lines has the same price in the game.
	 * @param name
	 */
	public ShippingLine(String name) {
		super(name, 4000);
	}

	@Override
	public int getRent(GameBoard gameBoard, int roll) {
		return rent[numberOfShippingLinesOwnedByEachPlayer[owner.getID()]-1];
	}

	@Override
	public boolean buyField(Player player, GameBoard gameBoard, PlayerList playerList) {
		return buyField(player, this.price, gameBoard, playerList);
	}

	@Override
	public boolean buyField(Player player, int price, GameBoard gameBoard, PlayerList playerList) {
		if(player.getBankAccount().withdraw(price)) {
			this.setOwner(player);
			numberOfShippingLinesOwnedByEachPlayer[player.getID()]++;
			return true;
		} else {
			SequenceController.getMoneySequence(player, null, false, gameBoard, playerList, price, true);
			// request is only executed if the player got enough money
			if(player.getBankAccount().withdraw(price)) {
				this.setOwner(player);
				numberOfShippingLinesOwnedByEachPlayer[player.getID()]++;
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
			numberOfShippingLinesOwnedByEachPlayer[seller.getID()]--;
			numberOfShippingLinesOwnedByEachPlayer[buyer.getID()]++;
			return true;
		} else {
			SequenceController.getMoneySequence(buyer, null, false, gameBoard, playerList, price, true);
			// request is only executed if the player got enough money
			if(buyer.getBankAccount().transfer(seller, price)) {
				this.setOwner(buyer);
				numberOfShippingLinesOwnedByEachPlayer[seller.getID()]--;
				numberOfShippingLinesOwnedByEachPlayer[buyer.getID()]++;
				return true;
			} else {
				return false;
			}

		}
	}
}
