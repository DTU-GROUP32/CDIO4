package entity;

public class DiceCupAutoTest extends DiceCup {

	int index;
	int faceValue1;
	int faceValue2;
	
	public DiceCupAutoTest(int index) {
		super();
		this.index = index;
	}
	
	@Override
	public void rollDices()	{
		
		int[][] data = {{5,1}, // Spiller 1, Roskildevej, køb
						{2,6}, // Spiller 2, Valby Langgade, køb
						{3,6}, // Spiller 3, Allegade, køb
						{6,3}, // Spiller 1, Kalund/Århus
						{1,1}, // Spiller 2, slår 3x2 ens
						{2,2},
						{2,2},
						{6,3}, // Spiller 3, fødselsdag
						{5,2}, // Spiller 1, Køb af rederi
						{2,5}, // Spiller 2, betal sig ud, og slå - land på rederi, dobbelt leje						
						{1,2},	// Spiller 3, Handle, sælge, grund til spiller 1, hvor spiller 1 ikke har penge nok og pantsætter sin grund for at købe den..
						{1,3},	// Spiller 1, sælger sine grunde til spiller 2 billigt
						{1,2}	// Spiller 2, ejer nu alle grundene, hæver pantsætning og bygger på grunde.
						}; 
						
		faceValue1 = data[index][0];
		faceValue2 = data[index][1];
		index = ++index % data.length;
		
		//System.out.println(index);
		//int[] data = {1,2,3,4,5,6};
//		int[] data = {5,5,
//					  6,6,
//					  6,6,
//					  1,2,
//					  3,1,
//					  1,2,
//					  1,2,
//					  2,1,
//					  3,3};
//		terning1 = data[index++ % data.length];
//		terning2 = data[index++ % data.length];
	}
	
	@Override
	public boolean diceEvalEqual() {
		return faceValue1 == faceValue2;
	}
}


//	private Dice[] dices;
//
//	/**
//	 * Default constructor
//	 */
//	public DiceCupAutoTest() {
//		this(2);
//	}
//
//	/**
//	 * Secondary constructor, takes diceCount for constructing dice cup with custom amount of dices.
//	 * @param diceCount Amount of die in cup
//	 */
//	public DiceCupAutoTest(int diceCount) {
//		dices = new Dice[diceCount];
//		for(int i = 0; i < diceCount; i++) {
//			this.dices[i] = new Dice();
//		}
//	}
//
//	/**
//	 * Gets the array of dices
//	 * @return Dice dices
//	 */
//	public Dice[] getDices() {	
//		return this.dices;
//	}
//
//	/**
//	 * Rolls all of the dices, which sets them to a random number between 1 and the number of sides it has.
//	 */
//	public void rollDices() {
//		for(int i = 0; i < dices.length; i++) {
//			dices[i].roll();
//		}
//	}
//
//	/**
//	 * Gets the sum of the dices
//	 * @return sum
//	 */
//	public int getSum() {
//		int sum = 0;
//		for(int i = 0; i < dices.length; i++) {
//			sum += dices[i].getFaceValue();
//		}
//		return sum;
//	}
//
//	/**
//	 * Sets the sides of the first dice to 'a' and the sides of the second dice to 'b',
//	 * if the sum of 'a' and 'b' is 12, and returns true if the sides were changed, otherwise false.
//	 * @param a int - sides on dice at index 0.
//	 * @param b int - sides on dice at index 1.
//	 * @return change valid and performed returned as boolean true or false
//	 */
//	public boolean setDiceSides(int a, int b) {
//		if (a+b == 12) {
//			dices[0].setSides(a);
//			dices[1].setSides(b);
//			return true;
//		} else
//			return false;
//	}
//
//	/**
//	 * Checks if the dices a equal.
//	 * @return
//	 */
//	public boolean diceEvalEqual() {
//		return (this.dices[0].getFaceValue() == this.dices[1].getFaceValue());
//	}
//
//}
