package nl.sogyo.mancala;

public class Kalaha extends Location {

	public Kalaha() {
		this.stones = 0;
		this.nextLocation = this;
	}

	Kalaha(Location firstLocation, int fieldsToGo, Location previous) {
		super(firstLocation, fieldsToGo, previous);
		this.stones = 0;
	}

	@Override
	public boolean isPlayable() {
		return false;
	}

	@Override
	void continueMove(int stonesToGo) {
		if (this.getPlayer().hasTurn()) {
			super.continueMove(stonesToGo);
		} else {
			this.getNextLocation().continueMove(stonesToGo);
		}
	}
}
