package nl.sogyo.mancala;

public class Field extends Location {

	public Field() {
		this.stones = 4;
		this.player = new Player();
		this.nextLocation = new Field(this, 13, this.player);
	}

	Field(Location firstLocation, int fieldsToGo, Player player) {
		super(firstLocation, fieldsToGo, player);
		this.stones = 4;
	}

	public void doMove() {
		if (this.getPlayer().hasTurn()) {
			this.getNextLocation().continueMove(this.getStones());
			this.stones = 0;
		} else {
			throw new IllegalArgumentException("Cannot move opponents fields!");
		}
	}

	public int takeStones() {
		int stonesToTake = this.getStones();
		this.stones = 0;
		return stonesToTake;
	}

	@Override
	void continueMove(int stonesToGo) {
		int preMoveStones = this.getStones();
		super.continueMove(stonesToGo);
		if (stonesToGo == 1) {
			if (preMoveStones == 0) {
				int stonesTaken = ((Field) this.getOpposite()).takeStones();
				Kalaha ownKalaha = this.getNextKalaha();
				ownKalaha.add(stonesTaken + 1);
				this.stones = 0;
			}
		}
	}

	// For testing
	static Field getTakeStonesTestSetup() {
		Field field = new Field();
		Field fieldFour = (Field) field.getNthLocationRelative(4);
		fieldFour.stones = 0;
		return field;
	}
}
