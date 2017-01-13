package entity.fields;

import entity.GameBoard;
import entity.Player;
import entity.PlayerList;
import entity.language.Language;
import entity.language.LanguageHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChanceTest {
    Player player1, player2;
    GameBoard gameBoard;
    PlayerList playerList;
    Field chanceFieldTest;
    @Before
    public void setUp() throws Exception {
        chanceFieldTest = new Chance("TestField");
        gameBoard = new GameBoard(LanguageHandler.getInstance());
        playerList = new PlayerList(2);
        playerList.addPlayer(0, "Test1");
        playerList.addPlayer(1, "Test2");
        player1 = playerList.getPlayer(0);
        player2 = playerList.getPlayer(1);
    }

    @After
    public void tearDown() throws Exception {
        player1 = null;
        player2 = null;
        gameBoard = null;
        playerList = null;
    }

    @Test
    public void landOnField() throws Exception {
        for(int i = 1; i <= 27; i++){
            int expected;
            int onFieldBefore = player1.getOnField();
            chanceFieldTest.setTopCardNumber(i);
            int roll = (int)((Math.random()*6 + 1)+(Math.random()*6 + 1));
            switch (i){
                case 1:
                    expected = player1.getBankAccount().getBalance()+1000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 2:
                    expected = player1.getBankAccount().getBalance()+1000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 3:
                    expected = player1.getBankAccount().getBalance()+1000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 4:
                    expected = player1.getBankAccount().getBalance()+200;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 5:
                    expected = player1.getBankAccount().getBalance();
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 6:
                    expected = player1.getBankAccount().getBalance()+3000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 7:
                    expected = player1.getBankAccount().getBalance()+200;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 8:
                    expected = player1.getBankAccount().getBalance()+1000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 9:
                    expected = player1.getBankAccount().getBalance()+500;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 10:
                    expected = player1.getBankAccount().getBalance()-200;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 11:
                    expected = player1.getBankAccount().getBalance()-1000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 12:
                    expected = player1.getBankAccount().getBalance()-3000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 13:
                    expected = player1.getBankAccount().getBalance()-1000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 14:
                    expected = player1.getBankAccount().getBalance()-3000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 15:
                    expected = player1.getBankAccount().getBalance()-2000;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 16:
                    expected = player1.getBankAccount().getBalance()-200;
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 17:
                    expected = player1.getBankAccount().getBalance();
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 18:
                    expected = player1.getBankAccount().getBalance();
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", expected, player1.getBankAccount().getBalance());
                    break;
                case 19:
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", 1, player1.getGetOutOfJailCardCount());
                    break;
                case 20:
                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
                    assertEquals("Chance Card "+i+" failed", 10, player1.getOnField());
                    assertTrue(player1.isPlayerInJail());
                    break;
//                case 21:
//                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
//                    assertEquals("Chance Card "+i+" failed", 1, 1);
//                    break;
//                case 22:
//                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
//                    assertEquals("Chance Card "+i+" failed", 1, 1);
//                    break;
//                case 23:
//                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
//                    assertEquals("Chance Card "+i+" failed", 24, player1.getOnField());
//                    break;
//                case 24:
//                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
//                    assertEquals("Chance Card "+i+" failed", 0, player1.getOnField());
//                    break;
//                case 25:
//                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
//                    assertEquals("Chance Card "+i+" failed", 11, player1.getOnField());
//                    break;
//                case 26:
//                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
//                    assertEquals("Chance Card "+i+" failed", 39, player1.getOnField());
//                    break;
//                case 27:
//                    chanceFieldTest.landOnField(player1, roll, gameBoard, playerList, false);
//                    assertEquals(1, 1);
//                    break;
                default:
                    assertEquals(1, 1);
                    break;
            }
        }
    }

    @Test
    public void getTopCardNumber() throws Exception {
    }

}