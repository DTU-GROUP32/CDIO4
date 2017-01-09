package entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player testPlayer;
    DiceCup diceCup;
    @Before
    public void setUp() throws Exception {
        testPlayer = new Player("Test");
        diceCup = new DiceCup(2);
    }

    @After
    public void tearDown() throws Exception {
        testPlayer = null;
        diceCup = null;
    }

    /**
     * Method tests that player can move around the gameboard and each time crossing start is granted 4000 bonus.
     * @throws Exception
     */
    @Test
    public void movePlayer() throws Exception {
        for(int i = 0; i < 100; i++){
            diceCup.rollDices();
            int roll = diceCup.getSum();
            int expectedOnField = testPlayer.getOnField()+roll;
            int expectedBalance = testPlayer.getBankAccount().getBalance();
            if(roll + testPlayer.getOnField() > 39){
                expectedOnField -= 40;
                expectedBalance += 4000;
            }
            testPlayer.movePlayer(roll);
            // Assert player is on expected field
            assertEquals(testPlayer.getOnField(), expectedOnField);
            // Assert player balance corresponds with start-bonuses gained.
            assertEquals(testPlayer.getBankAccount().getBalance(), expectedBalance);
        }
    }
}