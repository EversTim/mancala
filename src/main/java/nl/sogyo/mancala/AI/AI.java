package nl.sogyo.mancala.AI;

import nl.sogyo.mancala.backend.Mancala;

public class AI {
	private Mancala mancala;

	public AI(Mancala mancala) {
		this.mancala = mancala;
	}

	Mancala getMancala() {
		return this.mancala;
	}

	public int getBestMove() {
		return 4;
	}
}
