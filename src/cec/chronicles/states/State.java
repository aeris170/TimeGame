package cec.chronicles.states;

import java.awt.Graphics;

import cec.chronicles.Game;

/**
 * States.
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public abstract class State {

	private static State currentState = null;

	/**
	 * Sets the state to the passed value
	 * 
	 * @param state
	 */
	public static void setState(State state) {
		currentState = state;
	}

	/**
	 * @return The current state
	 */
	public static State getState() {
		return currentState;
	}

	// ABSTRACT PART
	public static Game game;

	/**
	 * Constructor
	 * 
	 * @param game
	 */
	public State(Game game) {
		State.game = game;
	}

	/**
	 * Updates the State and its components
	 */
	public abstract void tick();

	/**
	 * Draws the graphics object g to the canvas(Display class)
	 * 
	 * @param g
	 */
	public abstract void render(Graphics g);
}