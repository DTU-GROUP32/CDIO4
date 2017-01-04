package entity.fields;

import entity.GameBoard;

public class Plot extends Ownable {

	private int[] rent;
	private int constructionPrice;
	private int propertyGroup;
	private int constructionRate;

	/**
	 * Constructor. Territory has a price and rent
	 * @param price
	 * @param rent
	 */
	public Plot(String name, int price, int[] rent, int constructionPrice, int propertyGroup){
		super(name, price);
		this.rent = rent;
		this.constructionPrice = constructionPrice;
		this.propertyGroup = propertyGroup;
		this.constructionRate = 0;
	}

	@Override
	public int getRent(GameBoard gameBoard){
		if (gameBoard.evalPropertyGroupSameOwner(gameBoard.getPropertyGroup(this.propertyGroup)) && constructionRate == 0)
			return this.rent[constructionRate] * 2;
		return this.rent[constructionRate];
	}

	public boolean buildConstruction(){
		if(owner.getBankAccount().withdraw(constructionPrice))
		{
			constructionRate++;
			return true;
		}
		return false;
	}

	public boolean sellConstruction(){
		owner.getBankAccount().deposit(constructionPrice / 2);
		return true;
	}

	/**
	 * @return the constructionRate
	 */
	public int getConstructionRate() {
		return constructionRate;
	}

	/**
	 * @return the constructionPrice
	 */
	public int getConstructionPrice() {
		return constructionPrice;
	}

	/**
	 * @return the propertyGroup
	 */
	public int getPropertyGroup() {
		return propertyGroup;
	}	
}
