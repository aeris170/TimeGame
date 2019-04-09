package cec.chronicles;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import cec.chronicles.display.Display;
import cec.chronicles.gfx.Assets;
import cec.chronicles.handler.Handler;
import cec.chronicles.input.KeyManager;
import cec.chronicles.states.GameState;
import cec.chronicles.states.State;

/**
 * The game class where every other object is working with one another.
 * 
 * @author Doða Oruç, Unsal Ozturk
 * @version 01.05.2017
 */
public class Game implements Runnable {

	public static int width, height;
	public String title;
	public Handler handler;

	private Display display;
	private boolean running = false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private State gameState;
	private KeyManager keyManager;

	/**
	 * Constructor
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public Game(String title, int width, int height) {
		Game.width = width;
		Game.height = height;
		this.title = title;
		keyManager = new KeyManager();
		handler = new Handler();
	}

	/**
	 * Constructs a Display object and a GameState object
	 */
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();

		gameState = new GameState(this, handler);
	}

	/**
	 * Calls the tick method of KeyManager. Calls the tick method of GameState
	 * or sets the state of the application to GameState
	 */
	private void tick() {
		keyManager.tick();

		if (State.getState() != null) {
			State.getState().tick();
		} else {
			State.setState(gameState);
		}
	}

	/**
	 * Draws the g(graphics object) to a canvas
	 */
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		g.clearRect(0, 0, width, height);

		if (State.getState() != null) {
			State.getState().render(g);
		}

		bs.show();
		g.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println("Frames:" + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	/**
	 * Returns KeyManager object
	 * 
	 * @return The keyManager object in the Game class
	 */
	public KeyManager getKeyManager() {
		return keyManager;
	}

	/**
	 * Starts the game, or thread
	 */
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stops the game, or thread
	 */
	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes a variable, minimum value and a maximum value then keeps the
	 * variable inside the min and max.
	 * 
	 * @param var
	 * @param min
	 * @param max
	 * @return The variable(kept inside the limits)
	 */
	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	// Unsal Ozturk: I added this method for the menu implementation
	/**
	 * Returns Display object
	 * 
	 * @return The display object in the Game class
	 */
	public Display getDisplay() {
		return display;
	}
}