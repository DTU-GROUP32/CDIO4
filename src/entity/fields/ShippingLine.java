package entity.fields;

import entity.GameBoard;
import entity.Player;

public class ShippingLine extends Ownable {

	private final int[] rent = {500,1000,2000,4000};
	private static int[] numberOfShippingLinesOwnedByEachPlayer = {0,0,0,0,0,0};

	/**
	 * Default constructor. Shipping line has a price of 4000.
	 */
	public ShippingLine(String name) {
		super(name, 4000);
	}

	@Override
	public int getRent(GameBoard gameBoard) {
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

	@Override
	public int getConstructionRate() {
		return 0;
	}

	@Override
	public int getConstructionPrice() {
		return 0;
	}

	@Override
	public int getPropertyGroup() {
		return 0;
	}

	@Override
	public boolean buildConstruction() {
		return false;
	}

	@Override
	public boolean sellConstruction() {
		return false;
	}

	@Override
	public void setConstructionRate(int rate) {
		// TODO Auto-generated method stub
		
	}
}
