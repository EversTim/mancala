package nl.sogyo.mancala.backend;

class Player {

	private Player opponent;
	private boolean hasTurn;
	private Winner winner;

	Player() {
		this.opponent = new Player(this);
		this.hasTurn = true;
	}

	Player(Player opponent) {
		this.opponent = opponent;
		this.hasTurn = false;
	}

	Player getOpponent() {
		return this.opponent;
	}

	boolean hasTurn() {
		return this.hasTurn;
	}

	void changeTurn() {
		this.getOpponent().setTurn(this.hasTurn);
		this.hasTurn = !this.hasTurn;
	}

	void setTurn(boolean hasTurn) {
		this.hasTurn = hasTurn;
	}

	Winner getWinner() {
		return this.winner;
	}

	void setWinner(Winner winner) {
		this.winner = winner;
	}
}
