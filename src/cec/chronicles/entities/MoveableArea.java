package cec.chronicles.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import cec.chronicles.Game;
import cec.chronicles.ID.ID;
import cec.chronicles.states.GameState;

/**
 * Moveable Areas in the screen
 * 
 * @author Doða Oruç, Unsal Ozturk
 * @version 12.5.2017
 */
public class MoveableArea extends Entity {

	private static Timer timer;

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
	public MoveableArea(Game game, float x, float y, int width, int height, ID id) {
		super(x, y, width, height, id);
	}

	/**
	 * After a brief moment, removes the MoveableArea object, hence flashing the
	 * object for once.
	 */
	public static void flash() {
		MoveableArea area = new MoveableArea(GameState.game, 0, Game.height * 17 / 36, Game.width - GameState.player.getWidth(), 450, ID.MoveableArea);
		GameState.getHandler().addObject(area);
		timer = new Timer(100, null);
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.getHandler().removeObject(area);
				timer.stop();
			}
		};
		timer = new Timer(50, listener);
		timer.start();
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
		g.setColor(Color.YELLOW);
		// g.fillRect((int)x, (int)y, width, height);
	}
}