package nl.sogyo.mancala;

public class Field extends Location {

	public Field() {
		this(4);
	}

	Field(Location firstLocation, int fieldsToGo, Player player) {
		this(4, firstLocation, fieldsToGo, player);
		this.stones = 4;
	}

	Field(int stones, Location firstLocation, int fieldsToGo, Player player) {
		super(stones, firstLocation, fieldsToGo, player);
		this.stones = stones;
	}

	public Field(int stones) {
		this.stones = stones;
		this.player = new Player();
		this.nextLocation = new Field(stones, this, 13, this.player);
	}

	public void doMove() {
		if (this.isPlayable()) {
			if (this.hasMove()) {
				this.getNextLocation().continueMove(this.getStones());
				this.stones = 0;
			} else {
				this.endGame();
			}
		} else {
			throw new IllegalArgumentException("Cannot move opponents fields!");
		}
	}

	private boolean hasMove() {
		Kalaha thisPlayersKalaha = this.getNextKalaha();
		Field firstField = (Field) thisPlayersKalaha.getNextLocation().getNextKalaha().getNextLocation();
		int totalStones = firstField.getTotalStonesToKalaha();
		if (totalStones > thisPlayersKalaha.getStones()) {
			return true;
		}
		return false;
	}

	public int takeStones() {
		int stonesToTake = this.getStones();
		this.empty();
		return stonesToTake;
	}

	@Override
	void continueMove(int stonesToGo) {
		int preMoveStones = this.getStones();
		super.continueMove(stonesToGo);
		if (stonesToGo == 1) {
			if (preMoveStones == 0) {
				int stonesTaken = ((Field) this.getOpposite()).takeStones();
				this.empty();
				Kalaha ownKalaha = this.getNextKalaha();
				ownKalaha.add(stonesTaken + 1);
			}
		}
	}

	private void empty() {
		this.stones = 0;
	}

	// For testing
	static Field getTakeStonesTestSetup() {
		Field field = new Field();
		Field fieldFour = (Field) field.getNthLocationRelative(4);
		fieldFour.stones = 0;
		return field;
	}

	static Field getCheckWinnerPlayerOneTestSetup() {
		Field field = new Field();
		field.stones = 0;
		Location cur = field.getNextLocation();
		while (cur != field) {
			cur.stones = 0;
			cur = cur.getNextLocation();
		}
		Kalaha kal = field.getNextKalaha();
		kal.stones = 6;
		return field;
	}

	static Field getCheckWinnerPlayerTwoTestSetup() {
		Field field = Field.getCheckWinnerPlayerOneTestSetup();
		Location secondOppField = field.getNextKalaha().getNextLocation().getNextLocation();
		secondOppField.stones = 7;
		return field;
	}
}
