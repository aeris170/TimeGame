package cec.menu;

import java.awt.event.ActionListener;
/**
 * Defines a custom ActionListener for the menu hierarchy.
 * @author Unsal Ozturk
 * @version 01052017
 */
import javax.swing.JFrame;

import cec.chronicles.input.SoundLoader;

import java.awt.event.ActionEvent;

/**
 * Defines a general listener for menu buttons for panels.
 * 
 * @author Unsal Ozturk
 * @version 05.07.2017
 */
public class MenuListener implements ActionListener {
	private AbstractCECPanel menu;
	private AbstractCECPanel panel;

	public MenuListener(AbstractCECPanel menu, AbstractCECPanel panel) {
		this.menu = menu;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame tmp = menu.getFrame();
		SoundLoader.menuClick.loop(0);
		tmp.remove(menu);
		tmp.add(panel);
		tmp.repaint();
		tmp.revalidate();
	}
}