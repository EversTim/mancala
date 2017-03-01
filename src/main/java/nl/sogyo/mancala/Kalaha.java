package nl.sogyo.mancala;

public class Kalaha extends Location {

	public Kalaha() {
		this.stones = 0;
		this.nextLocation = this;
	}

	Kalaha(Location firstLocation, int fieldsToGo) {
		super(firstLocation, fieldsToGo);
		this.stones = 0;
	}

	@Override
	public boolean isPlayable() {
		return false;
	}
}
