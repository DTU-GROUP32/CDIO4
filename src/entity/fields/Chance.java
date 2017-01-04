package entity.fields;

import java.util.ArrayList;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public class Chance extends Field {

	private static int[] chanceCards = {1,1,1,2,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,19,20,20,21,21,22,23,24,25,26,27};
	
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
			//TODO Tjek op p� pants�tnings sekvens
			//TODO G�lder ogs� for ALLE nedenst�ende withdraw-metoder
			boolean check = true;
			for(int i = 0; i < playerList.getPlayers().length; i++) {
				if(playerList.getPlayer(i).getBankAccount().getBalance() < 200)
					check = false;		
			}
			if(check == false)
				return check;
			else for(int i = 0; i < playerList.getPlayers().length; i++)
				playerList.getPlayer(i).getBankAccount().transfer(player, 200);
			break;
		case 8:
			player.getBankAccount().deposit(1000);
			break;
		case 9:
			player.getBankAccount().deposit(500);
			break;
		case 10:
			if(player.getBankAccount().withdraw(200) == false)
				return false;
			break;
		case 11:
			if(player.getBankAccount().withdraw(1000) == false)
				return false;
			break;
		case 12:
			if(player.getBankAccount().withdraw(3000) == false)
				return false;
			break;
		case 13:
			if(player.getBankAccount().withdraw(1000) == false)
				return false;
			break;
		case 14:
			if(player.getBankAccount().withdraw(3000) == false)
				return false;
			break;
		case 15:
			if(player.getBankAccount().withdraw(2000) == false)
				return false;
			break;
		case 16:
			if(player.getBankAccount().withdraw(200) == false)
				return false;
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
			
			if(player.getBankAccount().withdraw(amountToPay) == false)
				return false;
			
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
			
			if(player.getBankAccount().withdraw(amountToPay1) == false)
				return false;
			
			break;
		case 19:
			player.setGetOutOfJail(player.getGetOutOfJail()+1);
			break;
		case 20:
			player.setOnField(10);
			player.setInJail(true);
			break;
		case 21:
			//TODO N�rmeste redderi (k�be sekvens)
			break;
		case 22:
			//TODO Samme som 21
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
	
}
