package com.darwin.demo.ui;

import java.awt.Shape;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class ClockFace extends JComponent {

	private ClockUIApplication clock;

	protected abstract Shape getShape();

	public void setApplication(ClockUIApplication clock) {
		this.clock = clock;
	}

	final protected int getHour() {
		return clock == null ? 0 : clock.getHour();
	}

	final protected int getMinute() {
		return clock == null ? 0 : clock.getMinute();
	}

	final protected int getSecond() {
		return clock == null ? 0 : clock.getSecond();
	}
}
