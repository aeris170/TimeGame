package cec.chronicles.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import cec.chronicles.Game;
import cec.chronicles.ID.ID;
import cec.chronicles.gfx.Animation;
import cec.chronicles.gfx.Assets;
import cec.chronicles.input.SoundLoader;
import cec.chronicles.states.GameState;

/**
 * Every uncontrollable character, NPC.
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public class David extends Creature {

	private boolean facingRight;
	private boolean hasReached;

	private int counter;

	// Animation time
	private Animation animRight, animLeft;

	/**
	 * Constructor
	 * 
	 * @param game
	 * @param x
	 * @param y
	 * @param id
	 */
	public David(Game game, float x, float y, ID id) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, id);
		hasReached = false;
		counter = 0;

		// Animation
		animRight = new Animation(150, Assets.david_right);
		animLeft = new Animation(150, Assets.david_left);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.entities.Entity#getBounds()
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 75, 200);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.entities.creatures.Creature#move()
	 */
	public void move() {
		counter++;

		if (counter > 30) {
			SoundLoader.step.loop(0);
			counter = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.entities.Entity#tick()
	 */
	public void tick() {
		// Animations
		animRight.tick();
		animLeft.tick();
		// Movement
		x += xMove;
		y += yMove;

		float distance = (float) Math.sqrt((x - GameState.player.getX()) * (x - GameState.player.getX())
				+ (y - GameState.player.getY()) * (y - GameState.player.getY()));

		if (!hasReached && distance >= 40) {
			float diffX = x - GameState.player.getX();
			float diffY = y - GameState.player.getY();

			xMove = (float) ((-1.0 / distance) * diffX);
			yMove = (float) ((-1.0 / distance) * diffY);
			
			move();
		} else {
			xMove = 0;
			yMove = 0;
			hasReached = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.entities.Entity#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		if (xMove == 0 && yMove == 0) {
			if (facingRight) {
				g.drawImage(animRight.getFirstFrame(), (int) x, (int) y, 75, 200, null);
			} else {
				g.drawImage(animLeft.getFirstFrame(), (int) x, (int) y, 75, 200, null);
			}

		} else if (xMove <= 0 && !facingRight) {
			g.drawImage(getCurrentAnimationFrame(animLeft), (int) x, (int) y, 75, 200, null);
		} else {
			g.drawImage(getCurrentAnimationFrame(animRight), (int) x, (int) y, 75, 200, null);
		}
	}

	/**
	 * Returns the current animation frame
	 * 
	 * @param a
	 * @return The current animation frame
	 */
	private BufferedImage getCurrentAnimationFrame(Animation a) {
		return a.getCurrentFrame();
	}
}