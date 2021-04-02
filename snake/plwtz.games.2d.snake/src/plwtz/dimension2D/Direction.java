package plwtz.dimension2D;

public enum Direction {
	EAST(1, 0), SOUTH(0, 1), WEST(-1, 0), NORTH(0, -1);

	public final int x;
	public final int y;

	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
