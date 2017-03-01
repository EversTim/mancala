package nl.sogyo.mancala;

public class Player {

	private Player opponent;
	private boolean hasTurn;

	public Player() {
		this.hasTurn = true;
		this.opponent = new Player(this);
	}

	public Player(Player opponent) {
		this.hasTurn = false;
		this.opponent = opponent;
	}

	public Player getOpponent() {
		return this.opponent;
	}

	public boolean hasTurn() {
		return this.hasTurn;
	}

	public void startTurn() {
		this.hasTurn = true;
	}

	public void endTurn() {
		this.hasTurn = false;
		this.opponent.startTurn();
	}
}
