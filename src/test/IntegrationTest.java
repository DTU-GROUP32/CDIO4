package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.GameBoard;
import entity.Player;

public class IntegrationTest {
	
	Player p1;
	Player p2;
	GameControllerTestStub testStub;
	GameBoard gameBoard;
	
	@Before
	public void setUp() throws Exception {
		p1 = new Player("p1", 30000);
		p2 = new Player("p2", 30000);
		testStub = new GameControllerTestStub();
		gameBoard = new GameBoard();
	}

	@After
	public void tearDown() throws Exception {
		p1 = null;
		p2 = null;
		testStub = null;
		gameBoard = null;
	}

	/**
	 * This test-case tests if setting the player to land on a specific non-ownable field,
	 * will correctly effect the balance of that players BankAccount.
	 * Player 1 i set to land on field 6, which is a +500 refuge, and field 18 with the choice to pay 10%,
	 * which is a -4000/10% tax with a choice.
	 * Player 2 is set to land on field 10, which is a -2000 tax, and field 18 with the choice to pay 4000,
	 * which is a -4000/10% tax with a choice.
	 */
	@Test
	public void notOwnableTest() {
		int p1Expected = 30000 + 500;
		int p2Expected = 30000 - 2000;
		setPlayer(p1, 6, false);
		setPlayer(p2, 10, false);
		testStub.playTurnTest(gameBoard, p1, true);
		testStub.playTurnTest(gameBoard, p2, true);
		int p1Actual = p1.getBankAccount().getBalance();
		int p2Actual = p2.getBankAccount().getBalance();
		assertEquals(p1Expected, p1Actual);
		assertEquals(p2Expected, p2Actual);
		p1Expected = (30000 + 500) * 9 / 10;
		p2Expected = (30000 - 2000) - 4000;
		setPlayer(p1, 18, true);
		setPlayer(p2, 18, false);
		testStub.playTurnTest(gameBoard, p1, true);
		testStub.playTurnTest(gameBoard, p2, true);
		p1Actual = p1.getBankAccount().getBalance();
		p2Actual = p2.getBankAccount().getBalance();
		assertEquals(p1Expected, p1Actual);
		assertEquals(p2Expected, p2Actual);
	}

	/**
	 * This test-case tests if landing on an ownable field which is bought,
	 * will correctly effect the balance of that players BankAccount.
	 * Player 1 is set to land on field 1, which is a 1000/100 territory, and buy it,
	 * and after that set to land on field 3, which is a 1500/300 territory, that is owned by player 2.
	 * Player 2 is set to land on field 3, which is a 1500/300 territory, and buy it,
	 * and after that set to land on field 1, which is a 1000/100 territory, that is owned by player 2.
	 */
	@Test
	public void ownableOwnedTest() {
		int p1Expected = 30000 - 1000 - 300 + 100;
		int p2Expected = 30000 - 1500 + 300 - 100;
		setPlayer(p1, 1, false);
		setPlayer(p2, 3, false);
		testStub.playTurnTest(gameBoard, p1, true);
		testStub.playTurnTest(gameBoard, p2, true);
		setPlayer(p1, 3, false);
		setPlayer(p2, 1, false);
		testStub.playTurnTest(gameBoard, p1, true);
		testStub.playTurnTest(gameBoard, p2, true);
		int p1Actual = p1.getBankAccount().getBalance();
		int p2Actual = p2.getBankAccount().getBalance();
		assertEquals(p1Expected, p1Actual);
		assertEquals(p2Expected, p2Actual);
	}
	
	/**
	 * This test-case tests if there by default is no owner of an ownable field, and then if setting a player to land
	 * on the field and saying that he wants to buy it, will result in him being the owner of that field.
	 * Player 1 is set to land on field 1  and buy it.
	 * Player 2 is set to land on field 3, and buy it.
	 */
	@Test
	public void OwnableNotOwnedBuyTest() {
		Player f1ownerExpected = null;
		Player f3ownerExpected = null;
		Player f1ownerActual = gameBoard.getField(1).getOwner();
		Player f3ownerActual = gameBoard.getField(3).getOwner();
		assertEquals(f1ownerExpected, f1ownerActual);
		assertEquals(f3ownerExpected, f3ownerActual);
		setPlayer(p1, 1, false);
		setPlayer(p2, 3, false);
		testStub.playTurnTest(gameBoard, p1, true);
		testStub.playTurnTest(gameBoard, p2, true);
		f1ownerExpected = p1;
		f3ownerExpected = p2;
		f1ownerActual = gameBoard.getField(1).getOwner();
		f3ownerActual = gameBoard.getField(3).getOwner();
		assertEquals(f1ownerExpected, f1ownerActual);
		assertEquals(f3ownerExpected, f3ownerActual);
	}
	
	/**
	 * This test-case tests if there by default is no owner of an ownable field, and then if setting a player to land
	 * on the field and saying that he doesn't want to buy it, will result in the there still being no owner.
	 * Player 1 is set to land on field 1, and not buy it.
	 * Player 2 is set to land on field 3, and not buy it.
	 */
	@Test
	public void OwnableNotOwnedDontBuyTest() {
		Player f1ownerExpected = null;
		Player f3ownerExpected = null;
		Player f1ownerActual = gameBoard.getField(1).getOwner();
		Player f3ownerActual = gameBoard.getField(3).getOwner();
		assertEquals(f1ownerExpected, f1ownerActual);
		assertEquals(f3ownerExpected, f3ownerActual);
		setPlayer(p1, 1, false);
		setPlayer(p2, 3, false);
		testStub.playTurnTest(gameBoard, p1, false);
		testStub.playTurnTest(gameBoard, p2, false);
		f1ownerActual = gameBoard.getField(1).getOwner();
		f3ownerActual = gameBoard.getField(3).getOwner();
		assertEquals(f1ownerExpected, f1ownerActual);
		assertEquals(f3ownerExpected, f3ownerActual);
	}
	
	/**
	 * Method to make it easier to set the players values.
	 * @param player - the player to be changed.
	 * @param onField - field to set him on.
	 * @param taxChoice - how he wants to get taxed, true = percentage, false = fixed rate.
	 */
	private void setPlayer(Player player, int onField, boolean taxChoice) {
		player.setOnField(onField);
		player.setTaxChoice(taxChoice);
	}
}
