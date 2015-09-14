package com.darwin.demo.ui;

import java.awt.Dimension;
import java.awt.Point;

public class BinClockFace {
	public static void main(String[] args) {
		new BinClockFace().run();
	}

	private void run() {
		ProcessContext processContext = new ProcessContext();
		ClockUIApplication clockUIApplication = getClockUI();

		processContext.onEventType_Notify(TimerElapsedEvent.class,
				clockUIApplication);

		Timer timer = new Timer().setFrequency(new Second(1));
		new Thread(timer, timer.toString()).start();
		processContext.register(timer);

		clockUIApplication.setLocation(new Point(200, 200));

		processContext.unregister(timer);
		processContext.register(timer);

		// System.exit(0);
	}

	private ClockUIApplication getClockUI() {
		ClockUIApplication clockUI = new ClockUIApplication();
		clockUI.setFace(new BinaryClockFace(new Dimension(400, 400)));
		Thread clockUI_Process = new Thread(clockUI, clockUI.getClass()
				.getName());
		clockUI_Process.setDaemon(true);
		clockUI_Process.start();
		return clockUI;
	}

}