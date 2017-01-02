package entity.fields;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Brewery extends Ownable {

	private int baseRent;
	private static int[] numberOfBreweriesOwnedByEachPlayer = {0,0,0,0,0,0};

	public Brewery(String name) {
		super(name, 3000);
		this.baseRent = 100;
	}

	@Override
	public boolean landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		if(player.getBankAccount().transfer(owner, this.getRent(gameBoard) * roll))
			return true;
		return false;
	}

	@Override
	public int getRent(GameBoard gameBoard) {
		return baseRent * numberOfBreweriesOwnedByEachPlayer[owner.getID()];
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
}
