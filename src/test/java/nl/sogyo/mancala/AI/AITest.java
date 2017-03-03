package nl.sogyo.mancala.AI;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import nl.sogyo.mancala.backend.Mancala;

public class AITest {

	@Test
	public void preScoringStateShouldEqualPostScoringState() {
		Mancala mancala = new Mancala();
		AI ai = new AI(mancala, 0);
		ArrayList<Integer> startingState = ai.getMancala().getStoneAmounts();
		ai.getBestMove(3);
		ArrayList<Integer> endingState = ai.getMancala().getStoneAmounts();
		assertArrayEquals(startingState.toArray(new Integer[1]), endingState.toArray(new Integer[1]));
	}

	@Test
	public void preScoringTurnShouldEqualPostScoringTurn() {
		Mancala mancala = new Mancala();
		AI ai = new AI(mancala, 0);
		int startingTurn = ai.getMancala().getCurrentTurn();
		ai.getBestMove(3);
		int endingTurn = ai.getMancala().getCurrentTurn();
		assertEquals(startingTurn, endingTurn);
	}

	@Test
	public void getBestMoveShouldReturnBetweenOneAndSixInclusive() {
		Mancala mancala = new Mancala();
		AI ai = new AI(mancala, 0);
		int move = ai.getBestMove(3);
		assertTrue(1 <= move);
		assertTrue(6 >= move);
	}

	@Test
	public void getTentativeScoreShouldReturnTwentyFourOnNewGame() {
		Mancala mancala = new Mancala();
		AI ai = new AI(mancala, 0);
		assertEquals(24, ai.getTentativeScore(ai.getMancala(), 0));
	}

	@Test
	public void getHeuristicScoreShouldReturnTwentySixAferMovingThree() {
		Mancala mancala = new Mancala();
		mancala.doMove(3);
		AI ai = new AI(mancala, 0);
		assertEquals(26, ai.getHeuristicScore(mancala, 0));
	}
}
