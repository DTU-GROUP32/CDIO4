package entity.language;

import entity.Player;

public class LanguageHandler {

	private Language selectedLanguage;
	private static LanguageHandler instance;

	/**
	 * Default constructor that takes a parameter to initialize a entity.language
	 * @param language
	 */
	private LanguageHandler(String language) {
		setLanguage(language);
	}
	
	public static LanguageHandler getInstance() {
		if(instance == null)
			 instance = new LanguageHandler("Dansk");
		return instance;
	}

	/**
	 * Changes entity.language and sets it to either Danish or English
	 * @param language
	 */
	public void setLanguage(String language) {
		switch(language) {
		case "Dansk": selectedLanguage = new Dansk(); break;
		case "English": selectedLanguage = new English(); break;
		default: selectedLanguage = new English();
		}
	}

	/**
	 * Notifies of entity.language change
	 * @return String
	 */
	public String notifyLangChange(){
		return selectedLanguage.notifyLangChange();
	}

	/**
	 * Returns name of the field, using the number of the field to determine the exact field
	 * @param fieldNumber
	 * @return String
	 */
	public String fieldNames(int fieldNumber) {
		return selectedLanguage.fieldNames(fieldNumber);
	}

	/**
	 * Returns the price of the field, using the number of the field to determine the exact field
	 * @param fieldNumber
	 * @return String
	 */
	public String fieldPrices(int fieldNumber) {
		return selectedLanguage.fieldPrices(fieldNumber);
	}

	/**
	 * Returns a string that describes the field, depending on the number of the field
	 * @param fieldNumber
	 * @return String
	 */
	public String fieldDescription(int fieldNumber) {
		return selectedLanguage.fieldDescription(fieldNumber);
	}
	
	public String getChanceCardMsg(int topCardNumber) {
		return selectedLanguage.getChanceCardMsg(topCardNumber);
	}
	
	/**
	 * Asks for number of players to add into the game
	 * @return String
	 */
	public String askForNumberOfPlayers() {
		return selectedLanguage.askForNumberOfPlayers();
	}

	/** 
	 * Asks for player name.
	 * @return String
	 */
	public String askForPlayerName(int playerNumber){
		return selectedLanguage.askForPlayerName(playerNumber);
	}

	/** 
	 * Tells user that the game will start shortly. 
	 * @return String
	 */
	public String readyToBegin(){
		return selectedLanguage.readyToBegin();
	}

	/**
	 * Premessage at the start of players turn 
	 * @return String
	 */
	public String preMsg(Player player){
		return selectedLanguage.preMsg(player);
	}

	/**
	 * Switch case that displays the field message that was landed on.
	 * @return String
	 */
	public String fieldMsg(int fieldNumber){
		return selectedLanguage.fieldMsg(fieldNumber);
	}

	/**
	 * Returns a message that asks wether or not the player wants to buy the field
	 * @param price
	 * @return String
	 */
	public String buyingOfferMsg(int price){
		return selectedLanguage.buyingOfferMsg(price);
	}

	/**
	 * Returns yes
	 * @return String
	 */
	public String yes() {
		return selectedLanguage.yes();
	}

	/**
	 * Returns no
	 * @return String
	 */
	public String no() {
		return selectedLanguage.no();
	}

	/**
	 * Returns a message that confirms your purchase
	 * @return
	 */
	public String purchaseConfirmation() {
		return selectedLanguage.purchaseConfirmation();
	}

	
	/**
	 * Returns a message that tells you that you don't have enough money
	 * @return
	 */
	public String notEnoughMoney() {
		return selectedLanguage.notEnoughMoney();
	}

	/**
	 * Returns a message that
	 * @param owner
	 * @return String
	 */
	public String landedOnOwnedField(Player owner) {
		return selectedLanguage.landedOnOwnedField(owner);
	}

	/**
	 * Returns a message that tells you how much you paid to who
	 * @param amountPayed
	 * @param owner
	 * @return String
	 */
	public String youPaidThisMuchToThisPerson(int amountPayed, Player owner) {
		return selectedLanguage.youPaidThisMuchToThisPerson(amountPayed, owner);
	}

	/**
	 * Returns a message that tells you that you already own this field
	 * @return
	 */
	public String youOwnThisField() {
		return selectedLanguage.youOwnThisField();
	}

	/**
	 * Returns a message that asks you if you want to pay either 10% or 4000
	 * @return String
	 */
	public String getTaxChoice() {
		return selectedLanguage.getTaxChoice();
	}

	/**
	 * Returns message used for non-ownable field, determined by which field it is
	 * @param onField
	 * @return String
	 */
	public String nonOwnableFieldEffectMsg(int onField) {
		return selectedLanguage.nonOwnableFieldEffectMsg(onField);
	}

	/**
	 * Returns a message that tells you're broke
	 * @return
	 */
	public String youAreBroke() {
		return selectedLanguage.youAreBroke();
	}

	/**
	 * Prints who won with how many points.
	 * @return String
	 */
	public String winnerMsg(Player player){
		return selectedLanguage.winnerMsg(player);
	}

	/**
	 * Prints the available commands in the menu.
	 * @return String
	 */
	public String menu(){
		return selectedLanguage.menu();
	}

	/**
	 * Prints the rules of the game.
	 * @return String
	 */
	public String printRules(){
		return selectedLanguage.printRules();
	}

	/**
	 * Prints the score. 
	 * @return String
	 */
	public String printScore(Player[] players){
		return selectedLanguage.printScore(players);
	}

	/** 
	 * Prints how to change the dices.
	 * @return String
	 */
	public String changeDices(){
		return selectedLanguage.changeDices();
	}

	/**
	 * Prints that the dices were changed successfully.			
	 * @return String
	 */
	public String printDiceChangeSucces(){
		return selectedLanguage.printDiceChangeSucces();
	}

	/**
	 * Prints a error message if the dices couldn't be changed.
	 * @return String
	 */
	public String printDiceChangeNotExecuted(){
		return selectedLanguage.printDiceChangeNotExecuted();
	}

	/**
	 * Prints button message for throw-action
	 * @return String
	 */
	public String throwDices(){ return selectedLanguage.throwDices(); }
	
	/**
	 * Prints  message when trying to build houses but dont own all the fields.
	 * @return String
	 */

	public String notBuildable(){ return selectedLanguage.notBuildable(); }
	
	/**
	 * Prints  message when building houses
	 * @return String
	 */
	
	public String buildable(){ return selectedLanguage.buildable();}
	
	/**
	 * Prints  message when trying to destroy a not destroyable property
	 * @return String
	 */
	
	public String notDemolitionable(){ return selectedLanguage.notDemolitionable(); }
	
	/**
	 * Prints  message when destroying a property
	 * @return String
	 */
	
	public String chooseDemolitionable(){ return selectedLanguage.chooseDemolition(); }
	
	/**
	 * Prints  message when a trade is not available
	 * @return String
	 */
	
	public String notTradeable() { return selectedLanguage.notTradeable(); }
	
	/**
	 * Prints  message for choosing a plot to trade
	 * @return String
	 */
	
	public String choosePlotTrade(){ return selectedLanguage.choosePlotTrade(); }
	
	/**
	 * Prints  message for choosing a buyer to trade
	 * @return String
	 */
	
	public String chooseBuyerTrade(){ return selectedLanguage.chooseBuyerTrade(); }
	
	/**
	 * Prints  message for choosing trade price
	 * @return String
	 */
	
	public String tradePrice(){ return selectedLanguage.tradePrice(); }
	
	/**
	 * Prints  message for asking the counterpart if want to trade.
	 * @return String
	 */
	
	public String wantToTrade(){ return selectedLanguage.wantToTrade(); }
	
	/**
	 * Prints  message when you have no pawnablefields
	 * @return String
	 */
	
	public String noPawnableFields(){ return selectedLanguage.noPawnableFields();}
	
	/**
	 * Prints  message when selecting a pawnable field
	 * @return String
	 */
	
	public String choosePawnField(){ return selectedLanguage.choosePawnField(); }
	
	/**
	 * Prints  message for succesful pawn
	 * @return String
	 */
	
	public String pawnSucces(){ return selectedLanguage.pawnSucces(); }
	
	/**
	 * Prints  message when field isnt pawnable. 
	 * @return String
	 */
	
	public String notPawnable(){ return selectedLanguage.notPawnable(); }

	/**
	 * Prints  message when player got no pawned properties 
	 * @return String
	 */

	public String noPawnedProperties(){ return selectedLanguage.noPawnedProperties();}
	
	/**
	 * Prints  message when player chooses which pawned field to withdraw
	 * @return String
	 */
	
	public String choosePawnedWithdraw(){ return selectedLanguage.choosePawnedWithdraw();}
	
	/**
	 * Prints  message when withdraw succesful
	 * @return String
	 */
	
	public String pawnedWithdrawSucces(){ return selectedLanguage.pawnedWithdrawSucces();}
	
	/**
	 * Prints  message when withdraw unsuccesful 
	 * @return String
	 */
	
	public String pawnedWithdrawUnsuccesful(){ return selectedLanguage.pawnedWithdrawUnsuccesful();}
	
	/**
	 * Prints options menu selections
	 * @return String
	 */
	
	public String pawn(){ return selectedLanguage.pawn();}
	
	/**
	 * Prints options menu selections
	 * @return String
	 */
	
	public String sell(){ return selectedLanguage.sell();}
	
	/**
	 * Prints options menu selections
	 * @return String
	 */
	
	public String trade(){ return selectedLanguage.trade();}
	
	/**
	 * Prints options menu selections
	 * @return String
	 */
	
	public String bankrupt(){ return selectedLanguage.bankrupt();}
	
	/**
	 * Prints  message when a player has to pay a targeted amount
	 * @return String
	 */
	
	public String toPay(){ return selectedLanguage.toPay();}
	
	/**
	 * Prints message if player got enough money 
	 * @return String
	 */
	
	public String canGetMoney(){ return selectedLanguage.canGetMoney();}
	
	public String build(){
		return selectedLanguage.build();
	}
	
}
