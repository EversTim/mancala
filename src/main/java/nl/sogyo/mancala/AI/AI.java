package nl.sogyo.mancala.AI;

import java.util.ArrayList;

import nl.sogyo.mancala.backend.Mancala;

public class AI {
	private Mancala mancala;
	private final int player;

	public AI(Mancala mancala, int player) {
		this.mancala = mancala;
		this.player = player;
	}

	Mancala getMancala() {
		return this.mancala;
	}

	public int getPlayer() {
		return this.player;
	}

	public int getBestMove(int depth) {
		int bestScore = Integer.MIN_VALUE;
		int bestMove = 0;
		for (int i = 1; i <= 6; i++) {
			Mancala newState = this.mancala.deepClone();
			boolean hasMoved = newState.doMove(i);
			if (hasMoved) {
				int score = 0;
				if (newState.getCurrentTurn() != this.mancala.getCurrentTurn()) {
					score = this.getScoreMinimize(newState, depth - 1);
				} else {
					score = this.getScoreMaximize(newState, depth);
				}
				if (score > bestScore) {
					bestScore = score;
					bestMove = i;
				}
			}
		}
		System.out.println(this.leafCount);
		return bestMove;
	}

	public int leafCount = 0;

	private int getScoreMaximize(Mancala currentState, int depth) {
		if ((depth <= 0) || !currentState.canCurrentPlayerMove()) {
			return this.getHeuristicScore(currentState);
		}
		int bestScore = Integer.MIN_VALUE;
		for (int i = 1; i <= 6; i++) {
			Mancala newState = currentState.deepClone();
			boolean hasMoved = newState.doMove(i);
			if (hasMoved) {
				int score = 0;
				if (newState.getCurrentTurn() != currentState.getCurrentTurn()) {
					score = this.getScoreMinimize(newState, depth - 1);
				} else {
					score = this.getScoreMaximize(newState, depth);
				}
				bestScore = Math.max(bestScore, score);
			}
		}
		return bestScore;
	}

	private int getScoreMinimize(Mancala currentState, int depth) {
		if ((depth <= 0) || !currentState.canCurrentPlayerMove()) {
			return this.getHeuristicScore(currentState);
		}
		int bestScore = Integer.MAX_VALUE;
		for (int i = 1; i <= 6; i++) {
			Mancala newState = currentState.deepClone();
			boolean hasMoved = newState.doMove(i);
			if (hasMoved) {
				int score = 0;
				if (newState.getCurrentTurn() != currentState.getCurrentTurn()) {
					score = -this.getScoreMaximize(newState, depth - 1);
				} else {
					score = -this.getScoreMinimize(newState, depth);
				}
				bestScore = Math.min(bestScore, score);
			}
		}
		return bestScore;
	}

	public int getHeuristicScore(Mancala currentState) {
		this.leafCount++;
		ArrayList<Integer> stoneAmounts = currentState.getStoneAmounts();
		int score = 0 * this.getTentativeScoreDiff(currentState, stoneAmounts);
		score += 2 * this.getKalahaScoreDiff(currentState, stoneAmounts);
		return score;
	}

	public int getTentativeScoreDiff(Mancala currentStatee, ArrayList<Integer> stoneAmounts) {
		int result = 0;
		for (int i = 0; i < 7; i++) {
			result += stoneAmounts.get(i);
		}
		for (int i = 7; i < 14; i++) {
			result -= stoneAmounts.get(i);
		}
		return result;
	}

	public int getKalahaScoreDiff(Mancala currentStatee, ArrayList<Integer> stoneAmounts) {
		int kalahaOne = stoneAmounts.get(6);
		int kalahaTwo = stoneAmounts.get(6 + 7);
		return kalahaOne - kalahaTwo;
	}
}
