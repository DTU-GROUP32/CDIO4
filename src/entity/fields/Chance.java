package entity.fields;

import java.util.ArrayList;

import control.SequenceController;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Chance extends Field {

	private int[] chanceCards = {1,1,1,2,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,19,20,20,21,21,22,23,24,25,26,27};

	/**
	 * Constructor for the Chance field, only takes a name.
	 * The constructor also shuffles the "chance cards" represented in the array.
	 * @param name
	 */
	public Chance(String name) {
		super(name);

		for(int i = 0; i < 1000; i++)
		{
			int rnd1 = (int) (Math.random() * chanceCards.length);
			int rnd2 = (int) (Math.random() * chanceCards.length);
			int temp = chanceCards[rnd1];
			chanceCards[rnd1] = chanceCards[rnd2];
			chanceCards[rnd2] = temp;
		}
	}

	@Override
	public void landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {

		switch(chanceCards[0]) {
		case 1:
		case 2:
		case 3:
			player.getBankAccount().deposit(1000);
			break;
		case 4:
			player.getBankAccount().deposit(200);
			break;
		case 5:
			if(player.getTotalAssetsForTaxPurposes(gameBoard) <= 15000) {
				player.getBankAccount().deposit(40000);
			}
			break;
		case 6:
			player.getBankAccount().deposit(3000);
			break;
		case 7:
			// for each player in the player list
			for(int i = 0; i < playerList.getPlayers().length; i++) {
				// if the player has less than 200
				if(playerList.getPlayer(i).getBankAccount().getBalance() < 200) {
					// and if the player isn't the receiver, he will be asked to get more money
					if(playerList.getPlayer(i) != player) {
						SequenceController.getMoneySequence(playerList.getPlayer(i), player, false, gameBoard, playerList, 200, false);
					}
				}
			}
			// each player transfers 200 to the receiver
			for(int i = 0; i < playerList.getPlayers().length; i++) {
				// except if the player is the receiver
				if(playerList.getPlayer(i) != player) {
					playerList.getPlayer(i).getBankAccount().transfer(player, 200);
				}
			}
			break;
		case 8:
			player.getBankAccount().deposit(1000);
			break;
		case 9:
			player.getBankAccount().deposit(500);
			break;
		case 10:
			// tries to withdraw 200 from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(200) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, 200, false);
			}
			break;
		case 11:
			// tries to withdraw 1000 from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(1000) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, 1000, false);
			}
			break;
		case 12:
			// tries to withdraw 3000 from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(3000) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, 3000, false);
			}
			break;
		case 13:
			// tries to withdraw 1000 from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(1000) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, 1000, false);
			}
			break;
		case 14:
			// tries to withdraw 3000 from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(3000) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, 3000, false);
			}
			break;
		case 15:
			// tries to withdraw 2000 from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(2000) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, 2000, false);
			}
			break;
		case 16:
			// tries to withdraw 200 from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(200) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, 200, false);
			}
			break;
		case 17:

			ArrayList<Field> list = gameBoard.getDemolitionableList(player);
			int amountToPay = 0;

			// if the player has any buildings
			if(list.size() > 0)
				// for each field with constructions
				for(int i = 0; i < list.size(); i++)
				{
					int constructionRate = list.get(i).getConstructionRate();
					// 800 will be added for each house
					if(constructionRate < 5)
						amountToPay += constructionRate * 800;
					// 2300 will be added for a hotel
					if(constructionRate == 5)
						amountToPay += 2300;
				}

			// tries to withdraw the amount to pay from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(amountToPay) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, amountToPay, false);
			}
			break;
		case 18:

			ArrayList<Field> list1 = gameBoard.getDemolitionableList(player);
			int amountToPay1 = 0;

			// if the player has any buildings
			if(list1.size() > 0)
				// for each field with constructions
				for(int i = 0; i < list1.size(); i++)
				{
					int constructionRate = list1.get(i).getConstructionRate();
					// 500 will be added for each house
					if(constructionRate < 5)
						amountToPay1 += constructionRate * 500;
					// 2000 will be added for a hotel
					if(constructionRate == 5)
						amountToPay1 += 2000;
				}

			// tries to withdraw the amount to pay from the player, if it returns false, the player will be asked to get more money
			if(player.getBankAccount().withdraw(amountToPay1) == false) {
				SequenceController.getMoneySequence(player, null, true, gameBoard, playerList, amountToPay1, false);
			}
			break;
		case 19:
			// player gets 1 added to his get out of jail count
			player.setGetOutOfJailCardCount(player.getGetOutOfJailCardCount()+1);
			break;
		case 20:
			// puts the player jail, because the setOnField-method is used instead of the movePlayer-method, the start bonus can't get triggered
			player.setOnField(10);
			player.setPlayerInJail(true);
			break;
		case 21:
			// moves the player to the nearest shipping line
			if(player.getOnField() > 4 && player.getOnField() < 15)
				player.movePlayer(15-player.getOnField());
			else if(player.getOnField() > 14 && player.getOnField() < 25)
				player.movePlayer(25-player.getOnField());
			else if(player.getOnField() > 24 && player.getOnField() < 35)
				player.movePlayer(35-player.getOnField());
			else if(player.getOnField() > 34)
				player.movePlayer(45-player.getOnField());
			else player.movePlayer(5-player.getOnField());
			// if the field is owned the player pays double rent
			if(gameBoard.getField(player.getOnField()).getOwner() != null) {
				gameBoard.getField(player.getOnField()).landOnField(player, 0, gameBoard, playerList, false);
				gameBoard.getField(player.getOnField()).landOnField(player, 0, gameBoard, playerList, false);
			}
			// else the player can buy the shipping line
			else {
				SequenceController.buyPropertySequence(player, gameBoard.getField(player.getOnField()), gameBoard, playerList);
			}
			break;
		case 22:
			// moves the player to the nearest shipping line
			if(player.getOnField() > 4 && player.getOnField() < 15)
				player.movePlayer(15-player.getOnField());
			else if(player.getOnField() > 14 && player.getOnField() < 25)
				player.movePlayer(25-player.getOnField());
			else if(player.getOnField() > 24 && player.getOnField() < 35)
				player.movePlayer(35-player.getOnField());
			else if(player.getOnField() > 34)
				player.movePlayer(45-player.getOnField());
			else player.movePlayer(5-player.getOnField());
			// if the field is owned the player pays rent
			if(gameBoard.getField(player.getOnField()).getOwner() != null) {
				gameBoard.getField(player.getOnField()).landOnField(player, 0, gameBoard, playerList, false);
			}
			// else the player can buy the shipping line
			else {
				SequenceController.buyPropertySequence(player, gameBoard.getField(player.getOnField()), gameBoard, playerList);
			}
			break;
		case 23:
			// moves the player to the field 24(logic)
			if(player.getOnField() >= 24)
				player.movePlayer(64-player.getOnField());
			else player.movePlayer(24-player.getOnField());
			// executes land on field sequence for the field he moved to
			SequenceController.landOnFieldSequence(player, roll, gameBoard, playerList);
			break;
		case 24:
			// moves the player to the field 0(logic)
			player.movePlayer(40-player.getOnField());
			// executes land on field sequence for the field he moved to
			SequenceController.landOnFieldSequence(player, roll, gameBoard, playerList);
			break;
		case 25:
			// moves the player to the field 11(logic)
			if(player.getOnField() >= 11)
				player.movePlayer(51-player.getOnField());
			else player.movePlayer(11-player.getOnField());
			// executes land on field sequence for the field he moved to
			SequenceController.landOnFieldSequence(player, roll, gameBoard, playerList);
			break;
		case 26:
			// moves the player to the field 39(logic)
			if(player.getOnField() >= 39)
				player.movePlayer(79-player.getOnField());
			else player.movePlayer(39-player.getOnField());
			// executes land on field sequence for the field he moved to
			SequenceController.landOnFieldSequence(player, roll, gameBoard, playerList);
			break;
		case 27:
			// moves the player three fields back
			player.setOnField(player.getOnField()-3);
			if(player.getOnField() < 0){
				player.setOnField(40+player.getOnField());
			}
			// executes land on field sequence for the field he moved to
			SequenceController.landOnFieldSequence(player, roll, gameBoard, playerList);
			break;
		default:
			break;
		}

		// moves the card that was just used to the bottom of the pile(left shift)
		int temp = chanceCards[0];
		for(int i = 0; i < chanceCards.length-1; i++) {
			chanceCards[i] = chanceCards[i+1];
		}
		chanceCards[chanceCards.length-1] = temp;
	}

	/**
	 * Returns the number of the card that is in the top of the card stack.
	 * @return topCardNumber
	 */
	@Override
	public int getTopCardNumber() {
		return chanceCards[0];
	}

	@Override
	public void setTopCardNumber(int number){
		this.chanceCards[0] = number;
	}
}