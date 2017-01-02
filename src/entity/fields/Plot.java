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
		// numbers of fields in group
		int propertyGroup = this.getPropertyGroup();
		int fieldsInGroup = 0;
		for(Field field : gameBoard.getFields())
		{
			if(field.getPropertyGroup() == propertyGroup)
				fieldsInGroup++;
		}

		// making property group
		Field[] fieldGroup = new Field[fieldsInGroup];
		int arrayIndex = 0;
		for(int i = 0; i < gameBoard.getFields().length; i++)
		{
			if(gameBoard.getField(i).getPropertyGroup() == propertyGroup)
				fieldGroup[arrayIndex] = gameBoard.getField(i);
			arrayIndex++;
		}

		// evaluating
		boolean ownedBySame = false;
		if(fieldGroup.length == 2)
			ownedBySame = fieldGroup[0].getOwner() == fieldGroup[1].getOwner();
		else ownedBySame = fieldGroup[0].getOwner() == fieldGroup[1].getOwner() && fieldGroup[0].getOwner() == fieldGroup[2].getOwner();

		//returning
		if (ownedBySame && constructionRate == 0)
			return rent[constructionRate] * 2;
		return rent[constructionRate];
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
