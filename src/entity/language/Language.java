package entity.language;

import entity.Player;

public interface Language {

	String notifyLangChange();

	String askForNumberOfPlayers();

	String askForPlayerName(int playerNumber);

	String fieldDescription(int fieldNumber);

	String fieldNames(int fieldNumber);

	String fieldPrices(int fieldPrice);

	String readyToBegin();

	String winnerMsg(Player player);

	String youAreInJailMsg(Player player);

	String throwDices();

	String payOneThousand();

	String useGetOutOfJail();

	String preMsg(Player player);

	String build();

	String trade();

	String undoPawn();

	String noMoreAttemptsAtRollingOutOfJail();

	String attemptAtRollingOutOfJailUnsuccessful();

	String youGetJailedForThreeTimesEqual();

	String getChanceCardMsg(int topCardNumber);

	String fieldMsg(int fieldNumber);

	String landedOnOwnedField(Player owner);

	String youPaidThisMuchToThisPerson(int amountPayed, Player owner);

	String youOwnThisField();

	String getTaxChoice();

	String notBuildable();

	String choosePlotToBuildOn();

	String noDemolitionableProperties();

	String choosePropertyToDemolishOn();

	String noTradeableProperties();

	String choosePlotTrade();

	String choosePropertyBuyer();

	String enterTradePrice();

	String confirmTrade(String fieldName, String buyerName, int price);

	String yes();

	String no();

	String noPawnableFields();

	String choosePropertyToPawn();

	String pawnSuccessful();

	String pawnUnsuccessful();

	String noPawnedProperties();

	String choosePropertyToUndoPawn();

	String undoPawnSuccessful();

	String undoPawnUnsuccessful();

	String buyingOfferMsg(int price);

	String purchaseConfirmation();

	String notEnoughMoney();
	
	String auctionNotification();

	String enterAuctionPrice();

	String confirmPurchase(String fieldName, int price);

	String wantToRunAuctionSequenceAgain();

	String pawn();

	String demolish();

	String bankrupt();

	String toPay(int targetAmount);

	String canGetMoney();

	String bankruptcyConcluded();

	String nonOwnableFieldEffectMsg(int onField);

	String menu();

	String printRules();

	String printScore(Player[] players);

	String changeDices();

	String printDiceChangeSucces();

	String printDiceChangeNotExecuted();
}

