package plwtz.snake;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import plwtz.awt.event.keyboard.KeyBoardAdapter;
import plwtz.dimension2D.Position;
import plwtz.dimension2D.event.CollisionDetected;
import plwtz.dimension2D.event.SetDirectionEvent;
import plwtz.dimension2D.event.listener.CollisionDetector;
import plwtz.game.event.GameOverEvent;
import plwtz.game.event.MoveEvent;
import plwtz.game.event.MoveGenerator;
import plwtz.game.event.PauseEvent;
import plwtz.game.event.StartGameEvent;
import plwtz.java.events.EventContext;
import plwtz.java.events.EventListener;
import plwtz.snake.ui.SnakeGameUI;
import plwtz.snake.ui.SnakeGameUI.SnakeUI;

public class SnakeGameController implements EventListener<GameOverEvent>, GameOverEvent.Handler, MoveEvent.Handler,
		CollisionDetected.Handler, StartGameEvent.Handler {
	private Dimension boardDimension;
	private Snake snake;
	private SnakeBoard snakeBoard;
	private MoveGenerator moveGenerator;
	private SnakeGameUI snakeGameUI;

	public SnakeGameController(Dimension boardDimension, Snake.Length initialSnakeLength,
			Position initialSnakePosition) {
		this.boardDimension = boardDimension;
		snake = new Snake(initialSnakeLength, initialSnakePosition);

		snakeBoard = new SnakeBoard(boardDimension);
		new CollisionDetector(snake);
		new CollisionDetector(snakeBoard);

		EventContext.register(this);
	}

	public Dimension boardDimension() {
		return snakeBoard.dimension();
	}

	public KeyBoardAdapter keyboardAdapter() {
		KeyBoardAdapter keyboardAdapter = new KeyBoardAdapter();
		keyboardAdapter.on(KeyEvent.VK_LEFT, SetDirectionEvent.broadcastSetWestDirectionCommand);
		keyboardAdapter.on(KeyEvent.VK_RIGHT, SetDirectionEvent.broadcastSetEastDirectionCommand);
		keyboardAdapter.on(KeyEvent.VK_UP, SetDirectionEvent.broadcastSetNorthDirectionCommand);
		keyboardAdapter.on(KeyEvent.VK_DOWN, SetDirectionEvent.broadcastSetSouthDirectionCommand);
		keyboardAdapter.on(KeyEvent.VK_SPACE, PauseEvent.broadcastPauseCommand);
		keyboardAdapter.on(KeyEvent.VK_Q, GameOverEvent.broadcastGameOverCommand);
		keyboardAdapter.on(KeyEvent.VK_S, StartGameEvent.startGame);
		return keyboardAdapter;
	}

	public void start(int delay) {
		EventQueue.invokeLater(() -> {
			this.moveGenerator = new MoveGenerator(delay);
			moveGenerator.start();
		});

		EventQueue.invokeLater(() -> {
			snakeGameUI = new SnakeGameUI(new Dimension(boardDimension.width * 10, boardDimension.height * 10),
					keyboardAdapter(), snake);
			snakeGameUI.setVisible(true);
		});
	}

	@Override
	public void handle(MoveEvent moveEvent) {
//		snake.move();
		snakeGameUI.repaint();
	}

	@Override
	public void handle(GameOverEvent event) {
		gameOver();
	}

	@Override
	public void handle(CollisionDetected collissionDetectedEvent) {
		gameOver();
	}

	private void gameOver() {
		snakeGameUI.snakeUIBoard().clear();
		snakeGameUI.snakeUIBoard().add(snakeGameUI.gameOverPanel());
		MoveGenerator.stop.run();
	}

	@Override
	public void handle(StartGameEvent startGameEvent) {
		snakeGameUI.snakeUIBoard().clear();
		snakeGameUI.snakeUIBoard().add(new SnakeUI(snake));

		snake.restart();
		moveGenerator.start();
	}

}
