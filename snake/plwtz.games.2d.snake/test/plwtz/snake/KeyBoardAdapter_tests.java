package plwtz.snake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import plwtz.awt.event.keyboard.KeyBoardAdapter;
import plwtz.dimension2D.Direction;
import plwtz.dimension2D.event.SetDirectionEvent;
import plwtz.dimension2D.event.SetDirectionEvent.EventCollector;
import plwtz.java.events.EventContext;
//import plwtz.snake.ui.KeyBoardAdapter;

public class KeyBoardAdapter_tests {
	@BeforeEach
	public void beforeEach() {
		EventContext.reset();
	}

	@Test
	public void test_KeyBoardAdapter_broadCasting_directions() {
		KeyBoardAdapter keyboardAdapter = new KeyBoardAdapter();
		keyboardAdapter.on(KeyEvent.VK_DOWN, SetDirectionEvent.broadcastSetSouthDirectionCommand);
		keyboardAdapter.on(KeyEvent.VK_UP, SetDirectionEvent.broadcastSetNorthDirectionCommand);
		keyboardAdapter.on(KeyEvent.VK_LEFT, SetDirectionEvent.broadcastSetWestDirectionCommand);
		keyboardAdapter.on(KeyEvent.VK_RIGHT, SetDirectionEvent.broadcastSetEastDirectionCommand);

		KeyEvent keyDownEvent = EasyMock.createMock(KeyEvent.class);
		EasyMock.expect(keyDownEvent.getKeyCode()).andReturn(KeyEvent.VK_DOWN).anyTimes();
		EasyMock.replay(keyDownEvent);

		KeyEvent keyUpEvent = EasyMock.createMock(KeyEvent.class);
		EasyMock.expect(keyUpEvent.getKeyCode()).andReturn(KeyEvent.VK_UP).anyTimes();
		EasyMock.replay(keyUpEvent);

		KeyEvent keyRightEvent = EasyMock.createMock(KeyEvent.class);
		EasyMock.expect(keyRightEvent.getKeyCode()).andReturn(KeyEvent.VK_RIGHT).anyTimes();
		EasyMock.replay(keyRightEvent);

		KeyEvent keyLeftEvent = EasyMock.createMock(KeyEvent.class);
		EasyMock.expect(keyLeftEvent.getKeyCode()).andReturn(KeyEvent.VK_LEFT).anyTimes();
		EasyMock.replay(keyLeftEvent);

		EventCollector directionEventCollector = new SetDirectionEvent.EventCollector();

		
		KeyEvent keyEvent=keyDownEvent;
		Direction expectedDirection = Direction.SOUTH;
		directionEventCollector.events.clear();
		keyboardAdapter.keyPressed(keyEvent);
		assertTrue(directionEventCollector.events.size() == 1);
		assertEquals(expectedDirection, directionEventCollector.events.get(0).direction());

		keyEvent=keyUpEvent;
		expectedDirection = Direction.NORTH;
		directionEventCollector.events.clear();
		keyboardAdapter.keyPressed(keyEvent);
		assertTrue(directionEventCollector.events.size() == 1);
		assertEquals(expectedDirection, directionEventCollector.events.get(0).direction());


		keyEvent=keyLeftEvent;
		expectedDirection = Direction.WEST;
		directionEventCollector.events.clear();
		keyboardAdapter.keyPressed(keyEvent);
		assertTrue(directionEventCollector.events.size() == 1);
		assertEquals(expectedDirection, directionEventCollector.events.get(0).direction());

		keyEvent=keyRightEvent;
		expectedDirection = Direction.EAST;
		directionEventCollector.events.clear();
		keyboardAdapter.keyPressed(keyEvent);
		assertTrue(directionEventCollector.events.size() == 1);
		assertEquals(expectedDirection, directionEventCollector.events.get(0).direction());

	}

}
