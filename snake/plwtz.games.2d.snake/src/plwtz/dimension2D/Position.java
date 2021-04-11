package plwtz.dimension2D;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "Position2D(" + x + ", " + y + ")";
	}

	/**
	 * Returns a new Position object positioned next to the addressed Position in the given Direction
	 * @param direction
	 * @return
	 */
	public Position move(Direction direction) {
		return new Position(x + direction.x, y + direction.y);
	}

	public boolean equals(Position position) {
		return x == position.x && y == position.y;
	}

	public int x() {
		return x;
	}
	public int y() {
		return y;
	}
}
