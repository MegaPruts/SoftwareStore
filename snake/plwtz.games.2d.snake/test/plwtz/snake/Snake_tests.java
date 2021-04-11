package plwtz.snake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static plwtz.dimension2D.event.MoveEvent.broadcastMoveCommand;
import static plwtz.game.event.MoveEvent.broadcastMoveCommand;
import static plwtz.dimension2D.event.listener.CollisionDetector.occupiesPosition;
import static plwtz.java.Repeat.repeat;

import java.awt.Dimension;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import plwtz.dimension2D.Direction;
import plwtz.dimension2D.Position;
import plwtz.dimension2D.event.CollisionDetected;
import plwtz.dimension2D.event.CollisionDetected.Collector;
import plwtz.dimension2D.event.SetDirectionEvent;
import plwtz.dimension2D.event.listener.CollisionDetector;
import plwtz.game.event.MoveEvent;
import plwtz.java.events.EventContext;
import plwtz.snake.Snake.Length;

public class Snake_tests {

	private final Consumer<SetDirectionEvent> setDirectionCommand = (m) -> EventContext.execute(m);
//	private final Consumer<MoveEvent> broadcastMoveCommand = (m) -> ObjectContext.execute(m);

//	private final MoveEvent moveEvent = new MoveEvent();

	@BeforeEach
	public void beforeEach() {
		EventContext.reset();
	}

	@Test
	public void test_Snake_creation() {
		Position initialPosition = new Position(5, 5);
		Snake snake = new Snake(new Snake.Length(3), initialPosition);

		// initially the snake only occupies the head position
		assertTrue(occupiesPosition(snake.positionList(), initialPosition));
		// all surrounding positions are free
		Stream.of(Direction.values())
				.forEach((d) -> assertFalse(occupiesPosition(snake.positionList(), initialPosition.move(d))));
	}

	private void move(Direction direction) {
		setDirection(direction);
		move();
	}

	private void move(Direction direction, int timesToRepeat) {
		setDirection(direction);
		repeat(timesToRepeat, broadcastMoveCommand);
	}

	private void move() {
		broadcastMoveCommand.run();
	}

	@Test
	public void test_Snake_listeningTo_Move() {
		Position initialPosition = new Position(5, 5);
		Snake snake = new Snake(new Snake.Length(3), initialPosition);

		move(Direction.EAST);
		assertTrue(occupiesPosition(snake.positionList(), new Position(6, 5)));
	}

	@Test
	public void test_SnakeExtension_duringInitialMovements() {
		Position initialPosition = new Position(5, 5);
		Position positionAfterOneMove = initialPosition.move(Direction.EAST);
		Position positionAfterTwoMoves = positionAfterOneMove.move(Direction.EAST);
		Position positionAfterThreeMoves = positionAfterTwoMoves.move(Direction.EAST);

		Snake snake = new Snake(new Snake.Length(3), initialPosition);
		// First move makes the snake grow by 1 to 2 dots
		move(Direction.EAST);

		assertEquals(2, snake.positionList().size());
		assertTrue(occupiesPosition(snake.positionList(), initialPosition));
		assertTrue(occupiesPosition(snake.positionList(), positionAfterOneMove));
		assertFalse(occupiesPosition(snake.positionList(), positionAfterTwoMoves));

		// Second move makes the snake grow by 1 to 3 dots
		move(Direction.EAST);

		assertEquals(3, snake.positionList().size());
		assertTrue(occupiesPosition(snake.positionList(), initialPosition));
		assertTrue(occupiesPosition(snake.positionList(), positionAfterOneMove));
		assertTrue(occupiesPosition(snake.positionList(), positionAfterTwoMoves));
		assertFalse(occupiesPosition(snake.positionList(), positionAfterThreeMoves));

		// Third move keeps the snake to its given 3 dot length
		// Thus this move empties the initialPosition
		move(Direction.EAST);

		assertEquals(3, snake.positionList().size());
		assertFalse(occupiesPosition(snake.positionList(), initialPosition));
		assertTrue(occupiesPosition(snake.positionList(), positionAfterOneMove));
		assertTrue(occupiesPosition(snake.positionList(), positionAfterTwoMoves));
		assertTrue(occupiesPosition(snake.positionList(), positionAfterThreeMoves));

	}

	@Test
	public void test_GameCollision_offSnake_onItself() {
		// Create a Snake long enough to 'eat' itself, but small enough to fit on the
		// board.
		Length snakeLength = new Snake.Length(9);
		Position initialPosition = new Position(5, 5);

		Snake snake = new Snake(snakeLength, initialPosition);
		CollisionDetector collisionDetector = new CollisionDetector(snake);
		Collector collisionDetectorCollector = new CollisionDetected.Collector();
		// Stretch the snake to its full length eastwards.
		move(Direction.EAST, snakeLength.length());

		// Now make a u-turn to collide with itself
		move(Direction.SOUTH, 2);
		move(Direction.WEST, 2);
		move(Direction.NORTH, 2);

		assertTrue(collisionDetectorCollector.events.size() == 1);
	}

	@Test
	public void test_GameCollision_offSnake_onBoardBorder() {
		// Create the SnakeGame with plenty of room on the board.
		Dimension snakeBoardDimension = new Dimension(10, 10);
		SnakeBoard snakeBoard = new SnakeBoard(snakeBoardDimension);

		Length snakeLength = new Snake.Length(3);
		Position initialPosition = new Position(0, 0);

		Snake snake = new Snake(snakeLength, initialPosition);

		CollisionDetector collisionDetector = new CollisionDetector(snakeBoard);
		Collector collisionDetectorCollector = new CollisionDetected.Collector();
		move(Direction.SOUTH);
		assertTrue(collisionDetectorCollector.events.size() == 1);
	}

	private void setDirection(Direction direction) {
		setDirection(new SetDirectionEvent(direction), 1);
	}

	private void setDirection(SetDirectionEvent move, int timesToRepeat) {
		repeat(timesToRepeat, setDirectionCommand, move);
	}

}
