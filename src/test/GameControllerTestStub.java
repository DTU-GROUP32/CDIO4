package test;

import entity.GameBoard;
import entity.Player;
import entity.fields.Field;

public class GameControllerTestStub {
	
	/**
	 * Method that implements the exact same logic as our gameController's playTurn, just without all the GUI interaction.
	 * Remember to set the player onField and taxChoice attributes before you inject the player, and remember that the taxChoice will be reset
	 * to false whenever the player is actually taxed.
	 * @param gameBoard - use the default gameBoard constructor to create this object.
	 * @param player - the player who is landing on the field.
	 * @param wantToBuy - position on whether to buy an unowned field or not, true = wants to buy, false = doesn't want to buy.
	 */
	public void playTurnTest(GameBoard gameBoard, Player player, boolean wantToBuy) {
		Field field = gameBoard.getField(player.getOnField());
		if(field.isOwnable())
		{
			if(field.getOwner() == null)
			{
				if(wantToBuy)
				{
						field.buyField(player);
				}
			} else
			{
				field.landOnField(player);
			}
		} else
		{
			field.landOnField(player);
		}
	}
}