package cec.chronicles.map;

import java.awt.image.BufferedImage;

import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Maps
 * 
 * @author Doða Oruç, Kaan Ünlü, Unsal Ozturk
 * @verison 7.5.2017
 */
public class Map {

	private Timer timer;
	private int currentFrame;
	private BufferedImage map;
	private BufferedImage[] frames;

	/**
	 * Constructor
	 * 
	 * @param map
	 */
	public Map(BufferedImage map) {
		this.map = map;
	}

	/**
	 * Constructor
	 * 
	 * @param frames
	 */
	public Map(BufferedImage[] frames) {
		this.frames = frames;
		this.map = frames[0];
		timer = new Timer(300, null);
	}

	/**
	 * Sets the map to the passed value
	 * 
	 * @param i
	 */
	public void setMap(int i) {
		map = frames[i];
	}

	/**
	 * Starts the animation of a map
	 */
	public void startAnim() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentFrame++;
				currentFrame = currentFrame % frames.length;
				setMap(currentFrame);
			}
		};
		timer = new Timer(300, listener);
		timer.start();
	}

	public void stopAnim() {
		map = frames[0];
		timer.stop();
	}

	/**
	 * Crops the image it is used on
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage crop(int x, int y, int width, int height) {
		return map.getSubimage(x, y, width, height);
	}

	/**
	 * 
	 */
	public void tick() {
	}

	/**
	 * Draws the image on to the canvas
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		g.drawImage(map, (int) 0, (int) 0, 1200, 650, null);
	}
}