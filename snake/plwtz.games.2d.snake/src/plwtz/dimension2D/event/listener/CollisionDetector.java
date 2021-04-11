package plwtz.dimension2D.event.listener;

import java.util.List;
import java.util.function.Consumer;

import plwtz.dimension2D.Position;
import plwtz.dimension2D.PositionList;
import plwtz.dimension2D.event.AllocatePosition;
import plwtz.dimension2D.event.CollisionDetected;
import plwtz.java.events.EventListener;
import plwtz.java.events.EventContext;

public class CollisionDetector implements EventListener<AllocatePosition> {
	public static final Consumer<CollisionDetected> collisionDetectedEvent = (p) -> EventContext.execute(p);

	private PositionList subject;

	public CollisionDetector(PositionList subject) {
		this.subject = subject;
		EventContext.register(this);
	}

	@Override
	public void handle(AllocatePosition event) {
		if (occupiesPosition(subject.positionList(), event.position())) {
			System.out.printf("CollisionDetected: Object(%s) onPosition %s against %s\n",
					event.subject().getClass().getSimpleName(), event.position(), subject.getClass().getSimpleName());
			collisionDetectedEvent.accept(new CollisionDetected(event.subject(), event.position(), subject));
		}
	}

	public static boolean occupiesPosition(List<Position> positionList, Position targetPosition) {
		return positionList.stream().filter(pl -> pl.equals(targetPosition)).count() == 1;
	}

}
