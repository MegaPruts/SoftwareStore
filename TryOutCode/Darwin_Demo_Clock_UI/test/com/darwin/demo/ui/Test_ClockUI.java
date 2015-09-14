package com.darwin.demo.ui;

import java.awt.Dimension;

public class Test_ClockUI {
	public static void main(String[] args) {
		new Test_ClockUI().run();
		// testSuite = new TestSuite();
	}

	// private Log log = Log.getLog(Test_ClockUI.class);

	private void run() {
		// final String METHOD_SIGNATURE = "run()";
		// log.enteringMethod(METHOD_SIGNATURE);

		ClockUIApplication romanClock = getRomanClockApp();

		ClockUIApplication binaryClock = getBinaryClockApp();

		Timer timer = new Timer().setFrequency(new Second(1));
		new Thread(timer, timer.toString()).start();

		ProcessContext processContext = new ProcessContext();
		processContext.onEventType_Notify(TimerElapsedEvent.class, romanClock);
		processContext.onEventType_Notify(TimerElapsedEvent.class, binaryClock);
		processContext.register(timer);

		// log.leavingMethod(METHOD_SIGNATURE);
	}

	private ClockUIApplication getRomanClockApp() {
		// final String METHOD_SIGNATURE = "getRomanClockApp(ProcessContext
		// processContext)";
		// log.enteringMethod(METHOD_SIGNATURE);
		ClockUIApplication clockApp = new ClockUIApplication();
		clockApp.setFace(new RomanClockFace(new Dimension(150, 150)));
		Thread clockUI_Process = new Thread(clockApp, clockApp.getClass()
				.getName());
		clockUI_Process.setDaemon(true);
		clockUI_Process.start();
		// log.leavingMethod(METHOD_SIGNATURE);
		return clockApp;
	}

	private ClockUIApplication getBinaryClockApp() {
		// final String METHOD_SIGNATURE = "getBinaryClockApp(ProcessContext
		// processContext)";
		// log.enteringMethod(METHOD_SIGNATURE);
		ClockUIApplication clockApp = new ClockUIApplication();
		clockApp.setFace(new BinaryClockFace(new Dimension(100, 100)));
		Thread clockUI_Process = new Thread(clockApp, clockApp.getClass()
				.getName());
		clockUI_Process.setDaemon(true);
		clockUI_Process.start();
		// log.leavingMethod(METHOD_SIGNATURE);
		return clockApp;
	}
}