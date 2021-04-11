package plwtz.game.event;

import plwtz.java.ToString;
import plwtz.java.events.Event;

public class StopMoveGeneratorEvent implements Event {

	public interface Handler {
		void handle(StopMoveGeneratorEvent stopMoveGeneratorEvent);
	}

	public String toString() {
		return ToString.toString(this);
	}
}