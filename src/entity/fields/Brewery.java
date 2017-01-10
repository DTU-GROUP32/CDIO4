package entity.fields;

import control.SequenceController;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Brewery extends Ownable {

	private int baseRent;
	private static int[] numberOfBreweriesOwnedByEachPlayer = {0,0,0,0,0,0};

	/**
	 * Constructor for the brewery field. Only takes a name.
	 * By default it sets the base rent to 100.
	 * @param name
	 */
	public Brewery(String name) {
		super(name, 3000);
		this.baseRent = 100;
	}

	@Override
	public int getRent(GameBoard gameBoard, int roll) {
		return baseRent * roll * numberOfBreweriesOwnedByEachPlayer[owner.getID()];
	}

	@Override
	public boolean buyField(Player player, GameBoard gameBoard, PlayerList playerList) {
		return buyField(player, this.price, gameBoard, playerList);
	}

	@Override
	public boolean buyField(Player player, int price, GameBoard gameBoard, PlayerList playerList) {
		if(player.getBankAccount().withdraw(price)) {
			this.setOwner(player);
			numberOfBreweriesOwnedByEachPlayer[player.getID()]++;
			return true;
		} else {
			SequenceController.getMoneySequence(player, null, false, gameBoard, playerList, price, true);
			// request is only executed if the player got enough money
			if(player.getBankAccount().withdraw(price)) {
				this.setOwner(player);
				numberOfBreweriesOwnedByEachPlayer[player.getID()]++;
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
			numberOfBreweriesOwnedByEachPlayer[seller.getID()]--;
			numberOfBreweriesOwnedByEachPlayer[buyer.getID()]++;
			return true;
		} else {
			SequenceController.getMoneySequence(buyer, null, false, gameBoard, playerList, price, true);
			// request is only executed if the player got enough money
			if(buyer.getBankAccount().transfer(seller, price)) {
				this.setOwner(buyer);
				numberOfBreweriesOwnedByEachPlayer[seller.getID()]--;
				numberOfBreweriesOwnedByEachPlayer[buyer.getID()]++;
				return true;
			} else {
				return false;
			}

		}
	}
}
