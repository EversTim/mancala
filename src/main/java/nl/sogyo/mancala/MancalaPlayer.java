package nl.sogyo.mancala;

import nl.sogyo.mancala.backend.Mancala;

public class MancalaPlayer {
	public static void main(String... args) {
		MancalaPlayer play = new MancalaPlayer();
		System.out.println(MancalaFormatter.format(play.getMancala()));
		play.play(0);
		System.out.println(MancalaFormatter.format(play.getMancala()));
	}

	private Mancala mancala;

	public Mancala getMancala() {
		return this.mancala;
	}

	public MancalaPlayer() {
		this.mancala = new Mancala();
	}

	public void play(int field) {
		this.mancala.doMove(field);
	}
}
