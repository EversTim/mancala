package nl.sogyo.mancala;

import static org.junit.Assert.*;

import org.junit.Test;

public class KalahaTest {

	@Test
	public void kalahaIsNeverPlayable() {
		Kalaha k = new Kalaha();
		assertFalse(k.isPlayable());
	}

	@Test
	public void kalahaIsItsOwnOpposite() {
		Kalaha k = new Kalaha();
		Location opp = k.getOpposite();
		assertEquals(k, opp);
	}

	@Test
	public void opponentKalahaShouldNotTakeAStone() {
		Field field = new Field();
		field = (Field) field.getNthLocationRelative(12);
		field.doMove();
		assertEquals(0, field.getNextLocation().getStones());
	}

	@Test
	public void ownKalahaShouldTakeAStone() {
		Field field = new Field();
		field = (Field) field.getNthLocationRelative(5);
		field.doMove();
		assertEquals(1, field.getNextLocation().getStones());
	}

	@Test
	public void doMoveOnTakeStoneBoardShouldHaveFiveInKalaha() {
		Field field = Field.getTakeStonesTestSetup();
		field.doMove();
		Kalaha kal = field.getNextKalaha();
		assertEquals(5, kal.getStones());
	}
}
