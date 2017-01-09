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
	public abstract int getBonus();

	/**
	 * Returns the tax amount of the field.
	 * @return taxAmount
	 */
	public abstract int getTaxAmount();

	/**
	 * Returns the tax rate of the field.
	 * @return taxRate
	 */
	public abstract int getTaxRate();

	/**
	 * Returns the number of the card that is in the top of the card stack.
	 * @return topCardNumber
	 */
	public abstract int getTopCardNumber();

	/**
	 * Returns the price of the field.
	 * @return price
	 */
	public abstract int getPrice();

	/**
	 * Returns the owner of the field.
	 * @return owner
	 */
	public abstract Player getOwner();

	/**
	 * Sets the owner of the field.
	 * @param newOwner
	 */
	public abstract void setOwner(Player newOwner);

	/**
	 * Returns the pawn value of the field.
	 * @return pawnValue
	 */
	public abstract int getPawnValue();

	/**
	 * Returns whether the field is pawned or not.
	 * @return isPawned
	 */
	public abstract boolean isPawned();

	/**
	 * Resets isPawned to false(used when a pawned field it returned to the bank on bankruptcy).
	 */
	public abstract void releasePawnField();

	/**
	 * Makes you able to buy an ownable field. Takes the player buying the field as input.
	 * @param player
	 * @return if the action was carried out
	 */
	public abstract boolean buyField(Player player);

	/**
	 * Makes you able to buy an ownable field at a specific price. Takes the player buying the field and the price as input.
	 * (used when a field is sold on auction)
	 * @param player
	 * @return if the action was carried out
	 */
	public abstract boolean buyField(Player player, int price);

	/**
	 * Makes a trade between two players, the parameter names should be self-explanatory.
	 * @param seller
	 * @param buyer
	 * @param price
	 * @return if the action was carried out
	 */
	public abstract boolean tradeField(Player seller, Player buyer, int price);

	/**
	 * Pawns the field(including money transactions).
	 * @return if the action was carried out
	 */
	public abstract boolean pawnField();

	/**
	 * Undoes the pawn of the field(including money transactions).
	 * @return if the action was carried out
	 */
	public abstract boolean undoPawnField();

	/**
	 * Returns the rent array of a plot field(used for setting up the rent list in the GUI).
	 * @return rent
	 */
	public abstract int[] getRentArray();

	/**
	 * Returns rent of the field to be used when executing landOnField method.
	 * @return calculated rent
	 */
	public abstract int getRent(GameBoard gameBoard, int roll);

	/**
	 * Returns the price of constructing on the field.
	 * @return constructionPrice
	 */
	public abstract int getConstructionPrice();

	/**
	 * Returns the property group number of the field.
	 * @return propertyGroup
	 */
	public abstract int getPropertyGroup();

	/**
	 * Returns the construction rate of the field.
	 * @return constructionRate
	 */
	public abstract int getConstructionRate();

	/**
	 * Sets the construction rate of the field
	 * @param rate
	 */
	public abstract void setConstructionRate(int rate);

	/**
	 * Raises the construction rate by 1 and performs money transaction.
	 * @return if the action was carried out
	 */
	public abstract boolean buildConstruction();

	/**
	 * Lowers the construction rate by 1 and performs money transaction.
	 * @return if the action was carried out
	 */
	public abstract boolean sellConstruction();
}
