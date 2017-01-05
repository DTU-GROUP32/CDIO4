package entity.language;

import entity.Player;

public interface Language {

	String notifyLangChange();

	String fieldNames(int fieldNumber);

	String fieldPrices(int fieldNumber);

	String fieldDescription(int fieldNumber);
	
	String getChanceCardMsg(int topCardNumber);

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

	String build();

	String trade();



	// Updating Language class regards to changes in SequenceController class



}