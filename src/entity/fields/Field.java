package entity.fields;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;

public abstract class Field {

	protected String name;
	protected int ID;
	private static int nextID = 0;
	
	public Field(String name){
		this.name = name;
		this.ID = nextID++;
	}
	
	public String getName(){
		return this.name;
	}

	public int getID(){ return this.ID; }
	
	/**
	 * Returns the price of the field
	 * @return price
	 */
	public abstract int getPrice();

	/**
	 * Returns the owner of the field
	 * @return owner
	 */
	public abstract Player getOwner();

	/**
	 * Sets the owner of the field
	 * @param newOwner
	 */
	public abstract void setOwner(Player newOwner);

	/**
	 * Method that will evaluate what is going to happen to the player that lands on the field
	 * @param player
	 */
	public abstract boolean landOnField(Player player, int roll, GameBoard gameBoard, PlayerList playerList, boolean taxChoice);

	/**
	 * Returns rent of the field
	 * @return rent
	 */
	public abstract int getRent(GameBoard gameBoard);

	/**
	 * Makes you able to buy an ownable field. Will set you to the owner if you want to buy it for certain price
	 * @param player
	 */
	public abstract boolean buyField(Player player);
	
	public abstract boolean buyField(Player player, int price);
	
	public abstract boolean tradeField(Player seller, Player buyer, int price);
	
	public abstract boolean pawnField();
	
	public abstract boolean releasePawnedField();
	
	public abstract boolean buildConstruction();
	
	public abstract boolean sellConstruction();
	
	public abstract int getConstructionRate();

	public abstract int getConstructionPrice();

	public abstract int getPawnValue();

	public abstract int getPropertyGroup();
}
