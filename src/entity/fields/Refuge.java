package entity.fields;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Refuge extends Field {

	private int bonus;

	/**
	 * Constructor. Refuge, which is not ownable, that has a bonus
	 * @param bonus
	 */
	public Refuge(String name, int bonus) {
		super(name);
		this.bonus = bonus;
	}

	/**
	 * Returns the bonus
	 * @return
	 */
	public int getBonus() {
		return bonus;
	}

	@Override
	public boolean landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		player.getBankAccount().deposit(bonus);
		return false;
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public Player getOwner() {
		return null;
	}

	@Override
	public void setOwner(Player newOwner) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRent(GameBoard gameBoard) {
		return 0;
	}

	@Override
	public boolean buyField(Player player) {
		return false;
	}

	@Override
	public boolean tradeField(Player seller, Player buyer, int price) {
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
	public int getPawnValue() {
		return 0;
	}

	@Override
	public int getPropertyGroup() {
		return 0;
	}

	@Override
	public boolean pawnField() {
		return false;
	}

	@Override
	public boolean undoPawnField() {
		return false;
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
	public boolean buyField(Player player, int price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void releasePawnField() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConstructionRate(int rate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsPawned() {
		// TODO Auto-generated method stub
		return false;
	}
}
