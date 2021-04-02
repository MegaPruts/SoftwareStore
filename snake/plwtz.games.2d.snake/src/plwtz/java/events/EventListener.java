package plwtz.java.events;

public interface EventListener<E extends Event> {

	void handle(E event);

}
