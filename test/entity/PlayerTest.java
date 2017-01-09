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

    @Test
    public void movePlayer() throws Exception {
        for(int i = 0; i < 100; i++){
            diceCup.rollDices();
            int roll = diceCup.getSum();
            int expected = testPlayer.getOnField()+roll;
            if(roll + testPlayer.getOnField() > 39){
                expected -= 40;
            }
            testPlayer.movePlayer(roll);
            assertEquals(testPlayer.getOnField(), expected);
        }
    }
}