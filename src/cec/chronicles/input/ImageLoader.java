
package cec.chronicles.input;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Standard image loading using java.io.
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public class ImageLoader {

	/**
	 * Constructor
	 * 
	 * @param path
	 * @return
	 */
	public static BufferedImage LoadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}