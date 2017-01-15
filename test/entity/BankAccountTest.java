package entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTest {
    Player testPlayer1, testPlayer2;
    @Before
    public void setUp() throws Exception {
        testPlayer1 = new Player("test1");
        testPlayer2 = new Player("test2");
    }

    @After
    public void tearDown() throws Exception {
        testPlayer1 = null;
        testPlayer2 = null;
    }

    @Test
    public void withdraw() throws Exception {
        int balanceBefore;
        for(int i = 0; i < 100; i++){
            balanceBefore = testPlayer1.getBankAccount().getBalance();
            if(testPlayer1.getBankAccount().withdraw(i*20)){
                assertEquals(balanceBefore-i*20, testPlayer1.getBankAccount().getBalance());
            }else{
                assertEquals(balanceBefore, testPlayer1.getBankAccount().getBalance());
            }
        }
    }

    @Test
    public void deposit() throws Exception {
        int balanceBefore;
        for(int i = 0; i < 100; i++){
            balanceBefore = testPlayer1.getBankAccount().getBalance();
            testPlayer1.getBankAccount().deposit(i*20);
            assertEquals(balanceBefore+i*20, testPlayer1.getBankAccount().getBalance());
        }
    }

    @Test
    public void transfer() throws Exception {
        int player1BalanceBefore;
        int player2BalanceBefore;
        for(int i = 0; i < 100; i++){
            player1BalanceBefore = testPlayer1.getBankAccount().getBalance();
            player2BalanceBefore = testPlayer2.getBankAccount().getBalance();
            if(testPlayer1.getBankAccount().transfer(testPlayer2, i*20)){
                assertEquals(player1BalanceBefore-i*20, testPlayer1.getBankAccount().getBalance());
                assertEquals(player2BalanceBefore+i*20, testPlayer2.getBankAccount().getBalance());
            }else{
                assertEquals(player1BalanceBefore, testPlayer1.getBankAccount().getBalance());
                assertEquals(player2BalanceBefore, testPlayer2.getBankAccount().getBalance());
            }
        }
    }

}