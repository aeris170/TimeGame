package cec.chronicles.states;

import java.awt.Graphics;

import cec.chronicles.entities.creatures.Player;
import cec.chronicles.gfx.Assets;
import cec.chronicles.handler.Handler;
import cec.chronicles.input.SoundLoader;
import cec.chronicles.entities.MoveableArea;
import cec.chronicles.entities.TransitionTrigger;
import cec.chronicles.entities.creatures.David;
import cec.chronicles.map.Map;
import cec.chronicles.Game;
import cec.chronicles.ID.ID;

/**
 * Playable state.
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public class GameState extends State {

	public static int index;
	public static int prevIndex;
	// public static MoveableArea moveable;

	private Map[] allMaps;
	public static Player player;
	public static David david;
	public static TransitionTrigger leftTrigger, rightTrigger;
	private static Handler handler;

	/**
	 * Constructor
	 * 
	 * @param game
	 * @param handler
	 */
	public GameState(Game game, Handler handler) {
		super(game);
		GameState.handler = handler;
		player = new Player(game, 100, 400, ID.Player, handler);
		david = new David(game, 100, 400, ID.David);
		leftTrigger = new TransitionTrigger(game, 0, 0, 50, Game.height, ID.TransitionTriggerLeft);
		rightTrigger = new TransitionTrigger(game, Game.width - 50, 0, Game.width, Game.height,
				ID.TransitionTriggerRight);
		handler.addObject(player);
		handler.addObject(leftTrigger);
		handler.addObject(rightTrigger);
		allMaps = Assets.maps;
		MoveableArea.flash();
	}

	/**
	 * @return The current index of the allMaps array
	 */
	public static int getIndex() {
		return index;
	}

	/**
	 * Sets the index variable to the passed value
	 * 
	 * @param index
	 * @return Whether the set operation was a success or not
	 */
	public static boolean setIndex(int index) {
		if (index < 0 || index >= Assets.NUM_OF_MAPS) {
			return false;
		} else {
			GameState.index = index;
			return true;
		}
	}

	/**
	 * Teleport the player after touching a transition trigger
	 * 
	 * @param id
	 */
	public static void teleport(ID id) {
		if (id == ID.TransitionTriggerLeft) {
			player.setX(Game.width - 50 - 100);
			player.setY(Game.height);
		} else if (id == ID.TransitionTriggerRight) {
			player.setX(50 + 100);
			player.setY(Game.height);
		}
	}

	/**
	 * @return The handler
	 */
	public static Handler getHandler() {
		return handler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.states.State#tick()
	 */
	public void tick() {
		handler.tick();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.states.State#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		try {
			allMaps[index].render(g);
		} catch (Exception ex) {
		}
		handler.render(g);
	}
}