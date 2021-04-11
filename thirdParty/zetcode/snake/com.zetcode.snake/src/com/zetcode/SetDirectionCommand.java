package com.zetcode;

public class SetDirectionCommand implements Command {

	private Direction direction;
	private SnakeObject snake;

	public SetDirectionCommand(SnakeObject snake, Direction direction) {
		this.snake = snake;
		this.direction = direction;
	}

	@Override
	public void execute() {
		snake.setDirection(direction);
	}

}
