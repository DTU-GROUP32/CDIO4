package entity.fields;

import entity.GameBoard;

public class Plot extends Ownable {

	private int[] rent;
	private int constructionPrice;
	private int propertyGroup;
	private int constructionRate;

	/**
	 * Constructor for the plot field. Takes a name, a price, a rent array, a construction price and a property group number.
	 * By default the construction rate is set to O.
	 * @param name
	 * @param price
	 * @param rent
	 * @param constructionPrice
	 * @param propertyGroup
	 */
	public Plot(String name, int price, int[] rent, int constructionPrice, int propertyGroup){
		super(name, price);
		this.rent = rent;
		this.constructionPrice = constructionPrice;
		this.propertyGroup = propertyGroup;
		this.constructionRate = 0;
	}

	@Override
	public int[] getRentArray() {
		return this.rent;
	}
	
	@Override
	public int getConstructionPrice() {
		return this.constructionPrice;
	}

	@Override
	public int getPropertyGroup() {
		return this.propertyGroup;
	}

	@Override
	public int getConstructionRate() {
		return this.constructionRate;
	}

	@Override
	public void setConstructionRate(int rate) {
		this.constructionRate = rate;
	}
	
	@Override
	public int getRent(GameBoard gameBoard, int roll){
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
		constructionRate--;
		return true;
	}
	
	// all methods under this line are default methods

	@Override
	public int getBonus() {
		return 0;
	}

	@Override
	public int getTaxAmount() {
		return 0;
	}

	@Override
	public int getTaxRate() {
		return 0;
	}

	@Override
	public int getTopCardNumber() {
		return 0;
	}
}
