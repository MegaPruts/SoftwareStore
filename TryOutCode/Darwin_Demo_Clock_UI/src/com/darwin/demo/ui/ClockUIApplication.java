package com.darwin.demo.ui;

import java.awt.Point;
import java.awt.geom.Area;
import java.util.Date;

import javax.swing.JFrame;

import com.darwin.demo.ui.events.IUserInterfaceEvent;
import com.darwin.demo.ui.events.ObjectMoved2D;
import com.darwin.ui.input.mouse.IMouseEventConsumer;
import com.darwin.ui.input.mouse.awt.MouseAdapter;
import com.sun.jna.examples.WindowUtils;

public class ClockUIApplication implements Runnable, IMouseEventConsumer {
	public static String PACKAGE_NAME = "com.darwin.demo.ui";

	public static String CLASS_NAME = "ClockUIApplication";

	private int minute;
	private int hour;
	private int second;

	private ClockFace face;
	private JFrame frame;
	private MouseAdapter mouseInputAdapter;

	public ClockUIApplication() {
		ProcessContext uiContext = new ProcessContext();
		uiContext.onEventType_Notify(ObjectMoved2D.class, this);

		// And display it in an ApplicationWindow
		frame = new JFrame("Clock UI Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mouseInputAdapter = new MouseAdapter(uiContext, frame, this);
		frame.addMouseListener(mouseInputAdapter);
		frame.addMouseMotionListener(mouseInputAdapter);
		frame.addMouseWheelListener(mouseInputAdapter);
		frame.setName(getID());

		// remove WindowDecoration aka WindowTitleBar & Min/Max/Close-buttons
		frame.setUndecorated(true);
		frame.setLocation(100, 100);
	}

	public void setFace(ClockFace face) {
		this.face = face;
		this.face.setApplication(this);
	}

	public void run() {
		frame.setVisible(true);
		java.awt.Shape mask = new Area(face.getShape());
		WindowUtils.setWindowMask(frame, mask);
		frame.getContentPane().add(face);
		frame.pack();
	}

	public void setTime(Date date) {
		minute = date.getMinutes();
		hour = date.getHours();
		second = date.getSeconds();
		face.repaint();
	}

	void setLocation(Point point) {
		frame.setLocation(point);
	}

	public String getID() {
		String retValue = this.getClass().getName() + "@" + this.hashCode();
		return retValue;
	}

	public void setContext(ProcessContext context) {
		if (mouseInputAdapter != null)
			mouseInputAdapter.setContext(context);
	}

	public void eventFired(IProcessEvent processEvent) {
		if (processEvent instanceof TimerElapsedEvent)
			processEvent((TimerElapsedEvent) processEvent);
		else if (processEvent instanceof ObjectMoved2D) {
			move(((ObjectMoved2D) processEvent).getMove());
		}
	}

	private void move(Point displacement) {
		Point location = frame.getLocation();
		location.translate(displacement.x, displacement.y);
		setLocation(location);
	}

	public void processEvent(TimerElapsedEvent timerElapsedEvent) {
		setTime(timerElapsedEvent.getTime());
	}

	public void processEvent(ObjectMoved2D movement) {
		move(movement.getMove());
	}

	public void processEvent(IUserInterfaceEvent processEvent) {
		final String METHOD_NAME = "processEvent";
		System.err.println(CLASS_NAME + "." + METHOD_NAME
				+ " is not yet implemented!!!");

	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}
}
