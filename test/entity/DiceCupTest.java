package entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceCupTest {
    DiceCup diceCup;
    @Before
    public void setUp() throws Exception {
        diceCup = new DiceCup(2);
    }

    @After
    public void tearDown() throws Exception {
        diceCup = null;
    }

    @Test
    public void getDices() throws Exception {
        assertTrue(diceCup.getDices() instanceof Dice[]);
    }

    @Test
    public void rollDices() throws Exception {
        for(int i = 0; i < 100; i++){

        }
    }

    @Test
    public void getSum() throws Exception {
    }

    @Test
    public void setDiceSides() throws Exception {
    }

    @Test
    public void diceEvalEqual() throws Exception {
    }

}