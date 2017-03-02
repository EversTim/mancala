package nl.sogyo.mancala.backend;

import java.util.ArrayList;

public class Mancala {
	private Field field;

	public Mancala() {
		this(4);
	}

	public Mancala(int numberOfStones) {
		this.field = new Field(numberOfStones);
	}

	public boolean doMove(int fieldNumber) {
		Location toPlay = this.field.getNthLocationRelative(fieldNumber);
		if (toPlay.isPlayable()) {
			((Field) toPlay).doMove();
			return true;
		}
		return false;
	}

	Field getField() {
		return this.field;
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
}
