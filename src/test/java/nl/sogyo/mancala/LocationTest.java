package nl.sogyo.mancala;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {

	@Test
	public void doMoveOnNewBoardShouldAddOneToLocationOne() {
		Field field = new Field();
		field.doMove();
		assertEquals(5, field.getNextLocation().getNextLocation().getStones());
	}

	@Test
	public void doMoveOnNewBoardShouldAddOneToLocationFour() {
		Field field = new Field();
		field.doMove();
		Location fieldFour = field.getNextLocation().getNextLocation().getNextLocation().getNextLocation();
		assertEquals(5, fieldFour.getStones());
	}

	@Test
	public void doMoveOnNewBoardShouldAddOneToLocationFourUsingGetNthFieldMethod() {
		Field field = new Field();
		field.doMove();
		Location fieldFour = field.getNthLocationRelative(4);
		assertEquals(5, fieldFour.getStones());
	}

	@Test
	public void doMoveOnNewBoardShouldAddZeroToLocationFive() {
		Field field = new Field();
		field.doMove();
		Location fieldFive = field.getNextLocation().getNextLocation().getNextLocation().getNextLocation()
				.getNextLocation();
		assertEquals(4, fieldFive.getStones());
	}

	@Test
	public void locationFourteenShouldEqualLocationZero() {
		Field field = new Field();
		Location fieldFourteen = field.getNextLocation().getNextLocation().getNextLocation().getNextLocation()
				.getNextLocation().getNextLocation().getNextLocation().getNextLocation().getNextLocation()
				.getNextLocation().getNextLocation().getNextLocation().getNextLocation().getNextLocation();
		assertEquals(fieldFourteen, field);
	}

	@Test
	public void locationFourteenShouldEqualLocationZeroUsingGetNthFieldMethod() {
		Field field = new Field();
		Location fieldFourteen = field.getNthLocationRelative(14);
		assertEquals(fieldFourteen, field);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getNthLocationRelativeShouldThrowErrorIfNIsNegative() {
		Field field = new Field();
		field.getNthLocationRelative(-1);
	}

	@Test
	public void locationZeroShouldBeOppositeLocationTwelve() {
		Field field = new Field();
		Location opposite = field.getOpposite();
		Location fieldThirteen = field.getNthLocationRelative(12);
		assertEquals(fieldThirteen, opposite);
	}

	@Test
	public void locationFiveShouldBeOppositeLocationSeven() {
		Location fieldFive = (new Field()).getNthLocationRelative(5);
		Location opposite = fieldFive.getOpposite();
		Location fieldSeven = fieldFive.getNthLocationRelative(2);
		assertEquals(fieldSeven, opposite);
	}

	@Test
	public void locationTwelveShouldBeOppositeLocationZero() {
		Field field = new Field();
		Location fieldThirteen = field.getNthLocationRelative(12);
		Location opposite = fieldThirteen.getOpposite();
		assertEquals(field, opposite);
	}

	@Test
	public void locationSevenShouldBeOppositeLocationFive() {
		Location fieldFive = (new Field()).getNthLocationRelative(5);
		Location fieldSeven = fieldFive.getNthLocationRelative(2);
		Location opposite = fieldSeven.getOpposite();
		assertEquals(fieldFive, opposite);
	}

	@Test
	public void locationSixShouldBeKalaha() {
		Location field = new Field();
		Location locSix = field.getNthLocationRelative(6);
		assertTrue(locSix instanceof Kalaha);
	}

	@Test
	public void locationThirteenShouldBeKalaha() {
		Location field = new Field();
		Location locThirteen = field.getNthLocationRelative(13);
		assertTrue(locThirteen instanceof Kalaha);
	}

	@Test
	public void ownerOfLocationShouldChangeAfterKalaha() {
		Location field = new Field();
		Location fieldAfterKalaha = field.getNextKalaha().getNextLocation();
		assertFalse(field.getPlayer() == fieldAfterKalaha.getPlayer());
	}

	@Test
	public void ownerOfLocationShouldSwitchToOpponentAfterKalaha() {
		Location field = new Field();
		Location fieldAfterKalaha = field.getNextKalaha().getNextLocation();
		assertTrue(field.getPlayer().getOpponent() == fieldAfterKalaha.getPlayer());
	}
}
