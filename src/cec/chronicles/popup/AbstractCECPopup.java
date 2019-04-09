package cec.chronicles.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cec.chronicles.states.GameState;

/**
 * Creates a pop-up for puzzles
 * 
 * @author Pýnar Ayaz, Ünsal Öztürk
 * @version 12.5.2017
 */
public class AbstractCECPopup extends JPanel {

	private static final long serialVersionUID = 2047023663541235647L;
	private JFrame frame;
	private JButton back;

	/**
	 * Constructor
	 * 
	 * @param frame
	 */
	public AbstractCECPopup(JFrame frame) {
		this.frame = frame;
		back = new JButton("Done!");
		back.addActionListener(new BackListener());
		back.setEnabled(false);
	}

	/**
	 * Adds the puzzle panel and renders the canvas of the game invisible
	 */
	public void drawPanel() {
		frame.getContentPane().add(this);
		GameState.game.getDisplay().getCanvas().setVisible(false);
		frame.repaint();
		frame.revalidate();
	}

	// generic retard: rahmetli ozal bazi isler icin 24 saatlik yasa
	// cikarirdi... sonra silerdi...
	/**
	 * Removes the puzzle panel from the frame then adds the canvas of the game
	 */
	public void disposePanel() {
		frame.getContentPane().remove(this);
		GameState.game.getDisplay().getCanvas().setVisible(true);
		GameState.game.getDisplay().getCanvas().setFocusable(true);
		GameState.game.getDisplay().getCanvas().requestFocus();
		GameState.game.getDisplay().getCanvas().setFocusable(false);
		frame.repaint();
		frame.revalidate();
	}

	/**
	 * @return The back button
	 */
	public JButton getDoneButton() {
		return back;
	}

	/**
	 * Enables the back button
	 */
	public void enableBackButton() {
		back.setEnabled(true);
	}

	private class BackListener implements ActionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			disposePanel();
		}
	}
}