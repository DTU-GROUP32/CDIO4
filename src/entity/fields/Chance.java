package entity.fields;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Chance extends Field {

	public Chance(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOwner(Player newOwner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRent(GameBoard gameBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean buyField(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tradeField(Player seller, Player buyer, int price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getConstructionRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getConstructionPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPawnValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPropertyGroup() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
