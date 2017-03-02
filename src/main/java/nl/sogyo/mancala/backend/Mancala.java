package nl.sogyo.mancala.backend;

import java.util.ArrayList;

public class Mancala {
	public Field field;

	public Mancala() {
		this(4);
	}

	public Mancala(int numberOfStones) {
		this.field = new Field(numberOfStones);
	}

	private Field getField() {
		return this.field;
	}

	public boolean doMove(int fieldNumber) {
		if ((fieldNumber < 0) || (fieldNumber > 13)) {
			throw new IllegalArgumentException("doMove must be given a number between 0 and 13 inclusive.");
		}
		Location toPlay = this.field.getNthLocationRelative(fieldNumber);
		try {
			((Field) toPlay).doMove();
			return true;
		} catch (IllegalArgumentException iae) {
			return false;
		}
	}

	public boolean hasWinner() {
		return (this.field.getPlayer().getWinner() != WinnerRelative.UNDECIDED);
	}

	public Winner getWinner() {
		if (this.getField().getPlayer().getWinner() == WinnerRelative.DRAW) {
			return Winner.DRAW;
		} else if (this.getField().getPlayer().getWinner() == WinnerRelative.SELF) {
			return Winner.PLAYER_ONE;
		} else if (this.getField().getPlayer().getWinner() == WinnerRelative.OTHER) {
			return Winner.PLAYER_TWO;
		} else {
			return Winner.UNDECIDED;
		}
	}

	public ArrayList<Integer> getStoneAmounts() {
		ArrayList<Integer> stones = new ArrayList<Integer>(14);
		stones.add(this.field.getStones());
		Location cur = this.field.getNextLocation();
		while (cur != this.field) {
			stones.add(cur.getStones());
			cur = cur.getNextLocation();
		}
		return stones;
	}

	public int getCurrentTurn() {
		if (this.field.getPlayer().hasTurn() == true) {
			return 0;
		}
		return 1;
	}
}
