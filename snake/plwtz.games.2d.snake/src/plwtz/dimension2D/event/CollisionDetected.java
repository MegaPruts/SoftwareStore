package plwtz.dimension2D.event;

import java.util.ArrayList;
import java.util.List;

import plwtz.dimension2D.Position;
import plwtz.game.event.MoveEvent;
import plwtz.java.ToString;
import plwtz.java.events.Event;
import plwtz.java.events.EventContext;
import plwtz.java.events.EventListener;

public class CollisionDetected implements Event {

	public interface Handler {
		void handle(CollisionDetected collissionDetectedEvent);
	}

	private Position position;
	private Object obstacle;
	private Object subject;

	public CollisionDetected(Object subject, Position position, Object obstacle) {
		this.subject = subject;
		this.position = position;
		this.obstacle = obstacle;
	}

	public Position position() {
		return position;
	}

	public Object obstacle() {
		return obstacle;
	}

	public Object subject() {
		return subject;
	}

	public String toString() {
		return ToString.toString(this);
	}

	public static class Collector implements EventListener<CollisionDetected> {

		public Collector() {
			EventContext.register(this);
		}

		public List<CollisionDetected> events = new ArrayList<>();

		@Override
		public void handle(CollisionDetected event) {
			events.add(event);
		}

	}

}
