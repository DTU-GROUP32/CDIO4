package entity;

public class DiceCup {
	
	private Dice[] dices;

	/**
	 * Default constructor
	 */
	public DiceCup() {
		this(2);
	}

	/**
	 * Secondary constructor, takes diceCount for constructing dice cup with custom amount of dices.
	 * @param diceCount Amount of die in cup
	 */
	public DiceCup(int diceCount) {
		dices = new Dice[diceCount];
		for(int i = 0; i < diceCount; i++) {
			this.dices[i] = new Dice();
		}
	}

	/**
	 * Gets the array of dices
	 * @return Dice dices
	 */
	public Dice[] getDices() {	
		return this.dices;
	}

	/**
	 * Rolls all of the dices, which sets them to a random number between 1 and the number of sides it has.
	 */
	public void rollDices() {
		for(int i = 0; i < dices.length; i++) {
			dices[i].roll();
		}
	}

	/**
	 * Gets the sum of the dices
	 * @return sum
	 */
	public int getSum() {
		int sum = 0;
		for(int i = 0; i < dices.length; i++) {
			sum += dices[i].getFaceValue();
		}
		return sum;
	}

	/**
	 * Sets the sides of the first dice to 'a' and the sides of the second dice to 'b',
	 * if the sum of 'a' and 'b' is 12, and returns true if the sides were changed, otherwise false.
	 * @param a int - sides on dice at index 0.
	 * @param b int - sides on dice at index 1.
	 * @return change valid and performed returned as boolean true or false
	 */
	public boolean setDiceSides(int a, int b) {
		if (a+b == 12) {
			dices[0].setSides(a);
			dices[1].setSides(b);
			return true;
		} else
			return false;
	}
}
