package entity;

public class Player{
	
	private String playerName;
	private BankAccount bankAccount;
	private int onField;
	private int ID;
	private static int nextID = 0;
	private int lastRoll;
	private boolean taxChoice;

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
		while(this.onField > 21)
			this.onField -= 22;
	}
	
	/**
	 * Returns player ID
	 * @return
	 */
	public int getID(){
		return this.ID;
	}

	/**
	 * Returns sum of the last roll.
	 * @return lastRoll
	 */
	public int getLastRoll() {
		return lastRoll;
	}

	/**
	 * Sets sum of last roll.
	 * @param lastRoll
	 */
	public void setLastRoll(int lastRoll) {
		this.lastRoll = lastRoll;
	}

	/**
	 * Returns wether or not it is a Tax with a choice
	 * @return
	 */
	public boolean isTaxChoice() {
		return taxChoice;
	}

	/**
	 * Returns tax choice
	 * @param taxChoice
	 */
	public void setTaxChoice(boolean taxChoice) {
		this.taxChoice = taxChoice;
	}
	
	public static void resetID() {
		nextID = 0;
	}
}