package com.darwin.demo.ui;

import java.util.Collection;
import java.util.Hashtable;

import com.darwin.ui.input.mouse.IMouseEventConsumer;

public class ProcessContext extends BaseComponent {
	// private static Log log = Log.getLog(ProcessContext.class);

	private Hashtable<String, IEventProducer> eventProducers = new Hashtable<String, IEventProducer>();

	private Hashtable<String, Hashtable<String, IEventListener>> eventConsumers = new Hashtable<String, Hashtable<String, IEventListener>>();

	synchronized public void register(IEventProducer eventProducer) {
		// final String METHOD_SIGNATURE = "register(IEventProducer
		// eventProducer)";
		// log.enteringMethod(METHOD_SIGNATURE);
		eventProducers.put(eventProducer.getID(), eventProducer);
		eventProducer.setContext(this);
		// log.leavingMethod(METHOD_SIGNATURE);
	}

	synchronized public void unregister(IEventProducer eventProducer) {
		// final String METHOD_SIGNATURE = "unregister(IEventProducer
		// eventProducer)";
		// log.enteringMethod(METHOD_SIGNATURE);
		if (eventProducers.containsKey(eventProducer.getID())) {
			eventProducers.get(eventProducer.getID()).clearContext();
			eventProducers.remove(eventProducer.getID());
		}
		// log.leavingMethod(METHOD_SIGNATURE);
	}

	synchronized public void onEventType_Notify(Class event,
			IEventListener eventConsumer) {
		// final String METHOD_SIGNATURE = "register(IEventConsumer
		// eventConsumer, Class eventClass)";
		// log.enteringMethod(METHOD_SIGNATURE);
		if (!eventConsumers.containsKey(event.getName()))
			eventConsumers.put(event.getName(),
					new Hashtable<String, IEventListener>());
		eventConsumers.get(event.getName()).put(eventConsumer.getID(),
				eventConsumer);
		// log.leavingMethod(METHOD_SIGNATURE);
	}

	/*
	 * TODO Paul Rijnvos - Accepting a notification should be done in a
	 * synchronized method. - Processing the events should be done in a separate
	 * thread !!!
	 */
	synchronized public void fireEvent(IProcessEvent processEvent) {
		// final String METHOD_SIGNATURE = "deliverEvent(final ProcessEvent
		// processEvent)";
		// log.enteringMethod(METHOD_SIGNATURE);
		if (!eventConsumers.containsKey(processEvent.getClass().getName())) {
			System.out.println("No eventConsumer found for "
					+ processEvent.getClass().getName());
			// log.leavingMethod(METHOD_SIGNATURE);
			return;
		}
		// log.info("processEvent is of type(" +
		// processEvent.getClass().getName()
		// + ")", null);
		for (IEventListener eventConsumer : (Collection<IEventListener>) eventConsumers
				.get(processEvent.getID()).values()) {
			processEvent(eventConsumer, processEvent);
		}
		// log.leavingMethod(METHOD_SIGNATURE);
	}

	synchronized private void processEvent(IEventListener eventConsumer,
			IProcessEvent processEvent) {
		// final String METHOD_SIGNATURE = "processEvent(IEventConsumer
		// eventConsumer, ProcessEvent processEvent)";
		// log.enteringMethod(METHOD_SIGNATURE);
		eventConsumer.eventFired(processEvent);
		// log.leavingMethod(METHOD_SIGNATURE);
	}

	public Collection<IEventListener> getConsumers(
			Class<IMouseEventConsumer> name) {
		final String METHOD_NAME = "getConsumers";
		// TODO Auto-generated method stub
		System.err.println(METHOD_NAME + " is not yet implemented!!!");
		return eventConsumers.get(name).values();
	}
}
