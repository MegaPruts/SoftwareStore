package com.zetcode;

import java.awt.Graphics;
import java.awt.Image;

public class SnakeObject implements DirectionConsumer {
	private final int ALL_DOTS = 900;
	private final int DOT_SIZE = 10;

	final int x[] = new int[ALL_DOTS];
	final int y[] = new int[ALL_DOTS];

	Image headImage;
	Image ballImage;

	Direction direction;
	int length;

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	void move() {

		if (direction != null) {
			for (int z = length; z > 0; z--) {
				x[z] = x[(z - 1)];
				y[z] = y[(z - 1)];
			}

			if (Direction.LEFT == direction) {
				x[0] -= DOT_SIZE;
			}

			if (Direction.RIGHT == direction) {
				x[0] += DOT_SIZE;
			}

			if (Direction.UP == direction) {
				y[0] -= DOT_SIZE;
			}

			if (Direction.DOWN == direction) {
				y[0] += DOT_SIZE;
			}
		}
	}

	public void draw(Graphics g, Board board) {
		// draw snake...

	}

	public void incrementLength() {
		length++;
	}
}
