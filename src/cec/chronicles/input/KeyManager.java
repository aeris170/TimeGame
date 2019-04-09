package cec.chronicles.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Appropriate key listener for the game engine.
 * 
 * @author Doða Oruç, Unsal Ozturk
 * @version 01.05.2017
 */
public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, down, left, right, use, escape;

	/**
	 * Constructor
	 */
	public KeyManager() {
		keys = new boolean[256];
	}

	/**
	 * Initializes the key events
	 */
	public void tick() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		use = keys[KeyEvent.VK_E];
		escape = keys[KeyEvent.VK_ESCAPE]; // Unsal Ozturk: Added escape to the
											// list of listened keys.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	// Unsal Ozturk: Added this method so that the keylistener escapes from a
	// constant state of ghost "escape" events.
	/**
	 * Disables the escape key
	 */
	public void disableEscape() {
		keys[KeyEvent.VK_ESCAPE] = false;
	}

	/**
	 * Disables the use key
	 */
	public void disableE() {
		keys[KeyEvent.VK_E] = false;
	}
}