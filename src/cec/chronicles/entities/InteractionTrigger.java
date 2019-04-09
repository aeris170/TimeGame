package cec.chronicles.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cec.chronicles.Game;
import cec.chronicles.ID.ID;

/**
 * Interactions
 * 
 * @author Faruk Oruc, Do�a Oru�
 * @version 12.5.2017
 */
public class InteractionTrigger extends Entity {

	/**
	 * Constructor
	 *
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 */
	public InteractionTrigger(Game game, float x, float y, int width, int height, ID id) {
		super(x, y, width, height, id);
	}

	/* (non-Javadoc)
	 * @see cec.chronicles.entities.Entity#getBounds() */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	/* (non-Javadoc)
	 * @see cec.chronicles.entities.Entity#tick() */
	@Override
	public void tick() {}

	/* (non-Javadoc)
	 * @see cec.chronicles.entities.Entity#render(java.awt.Graphics) */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		// g.drawRect((int) x, (int) y, width, height);
	}
}