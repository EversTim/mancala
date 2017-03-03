package nl.sogyo.mancala;

import java.util.Scanner;

import nl.sogyo.mancala.backend.Mancala;
import nl.sogyo.mancala.backend.Winner;

public class MancalaPlayer {
	public static void main(String... args) {
		MancalaPlayer play = new MancalaPlayer();
		play.play();
	}

	private Mancala mancala;

	public Mancala getMancala() {
		return this.mancala;
	}

	public MancalaPlayer() {
		this.mancala = new Mancala(4);
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
