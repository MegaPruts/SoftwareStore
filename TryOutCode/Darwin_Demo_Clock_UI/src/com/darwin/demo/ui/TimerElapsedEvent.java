package com.darwin.demo.ui;
import java.util.Date;


public class TimerElapsedEvent extends ProcessEvent implements ITimerEvent{

	private Date time;

	public TimerElapsedEvent(Date time) {
		this.time = time;
	}

	public Date getTime() {
		final String METHOD_SIGNATURE = "getTime";
		return time;
	}

}
