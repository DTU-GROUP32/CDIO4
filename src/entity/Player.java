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

	/**
	 * Default constructor.
	 */
	public Player(){
		this("John Doe");
	}

	/**
	 * Constructor that can give the player a name.
	 * @param name the players name.
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
	 * TODO;
	 * @param gameBoard
	 * @return
	 */
	public int getTotalAssets(GameBoard gameBoard){
		int totalAssets = 0;
		return totalAssets;
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
	
	public void setOnField(int onField) {
		this.onField = onField;
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
	 * Returns player ID
	 * @return
	 */
	public int getID(){
		return this.ID;
	}

	/**
	 * @return the equalsCount
	 */
	public int getEqualsCount() {
		return equalsCount;
	}

	/**
	 * @param equalsCount the equalsCount to set
	 */
	public void setEqualsCount(int equalsCount) {
		this.equalsCount = equalsCount;
	}

	/**
	 * @return the inJail
	 */
	public boolean isInJail() {
		return inJail;
	}

	/**
	 * @param inJail the inJail to set
	 */
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	/**
	 * @return the getOutOfJail
	 */
	public int getGetOutOfJail() {
		return getOutOfJail;
	}

	/**
	 * @param getOutOfJail the getOutOfJail to set
	 */
	public void setGetOutOfJail(int getOutOfJail) {
		this.getOutOfJail = getOutOfJail;
	}
	
	public static void resetID() {
		nextID = 0;
	}
}