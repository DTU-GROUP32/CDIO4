package entity.fields;

import java.util.ArrayList;

import control.SequenceController;
import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Chance extends Field {

	private static int[] chanceCards = {1,1,1,2,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,19,20,20,21,21,22,23,24,25,26,27};
	
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
	public boolean landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice) {
		
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
			if(player.getTotalAssets(gameBoard) <= 15000)
				player.getBankAccount().deposit(40000);
			break;
		case 6:
			player.getBankAccount().deposit(3000);
			break;
		case 7:
			for(int i = 0; i < playerList.getPlayers().length; i++)
				while(playerList.getPlayer(i).getBankAccount().getBalance() < 200)
					if(playerList.getPlayer(i) == player) {} 
					else {
						SequenceController.getMoneySequence(playerList.getPlayer(i), player, gameBoard, playerList, 200);
					}
			for(int i = 0; i < playerList.getPlayers().length; i++)
				playerList.getPlayer(i).getBankAccount().transfer(player, 200);
			break;
		case 8:
			player.getBankAccount().deposit(1000);
			break;
		case 9:
			player.getBankAccount().deposit(500);
			break;
		case 10:
			while(player.getBankAccount().withdraw(200) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, 200);
			break;
		case 11:
			while(player.getBankAccount().withdraw(1000) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, 1000);
			break;
		case 12:
			while(player.getBankAccount().withdraw(3000) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, 3000);
			break;
		case 13:
			while(player.getBankAccount().withdraw(1000) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, 1000);
			break;
		case 14:
			while(player.getBankAccount().withdraw(3000) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, 3000);
			break;
		case 15:
			while(player.getBankAccount().withdraw(2000) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, 2000);
			break;
		case 16:
			while(player.getBankAccount().withdraw(200) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, 200);
			break;
		case 17:
			ArrayList<Field> list = gameBoard.getDemolitionableList(player);
			int amountToPay = 0;
			
			if(list.size() > 0)
				for(int i = 0; i < list.size(); i++)
				{
					int constructionRate = list.get(i).getConstructionRate();
					if(constructionRate > 0 && constructionRate < 5)
						amountToPay += constructionRate * 800;
					if(constructionRate == 5)
						amountToPay += 2300;
				}
			
			while(player.getBankAccount().withdraw(amountToPay) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, amountToPay);
			
			break;
		case 18:
			ArrayList<Field> list1 = gameBoard.getDemolitionableList(player);
			int amountToPay1 = 0;
			
			if(list1.size() > 0)
				for(int i = 0; i < list1.size(); i++)
				{
					int constructionRate = list1.get(i).getConstructionRate();
					if(constructionRate > 0 && constructionRate < 5)
						amountToPay1 += constructionRate * 500;
					if(constructionRate == 5)
						amountToPay1 += 2000;
				}
			
			while(player.getBankAccount().withdraw(amountToPay1) == false)
				SequenceController.getMoneySequence(player, null, gameBoard, playerList, amountToPay1);
			
			break;
		case 19:
			player.setGetOutOfJail(player.getGetOutOfJail()+1);
			break;
		case 20:
			player.setOnField(10);
			player.setInJail(true);
			break;
		case 21:
			if(player.getOnField() > 4 && player.getOnField() < 15)
				player.movePlayer(15-player.getOnField());
			if(player.getOnField() > 14 && player.getOnField() < 25)
				player.movePlayer(25-player.getOnField());
			if(player.getOnField() > 24 && player.getOnField() < 35)
				player.movePlayer(35-player.getOnField());
			if(player.getOnField() > 34)
				player.movePlayer(45-player.getOnField());
			else player.movePlayer(5-player.getOnField());
			if(gameBoard.getField(player.getOnField()).getOwner() == null)
				SequenceController.buyPropertySequence(player, gameBoard.getField(player.getOnField()));
			else
				{
				gameBoard.getField(player.getOnField()).landOnField(player, 0, gameBoard, playerList, false);
				gameBoard.getField(player.getOnField()).landOnField(player, 0, gameBoard, playerList, false);
				}
			break;
		case 22:
			if(player.getOnField() > 4 && player.getOnField() < 15)
				player.movePlayer(15-player.getOnField());
			if(player.getOnField() > 14 && player.getOnField() < 25)
				player.movePlayer(25-player.getOnField());
			if(player.getOnField() > 24 && player.getOnField() < 35)
				player.movePlayer(35-player.getOnField());
			if(player.getOnField() > 34)
				player.movePlayer(45-player.getOnField());
			else player.movePlayer(5-player.getOnField());
			gameBoard.getField(player.getOnField()).landOnField(player, 0, gameBoard, playerList, false);
			break;
		case 23:
			if(player.getOnField() > 24)
				player.movePlayer(64-player.getOnField());
			else player.movePlayer(24-player.getOnField());
			break;
		case 24:
			player.movePlayer(40-player.getOnField());
			break;
		case 25:
			if(player.getOnField() > 11)
				player.movePlayer(51-player.getOnField());
			else player.movePlayer(11-player.getOnField());
			break;
		case 26:
			if(player.getOnField() > 39)
				player.movePlayer(79-player.getOnField());
			else player.movePlayer(39-player.getOnField());
			break;
		case 27:
			player.setOnField(player.getOnField()-3);
			if(player.getOnField() < 0){
				player.setOnField(40+player.getOnField());
			}
			gameBoard.getField(player.getOnField()).landOnField(player, roll, gameBoard, playerList, taxChoice);
			break;
		default:
			return false;
		}
		
		int temp = chanceCards[0];
	
		for(int i = 0; i < chanceCards.length-1; i++)
		{
			chanceCards[i] = chanceCards[i+1];
		}
		
		chanceCards[chanceCards.length-1] = temp;
	
		return true;
	}
	
	public int getTopCardNumber() {
		return chanceCards[0];
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
	public int getBonus() {
		return 0;
	}

	@Override
	public int getTaxAmount() {
		return 0;
	}

	@Override
	public int getTaxRate() {
		return 0;
	}

	@Override
	public int[] getRentArray() {
		return null;
	}
	
}
