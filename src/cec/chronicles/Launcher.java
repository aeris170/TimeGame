package cec.chronicles;

import javax.swing.ImageIcon;

import cec.menu.Menu;

/**
 * Creates a menu and a game object. Runs the game if a new game is started.
 * 
 * @author Doða Oruç, Unsal Ozturk
 * @version 01.05.2017
 */
public class Launcher {
	// Unsal Ozturk: The launcher now constructs a menu.
	public static Game game;
	public static Menu menu;

	/**
	 * Constructor
	 */
	public Launcher() {
		menu = new Menu(1920, 1080);
	}

	/**
	 * Constructs a Game object then calls it's start() method
	 */
	public static void run() {
		game = new Game("Time ()", menu.getOptions().getResolution().getX(), menu.getOptions().getResolution().getY());
		game.start();
	}

	/**
	 * Runs the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Launcher();
		System.out.println(new ImageIcon("textures/dialog icons/davud_head.ico"));
	}
}