package cec.chronicles.entities;

import cec.chronicles.ID.ID;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Everything you will see in the game window
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public abstract class Entity {

	protected float x, y;
	protected int width, height;
	protected ID id;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 */
	public Entity(float x, float y, int width, int height, ID id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}

	/**
	 * @return The identifier ID the entity has
	 */
	public ID getId() {
		return id;
	}

	/**
	 * Sets the identifier ID to the passed value
	 * 
	 * @param id
	 */
	public void setId(ID id) {
		this.id = id;
	}

	/**
	 * Updates the entity every 1/60th of a second
	 */
	public abstract void tick();

	/**
	 * @return A positional representation of the entity
	 */
	public abstract Rectangle getBounds();

	/**
	 * Draws the entity to the canvas(see Display class)
	 * 
	 * @param g
	 */
	public abstract void render(Graphics g);

	/**
	 * @return The top left corner of entity's x coordinate
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the top left corner of entity's x coordinate
	 * 
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return The top left corner of entity's y coordinate
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets the top left corner of entity's y coordinate
	 * 
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return The width of the entity
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the entity to the passed value
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return The height of the entity
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the entity to the passed value
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}