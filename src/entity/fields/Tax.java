package entity.fields;

import control.SequenceController;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Tax extends Field {

	private int taxAmount;
	private int taxRate; //in percent

	/**
	 * Constructor. Tax, which is not ownable, will have a name, a tax amount and a tax rate, which is set to 0 with this constructor.
	 * @param name
	 * @param taxAmount
	 */
	public Tax(String name, int taxAmount) {
		this(name, taxAmount, 0);
	}

	/**
	 * Constructor. Tax, which is not ownable, will have a name, a tax amount and a tax rate.
	 * @param name
	 * @param taxAmount
	 * @param taxRate
	 */
	public Tax(String name, int taxAmount, int taxRate) {
		super(name);
		this.taxAmount = taxAmount;
		this.taxRate = taxRate;
	}

	@Override
	public boolean landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		if (taxChoice)
		{
			while (player.getBankAccount().withdraw(player.getTotalAssets(gameBoard) * taxRate / 100) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, player.getTotalAssets(gameBoard) * taxRate / 100);
			return true;
		}
		else
		{
			while (player.getBankAccount().withdraw(taxAmount) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, taxAmount);	
			return true;
		}
	}
	
	@Override
	public int getTaxAmount() {
		return this.taxAmount;
	}

	@Override
	public int getTaxRate() {
		return this.taxRate;
	}
	
	// all methods under this line are default methods

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public Player getOwner() {
		return null;
	}

	@Override
	public void setOwner(Player newOwner) {}

	@Override
	public int getRent(GameBoard gameBoard, int roll) {
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
		return false;
	}

	@Override
	public void releasePawnField() {}

	@Override
	public void setConstructionRate(int rate) {}

	@Override
	public boolean getIsPawned() {
		return false;
	}

	@Override
	public int getTopCardNumber() {
		return 0;
	}

	@Override
	public int getBonus() {
		return 0;
	}

	@Override
	public int[] getRentArray() {
		return null;
	}
}
