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
	
	public String fieldInfo(int[] fieldRent, int fieldPrice, int constructionPrice) { 
		return selectedLanguage.fieldInfo(fieldRent, fieldPrice, constructionPrice); 
	} 

	public String readyToBegin(){
		return selectedLanguage.readyToBegin();
	}

	public String winnerMsg(Player player){
		return selectedLanguage.winnerMsg(player);
	}

	public String youAreInJailMsg(Player player) {
		return selectedLanguage.youAreInJailMsg(player);
	}

	public String throwDices(){ 
		return selectedLanguage.throwDices();
	}

	public String payOneThousand() {
		return selectedLanguage.payOneThousand();
	}

	public String useGetOutOfJail() {
		return selectedLanguage.useGetOutOfJail();
	}

	public String preMsg(Player player){
		return selectedLanguage.preMsg(player);
	}

	public String build(){ 
		return selectedLanguage.build();
	}

	public String trade(){ 
		return selectedLanguage.trade();
	}

	public String undoPawn() {
		return selectedLanguage.undoPawn();
	}

	public String whatDoYouWantToTrade() {
		return selectedLanguage.whatDoYouWantToTrade();
	}

	public String tradeProperties() {
		return selectedLanguage.tradeProperties();
	}

	public String tradeGetOutOfJailCard() {
		return selectedLanguage.tradeGetOutOfJailCard();
	}

	public String noMoreAttemptsAtRollingOutOfJail() {
		return selectedLanguage.noMoreAttemptsAtRollingOutOfJail();
	}

	public String attemptAtRollingOutOfJailUnsuccessful() {
		return selectedLanguage.attemptAtRollingOutOfJailUnsuccessful();
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

	public String landedOnOwnedFieldOwnerIsInJail(Player owner) {
		return selectedLanguage.landedOnOwnedFieldOwnerIsInJail(owner);
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

	public String choosePropertyToTrade(){ 
		return selectedLanguage.choosePropertyToTrade(); 
	}

	public String choosePropertyBuyer(){ 
		return selectedLanguage.choosePropertyBuyer(); 
	}

	public String enterPropertyTradePrice(){ 
		return selectedLanguage.enterPropertyTradePrice(); 
	}

	public String confirmPropertyTrade(String fieldName, String buyerName, int price){ 
		return selectedLanguage.confirmPropertyTrade(fieldName, buyerName, price); 
	}

	public String propertyTradeConfirmation(String buyerName, int price) {
		return selectedLanguage.propertyTradeConfirmation(buyerName, price);
	}

	public String yes() {
		return selectedLanguage.yes();
	}

	public String no() {
		return selectedLanguage.no();
	}

	public String chooseGetOutOfJailCardBuyer() {
		return selectedLanguage.chooseGetOutOfJailCardBuyer();
	}

	public String enterGetOutOfJailCardTradePrice() {
		return selectedLanguage.enterGetOutOfJailCardTradePrice();
	}

	public String confirmGetOutOfJailCardTrade(String buyerName, int price) {
		return selectedLanguage.confirmGetOutOfJailCardTrade(buyerName, price);
	}

	public String getOutOfJailCardPurchaseConfirmation() {
		return selectedLanguage.getOutOfJailCardPurchaseConfirmation();
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

	public String propertyPurchaseConfirmation() {
		return selectedLanguage.propertyPurchaseConfirmation();
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
	
	public String confirmPropertyPurchase(String fieldName, int price) {
		return selectedLanguage.confirmPropertyPurchase(fieldName, price);
	}

	public String wantToRunAuctionSequenceAgain() {
		return selectedLanguage.wantToRunAuctionSequenceAgain();
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

	public String getMoneySequenceStatus(String debitorName, int targetAmount, int amountToGet){ 
		return selectedLanguage.getMoneySequenceStatus(debitorName, targetAmount, amountToGet);
	}

	public String canGetMoney(){ 
		return selectedLanguage.canGetMoney();
	}

	public String bankruptcyConcluded() {
		return selectedLanguage.bankruptcyConcluded();
	}

	public String confirmBuild(int constructionPrice, String fieldName) {
		return selectedLanguage.confirmBuild(constructionPrice, fieldName);
	}

	public String confirmDemolition(String fieldName) {
		return selectedLanguage.confirmDemolition(fieldName);
	}

	public String wantToUndoPawnWithoutInterest() {
		return selectedLanguage.wantToUndoPawnWithoutInterest();
	}

	public String wantToRunVoluntaryGetMoneySequence(String debitorName) {
		return selectedLanguage.wantToRunVoluntaryGetMoneySequence(debitorName);
	}

	public String confirmUndoPawn(String fieldName) {
		return selectedLanguage.confirmUndoPawn(fieldName);
	}

	public String landedOnOwnedFieldButItsPawned(Player ownerOfField) {
		return selectedLanguage.landedOnOwnedFieldButItsPawned(ownerOfField);
	}

	public String landedOnOwnedFieldHasToPayDoubleRent(Player owner) {
		return selectedLanguage.landedOnOwnedFieldHasToPayDoubleRent(owner);
	}
}
