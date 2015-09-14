package com.darwin.ui.input.mouse;

import java.awt.Point;

public class MouseDraggedEvent implements IMouseEvent {

	private Point locationOnScreen;
//	private Point startDragLocation;
	private Point eventPoint;
private Point moveMent;

	public MouseDraggedEvent(Point eventPoint,
			Point locationOnScreen) {
//		this.startDragLocation = startDragLocation;
		this.eventPoint = eventPoint;
		this.locationOnScreen = locationOnScreen;
		this.moveMent = new Point(locationOnScreen);
		moveMent.translate(-eventPoint.x, -eventPoint.y);
	}

//	public Point getStartDragLocation() {
//		return startDragLocation;
//	}

	public Point getLocationOnScreen() {
		return locationOnScreen;
	}

	public Point getEventPoint() {
		return eventPoint;
	}

	public Point getMoveMent() {
		return moveMent;
	}

}
