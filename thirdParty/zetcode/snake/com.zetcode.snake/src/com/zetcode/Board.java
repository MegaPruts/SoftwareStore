package com.zetcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private final int B_WIDTH = 300;
	private final int B_HEIGHT = 300;
	private final int DOT_SIZE = 10;
	private final int RAND_POS = 29;
	private final int DELAY = 140;

	private boolean inGame = true;

	private Timer timer;
	private SnakeObject snake;
	private Apple apple;

	public Board() {

		initBoard();
	}

	private void initBoard() {
		snake = initSnake();
		apple = initApple();

		loadImages();

		KeyBoardAdapter keyboardAdapter = new KeyBoardAdapter();
		keyboardAdapter.on(KeyEvent.VK_LEFT, new SetDirectionCommand(snake, Direction.LEFT));
		keyboardAdapter.on(KeyEvent.VK_RIGHT, new SetDirectionCommand(snake, Direction.RIGHT));
		keyboardAdapter.on(KeyEvent.VK_UP, new SetDirectionCommand(snake, Direction.UP));
		keyboardAdapter.on(KeyEvent.VK_DOWN, new SetDirectionCommand(snake, Direction.DOWN));
		addKeyListener(keyboardAdapter);

		setBackground(Color.black);
		setFocusable(true);

		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		initGame();
	}

	private Apple initApple() {
		Apple apple = new Apple();
		ImageIcon iia = new ImageIcon("src/resources/apple.png");
		apple.appleImage = iia.getImage();
		return apple;
	}

	private SnakeObject initSnake() {
		SnakeObject snake = new SnakeObject();
		snake.length = 3;
		snake.direction = Direction.RIGHT;

		ImageIcon iih = new ImageIcon("src/resources/head.png");
		snake.headImage = iih.getImage();
		ImageIcon iid = new ImageIcon("src/resources/dot.png");
		snake.ballImage = iid.getImage();

		for (int z = 0; z < snake.length; z++) {
			snake.x[z] = 50 - z * 10;
			snake.y[z] = 50;
		}

		return snake;
	}

	private void loadImages() {

	}

	private void initGame() {

		randomlyPutAppleOnTheBoard();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);
	}

	private void doDrawing(Graphics g) {

		if (inGame) {

			// draw apple...
			g.drawImage(apple.appleImage, apple.apple_x, apple.apple_y, this);

//			snake.draw(g, this);
			for (int z = 0; z < snake.length; z++) {
				if (z == 0) {
					g.drawImage(snake.headImage, snake.x[z], snake.y[z], this);
				} else {
					g.drawImage(snake.ballImage, snake.x[z], snake.y[z], this);
				}
			}

			Toolkit.getDefaultToolkit().sync();

		} else {

			gameOver(g);
		}
	}

	private void gameOver(Graphics g) {

		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
	}

	private void checkApple() {

		if ((snake.x[0] == apple.apple_x) && (snake.y[0] == apple.apple_y)) {

			// Verhoog het aantal "dots" van de slang...
			snake.incrementLength();

			randomlyPutAppleOnTheBoard();
		}
	}

	private void checkCollision() {

		for (int z = snake.length; z > 0; z--) {

			if ((z > 4) && (snake.x[0] == snake.x[z]) && (snake.y[0] == snake.y[z])) {
				inGame = false;
			}
		}

		if (snake.y[0] >= B_HEIGHT) {
			inGame = false;
		}

		if (snake.y[0] < 0) {
			inGame = false;
		}

		if (snake.x[0] >= B_WIDTH) {
			inGame = false;
		}

		if (snake.x[0] < 0) {
			inGame = false;
		}

		if (!inGame) {
			timer.stop();
		}
	}

	/*
	 * Plaats de appel random op het bord
	 */
	private void randomlyPutAppleOnTheBoard() {

		int r = (int) (Math.random() * RAND_POS);
		apple.apple_x = ((r * DOT_SIZE));

		r = (int) (Math.random() * RAND_POS);
		apple.apple_y = ((r * DOT_SIZE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (inGame) {

			checkApple();
			checkCollision();
			snake.move();

		}

		repaint();
	}

}