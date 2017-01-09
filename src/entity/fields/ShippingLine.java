package entity.fields;

import entity.GameBoard;
import entity.Player;

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
	public boolean buyField(Player player) {
		if(player.getBankAccount().withdraw(this.price))
		{
			this.setOwner(player);
			numberOfShippingLinesOwnedByEachPlayer[player.getID()]++;
			return true;
		}
		return false;
	}

	@Override
	public boolean tradeField(Player seller, Player buyer, int price){
		if(buyer.getBankAccount().transfer(seller, price))
		{
			this.setOwner(buyer);
			numberOfShippingLinesOwnedByEachPlayer[seller.getID()]--;
			numberOfShippingLinesOwnedByEachPlayer[buyer.getID()]++;
			return true;
		}
		return false;
	}

}
