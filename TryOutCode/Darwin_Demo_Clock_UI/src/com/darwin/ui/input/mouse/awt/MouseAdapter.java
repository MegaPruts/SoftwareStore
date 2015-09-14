package com.darwin.ui.input.mouse.awt;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.darwin.demo.ui.ProcessContext;
import com.darwin.demo.ui.events.ObjectMoved2D;
import com.darwin.ui.input.mouse.IMouseEventConsumer;
import com.darwin.ui.input.mouse.IMouseEventProducer;
/**
 * 
 * @author Paul
 *
 */
public class MouseAdapter implements MouseListener, MouseMotionListener,
		MouseWheelListener, IMouseEventProducer {
//	private static Log log = Log.getLog(MouseInputAdapter.class);

	private ProcessContext context;

	private IMouseEventConsumer mouseEventConsumer;

	private Point mousePressedPoint;

	private MouseEvent event;

	private Point mousePosition;

	public MouseAdapter(ProcessContext context, Container awtContainer,
			IMouseEventConsumer mouseEventConsumer) {
		setContext(context);
		// context.register(this);
		this.mouseEventConsumer = mouseEventConsumer;
	}

	/**
	 * Invoked when the mouse has been clicked on a component.
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 */
	public void mousePressed(MouseEvent e) {
		mousePressedPoint = e.getComponent().getLocationOnScreen();
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse enters a component.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse exits a component.
	 */
	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

		Point mouseMovement = e.getPoint();

		mouseMovement.translate(-mousePosition.x, -mousePosition.y);

		sendEvent(new ObjectMoved2D(mouseMovement));
	}

	private void sendEvent(ObjectMoved2D event) {
		context.fireEvent(event);
	}

	public void mouseMoved(MouseEvent e) {
		mousePosition = e.getPoint();
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	public void clearContext() {
		this.context = null;
	}

	public String getID() {
		return this.getClass().getName();
	}

	public void setContext(ProcessContext context) {
		this.context = context;
	}
}
