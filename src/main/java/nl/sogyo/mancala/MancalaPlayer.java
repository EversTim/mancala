package nl.sogyo.mancala;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

import nl.sogyo.mancala.AI.AI;
import nl.sogyo.mancala.backend.Mancala;
import nl.sogyo.mancala.backend.Winner;

public class MancalaPlayer {

	public static void main(String... args) {
		MancalaPlayer play = new MancalaPlayer();
		play.play();
	}

	private Mancala mancala;
	boolean[] aiPlayers = new boolean[2];
	int[] aiDepths = new int[2];

	public MancalaPlayer() {
		this.mancala = new Mancala(4);
		this.aiPlayers[0] = false;
		this.aiPlayers[1] = true;
		this.aiDepths[0] = 5;
		this.aiDepths[1] = 5;
	}

	public Mancala getMancala() {
		return this.mancala;
	}

	public void play() {
		while (!this.mancala.hasWinner()) {
			System.out.println(MancalaFormatter.format(this.mancala));
			int currentTurn = this.mancala.getCurrentTurn();
			System.out.format("Currently it is player %1s's turn.%n", currentTurn + 1);
			this.askAndDoMove();
			this.printWinner();
		}
	}

	private void askAndDoMove() {
		boolean didMove = false;
		if (this.aiPlayers[this.mancala.getCurrentTurn()]) {
			AI ai = new AI(this.mancala.deepClone(), this.mancala.getCurrentTurn());
			Instant start = Instant.now();
			int moveToDo = ai.getBestMove(this.aiDepths[this.mancala.getCurrentTurn()]);
			Instant end = Instant.now();
			System.out.println("Player " + (this.mancala.getCurrentTurn() + 1) + " moved field " + moveToDo + " in "
					+ Duration.between(start, end) + ".");
			this.mancala.doMove(moveToDo);
		} else {
			do {
				try {
					int move = Integer.parseInt(getStringInput());
					didMove = this.mancala.doMove(move);
				} catch (NumberFormatException nfe) {
					System.out.println("Not a number! Please try again");
				} catch (IllegalArgumentException iae) {
					System.out.println("Illegal move, try again!");
				}
			} while (!didMove);
		}
	}

	private void printWinner() {
		Winner winner = this.mancala.getWinner();
		if (winner == Winner.DRAW) {
			System.out.println("It's a draw!");
		} else if (winner == Winner.PLAYER_ONE) {
			System.out.println("Player one won!");
		} else if (winner == Winner.PLAYER_TWO) {
			System.out.println("Player two won!");
		}
	}

	static Scanner sc = new Scanner(System.in);

	public static String getStringInput() {
		String strinput = null;
		try {
			strinput = sc.nextLine();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "\n");
			ex.printStackTrace();
			return null;
		}
		return strinput.trim();
	}
}
