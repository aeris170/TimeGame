package cec.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cec.chronicles.input.SoundLoader;

/**
 * An abstract custom panel class which has a reference to the frame of the
 * panel, a scaled background image and a back button.
 * 
 * @author Unsal Ozturk
 * @version 01.05.2017
 */
public abstract class AbstractCECPanel extends JPanel {
	private JFrame frame;
	private AbstractCECPanel superPanel;
	private BackButton backButton;
	private BufferedImage image;
	private MenuAnimation anim;

	/**
	 * Constructs an abstract panel.
	 * 
	 * @param frame
	 * @param superPanel
	 */
	public AbstractCECPanel(JFrame frame, AbstractCECPanel superPanel) {
		this.frame = frame;
		this.superPanel = superPanel;
		image = null;
		anim = null;
		backButton = new BackButton(this, superPanel);
	}

	/**
	 * Returns the reference to the frame in which the panel is drawn in.
	 * 
	 * @return a reference to the frame in which the panel is drawn in.
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Returns the panel to a more main menu.
	 * 
	 * @return A reference to the panel to the main menu.
	 */
	public AbstractCECPanel getSuperPanel() {
		return superPanel;
	}

	/**
	 * Adds the default back button without a certain Layout Manager.
	 */
	public void addBackButton() {
		add(backButton);
	}

	/**
	 * Returns a reference to the default back button of the CEC Panel.
	 * 
	 * @return A reference to the default back button.
	 */
	public BackButton getBackButton() {
		return backButton;
	}

	/**
	 * Sets the background image.
	 * 
	 * @param str
	 *            The filename of the image under the assets/ directory
	 */
	public void setImage(String str) {
		try {
			image = ImageIO.read(new File("assets/" + str));
			image = getScaledImage(image, frame.getX(), frame.getY());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Parses an image and scales it from the directory.
	 * 
	 * @param str
	 *            Name of the image.
	 * @param x
	 *            Width of the panel.
	 * @param y
	 *            Height of the panel.
	 * @return Image.
	 */
	public BufferedImage getImage(String str, int x, int y) {
		try {
			image = ImageIO.read(new File("assets/" + str + ".png"));
			image = getScaledImage(image, x, y);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return image;
	}

	/**
	 * Sets an image as the background of the panel.
	 * 
	 * @param i
	 */
	public void setImageWithoutScaling(BufferedImage i) {
		image = i;
	}

	/**
	 * Overrides the paint component method so that the background is drawn.
	 * 
	 * @param g
	 *            Graphics object of the panel.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}

	/**
	 * Scales an image.
	 * 
	 * @param srcImg
	 *            Source image.
	 * @param w
	 *            Desired width.
	 * @param h
	 *            Desired height.
	 * @return Scaled image.
	 */
	public BufferedImage getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}

	/**
	 * Sets the background animation of a given menu.
	 * 
	 * @param a
	 *            Menu animation object
	 */
	public void setAnimation(MenuAnimation a) {
		anim = a;
	}

	/**
	 * Fires the backbutton.
	 */
	public void fireBackButton() {
		backButton.doClick();
	}

	public MenuAnimation getAnimation() {
		return anim;
	}

	/**
	 * Defines a default backbutton.
	 * 
	 * @author Unsal Ozturk
	 * @version 20170507
	 */
	private class BackButton extends JButton {
		private AbstractCECPanel superPanel;
		private AbstractCECPanel panel;

		public BackButton(AbstractCECPanel panel, AbstractCECPanel superPanel) {
			super("Back");
			this.panel = panel;
			this.superPanel = superPanel;
			addActionListener(new BackButtonListener(panel, superPanel));
		}

		private class BackButtonListener implements ActionListener {

			private AbstractCECPanel superPanel;
			private AbstractCECPanel panel;

			public BackButtonListener(AbstractCECPanel panel, AbstractCECPanel superPanel) {
				this.panel = panel;
				this.superPanel = superPanel;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame tmp = panel.getFrame();
				SoundLoader.menuClick.loop(0);
				tmp.remove(panel);
				tmp.add(superPanel);
				tmp.repaint();
				tmp.revalidate();
			}
		}
	}
}