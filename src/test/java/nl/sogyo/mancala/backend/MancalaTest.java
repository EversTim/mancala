package nl.sogyo.mancala.backend;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MancalaTest {

	@Test
	public void doMoveOnFirstFieldShouldEmptyFirstField() {
		Mancala mancala = new Mancala();
		mancala.doMove(0);
		assertEquals(0, mancala.getField().getStones());
	}

	@Test
	public void getStoneAmountsOnFieldShouldReturnEnclosedArray() {
		Integer[] expected = { 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0 };
		Mancala mancala = new Mancala();
		ArrayList<Integer> stones = mancala.getStoneAmounts();
		Integer[] actual = mancala.getStoneAmounts().toArray(new Integer[stones.size()]);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void getStoneAmountsOnFieldSixShouldReturnEnclosedArray() {
		Integer[] expected = { 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0 };
		Mancala mancala = new Mancala(6);
		ArrayList<Integer> stones = mancala.getStoneAmounts();
		Integer[] actual = mancala.getStoneAmounts().toArray(new Integer[stones.size()]);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void newBoardShouldNotHaveWinner() {
		Mancala mancala = new Mancala();
		assertFalse(mancala.hasWinner());
	}
}
