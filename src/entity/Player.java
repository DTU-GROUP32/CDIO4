package entity;

public class Player{

	private String playerName;
	private BankAccount bankAccount;
	private int onField;
	private int ID;
	private static int nextID = 0;
	private int equalsInRowCount;
	private boolean playerInJail;
	private int getOutOfJailCardCount;
	private int attemptsAtGettingOutOfJailByEqualCount;

	/**
	 * Default constructor.
	 */
	public Player(){
		this("John Doe");
	}

	/**
	 * Constructor that can give the player a name.
	 * @param name - the players name.
	 */
	public Player(String name){
		this(name, 30000);
	}

	/**
	 * Constructor that can give the player a name and a starting balance.
	 * @param name - the players name.
	 * @param startingBalance - the players starting balance.
	 */
	public Player(String name, int startingBalance) {
		playerName = name;
		bankAccount = new BankAccount(startingBalance);
		this.onField = 0;
		this.ID = nextID++;
		this.equalsInRowCount = 0;
		this.playerInJail = false;
		this.getOutOfJailCardCount = 0;
		this.attemptsAtGettingOutOfJailByEqualCount = 0;
	}

	/**
	 * Calculates total releasable assets of player from his balance, owned plots and constructions.
	 * @param gameBoard
	 * @return totalAssets
	 */
	public int getTotalReleasableAssets(GameBoard gameBoard){
		int totalReleasableAssets = 0;
		// all his money
		totalReleasableAssets += this.bankAccount.getBalance();
		// for each property he owns, adds the pawn value and the value of his constructions if he sells them(half value)
		for(int i = 0; i < gameBoard.getPropertyList(this).size(); i++) {
			if(gameBoard.getPropertyList(this).get(i).isPawned() == false) {
				totalReleasableAssets += gameBoard.getPropertyList(this).get(i).getPawnValue();
			}
			totalReleasableAssets += gameBoard.getPropertyList(this).get(i).getConstructionRate() * gameBoard.getPropertyList(this).get(i).getConstructionPrice() / 2;
		}

		return totalReleasableAssets;
	}
	
	/**
	 * Calculates total assets of player for tax purposes from his balance, owned plots and constructions.
	 * @param gameBoard
	 * @return
	 */
	public int getTotalAssetsForTaxPurposes(GameBoard gameBoard) {
		int totalAssetsForTaxPurposes = 0;
		// all his money
		totalAssetsForTaxPurposes += this.bankAccount.getBalance();
		// for each property he owns, adds the price of the property and the price of his constructions
		for(int i = 0; i < gameBoard.getPropertyList(this).size(); i++) {
			totalAssetsForTaxPurposes += gameBoard.getPropertyList(this).get(i).getPrice();
			totalAssetsForTaxPurposes += gameBoard.getPropertyList(this).get(i).getConstructionRate() * gameBoard.getPropertyList(this).get(i).getConstructionPrice();
		}

		return totalAssetsForTaxPurposes;
	}

	/**
	 * Adds the number that was rolled to the field the player was on and moves him to a new field.
	 * @param roll - Integer value that was rolled.
	 */
	public void movePlayer(int roll){
		this.onField += roll;
		// if onField is bigger than 39
		while(this.onField > 39)
		{
			// a full round on the board is deducted
			this.onField -= 40;
			// and 4000 for passing start is added to the players bank account
			this.getBankAccount().deposit(4000);
		}
	}

	/**
	 * Returns boolean to determine if players balance is 0 or below
	 * @return Boolean
	 */
	public boolean isPlayerBroke() {
		return this.getBankAccount().getBalance() < 0;
	}

	/**
	 * Returns the name of the player
	 * @return name
	 */
	public String getName(){
		return this.playerName;
	}

	/**
	 * Sets the name of the player
	 * @param name
	 */
	public void setName(String name){
		this.playerName = name;
	}

	/**
	 * Returns players bank account
	 * @return
	 */
	public BankAccount getBankAccount(){
		return this.bankAccount;
	}

	/**
	 * Returns integer that tells which field the player is standing on
	 * @return onField
	 */
	public int getOnField(){
		return this.onField;
	}

	/**
	 * Sets the position of the player
	 * @param onField
	 */
	public void setOnField(int onField) {
		this.onField = onField;
	}

	/**
	 * Returns player ID
	 * @return
	 */
	public int getID(){
		return this.ID;
	}

	/**
	 * Returns how many times the player has rolled two equal face values
	 * @return the equalsCount
	 */
	public int getEqualsInRowCount() {
		return equalsInRowCount;
	}

	/**
	 * Sets the count of how many times the player has rolled two equal face values
	 */
	public void setEqualsInRowCount(int equalsInRowCount) {
		this.equalsInRowCount = equalsInRowCount;
	}

	/**
	 * Returns whether or not the player is in jail.
	 * @return playerInJail
	 */
	public boolean isPlayerInJail() {
		return playerInJail;
	}

	/**
	 * Sets the player to be either in or out of jail.
	 * Also resets the "attempts at getting out of jail by equal count".
	 * @param playerInJail
	 */
	public void setPlayerInJail(boolean playerInJail) {
		this.playerInJail = playerInJail;
		this.attemptsAtGettingOutOfJailByEqualCount = 0;
	}

	/**
	 * Returns the count of get out of jail cards.
	 * @return getOutOfJailCardCount
	 */
	public int getGetOutOfJailCardCount() {
		return getOutOfJailCardCount;
	}

	/**
	 * Sets the count  of get out of jail cards.
	 * @param getOutOfJailCardCount
	 */
	public void setGetOutOfJailCardCount(int getOutOfJailCardCount) {
		this.getOutOfJailCardCount = getOutOfJailCardCount;
	}

	/**
	 * Resets the count of players(only used for testing).
	 */
	public static void resetID() {
		nextID = 0;
	}

	/**
	 * Returns how many attempts the player has had a getting out of jail by rolling equal.
	 * @return
	 */
	public int getAttemptsAtGettingOutOfJailByEqualCount() {
		return attemptsAtGettingOutOfJailByEqualCount;
	}

	/**
	 * Increments the "attempts at getting out of jail by equal count" by 1.
	 */
	public void incrementAttemptsToGetOutOfJailByEqualCountByOne() {
		this.attemptsAtGettingOutOfJailByEqualCount++;
	}
}