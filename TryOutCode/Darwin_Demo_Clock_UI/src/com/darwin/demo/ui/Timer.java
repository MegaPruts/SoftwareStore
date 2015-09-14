package com.darwin.demo.ui;

import java.util.Date;

public class Timer extends ProcessComponent implements Runnable, IEventProducer {

	private Second frequency;

	private boolean run;

	public Timer setFrequency(Second seconds) {
		this.frequency = seconds;
		return this;
	}

	/**
	 * 
	 * This is the method that will be called on/via Thread.start()
	 * 
	 */
	public void run() {
		run = true;
		while (run) {
			if (context != null)
				synchronized (context) {
					context.fireEvent(((IProcessEvent) new TimerElapsedEvent(
							new Date())));
				}

			try {
				Thread.sleep(frequency.getSeconds() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void clearContext() {
		context = null;
	}

}
