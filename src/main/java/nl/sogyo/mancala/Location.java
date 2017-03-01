package nl.sogyo.mancala;

public abstract class Location {
	int stones;
	Location nextLocation;
	Player player;

	Location() {
	}

	Location(Location firstLocation, int fieldsToGo, Location previous) {
		fieldsToGo--;
		if (previous instanceof Kalaha) {
			this.player = previous.getPlayer().getOpponent();
		} else {
			this.player = previous.getPlayer();
		}
		if (fieldsToGo > 0) {
			if ((fieldsToGo % 7) == 1) {
				this.nextLocation = new Kalaha(firstLocation, fieldsToGo, this);
			} else {
				this.nextLocation = new Field(firstLocation, fieldsToGo, this);
			}
		} else {
			this.nextLocation = firstLocation;
		}
	}

	public Player getPlayer() {
		return this.player;
	}

	void continueMove(int stonesToGo) {
		if (stonesToGo > 0) {
			this.stones++;
			this.getNextLocation().continueMove(stonesToGo - 1);
		}
	}

	public Location getOpposite() {
		Location toTest = this;
		int count = 0;
		while (!(toTest instanceof Kalaha)) {
			toTest = toTest.getNextLocation();
			count++;
		}
		Location opposite = toTest;
		for (int i = 0; i < count; i++) {
			opposite = opposite.getNextLocation();
		}
		return opposite;
	}

	public Location getNextLocation() {
		return this.nextLocation;
	}

	public int getStones() {
		return this.stones;
	}

	public boolean isPlayable() {
		return true;
	}

	// Mainly debug methods
	Kalaha getNextKalaha() {
		Location loc = this;
		while (!(loc instanceof Kalaha)) {
			loc = loc.getNextLocation();
		}
		return (Kalaha) loc;
	}

	public Location getNthLocationRelative(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		Location loc = this;
		for (int i = 0; i < n; i++) {
			loc = loc.getNextLocation();
		}
		return loc;
	}
}
