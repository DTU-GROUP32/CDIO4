package entity.language;

import entity.Player;

/**
 * Every method in this class should be self-explanatory by their names
 * @author Frederik
 *
 */

public class LanguageHandler {

	private Language selectedLanguage;
	private static LanguageHandler instance;

	/**
	 * Constructor that takes a parameter to initialize a language
	 * @param language
	 */
	private LanguageHandler(String language) {
		setLanguage(language);
	}

	/**
	 * Returns an instance of LanguageHandler. And initializes the instance if it isn't already initialized
	 * @return instance
	 */
	public static LanguageHandler getInstance() {
		if(instance == null)
			instance = new LanguageHandler("Dansk");
		return instance;
	}

	/**
	 * Changes language to input parameter
	 * @param language
	 */
	public void setLanguage(String language) {
		switch(language) {
		case "Dansk": selectedLanguage = new Dansk(); break;
		case "English": selectedLanguage = new English(); break;
		default: selectedLanguage = new Dansk();
		}
	}

	public String notifyLangChange(){
		return selectedLanguage.notifyLangChange();
	}

	public String askForNumberOfPlayers() {
		return selectedLanguage.askForNumberOfPlayers();
	}

	public String askForPlayerName(int playerNumber){
		return selectedLanguage.askForPlayerName(playerNumber);
	}

	public String fieldDescription(int fieldNumber) {
		return selectedLanguage.fieldDescription(fieldNumber);
	}

	public String fieldNames(int fieldNumber) {
		return selectedLanguage.fieldNames(fieldNumber);
	}

	public String fieldPrices(int fieldPrice) {
		return selectedLanguage.fieldPrices(fieldPrice);
	}

	public String readyToBegin(){
		return selectedLanguage.readyToBegin();
	}

	public String winnerMsg(Player player){
		return selectedLanguage.winnerMsg(player);
	}

	public String preMsg(Player player){
		return selectedLanguage.preMsg(player);
	}

	public String throwDices(){ 
		return selectedLanguage.throwDices();
	}

	public String build(){ 
		return selectedLanguage.build();
	}

	public String trade(){ 
		return selectedLanguage.trade();
	}

	public String youGetJailedForThreeTimesEqual() {
		return selectedLanguage.youGetJailedForThreeTimesEqual();
	}

	public String getChanceCardMsg(int topCardNumber) {
		return selectedLanguage.getChanceCardMsg(topCardNumber);
	}

	public String fieldMsg(int fieldNumber){
		return selectedLanguage.fieldMsg(fieldNumber);
	}

	public String landedOnOwnedField(Player owner) {
		return selectedLanguage.landedOnOwnedField(owner);
	}

	public String youPaidThisMuchToThisPerson(int amountPayed, Player owner) {
		return selectedLanguage.youPaidThisMuchToThisPerson(amountPayed, owner);
	}

	public String youOwnThisField() {
		return selectedLanguage.youOwnThisField();
	}

	public String getTaxChoice() {
		return selectedLanguage.getTaxChoice();
	}

	public String notBuildable(){ 
		return selectedLanguage.notBuildable();
	}

	public String choosePlotToBuildOn(){
		return selectedLanguage.choosePlotToBuildOn();
	}

	public String noDemolitionableProperties(){ 
		return selectedLanguage.noDemolitionableProperties();
	}

	public String choosePropertyToDemolishOn(){ 
		return selectedLanguage.choosePropertyToDemolishOn(); 
	}

	public String noTradeableProperties() { 
		return selectedLanguage.noTradeableProperties();
	}

	public String choosePlotTrade(){ 
		return selectedLanguage.choosePlotTrade(); 
	}

	public String choosePropertyBuyer(){ 
		return selectedLanguage.choosePropertyBuyer(); 
	}

	public String enterTradePrice(){ 
		return selectedLanguage.enterTradePrice(); 
	}

	public String confirmTrade(String fieldName, String buyerName, int price){ 
		return selectedLanguage.confirmTrade(fieldName, buyerName, price); 
	}

	public String yes() {
		return selectedLanguage.yes();
	}

	public String no() {
		return selectedLanguage.no();
	}

	public String noPawnableFields(){ 
		return selectedLanguage.noPawnableFields();
	}

	public String choosePropertyToPawn(){ 
		return selectedLanguage.choosePropertyToPawn();
	}

	public String pawnSuccessful(){ 
		return selectedLanguage.pawnSuccessful(); 
	}

	public String pawnUnsuccessful(){
		return selectedLanguage.pawnUnsuccessful(); 
	}

	public String noPawnedProperties(){
		return selectedLanguage.noPawnedProperties();
	}

	public String choosePropertyToUndoPawn(){
		return selectedLanguage.choosePropertyToUndoPawn();
	}

	public String undoPawnSuccessful(){
		return selectedLanguage.undoPawnSuccessful();
	}

	public String undoPawnUnsuccessful(){ 
		return selectedLanguage.undoPawnUnsuccessful();
	}

	public String buyingOfferMsg(int price){
		return selectedLanguage.buyingOfferMsg(price);
	}

	public String purchaseConfirmation() {
		return selectedLanguage.purchaseConfirmation();
	}

	public String notEnoughMoney() {
		return selectedLanguage.notEnoughMoney();
	}
	
	public String auctionNotification() {
		return selectedLanguage.auctionNotification();
	}

	public String enterAuctionPrice() {
		return selectedLanguage.enterAuctionPrice();
	}
	
	public String confirmPurchase(String fieldName, int price) {
		return selectedLanguage.confirmPurchase(fieldName, price);
	}

	public String pawn(){ 
		return selectedLanguage.pawn();
	}

	public String demolish(){ 
		return selectedLanguage.demolish();
	}

	public String bankrupt(){ 
		return selectedLanguage.bankrupt();
	}

	public String toPay(int targetAmount){ 
		return selectedLanguage.toPay(targetAmount);
	}

	public String canGetMoney(){ 
		return selectedLanguage.canGetMoney();
	}

	public String bankruptcyConcluded() {
		return selectedLanguage.bankruptcyConcluded();
	}	

	//
	//
	// METHODS UNDER THIS LINE ARE NOT USED IN THIS VERSION OF THE GAME
	//
	//

	public String nonOwnableFieldEffectMsg(int onField) {
		return selectedLanguage.nonOwnableFieldEffectMsg(onField);
	}

	public String menu(){
		return selectedLanguage.menu();
	}

	public String printRules(){
		return selectedLanguage.printRules();
	}

	public String printScore(Player[] players){
		return selectedLanguage.printScore(players);
	}

	public String changeDices(){
		return selectedLanguage.changeDices();
	}

	public String printDiceChangeSucces(){
		return selectedLanguage.printDiceChangeSucces();
	}

	public String printDiceChangeNotExecuted(){
		return selectedLanguage.printDiceChangeNotExecuted();
	}
}
