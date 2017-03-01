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
}
