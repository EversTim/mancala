package nl.sogyo.mancala;

public class Player {

	private Player opponent;
	private boolean hasTurn;
	private Winner winner;

	public Player() {
		this.opponent = new Player(this);
		this.hasTurn = true;
	}

	public Player(Player opponent) {
		this.opponent = opponent;
		this.hasTurn = false;
	}

	public Player getOpponent() {
		return this.opponent;
	}

	public boolean hasTurn() {
		return this.hasTurn;
	}

	public void changeTurn() {
		this.getOpponent().setTurn(this.hasTurn);
		this.hasTurn = !this.hasTurn;
	}

	public void setTurn(boolean hasTurn) {
		this.hasTurn = hasTurn;
	}

	public Winner getWinner() {
		return this.winner;
	}

	void setWinner(Winner winner) {
		this.winner = winner;
	}
}
