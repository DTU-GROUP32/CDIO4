package entity.fields;

import entity.Player;

public class LaborCamp extends Ownable {
	
	private int baseRent;
	private static int[] numberOfLaborCampsOwnedByEachPlayer = {0,0,0,0,0,0};
	
	public LaborCamp() {
		super(2500);
		this.baseRent = 100;
	}

	@Override
	public void landOnField(Player player) {
		if(owner != null)
			owner.getBankAccount().deposit(player.getBankAccount().withdraw(this.getRent() * player.getLastRoll()));
	}
	
	@Override
	public int getRent() {
		return baseRent * numberOfLaborCampsOwnedByEachPlayer[owner.getID()];
	}
	
	@Override
	public void buyField(Player player) {
		super.buyField(player);
		numberOfLaborCampsOwnedByEachPlayer[player.getID()]++;
	}
}
