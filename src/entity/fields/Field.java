package entity.fields;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

/**
 * All methods of the entire field hierarchy are explained here in the field class' java doc.
 * Therefore only the constructors of each class are explained in their own java doc.
 * @author Christian
 *
 */

public abstract class Field {

	protected String name;
	protected int ID;
	private static int nextID = 0;

	/**
	 * Constructor for the field class, because the class is abstract, it's only used as a super constructor.
	 * It gives each field an ID.
	 * @param name
	 */
	public Field(String name){
		this.name = name;
		this.ID = nextID++;
	}

	/**
	 * Returns the name of the field.
	 * @return name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Returns the ID of the field.
	 * @return ID
	 */
	public int getID(){
		return this.ID;
	}

	/**
	 * Method that will execute what has to happen to the player that lands on the field.
	 * @param player
	 * @param roll
	 * @param gameBoard
	 * @param playerList
	 * @param taxChoice
	 * @return
	 */
	public abstract void landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice);

	/**
	 * Returns the bonus of the field.
	 * @return bonus
	 */
	public int getBonus() {
		return -1;
	}

	/**
	 * Returns the tax amount of the field.
	 * @return taxAmount
	 */
	public int getTaxAmount() {
		return -1;
	}

	/**
	 * Returns the number of the card that is in the top of the card stack.
	 * @return topCardNumber
	 */
	public int getTopCardNumber() {
		return -1;
	}

	/**
	 * Setter used for unittesting landOnField method
	 * @param number
	 */
	public void setTopCardNumber(int number){}

	/**
	 * Returns the tax rate of the field.
	 * @return taxRate
	 */
	public int getTaxRate() {
		return -1;
	}

	/**
	 * Returns the price of the field.
	 * @return price
	 */
	public int getPrice() {
		return -1;
	}

	/**
	 * Returns the owner of the field.
	 * @return owner
	 */
	public Player getOwner() {
		return null;
	}

	/**
	 * Sets the owner of the field.
	 * @param newOwner
	 */
	public void setOwner(Player newOwner) {}

	/**
	 * Returns the pawn value of the field.
	 * @return pawnValue
	 */
	public int getPawnValue() {
		return -1;
	}

	/**
	 * Returns whether the field is pawned or not.
	 * @return isPawned
	 */
	public boolean isPawned() {
		return false;
	}

	/**
	 * Resets isPawned to false(used when a pawned field it returned to the bank on bankruptcy).
	 */
	public void releasePawnField() {}

	/**
	 * Makes you able to buy an ownable field. Takes the player buying the field as input.
	 * @param player
	 * @return if the action was carried out
	 */
	public boolean buyField(Player player) {
		return false;
	}

	/**
	 * Makes you able to buy an ownable field at a specific price. Takes the player buying the field and the price as input.
	 * (used when a field is sold on auction)
	 * @param player
	 * @return if the action was carried out
	 */
	public boolean buyField(Player player, int price) {
		return false;
	}

	/**
	 * Makes a trade between two players, the parameter names should be self-explanatory.
	 * @param seller
	 * @param buyer
	 * @param price
	 * @return if the action was carried out
	 */
	public boolean tradeField(Player seller, Player buyer, int price) {
		return false;
	}

	/**
	 * Pawns the field(including money transactions).
	 * @return if the action was carried out
	 */
	public boolean pawnField() {
		return false;
	}

	/**
	 * Undoes the pawn of the field(including money transactions).
	 * @return if the action was carried out
	 */
	public boolean undoPawnField() {
		return false;
	}

	/**
	 * Returns the rent array of a plot field(used for setting up the rent list in the GUI).
	 * @return rent
	 */
	public int[] getRentArray() {
		return null;
	}

	/**
	 * Returns rent of the field to be used when executing landOnField method.
	 * @return calculated rent
	 */
	public int getRent(GameBoard gameBoard, int roll) {
		return -1;
	}

	/**
	 * Returns the price of constructing on the field.
	 * @return constructionPrice
	 */
	public int getConstructionPrice() {
		return -1;
	}

	/**
	 * Returns the property group number of the field.
	 * @return propertyGroup
	 */
	public int getPropertyGroup() {
		return -1;
	}

	/**
	 * Returns the construction rate of the field.
	 * @return constructionRate
	 */
	public int getConstructionRate() {
		return -1;
	}

	/**
	 * Sets the construction rate of the field
	 * @param rate
	 */
	public void setConstructionRate(int rate) {}

	/**
	 * Raises the construction rate by 1 and performs money transaction.
	 * @return if the action was carried out
	 */
	public boolean buildConstruction() {
		return false;
	}

	/**
	 * Lowers the construction rate by 1 and performs money transaction.
	 * @return if the action was carried out
	 */
	public boolean sellConstruction() {
		return false;
	}
}
