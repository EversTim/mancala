package nl.sogyo.mancala;

public class Kalaha extends Location {

	public Kalaha() {
		this.stones = 0;
		this.nextLocation = this;
	}

	Kalaha(Location firstLocation, int fieldsToGo, Player player) {
		super(firstLocation, fieldsToGo, player);
		this.stones = 0;
	}

	@Override
	public boolean isPlayable() {
		return false;
	}

	@Override
	void continueMove(int stonesToGo) {
		if (this.getPlayer().hasTurn()) {
			if (stonesToGo > 1) {
				super.continueMove(stonesToGo);
			}
		} else {
			this.getNextLocation().continueMove(stonesToGo);
		}
	}

	public void add(int stones) {
		this.stones += stones;
	}
}
