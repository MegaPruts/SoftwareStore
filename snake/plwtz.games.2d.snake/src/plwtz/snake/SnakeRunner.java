package plwtz.snake;

import java.awt.Dimension;

import plwtz.dimension2D.Position;
import plwtz.snake.Snake.Length;

public class SnakeRunner {
	private static final int DELAY = 300;

	public static void main(String[] args) {
		Dimension boardDimension = new Dimension(30, 30);
		Length initialSnakeLength = new Snake.Length(5);
		Position initialSnakePosition = new Position(5, 5);
		SnakeGameController snakeGame = new SnakeGameController(boardDimension, initialSnakeLength,
				initialSnakePosition);
		snakeGame.start(DELAY);
	}
}
