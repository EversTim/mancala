package nl.sogyo.mancala;

import java.util.ArrayList;

import nl.sogyo.mancala.backend.Mancala;

public class MancalaPlayer {
	public static void main(String... args) {
		MancalaPlayer play = new MancalaPlayer();
		play.play(0);
		System.out.println(play.formatMancala());
	}

	private Mancala mancala;

	public MancalaPlayer() {
		this.mancala = new Mancala(13);
	}

	public void play(int field) {
		this.mancala.doMove(field);
	}

	public String formatMancala() {
		ArrayList<Integer> stones = this.mancala.getStoneAmounts();
		StringBuilder build = new StringBuilder();
		for (Integer i : stones) {
			build.append(i);
			build.append(" ");
		}
		return build.toString().trim();
	}
}
