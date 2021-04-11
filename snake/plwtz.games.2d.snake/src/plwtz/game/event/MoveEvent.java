package plwtz.game.event;

import plwtz.java.ToString;
import plwtz.java.events.Event;
import plwtz.java.events.EventContext;

public class MoveEvent implements Event {
	public static final Runnable broadcastMoveCommand = () -> EventContext.execute(new MoveEvent());

	public interface Handler {
		void handle(MoveEvent moveEvent);

	}

	public String toString() {
		return ToString.toString(this);
	}
}
