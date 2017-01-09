package entity.language;

import entity.Player;

public class Dansk implements Language{

	@Override
	public String notifyLangChange(){
		return "Sproget er nu sat til dansk!";
	}

	@Override
	public String askForNumberOfPlayers() {
		return "Hvor mange spillere skal være med?";
	}

	@Override
	public String askForPlayerName(int playerNumber){
		return "Indtast spiller " + playerNumber + "'s navn, alle spillere skal have forskellige navne.";
	}

	@Override
	public String fieldDescription(int fieldNumber) {
		String fieldDescription = null;
		switch (fieldNumber) {
		case 0:  fieldDescription = "Modtag 4000";
		break;
		case 2:
		case 7:
		case 17:
		case 22:
		case 33:
		case 36: fieldDescription = "Prøv lykken";
		break;
		case 4:  fieldDescription = "Betal 10% eller kr. 4000";
		break;
		case 10: fieldDescription = "På besøg i fængselet";
		break;
		case 20: fieldDescription = "Parkering";
		break;
		case 30: fieldDescription = "De fængsles";
		break;
		case 38: fieldDescription = "Betal kr. 2000 i skat";
		break;
		}
		return fieldDescription;
	}

	@Override
	public String fieldNames(int fieldNumber) {
		String fieldName = null;
		switch (fieldNumber) {
		case 0:  fieldName = "START";
		break;
		case 1:  fieldName = "Rødovrevej";
		break;
		case 2:  fieldName = "Prøv lykken";
		break;
		case 3:  fieldName = "Hvidovrevej";
		break;
		case 4:  fieldName = "Skat";
		break;
		case 5:  fieldName = "SFL";
		break;
		case 6:  fieldName = "Roskildevej";
		break;
		case 7:  fieldName = "Prøv lykken";
		break;
		case 8:  fieldName = "Valby Langgade";
		break;
		case 9:  fieldName = "Allégade";
		break;
		case 10: fieldName = "På Besøg";
		break;
		case 11: fieldName = "Frederiksberg Alle";
		break;	
		case 12: fieldName = "TUBORG";
		break;	
		case 13: fieldName = "Bølowsvej";
		break;	
		case 14: fieldName = "Gl. Kongevej";
		break;	
		case 15: fieldName = "Kalundb./Århus";
		break;	
		case 16: fieldName = "Bernstorffsvej";
		break;	
		case 17: fieldName = "Prøv lykken";
		break;	
		case 18: fieldName = "Hellerupvej";
		break;	
		case 19: fieldName = "Strandvej";
		break;	
		case 20: fieldName = "Parkering";
		break;	
		case 21: fieldName = "Trianglen";
		break;	
		case 22: fieldName = "Prøv lykken";
		break;
		case 23: fieldName = "Østerbrogade";
		break;
		case 24: fieldName = "Grønningen";
		break;
		case 25: fieldName = "DFDS";
		break;
		case 26: fieldName = "Bredgade";
		break;
		case 27: fieldName = "Kgs.Nytorv";
		break;
		case 28: fieldName = "Coca Cola";
		break;
		case 29: fieldName = "Østergade";
		break;
		case 30: fieldName = "De Fængsles";
		break;
		case 31: fieldName = "Amagertorv";
		break;
		case 32: fieldName = "Vimmelskaftet";
		break;
		case 33: fieldName = "Prøv lykken";
		break;
		case 34: fieldName = "Nygade";
		break;
		case 35: fieldName = "Halssk./Knudsh.";
		break;
		case 36: fieldName = "Prøv lykken";
		break;
		case 37: fieldName = "Frederiksberg gade";
		break;
		case 38: fieldName = "Skat";
		break;
		case 39: fieldName = "Rådhuspladsen";
		break;
		}
		return fieldName;
	}

	@Override
	public String fieldPrices(int fieldPrice) {
		return "kr. " + fieldPrice;
	}

	@Override
	public String readyToBegin(){
		return "Spillet vil nu begynde. Spillet er vundet af den spiller der står tilbage når de andre er bankerot!";
	}

	@Override
	public String winnerMsg(Player player){
		return player.getName() + " har vundet spillet med " + player.getBankAccount().getBalance() + " kr.!";
	}

	@Override
	public String youAreInJailMsg(Player player) {
		return player.getName() + " er fængslet. De kan vælge at betale kr. 1000, slå to ens eller indfrie Deres benådning for at slippe fri.";
	}

	@Override
	public String throwDices(){
		return "Slå Terning";
	}

	@Override
	public String payOneThousand() {
		return "Betal";
	}

	@Override
	public String useGetOutOfJail() {
		return "Indfri benådning";
	}
	
	@Override
	public String preMsg(Player player){
		return "Det er " + player.getName() + "'s tur, tryk på en knap!";
	}

	@Override
	public String build() {
		return "Bygge";
	}
	
	@Override
	public String trade(){
		return "Handle";
	}

	@Override
	public String undoPawn() {
		return "Hæv pantsætning";
	}

	@Override
	public String whatDoYouWantToTrade() {
		return "Hvad vil du handle?";
	}

	@Override
	public String tradeProperties() {
		return "Ejendomme";
	}

	@Override
	public String tradeGetOutOfJailCard() {
		return "Løsladelseskort";
	}

	@Override
	public String noMoreAttemptsAtRollingOutOfJail() {
		return "Du har nu haft 3 forsøg til at slå to ens, du bliver derfor nødt til at betale kr. 1000, for at komme ud, og rykke hvad øjnene på dit tredje slag viser";
	}

	@Override
	public String attemptAtRollingOutOfJailUnsuccessful() {
		return  "Du slog desværre ikke to ens og bliver i fængsel.";
	}
	
	@Override
	public String youGetJailedForThreeTimesEqual() {
		return "De fængles for at have slået to ens 3 gange i træk!";
	}

	@Override
	public String getChanceCardMsg(int topCardNumber) {
		String chanceCardMsg = null;
		
		switch(topCardNumber) {
		case 1:
			chanceCardMsg = "Modtag udbytte af Deres aktier kr. 1000.";
			break;
		case 2:
			chanceCardMsg = "Deres præmieobligation er kommet ud. De modtager kr. 1000 af banken.";
			break;
		case 3:
			chanceCardMsg = "Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1000.";
			break;
		case 4:
			chanceCardMsg = "Værdien af egen avl fra nytthaven udgør kr. 200, som De modtager af banken.";
			break;
		case 5:
			chanceCardMsg = "De modtager \"Matador-legatet for værdig trængende\", stort kr. 40000. Ved værdig trængende forstås, at Deres formue, d.v.s. Deres kontante penge + skøder + bygninger ikke overstiger kr. 15000.";
			break;
		case 6:
			chanceCardMsg = "Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3000.";
			break;
		case 7:
			chanceCardMsg = "Det er Deres fødselsdag. Modtag af hver medspiller kr. 200.";
			break;
		case 8:
			chanceCardMsg = "De havde en række med elleve rigtige i tipning. Modtag kr. 1000.";
			break;
		case 9:
			chanceCardMsg = "De har vundet i Klasselotteriet. Modtag kr. 500.";
			break;
		case 10:
			chanceCardMsg = "De har måttet vedtage en parkeringsbøde. Betal kr. 200 i bøde.";
			break;
		case 11:
			chanceCardMsg = "De har kør frem for \"Fuld Stop\". Betal kr. 1000 i bøde";
			break;
		case 12:
			chanceCardMsg = "Betal kr. 3000 for reparation af Deres vogn";
			break;
		case 13:
			chanceCardMsg = "Betal Deres bilforsikring kr. 1000.";
			break;
		case 14:
			chanceCardMsg = "Betal kr. 3000 for reparation af Deres vogn";
			break;
		case 15:
			chanceCardMsg = "De har modtaget Deres tandlægeregning. Betal kr. 2000";
			break;
		case 16:
			chanceCardMsg = "De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal told kr. 200.";
			break;
		case 17:
			chanceCardMsg = "Ejendomsskatterne er steget, ekstraudgifterne er:\nkr. 800 pr. hus,\nkr. 2300 pr. hotel.";
			break;
		case 18:
			chanceCardMsg = "Oliepriserne er steget, og De skal betale:\nkr. 500 pr. hus,\nkr. 2000 pr. hotel.";
			break;
		case 19:
			chanceCardMsg = "I anledning af dronningens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det";
			break;
		case 20:
			chanceCardMsg = "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer \"Start\", indkasserer de ikke kr. 4000";
			break;
		case 21:
			chanceCardMsg = "Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen kan De købe det af banken.";
			break;
		case 22:
			chanceCardMsg = "Tag med den nærmeste færge - Flyt brikken frem, og hvis De passerer \"Start\", indkassér da kr. 4000.";
			break;
		case 23:
			chanceCardMsg = "Ryk frem til Grønningen. Hvis De passerer \"Start\", indkassér da kr. 4000.";
			break;
		case 24:
			chanceCardMsg = "Ryk frem til \"Start\".";
			break;
		case 25:
			chanceCardMsg = "Ryk frem til Frederiksberg Allé. Hvis De passerer \"Start\", indkassér kr. 4000.";
			break;
		case 26:
			chanceCardMsg = "Tag ind på Rådhuspladsen.";
			break;
		case 27:
			chanceCardMsg = "Ryk tre felter tilbage.";
			break;
		default:
			chanceCardMsg = "Prøv lykken";
			break;
		}
		
		return chanceCardMsg;
	}

	@Override
	public String fieldMsg(int fieldNumber){		
		String fieldString = null;
		
		switch (fieldNumber) {
		case 2:
		case 7:
		case 17:
		case 22:
		case 33:
		case 36:
			fieldString = "Du er landet på \"Prøv lykken\". Tryk på \"Prøv lykken\" bunken i midten for trække et kort, før du trykker \"OK!\"";
			break;
		case 4:
		case 38:
			fieldString = "Du er landet på et skatte-felt. Du bedes betale kr. 2000 i skat.";
			break;
		case 10:
			fieldString = "Du tager et smut på besøg i fængslet";
			break;
		case 20:
			fieldString = "Du er landet på \"Parkering\". Du modtager kr. 5000.";
			break;
		case 30:
			fieldString = "Du ryger i fængsel.";
			break;
		default:
			fieldString = "Du er landet på " + fieldNames(fieldNumber) + ".";
			break;
			
		}
		
		return fieldString;
	}

	@Override
	public String landedOnOwnedField(Player owner) {
		return "Dette felt ejes af " + owner.getName() + ", det kommer til at koste!";
	}
	
	@Override
	public String youPaidThisMuchToThisPerson(int amountPayed, Player owner) {
		return "Du betalte " + amountPayed + " kr. til " + owner.getName() + ".";
	}

	@Override
	public String landedOnOwnedFieldOwnerIsInJail(Player owner) {
		return "Dette felt ejes af " + owner.getName() + ", men han er i fængsel! Så du slipper for at betale ;)";
	}
	
	@Override
	public String youOwnThisField() {
		return "Slap af! Du ejer selv dette felt ;)";
	}
	
	@Override
	public String getTaxChoice() {
		return "Du kan vælge enten at betale 4000 kr eller 10% af din pengebeholdning,"
				+ "\nvil du betale 10%?";
	}
	
	@Override
	public String notBuildable(){
		return "Du har ingen grunde du kan bygge på.";
	}
	
	@Override
	public String choosePlotToBuildOn(){
		return "Vælg en grund at bygge på.";
	}
	
	@Override
	public String noDemolitionableProperties(){
		return "Du har ingen grunde hvor du kan nedrive bygninger.";
	}
	
	@Override
	public String choosePropertyToDemolishOn(){
		return "Vælg den grund hvor du vil nedrive en bygning.";
	}
	
	@Override
	public String noTradeableProperties(){
		return "Du har ingen felter at handle med.";
	}
	
	@Override
	public String choosePropertyToTrade(){
		return "Vælg det felt du vil handle.";
	}
	
	@Override
	public String choosePropertyBuyer(){
		return "Hvem køber feltet?";
	}
	
	@Override
	public String enterPropertyTradePrice(){
		return "Hvilken pris skal feltet sælges til?";
	}
	
	@Override
	public String confirmPropertyTrade(String fieldName, String buyerName, int price){
		return "Er du sikker på, at du vil sælge \"" + fieldName + "\" til " + buyerName + " for kr. " + price + "?";
	}

	@Override
	public String propertyTradeConfirmation(String buyerName, int price) {
		return buyerName + " har nu købt feltet til kr. " + price + "!";
	}
	
	@Override
	public String yes() {
		return "Ja!";
	}

	@Override
	public String no() {
		return "Nej!";
	}

	@Override
	public String chooseGetOutOfJailCardBuyer() {
		return "Hvem køber løsladelseskortet?";
	}

	@Override
	public String enterGetOutOfJailCardTradePrice() {
		return "Hvilken pris skal løsladelseskortet sælges til?";
	}

	@Override
	public String confirmGetOutOfJailCardTrade(String buyerName, int price) {
		return "Er du sikker på, at du vil sælge løsladelseskortet til " + buyerName + " for kr. " + price + "?";
	}

	@Override
	public String getOutOfJailCardPurchaseConfirmation() {
		return "Du har nu købt løsladelseskortet!";
	}
	
	@Override
	public String noPawnableFields(){
		return "Du har ingen felter at pantsætte.";
	}
	
	@Override
	public String choosePropertyToPawn(){
		return "Vælg et felt at pantsætte.";
	}
	
	@Override
	public String pawnSuccessful(){
		return "Din grund er blevet pantsat.";
	}
	
	@Override 
	public String pawnUnsuccessful(){
		return "Din grund kunne ikke pantsættes.";
	}
	
	@Override 
	public String noPawnedProperties(){
		return "Du har ingen felter at hæve pantsætningen på.";
	}
	
	@Override 
	public String choosePropertyToUndoPawn(){
		return "Vælg en grund at hæve pantsætningen på.";
	}
	
	@Override 
	public String undoPawnSuccessful(){
		return "Din pantsætning er blevet indfriet";
	}
	
	@Override
	public String undoPawnUnsuccessful(){
		return "Din pantsætning kunne ikke indfries";
	}
	
	@Override
	public String buyingOfferMsg(int price) {
		return "Dette felt er ikke ejet af nogen, vil du købe det for " + price + " kr.?";
	}
	
	@Override
	public String propertyPurchaseConfirmation() {
		return "Du har nu købt feltet!";
	}

	@Override
	public String notEnoughMoney() {
		return "Du havde desværre ikke nok penge..";
	}
	
	@Override
	public String auctionNotification() {
		return "Da spilleren der landede på dette felt ikke købte feltet, kan det nu købes af banken på auktion, feltets oprindelige pris er mindste prisen. Er der nogle andre der ønsker at købe feltet?";
	}
	
	@Override
	public String enterAuctionPrice(){
		return "Hvad blev auktionsprisen?";
	}

	@Override
	public String confirmPropertyPurchase(String fieldName, int price) {
		return "Vil du bekræfte købet af " + fieldName + " til kr. " + price + "?";
	}

	@Override
	public String wantToRunAuctionSequenceAgain() {
		return "Vil du køre denne auktions sekvens igen?";
	}
	
	@Override
	public String pawn(){
		return "Pantsæt";
	}
	
	@Override
	public String demolish(){
		return "Nedriv bygninger";
	}
	
	@Override
	public String bankrupt(){
		return "Erklær konkurs";
	}
	
	@Override
	public String toPay(int targetAmount){
		return "Du skal betale " + targetAmount + "kr., men du har ikke penge nok. Hvad vil du gøre?";
	}
	
	@Override
	public String canGetMoney(){
		return "Du kan godt få nok penge, ved at nedrive bygninger og pantsætte ejendomme!";
	}

	@Override
	public String bankruptcyConcluded() {
		return "Din konkurs er nu afviklet, tak for spillet!";
	}
}

