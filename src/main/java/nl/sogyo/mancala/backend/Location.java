package nl.sogyo.mancala.backend;

public abstract class Location {
	int stones;
	Location nextLocation;
	Player player;

	Location() {
	}

	Location(int stones, Location firstLocation, int fieldsToGo, Player player) {
		fieldsToGo--;
		this.player = player;
		if (fieldsToGo > 0) {
			if ((fieldsToGo % 7) == 1) {
				this.nextLocation = new Kalaha(stones, firstLocation, fieldsToGo, player);
			} else if ((fieldsToGo % 7) == 0) {
				this.nextLocation = new Field(stones, firstLocation, fieldsToGo, player.getOpponent());
			} else {
				this.nextLocation = new Field(stones, firstLocation, fieldsToGo, player);
			}
		} else {
			this.nextLocation = firstLocation;
		}
	}

	public Player getPlayer() {
		return this.player;
	}

	void continueMove(int stonesToGo) {
		this.stones++;
		if (stonesToGo > 1) {
			this.getNextLocation().continueMove(stonesToGo - 1);
		} else if (stonesToGo == 1) {
			this.getPlayer().getOpponent().changeTurn();
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
		return (this.getPlayer().hasTurn() && (this.getStones() != 0));
	}

	Kalaha getNextKalaha() {
		return this.getNextLocation().getNextKalaha();
	}

	// Mainly debug methods
	public Location getNthLocationRelative(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 0) {
			return this;
		}
		return this.getNextLocation().getNthLocationRelative(n - 1);
	}

	public int getTotalStonesToKalaha() {
		return this.getStones() + this.getNextLocation().getTotalStonesToKalaha();
	}

	public void moveStonesToKalaha() {
		this.getNextLocation().add(this.getStones());
		this.stones = 0;
		this.getNextLocation().moveStonesToKalaha();
	}

	public void findWinner() {
		Kalaha kalahaOne = this.getNextKalaha();
		Kalaha kalahaTwo = kalahaOne.getNextLocation().getNextKalaha();
		if (kalahaOne.getStones() > kalahaTwo.getStones()) {
			kalahaOne.getPlayer().setWinner(Winner.SELF);
			kalahaTwo.getPlayer().setWinner(Winner.OTHER);
		} else if (kalahaOne.getStones() < kalahaTwo.getStones()) {
			kalahaOne.getPlayer().setWinner(Winner.OTHER);
			kalahaTwo.getPlayer().setWinner(Winner.SELF);
		} else {
			kalahaOne.getPlayer().setWinner(Winner.DRAW);
			kalahaTwo.getPlayer().setWinner(Winner.DRAW);
		}
	}

	void endGame() {
		Location loc = this.getNextKalaha().getNextLocation();
		loc.moveStonesToKalaha();
		loc = loc.getNextKalaha().getNextLocation();
		loc.moveStonesToKalaha();
		this.findWinner();
	}

	public void add(int stones) {
		this.stones += stones;
	}
}
