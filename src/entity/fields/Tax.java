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
	public void landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		// if player chose to pay the tax rate of his total assets
		if (taxChoice) {
			// if the player can't pay a sequence to get money is executed
			if(player.getBankAccount().withdraw(player.getTotalAssetsForTaxPurposes(gameBoard) * taxRate / 100) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, player.getTotalAssetsForTaxPurposes(gameBoard) * taxRate / 100);
			}
		} 
		// else the player pays the fixed tax amount
		else {
			// if the player can't pay a sequence to get money is executed
			if(player.getBankAccount().withdraw(taxAmount) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, taxAmount);
			}
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
}
