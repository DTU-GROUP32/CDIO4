package entity.language;

import entity.Player;

public interface Language {

	String notifyLangChange();

	String fieldNames(int fieldNumber);

	String fieldPrices(int fieldNumber);

	String fieldDescription(int fieldNumber);

	String welcomeMsg();

	String askForNumberOfPlayers();

	String askForPlayerName(int playerNumber);

	String readyToBegin();

	String preMsg(Player player);

	String fieldMsg(int fieldNumber);

	String buyingOfferMsg(int price);

	String yes();

	String no();

	String purchaseConfirmation();

	String notEnoughMoney();

	String landedOnOwnedField(Player owner);

	String youPaidThisMuchToThisPerson(int amountPayed, Player owner);

	String youOwnThisField();

	String getTaxChoice();

	String nonOwnableFieldEffectMsg(int onField);

	String youAreBroke();

	String winnerMsg(Player player);

	String menu();

	String printRules();

	String printScore(Player[] players);

	String changeDices();

	String printDiceChangeSucces();

	String printDiceChangeNotExecuted();

	String throwDices();
	
	String notBuildable();
	
	String getChanceCardMsg(int topCardNumber);

	String buildable();
	
	String notDemolitionable();
	
	String chooseDemolition();
	
	String notTradeable();
	
	String choosePlotTrade();
	
	String chooseBuyerTrade();
	
	String tradePrice();
	
	String wantToTrade();
	
	String noPawnableFields();
	
	String choosePawnField();
	
	String pawnSucces();
	
	String notPawnable();

	String noPawnedProperties();

	String choosePawnedWithdraw();
	
	String pawnedWithdrawSucces();
	
	String pawnedWithdrawUnsuccesful();
	
	String pawn();
	
	String sell();
	
	String trade();
	
	String bankrupt();
	
	String toPay();
	
	String canGetMoney();

}
