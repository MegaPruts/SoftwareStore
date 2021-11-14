package plwtz.snake.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import plwtz.awt.event.keyboard.KeyBoardAdapter;
import plwtz.dimension2D.Position;
import plwtz.java.ToString;
import plwtz.java.events.Event;
import plwtz.java.events.EventContext;
import plwtz.snake.Snake;

public class SnakeGameUI extends JFrame {

	private static final long serialVersionUID = 5818100344081746394L;
	private static final Font FONT = new Font("Helvetica", Font.BOLD, 14);
	private static final String GAMEOVER_MESSAGE = "Game over";

	private SnakeUIBoard snakeUIBoard;
	private Drawable gameOverPanel;

	public SnakeGameUI(Dimension boardDimension, KeyBoardAdapter keyboardAdapter, Snake snake) {
		snakeUIBoard = new SnakeUIBoard(boardDimension, keyboardAdapter, new SnakeUI(snake));
		add(snakeUIBoard);
		setResizable(false);
		pack();

		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameOverPanel = new GameOverPanel(GAMEOVER_MESSAGE, boardDimension, FONT,
				getFontMetrics(FONT).stringWidth(GAMEOVER_MESSAGE));

	}

	public SnakeUIBoard snakeUIBoard() {
		return snakeUIBoard;
	}

	public Drawable gameOverPanel() {
		return gameOverPanel;
	}

	public static class SnakeUIBoard extends JPanel {

		private static final long serialVersionUID = -3228379641685974582L;
		private Set<Drawable> snakeBoardItems = new HashSet<>();

		public SnakeUIBoard(Dimension boardDimension, KeyListener keyboardAdapter, Drawable... drawablesArray) {
			snakeBoardItems.addAll(Arrays.asList(drawablesArray));

			setBackground(Color.black);
			setFocusable(true);
			setPreferredSize(boardDimension);
			addKeyListener(keyboardAdapter);
		}

		public void add(Drawable snakeBoardItem) {
			snakeBoardItems.add(snakeBoardItem);
			repaint();
		}

		public void clear() {
			snakeBoardItems.clear();
			repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			snakeBoardItems.stream().forEach(d -> d.draw(g, this));
		}

	}

	public static class SnakeUI implements Drawable {

		private Snake snake;

		private final int DOT_SIZE = 10;
		private Image headImage;
		private Image bodyImage;

		public SnakeUI(Snake snake) {
			this.snake = snake;
			headImage = new ImageIcon("src/resources/red_dot.png").getImage();
			bodyImage = new ImageIcon("src/resources/green_dot.png").getImage();
		}

		@Override
		public void draw(Graphics g, ImageObserver observer) {
			boolean head = true;
			for (Position p : snake.positionList()) {
				g.drawImage(head ? headImage : bodyImage, p.x() * DOT_SIZE, p.y() * DOT_SIZE, observer);
				head = false;
			}

		}

	}

	public static class GameOverPanel implements Drawable {
		public static final Runnable show = () -> EventContext.execute(new ShowGameOverPanelEvent());
		private String msg;
		private Dimension dimension;
		private Font font;
		private int stringWidth;

		public GameOverPanel(String msg, Dimension dimension, Font font, int stringWidth) {
			this.msg = msg;
			this.dimension = dimension;
			this.font = font;
			this.stringWidth = stringWidth;
		}

		@Override
		public void draw(Graphics g, ImageObserver observer) {
			g.setColor(Color.white);
			g.setFont(this.font);

			int x_pos = (dimension.width - stringWidth) / 2;
			int pos_y = dimension.height / 2;

			g.drawString(msg, x_pos, pos_y);

		}

		public static class ShowGameOverPanelEvent implements Event {

			public interface Handler {
				void handle(ShowGameOverPanelEvent showGameOverPanelEvent);
			}

			public String toString() {
				return ToString.toString(this);
			}
		}

	}

}
