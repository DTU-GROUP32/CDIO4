package entity.language;

import entity.Player;

public class LanguageHandler {

	private Language selectedLanguage;

	/**
	 * Default constructor that takes a parameter to initialize a entity.language
	 * @param language
	 */
	public LanguageHandler(String language) {
		setLanguage(language);
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
}
