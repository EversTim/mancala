package nl.sogyo.mancala.AI;

import java.io.IOException;
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
					score = this.getScoreMaximize(newState, depth - 1);
				}
				if (score > bestScore) {
					bestScore = score;
					bestMove = i;
				}
			}
		}
		return bestMove;
	}

	private int getScoreMaximize(Mancala currentState, int depth) {
		if ((depth == 0) || !currentState.canCurrentPlayerMove()) {
			return this.getHeuristicScore(currentState, currentState.getCurrentTurn());
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
					score = this.getScoreMaximize(newState, depth - 1);
				}
				bestScore = Math.max(bestScore, score);
			}
		}
		return bestScore;
	}

	private int getScoreMinimize(Mancala currentState, int depth) {
		if ((depth == 0) || !currentState.canCurrentPlayerMove()) {
			return this.getHeuristicScore(currentState, currentState.getCurrentTurn());
		}
		int bestScore = Integer.MAX_VALUE;
		for (int i = 1; i <= 6; i++) {
			Mancala newState = currentState.deepClone();
			boolean hasMoved = newState.doMove(i);
			if (hasMoved) {
				int score = 0;
				if (newState.getCurrentTurn() == currentState.getCurrentTurn()) {
					score = this.getScoreMinimize(newState, depth - 1);
				} else {
					score = this.getScoreMaximize(newState, depth - 1);
				}
				bestScore = Math.min(bestScore, score);
			}
		}
		return bestScore;
	}

	public int getHeuristicScore(Mancala currentState, int turn) {
		int score = this.getTentativeScore(currentState, turn);
		score += 2 * this.getKalahaScore(currentState, turn);
		return score;
	}

	public int getTentativeScore(Mancala currentState, int player) {
		ArrayList<Integer> stoneAmounts = currentState.getStoneAmounts();
		int result = 0;
		for (int i = 0 + (player * 7); i < (7 + (player * 7)); i++) {
			result += stoneAmounts.get(i);
		}
		return result;
	}

	public int getKalahaScore(Mancala currentState, int player) {
		ArrayList<Integer> stoneAmounts = currentState.getStoneAmounts();
		return stoneAmounts.get(6 + (player * 7));
	}
}
