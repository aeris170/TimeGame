package cec.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/**
 * Defines a menu animation with a delay given a set of animation frames and a
 * panel to draw the animation on.
 * 
 * @author Unsal Ozturk
 * @version 07.05.2017
 */
public class MenuAnimation {
	private int currentImageIndex;
	private int noOfFrames;
	private AbstractCECPanel p;
	private Timer timer;
	private BufferedImage[] frames;

	public MenuAnimation(int delay, int i, String str, AbstractCECPanel p) {
		currentImageIndex = 0;
		noOfFrames = i;
		this.p = p;
		timer = new Timer(delay, new TimerListener());
		frames = new BufferedImage[i];
		for (int c = 0; c < i; c++) {
			try {
				frames[c] = p.getImage(str + c, p.getFrame().getX(), p.getFrame().getY());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		timer.start();
	}

	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			p.setImageWithoutScaling(frames[currentImageIndex]);
			currentImageIndex++;
			currentImageIndex = currentImageIndex % noOfFrames;
			p.repaint();
			p.revalidate();
		}
	}
}