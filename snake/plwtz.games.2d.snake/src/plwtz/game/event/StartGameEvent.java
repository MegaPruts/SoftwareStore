package plwtz.game.event;

import plwtz.java.ToString;
import plwtz.java.events.Event;
import plwtz.java.events.EventContext;

public class StartGameEvent implements Event {
	public static final Runnable startGame = () -> EventContext.execute(new StartGameEvent());

	public String toString() {
		return ToString.toString(this);
	}
}
