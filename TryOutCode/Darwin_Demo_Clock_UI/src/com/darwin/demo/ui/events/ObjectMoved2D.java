package com.darwin.demo.ui.events;

import java.awt.Point;

public class ObjectMoved2D implements IUserInterfaceEvent {

	private Point move2d;

	public ObjectMoved2D(Point move2d) {
		this.move2d = move2d;
	}

	public Point getMove() {
		return move2d;
	}

	public String getID() {
		return "com.darwin.demo.ui.events.ObjectMoved2D";
	}

}
