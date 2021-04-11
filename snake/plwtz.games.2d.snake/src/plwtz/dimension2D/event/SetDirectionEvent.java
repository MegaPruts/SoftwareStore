package plwtz.dimension2D.event;

import java.util.ArrayList;
import java.util.List;

import plwtz.dimension2D.Direction;
import plwtz.java.ToString;
import plwtz.java.events.Event;
import plwtz.java.events.EventContext;
import plwtz.java.events.EventListener;

public class SetDirectionEvent implements Event {

	public final static Runnable broadcastSetEastDirectionCommand = () -> EventContext
			.execute(new SetDirectionEvent(Direction.EAST));
	public final static Runnable broadcastSetWestDirectionCommand = () -> EventContext
			.execute(new SetDirectionEvent(Direction.WEST));
	public final static Runnable broadcastSetNorthDirectionCommand = () -> EventContext
			.execute(new SetDirectionEvent(Direction.NORTH));
	public final static Runnable broadcastSetSouthDirectionCommand = () -> EventContext
			.execute(new SetDirectionEvent(Direction.SOUTH));

	private Direction direction;

	public SetDirectionEvent(Direction direction) {
		this.direction = direction;
	}

	public Direction direction() {
		return direction;
	}

	public String toString() {
		return ToString.toString(this);
	}

	public interface Handler {
		void handle(SetDirectionEvent moveEvent);
	}

	public static class EventCollector implements EventListener<SetDirectionEvent> {

		public EventCollector() {
			EventContext.register(this);
		}

		public List<SetDirectionEvent> events = new ArrayList<>();

		@Override
		public void handle(SetDirectionEvent event) {
			events.add(event);
		}

	}

}
