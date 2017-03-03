package nl.sogyo.mancala.AI;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import nl.sogyo.mancala.backend.Mancala;

public class AITest {

	@Test
	public void preScoringStateShouldEqualPostScoringState() {
		Mancala mancala = new Mancala();
		AI ai = new AI(mancala);
		ArrayList<Integer> startingState = ai.getMancala().getStoneAmounts();
		ai.getBestMove();
		ArrayList<Integer> endingState = ai.getMancala().getStoneAmounts();
		assertArrayEquals(startingState.toArray(new Integer[1]), endingState.toArray(new Integer[1]));
	}

	@Test
	public void preScoringTurnShouldEqualPostScoringTurn() {
		Mancala mancala = new Mancala();
		AI ai = new AI(mancala);
		int startingTurn = ai.getMancala().getCurrentTurn();
		ai.getBestMove();
		int endingTurn = ai.getMancala().getCurrentTurn();
		assertEquals(startingTurn, endingTurn);
	}

	@Test
	public void getBestMoveShouldReturnBetweenOneAndSixInclusive() {
		Mancala mancala = new Mancala();
		AI ai = new AI(mancala);
		int move = ai.getBestMove();
		assertTrue(1 <= move);
		assertTrue(6 >= move);
	}

}
