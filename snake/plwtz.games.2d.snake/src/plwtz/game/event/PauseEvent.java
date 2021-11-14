package plwtz.game.event;

import plwtz.java.ToString;
import plwtz.java.events.Event;
import plwtz.java.events.EventContext;

public class PauseEvent implements Event {
	public static final Runnable broadcastPauseCommand = () -> EventContext.execute(new PauseEvent());

	public String toString() {
		return ToString.toString(this);
	}
}
