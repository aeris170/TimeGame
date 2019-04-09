package cec.chronicles.gfx;

import java.awt.image.BufferedImage;

/**
 * Cycles through an array's indexes to create an illusion of motion.
 * 
 * @author Doğa Oruç
 * @version 01.05.2017
 */
public class Animation {

	public static final int DEFAULT_ANIMATION_TRANSITION_CHARACTERS = 150;
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;

	/**
	 * Constructor
	 * 
	 * @param speed
	 * @param frames
	 */
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}

	/**
	 * Cycles through the frames array
	 */
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++;
			timer = 0;
			if (index >= frames.length) {
				index = 0;
			}
		}
	}

	/**
	 * @return The buffered image at the current index
	 */
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	/**
	 * @return The first image in the array
	 */
	public BufferedImage getFirstFrame() {
		return frames[0];
	}
}