package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Dice;

public class DiceTest {
	Dice dice;

	@Before
	public void setUp() throws Exception {
		dice = new Dice();
		dice.roll();
	}

	@After
	public void tearDown() throws Exception {
		dice = null;
	}

	/**
	 * Tests if the getSides method works, because if it doesn't the sides won't be changed to 5 and then
	 * the assert will evaluate to false. This test assumes that the default constructor sets the sides to 6.
	 * @throws Exception
	 */
	@Test
	public void getSides() throws Exception {
		if(dice.getSides() == 6)
			dice.setSides(5);
		assertTrue(dice.getSides() == 5);
	}


	/**
	 * This test assumes the default constructor sets the sides to 6 and that the getSides method works.
	 * In that case the setSides method will have to work for the assert to evaluate to true.
	 * @throws Exception
	 */
	@Test
	public void setSides() throws Exception {
		dice.setSides(5);
		assertTrue(dice.getSides() == 5);
	}

	
	/**
	 * The idea behind this test is to see if the getFaceValue method returns a number between 1 and 6
	 * everytime it's called.
	 * @throws Exception
	 */
	@Test
	public void getFaceValue() throws Exception {
		int testRollCount = 6000;
		int collectedValues = 0;
		int[] actual = new int[6];
		for(int i = 0; i<testRollCount; i++){
			dice.roll();
			switch(dice.getFaceValue()){
			case 1:
				actual[0]++;
				break;
			case 2:
				actual[1]++;
				break;
			case 3:
				actual[2]++;
				break;
			case 4:
				actual[3]++;
				break;
			case 5:
				actual[4]++;
				break;
			case 6:
				actual[5]++;
				break;
			}
		}
		for(int i = 0; i<actual.length; i++){
			collectedValues += actual[i];
		}
		assertTrue(collectedValues == testRollCount);
	}

	
	/**
	 * In this test we test the fairness of the dice with Pearson's Chi Squared test, which is explained
	 * in more detail in the written report for this project.
	 * What you need to know, is that if the assert evaluates to true, we can't say that the dice is unfair.
	 * @throws Exception
	 */
	@Test
	public void roll() throws Exception {
		int testRollCount = 6000;
		int expected = testRollCount/dice.getSides();
		float chiSquare = 0;
		int[] actual = new int[6];
		for(int i = 0; i<testRollCount; i++){
			dice.roll();
			switch(dice.getFaceValue()){
			case 1:
				actual[0]++;
				break;
			case 2:
				actual[1]++;
				break;
			case 3:
				actual[2]++;
				break;
			case 4:
				actual[3]++;
				break;
			case 5:
				actual[4]++;
				break;
			case 6:
				actual[5]++;
				break;
			}
		}
		
		for(int i = 0; i<actual.length; i++){
			chiSquare += Math.pow(actual[i]-expected, 2)/expected;
		}
		assertTrue(chiSquare< 11.07);
	}

}