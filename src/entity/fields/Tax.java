package entity.fields;

import control.SequenceController;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Tax extends Field {

	private int taxAmount;
	private int taxRate; //in percent

	/**
	 * Default constructor. Tax, which is not ownable, will have a tax amount and a tax rate, which is automatically set to 0.
	 * @param taxAmount
	 */
	public Tax(String name, int taxAmount) {
		this(name, taxAmount, 0);
	}

	/**
	 * Constructor that has a tax amount and a tax rate
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
	public int getPrice() {
		return 0;
	}

	@Override
	public Player getOwner() {
		return null;
	}

	@Override
	public void setOwner(Player newOwner) {
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
		return false;
	}

	@Override
	public void releasePawnField() {
		
	}

	@Override
	public void setConstructionRate(int rate) {
	}

	@Override
	public boolean getIsPawned() {
		return false;
	}

	@Override
	public int[] getRent() {
		return null;
	}

	@Override
	public int getTopCardNumber() {
		return 0;
	}
}
