package plwtz.dimension2D.event;

import plwtz.dimension2D.Position;
import plwtz.java.events.Event;

public class AllocatePosition implements Event {

	private Position position;
	private Object subject;

	public AllocatePosition(Object subject, Position position) {
		this.subject = subject;
		this.position = position;
	}

	public Position position() {
		return position;
	}

	public Object subject() {
		return subject;
	}

}
