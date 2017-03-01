package nl.sogyo.mancala;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getStonesFromFreshFieldShouldEqualFour() {
		Field field = new Field();
		assertEquals(4, field.getStones());
	}

	@Test
	public void doMoveShouldEmptyThisField() {
		Field field = new Field();
		field.doMove();
		assertEquals(0, field.getStones());
	}

	@Test
	public void doMoveShouldAddOneToNextField() {
		Field field = new Field();
		field.doMove();
		assertEquals(5, field.getNextLocation().getStones());
	}

	@Test
	public void newFieldShouldBePlayable() {
		Field field = new Field();
		assertTrue(field.isPlayable());
	}

	@Test
	public void takeStonesOnNewFieldShouldReturnFour() {
		Field field = new Field();
		int stonesTaken = field.takeStones();
		assertEquals(4, stonesTaken);
	}

	@Test
	public void takeStonesShouldSetStonesToZero() {
		Field field = new Field();
		field.takeStones();
		assertEquals(0, field.getStones());
	}
}
