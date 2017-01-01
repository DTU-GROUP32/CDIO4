package entity.language;

import entity.Player;

public class English implements Language{

	@Override
	public String notifyLangChange(){
		return "The entity.language is now english!";
	}

	@Override
	public String fieldNames(int fieldNumber) {
		String fieldName;
		switch (fieldNumber) {
		case 0:  fieldName = "START";
		break;
		case 1:  fieldName = "Tribe Encampment";
		break;
		case 2:  fieldName = "Second Sail";
		break;
		case 3:  fieldName = "Crater";
		break;
		case 4:  fieldName = "Huts in the Mountain";
		break;
		case 5:  fieldName = "Mountain";
		break;
		case 6:  fieldName = "Monastery";
		break;
		case 7:  fieldName = "Cold Desert";
		break;
		case 8:  fieldName = "Sea Grover";
		break;
		case 9:  fieldName = "Black Cave";
		break;
		case 10: fieldName = "Goldmine";
		break;
		case 11: fieldName = "The Werewall";
		break;	
		case 12: fieldName = "The Pit";
		break;	
		case 13: fieldName = "Mountain Village";
		break;	
		case 14: fieldName = "The Buccaneers";
		break;	
		case 15: fieldName = "South Citadel";
		break;	
		case 16: fieldName = "Walled City";
		break;	
		case 17: fieldName = "Palace Gates";
		break;	
		case 18: fieldName = "Caravan";
		break;	
		case 19: fieldName = "Tower";
		break;	
		case 20: fieldName = "Privateer Armade";
		break;	
		case 21: fieldName = "Castle";
		break;	
		default: fieldName = "Unknown field!";
		break;
		}
		return fieldName;
	}
	
	@Override
	public String fieldPrices(int fieldNumber) {
		String fieldPrice;
		switch (fieldNumber) {
		case 0:  fieldPrice = "Refuge";
		break;
		case 1:  fieldPrice = "Price: 1000";
		break;
		case 2:  fieldPrice = "Price: 4000";
		break;
		case 3:  fieldPrice = "Price: 1500";
		break;
		case 4:  fieldPrice = "Price: 2500";
		break;
		case 5:  fieldPrice = "Price: 2000";
		break;
		case 6:  fieldPrice = "Monastery";
		break;
		case 7:  fieldPrice = "Price: 3000";
		break;
		case 8:  fieldPrice = "Price: 4000";
		break;
		case 9:  fieldPrice = "Price: 4000";
		break;
		case 10: fieldPrice = "Goldmine";
		break;
		case 11: fieldPrice = "Price: 4300";
		break;	
		case 12: fieldPrice = "Price: 2500";
		break;	
		case 13: fieldPrice = "Price: 4700";
		break;	
		case 14: fieldPrice = "Price: 4000";
		break;	
		case 15: fieldPrice = "Price: 5000";
		break;	
		case 16: fieldPrice = "Walled City";
		break;	
		case 17: fieldPrice = "Price: 5500";
		break;	
		case 18: fieldPrice = "Caravan";
		break;	
		case 19: fieldPrice = "Price: 6000";
		break;	
		case 20: fieldPrice = "Price: 4000";
		break;	
		case 21: fieldPrice = "Price: 8000";
		break;	
		default: fieldPrice = "Unknown field!";
		break;
		}
		return fieldPrice;
	}

	@Override
	public String fieldDescription(int fieldNumber) {
		String fieldName;
		switch (fieldNumber) {
		case 6:  fieldName = "Recieve 500 coins";
		break;
		case 10: fieldName = "Pay 2000 coins";
		break;
		case 16: fieldName = "Recieve 5000 coins";
		break;
		case 18: fieldName = "Pay 4000 or 10% of you coins";
		break;
		default: fieldName = "Unknown field!";
		break;
		}
		return fieldName;
	}

	@Override
	public String welcomeMsg(){
		return "Welcome to the game!";
	}

	@Override
	public String askForNumberOfPlayers() {
		return "How many players do you want to play the game? You can choose inbetween 2 and 6 players to be in the game";
	}

	@Override
	public String askForPlayerName(int playerNumber){
		return "Enter the name of player " + playerNumber + ". Players can't have the same name!";
	}

	@Override
	public String readyToBegin(){
		return "The game will now start. The game is won by the player who stands back when everyone else is broke";
	}

	public String preMsg(Player player){
		return "It's " + player.getName() + "s turn, press the button to roll the dice!";
	}

	@Override
	public String fieldMsg(int fieldNumber){
		String fieldString;
		switch (fieldNumber) {
		case 0:  fieldString = "START";
		break;
		case 1:  fieldString = "You're invited to a party at the Tribe Encampment!";
		break;
		case 2:  fieldString = "You sail with the Second Sail!";
		break;
		case 3:  fieldString = "You find a big crater and examine it!";
		break;
		case 4:  fieldString = "You further examine the mountain and find some huts in the mountain!";
		break;
		case 5:  fieldString = "You're arrived at a huge mountain!";
		break;
		case 6:  fieldString = "You see a monastery in the distance and walk closer!";
		break;
		case 7:  fieldString = "You're arrived at the cold desert!";
		break;
		case 8:  fieldString = "You sail with the Sea Grover";
		break;
		case 9:  fieldString = "You find a black cave!";
		break;
		case 10: fieldString = "You explore a cave that looks like a goldmine";
		break;
		case 11: fieldString = "You're arrived at the Werewall!";
		break;	
		case 12: fieldString = "You fell into a black hole!";
		break;	
		case 13: fieldString = "You're arrived at the famous mountain village!";
		break;	
		case 14: fieldString = "You sail with the The Buccaneers";
		break;	
		case 15: fieldString = "You're arrived at the South Citadel!";
		break;	
		case 16: fieldString = "You walk carefully through the Walled City";
		break;	
		case 17: fieldString = "You pass the large Palace Gates";
		break;	
		case 18: fieldString = "You found an abandoned Caravan!";
		break;	
		case 19: fieldString = "You try to clib the Tower!";
		break;	
		case 20: fieldString = "You go on a sailing adventure with the Privateer Armade";
		break;	
		case 21: fieldString = "You're invited into the big Castle!";
		break;	
		default: fieldString = "Unknown field!";
		break;
		}
		return fieldString;
	}

	@Override
	public String buyingOfferMsg(int price) {
		return "This field is not owned by anyone, do you want to buy it for " + price + " coins?";
	}

	@Override
	public String yes() {
		return "Yes!";
	}

	@Override
	public String no() {
		return "No!";
	}

	@Override
	public String purchaseConfirmation() {
		return "You have now bought this field!";
	}

	@Override
	public String notEnoughMoney() {
		return "You have not enough coins left..";
	}

	public String landedOnOwnedField(Player owner) {
		return "This field is already owned by someone, you'll have to pay rent!";
	}

	@Override
	public String youPaidThisMuchToThisPerson(int amountPayed, Player owner) {
		return "You paid " + amountPayed + " coins to " + owner.getName() + ".";
	}

	@Override
	public String youOwnThisField() {
		return "You already own this field";
	}
	
	@Override
	public String getTaxChoice() {
		return "You can either choose to pay 4000 coins or 10% of your current balance,"
				+ "\ndo you want to pay 10%?";
	}

	@Override
	public String nonOwnableFieldEffectMsg(int fieldNumber) {
		String message;
		switch (fieldNumber) {
		case 6:  message = "You landed on the Monastery, you'll recieve 500 coins!";
		break;
		case 10: message = "You landed on the Goldmine, you'll have to pay 2000 coins!";
		break;
		case 16: message = "You landed on the Walled City, you'll recieve 5000 coins";
		break;
		default: message = "Unknown field!";
		break;
		}
		return message;
	}

	@Override
	public String youAreBroke() {
		return "Sorry, you are broke, thanks for playing!";
	}

	@Override
	public String winnerMsg(Player player){
		return player.getName() + " have won the game with " + player.getBankAccount().getBalance() + " coins!";
	}
	
	@Override
	public String menu(){
		return "Tast 1 for at skifte antal sider på terningerne.\n" +
				"Tast 2 for at ændre sprog.\n" +
				"Tast 3 for at vise scoren.\n"+
				"Tast 4 for at afslutte spillet.\n" +
				"Tast 5 for at fortsætte spillet.";
	}

	@Override
	public String printRules(){
		return "Dette spil er et terningespil mellem 2 personer. Du slår med terninger og lander på et felt fra 2-12. \nDisse felter har enten en negativ eller positiv effekt på din beholdning. Her er vist listen over felterne: \n"
				+ "2. Tower: +250 \n"
				+ "3. Crater: -100 \n"
				+ "4. Palace gates: +100 \n"
				+ "5. Cold Desert: -20 \n"
				+ "6. Walled city: +180 \n"
				+ "7. Monastery: 0 \n"
				+ "8. Black cave: -70 \n"
				+ "9. Huts in the mountain: +60 \n"
				+ "10. The Werewall (werewolf-wall): -80, men spilleren får en ekstra tur \n"
				+ "11. The pit: -50 \n"
				+ "12. Goldmine: +650";
	}

	@Override
	public String printScore(Player[] players){
		StringBuilder str = new StringBuilder();
		str.append("The score is:");
		for (int i = 0; i < players.length; i++) 
			str.append("\n").append(players[i].getName()).append(" has ").append(players[i].getBankAccount().getBalance());
		return str.toString();
	}

	@Override
	public String changeDices(){
		return "Enter how many eyes you want them to have, in the form \"x,y\" - the sum of them must be 12"; // Summen måtte kun gå op til 12?
	}

	@Override
	public String printDiceChangeSucces(){
		return "Dice are now changed!";
	}

	@Override
	public String printDiceChangeNotExecuted(){
		return "Dice could not be changed";
	}	
}