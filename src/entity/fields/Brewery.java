package entity.fields;

import entity.GameBoard;
import entity.Player;

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
	public boolean buyField(Player player) {
		if(player.getBankAccount().withdraw(this.price))
		{
			this.setOwner(player);
			numberOfBreweriesOwnedByEachPlayer[player.getID()]++;
			return true;
		}
		return false;
	}

	@Override
	public boolean tradeField(Player seller, Player buyer, int price){
		if(buyer.getBankAccount().transfer(seller, price))
		{
			this.setOwner(buyer);
			numberOfBreweriesOwnedByEachPlayer[seller.getID()]--;
			numberOfBreweriesOwnedByEachPlayer[buyer.getID()]++;
			return true;
		}
		return false;
	}
}
