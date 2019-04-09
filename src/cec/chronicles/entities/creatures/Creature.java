package cec.chronicles.entities.creatures;

import cec.chronicles.entities.Entity;
import cec.chronicles.input.SoundLoader;
import cec.chronicles.Game;
import cec.chronicles.ID.ID;

/**
 * Every "living" or "controllable" being in the game.
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public abstract class Creature extends Entity {

	public static final int DEFAULT_HP = 10;
	public static final int DEFAULT_SPEED = 3;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;

	protected int health;
	protected int speed;

	private int counter;

	protected float xMove;
	protected float yMove;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 */
	public Creature(float x, float y, int width, int height, ID id) {
		super(x, y, width, height, id);
		health = DEFAULT_HP;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		counter = 0;
	}

	/**
	 * Changes the x and y coordinates depending on the creatures x and y
	 * plane-wise velocities then keeps the x and y coordinates of the creature
	 * inside the frame. Also plays a footstep sound
	 */
	public void move() {
		counter++;

		x += xMove;
		y += yMove;

		x = Game.clamp(x, 0, Game.width - getWidth());
		y = Game.clamp(y, Game.height * 17 / 36, 450);

		if (counter > 30 && (xMove != 0 || yMove != 0)) {
			SoundLoader.step.loop(0);
			counter = 0;
		}
	}

	// Getters and Setter below
	/**
	 * @return The x velocity of the creature
	 */
	public float getxMove() {
		return xMove;
	}

	/**
	 * Sets the x velocity to the passed variable
	 * 
	 * @param xMove
	 */
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	/**
	 * @return The y velocity of the creature
	 */
	public float getyMove() {
		return yMove;
	}

	/**
	 * Sets the y velocity to the passed variable
	 * 
	 * @param yMove
	 */
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	/**
	 * @return The "HEALTH" of the creature
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the "HEALTH" to the passed variable
	 * 
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return The speed of the creature
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed to the passed variable
	 * 
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}