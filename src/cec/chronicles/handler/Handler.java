package cec.chronicles.handler;

import cec.chronicles.entities.Entity;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * GameObject(Entity) Handler, conventional type.
 * 
 * @author Doða Oruç
 * @version 07.5.2017
 */
public class Handler {

	public LinkedList<Entity> object = new LinkedList<Entity>();

	/**
	 * Cycles through the LinkedList
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);

			tempObject.tick();
		}
	}

	/**
	 * Draws every individual object inside the handler to the canvas
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	/**
	 * Adds a game object(entity) to the handler
	 * 
	 * @param object
	 */
	public void addObject(Entity object) {
		this.object.add(object);
	}

	/**
	 * Removes a game object(entity) from the handler
	 * 
	 * @param object
	 */
	public void removeObject(Entity object) {
		this.object.remove(object);
	}
}