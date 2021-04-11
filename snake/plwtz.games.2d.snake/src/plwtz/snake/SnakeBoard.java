package plwtz.snake;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import plwtz.dimension2D.Direction;
import plwtz.dimension2D.Position;
import plwtz.dimension2D.PositionList;
import plwtz.java.ToString;

public class SnakeBoard implements PositionList {
	public List<Position> positionList = new ArrayList<>();

	private Dimension dimension;

	public SnakeBoard(Dimension dimension) {
		this.dimension = dimension;

		// NorthBorder
		{
			Position northPosition = new Position(0, -1);
			for (int i = 0; i < dimension.width; i++) {
				positionList.add(northPosition);
				northPosition = northPosition.move(Direction.EAST);
			}
		}
		// SouthBorder
		{
			Position sourthPosition = new Position(0, dimension.height);
			for (int i = 0; i < dimension.width; i++) {
				positionList.add(sourthPosition);
				sourthPosition = sourthPosition.move(Direction.EAST);
			}
		}

		// EastBorder
		{
			Position eastPosition = new Position(dimension.width, 0);
			for (int i = 0; i < dimension.height; i++) {
				positionList.add(eastPosition);
				eastPosition = eastPosition.move(Direction.SOUTH);
			}
		}
		// WestBorder
		Position westPosition = new Position(-1, 0);
		for (int i = 0; i < dimension.height; i++) {
			positionList.add(westPosition);
			westPosition = westPosition.move(Direction.SOUTH);
		}
	}

	@Override
	public List<Position> positionList() {
		return positionList;
	}

	public String toString() {
		return ToString.toString(this);
	}

	public Dimension dimension() {
		return dimension;
	}

}
