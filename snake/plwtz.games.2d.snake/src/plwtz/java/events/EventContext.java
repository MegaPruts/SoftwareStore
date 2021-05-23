package plwtz.java.events;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class EventContext {
	private static Logger log = LoggerFactory.getLogger(EventContext.class);
//	static 99Logger log = Logger.getLogger(log4jExample.class.getName());
	  
	private static final EventContext INSTANCE = new EventContext();

	public static void reset() {
		INSTANCE._reset();
	}

	public static <E extends Event> void register(EventListener<E> subject) {
		INSTANCE._register(subject);
	}

	public static <E extends Event> void execute(E event) {
		INSTANCE._execute(event);
	}

	private final Map<String, Set<EventHandlingMethod>> eventHandlingMethods = new HashMap<>();

	private void _reset() {
		eventHandlingMethods.clear();
	}

	public <E1 extends Event> void _execute(E1 event) {
		Set<EventHandlingMethod> _eventHandlers = eventHandlingMethods.get(className(event.getClass()));
		if (_eventHandlers != null)
			_eventHandlers.stream().forEach(h -> handle(h, event));
	}

	private <E extends Event> void handle(EventHandlingMethod methodHandler, E event) {
		if (methodHandler.handlerInstanceReference.get() == null) {
			log.info(() -> String.format("%s : removing eventHandler", this.getClass().getSimpleName()));
			eventHandlingMethods.get(className(event.getClass())).remove(methodHandler);
			return;
		}

		EventListener<E> handler = (EventListener<E>) methodHandler.handlerInstanceReference.get();
		log.info(() -> String.format("%s.handle(%s)", handler.getClass().getSimpleName(), event.toString()));
		try {
			methodHandler.methodInstance.invoke(handler, event);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error(() -> e.getMessage());
			e.printStackTrace();
		}
	}

	private <E4 extends Event> void _register(EventListener<E4> handlerInstance) {
		List<Method> methods = Stream
				.of(handlerInstance.getClass().getMethods()).filter(m -> m.getName().equals("handle")
						&& m.getParameterTypes().length == 1 && !m.getParameterTypes()[0].isInterface())
				.collect(Collectors.toList());

		methods.stream().forEach(m -> _register(handlerInstance, m, m.getParameterTypes()[0]));
	}

	private <E4 extends Event> void _register(EventListener<E4> handlerInstance, Method methodInstance,
			Class<?> eventClassToHandle) {
		String eventClassName = className(eventClassToHandle);
		if (!eventHandlingMethods.containsKey(eventClassName))
			eventHandlingMethods.put(eventClassName, new HashSet<>());
		eventHandlingMethods.get(eventClassName).add(new EventHandlingMethod(handlerInstance, methodInstance));
	}

	private String className(Class<?> clazz) {
		return clazz.getName();
	}

	private class EventHandlingMethod<E extends Event> {

		private WeakReference<EventListener<E>> handlerInstanceReference;
		private Method methodInstance;

		private EventHandlingMethod(EventListener<E> handlerInstance, Method methodInstance) {
			this.handlerInstanceReference = new WeakReference<EventListener<E>>(handlerInstance);
			this.methodInstance = methodInstance;
		}
	}

}
