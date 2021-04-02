package plwtz.snake;

import java.util.ArrayList;
import java.util.List;

import plwtz.dimension2D.event.CollisionDetected;
import plwtz.java.events.EventListener;
import plwtz.java.events.EventContext;

public class CollisionDetectorCollector implements EventListener<CollisionDetected> {

	public CollisionDetectorCollector() {
		EventContext.register(this);
	}

	public List<CollisionDetected> events = new ArrayList<>();

	@Override
	public void handle(CollisionDetected event) {
		events.add(event);
	}

}
