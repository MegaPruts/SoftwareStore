package com.zetcode;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyBoardAdapter extends KeyAdapter {

	private Map<Integer, Runnable> commands = new HashMap<>();

	public void on(Integer keyCode, Runnable command) {
		commands.put(keyCode, command);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (commands.containsKey(e.getKeyCode()))
			commands.get(e.getKeyCode()).run();
	}
}
