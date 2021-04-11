package plwtz.game.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import plwtz.java.events.EventContext;
import plwtz.java.events.EventListener;

public class MoveGenerator
		implements ActionListener, EventListener<PauseEvent>, StopMoveGeneratorEvent.Handler, PauseEvent.Handler {
	public static final Runnable stop = () -> EventContext
			.execute(new StopMoveGeneratorEvent());

	private Timer timer;
	private boolean paused = true;

	private int delay;

	public MoveGenerator(int delay) {
		this.delay = delay;
		EventContext.register(this);
	}

	public void start() {
		timer = new Timer(delay, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!paused)
			MoveEvent.broadcastMoveCommand.run();
	}

	@Override
	public void handle(PauseEvent pauseEvent) {
		paused = paused ? false : true;
	}

	@Override
	public void handle(StopMoveGeneratorEvent stopMoveGeneratorEvent) {
		timer.stop();

	}

}
