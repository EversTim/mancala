package nl.sogyo.mancala;

public class Field extends Location {

	public Field() {
		this.stones = 4;
		this.nextLocation = new Field(this, 13);
	}

	Field(Location firstLocation, int fieldsToGo) {
		super(firstLocation, fieldsToGo);
		this.stones = 4;
	}

	public void doMove() {
		this.getNextLocation().continueMove(this.getStones());
		this.stones = 0;
	}

	public int takeStones() {
		int stonesToTake = this.getStones();
		this.stones = 0;
		return stonesToTake;
	}

}
