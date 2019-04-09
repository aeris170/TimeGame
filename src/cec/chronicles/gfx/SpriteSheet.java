package cec.chronicles.gfx;

import java.awt.image.BufferedImage;

/**
 * Creates or crops an image.
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public class SpriteSheet {

	private BufferedImage sheet;

	/**
	 * Constructor
	 * 
	 * @param sheet
	 */
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	/**
	 * Crops the image that it used on
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}