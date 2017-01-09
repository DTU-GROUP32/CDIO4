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

	String whatDoYouWantToTrade();

	String tradeProperties();

	String tradeGetOutOfJailCard();

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

	String choosePropertyToTrade();

	String choosePropertyBuyer();

	String enterPropertyTradePrice();

	String confirmPropertyTrade(String fieldName, String buyerName, int price);

	String propertyTradeConfirmation(String buyerName, int price);

	String yes();

	String no();

	String chooseGetOutOfJailCardBuyer();

	String enterGetOutOfJailCardTradePrice();

	String confirmGetOutOfJailCardTrade(String buyerName, int price);

	String getOutOfJailCardPurchaseConfirmation();

	String noPawnableFields();

	String choosePropertyToPawn();

	String pawnSuccessful();

	String pawnUnsuccessful();

	String noPawnedProperties();

	String choosePropertyToUndoPawn();

	String undoPawnSuccessful();

	String undoPawnUnsuccessful();

	String buyingOfferMsg(int price);

	String propertyPurchaseConfirmation();

	String notEnoughMoney();
	
	String auctionNotification();

	String enterAuctionPrice();

	String confirmPropertyPurchase(String fieldName, int price);

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

