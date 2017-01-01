package entity.fields;

public class Territory extends Ownable {

	private int rent;

	/**
	 * Constructor. Territory has a price and rent
	 * @param price
	 * @param rent
	 */
	public Territory(int price, int rent) {
		super(price);
		this.rent = rent;
	}

	@Override
	public int getRent(){
		return rent;
	}
}
