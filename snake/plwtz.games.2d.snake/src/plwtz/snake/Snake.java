package plwtz.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import plwtz.dimension2D.Direction;
import plwtz.dimension2D.Position;
import plwtz.dimension2D.PositionList;
import plwtz.dimension2D.event.AllocatePosition;
import plwtz.dimension2D.event.SetDirectionEvent;
import plwtz.game.event.MoveEvent;
import plwtz.java.ToString;
import plwtz.java.events.EventContext;
import plwtz.java.events.EventListener;

public class Snake implements SetDirectionEvent.Handler, EventListener<SetDirectionEvent>, PositionList {

	private final Consumer<AllocatePosition> publishAllocatePositionEvent = (p) -> EventContext.execute(p);

//	private boolean eating;

	private int initialLength;

	/**
	 * TODO Verplaats de position van de slang naar zoiets als een 2DContext. Een
	 * slang an sich heeft nl niets te doen met een (2D) positie. De positie wordt
	 * nl bepaald binnen een "locatie context". Die kan bijv 2D zijn of 3D.
	 */
	private Position initialPosition;

	public Snake(Snake.Length initialLength, Position initialPosition) {
		this.initialLength = initialLength.length;
		this.initialPosition = initialPosition;
		restart();

		EventContext.register(this);
	}

	public void restart() {
		positionList.clear();
		positionList.add(initialPosition);
	}

	private List<Position> positionList = new ArrayList<>();

	@Override
	public List<Position> positionList() {
		return positionList;
	}

	private Position headPosition() {
		return positionList.get(0);
	}

	public void handle(MoveEvent event) {
		move();
	}

	public void move() {
		if (direction != null) {
			Position newPosition = headPosition().move(direction);

			aboutToMoveTo(newPosition);
			positionList.add(0, newPosition);
			if (positionList.size() > initialLength)
				positionList.remove(positionList.size() - 1);
		}
	}

	private void aboutToMoveTo(Position newPosition) {
		publishAllocatePositionEvent.accept(new AllocatePosition(this, newPosition));
	}

	private Direction direction;

	public void handle(SetDirectionEvent event) {
		direction = event.direction();
	}

	public String toString() {
		return ToString.toString(this);
	}

	public static class Length {
		private int length;

		public Length(int length) {
			this.length = length;
		}

		public int length() {
			return length;
		}
	}

}
