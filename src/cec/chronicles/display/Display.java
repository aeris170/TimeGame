package cec.chronicles.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The class that creates the display.
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public class Display {

	private JFrame frame;
	private Canvas canvas;

	private String title;
	private static int width, height;

	/**
	 * Constructor
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public Display(String title, int width, int height) {
		this.title = title;
		Display.width = width;
		Display.height = height;

		createDisplay();
	}

	/**
	 * Creates a frame and canvas. Does necessary thing like setVisible(true),
	 * then adds the canvas into the frame
	 */
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();
	}

	/**
	 * @return The canvas where everything is drawn
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @return The frame which holds the canvas
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @return The width of the frame
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the height of the frame to passed value
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		Display.width = width;
	}

	/**
	 * @return The height of the frame
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the frame to passed value
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		Display.height = height;
	}
}