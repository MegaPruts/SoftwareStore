package plwtz.game.event;

import plwtz.java.ToString;
import plwtz.java.events.Event;
import plwtz.java.events.EventContext;

public class GameOverEvent implements Event {
	public static final Runnable broadcastGameOverCommand = () -> EventContext.execute(new GameOverEvent());

	public String toString() {
		return ToString.toString(this);
	}
}
