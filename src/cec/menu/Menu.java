package cec.menu;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Defines a custom frame to put the menu hierarchy in.
 * 
 * @author Unsal Ozturk
 * @version 01.05.2017
 */
public class Menu extends JFrame {

	private MenuPanel mainMenu;
	private int x;
	private int y;

	public Menu(int x, int y) {
		this.x = x;
		this.y = y;
		mainMenu = new MenuPanel(this, x, y);
		add(mainMenu);
		setSize(new Dimension(x, y));
		setTitle("Time ()");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setVisible(true);
		// SoundLoader.menu_theme.loop(0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public GameOptions getOptions() {
		return mainMenu.getSettingsPanel().getOptions();
	}

	public MenuPanel getMenuPanel() {
		return mainMenu;
	}
}