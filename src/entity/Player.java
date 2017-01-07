package entity;

public class Player{
	
	private String playerName;
	private BankAccount bankAccount;
	private int onField;
	private int ID;
	private static int nextID = 0;
	private int equalsCount;
	private boolean inJail;
	private int getOutOfJail;
	private int inJailThrowCount;

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
		this.equalsCount = 0;
		this.inJail = false;
		this.getOutOfJail = 0;
	}
	
	/**
	 * Calculates total assets of player from his balance, owned plots and constructions.
	 * @param gameBoard
	 * @return totalAssets
	 */
	public int getTotalAssets(GameBoard gameBoard){
		int totalAssets = 0;
		totalAssets += this.bankAccount.getBalance();
		
		for(int i = 0; i < gameBoard.getPropertyList(this).size(); i++) {
			totalAssets += gameBoard.getPropertyList(this).get(i).getPawnValue();
			totalAssets += gameBoard.getPropertyList(this).get(i).getConstructionRate() * gameBoard.getPropertyList(this).get(i).getConstructionPrice() / 2;
		}
		
		return totalAssets;
	}
	
	/**
	 * Adds the number that was rolled to the field the player was on and moves him to a new field.
	 * @param roll - Integer value that was rolled.
	 */
	public void movePlayer(int roll){
		this.onField += roll;
		while(this.onField > 39)
		{
			this.onField -= 40;
			this.getBankAccount().deposit(4000);
		}
	}

	/**
	 * Returns boolean to determine if players balance is 0 or below
	 * @param atIndex Index of the player
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
	 * Returns how many times the player has rolled two equal facevalues
	 * @return the equalsCount
	 */
	public int getEqualsCount() {
		return equalsCount;
	}

	/**
	 * Sets the count of how many times the player has rolled two equal facevalues
	 * @param equalsCount the equalsCount to set
	 */
	public void setEqualsCount(int equalsCount) {
		this.equalsCount = equalsCount;
	}

	/**
	 * Returns wether or not the player is in jail
	 * @return the inJail
	 */
	public boolean isInJail() {
		return inJail;
	}

	/**
	 * Sets the player to be either in or out of jail
	 * @param inJail the inJail to set
	 */
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	/**
	 * Returns the count of the ability to get out of jail
	 * @return the getOutOfJail
	 */
	public int getGetOutOfJail() {
		return getOutOfJail;
	}

	/**
	 * Sets the count of the ability to get out of jail
	 * @param getOutOfJail the getOutOfJail to set
	 */
	public void setGetOutOfJail(int getOutOfJail) {
		this.getOutOfJail = getOutOfJail;
	}
	
	/**
	 * Resets the count of players
	 */
	public static void resetID() {
		nextID = 0;
	}

	/**
	 * @return the inJailThrowCount
	 */
	public int getInJailThrowCount() {
		return inJailThrowCount;
	}

	/**
	 * @param inJailThrowCount the inJailThrowCount to set
	 */
	public void setInJailThrowCount(int inJailThrowCount) {
		this.inJailThrowCount = inJailThrowCount;
	}
}