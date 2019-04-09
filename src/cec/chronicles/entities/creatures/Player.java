package cec.chronicles.entities.creatures;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Rectangle;

import cec.chronicles.Game;
import cec.chronicles.Launcher;
import cec.chronicles.ID.ID;
import cec.chronicles.entities.Entity;
import cec.chronicles.entities.InteractionTrigger;
import cec.chronicles.entities.MoveableArea;
import cec.chronicles.entities.TransitionTrigger;
import cec.chronicles.entities.UnmoveableArea;
import cec.chronicles.gfx.Animation;
import cec.chronicles.gfx.Assets;
import cec.chronicles.handler.Handler;
import cec.chronicles.input.SoundLoader;
import cec.chronicles.popup.EasterEggPopup;
import cec.chronicles.popup.puzzle.BlankPanel;
import cec.chronicles.popup.puzzle.FallingBalloons;
import cec.chronicles.popup.puzzle.FindMistakePuzzle;
import cec.chronicles.popup.puzzle.ReorderableJListPuzzle;
import cec.chronicles.popup.puzzle.UserSidedPuzzle;
import cec.chronicles.states.GameState;

/**
 * The character the actual player controls.
 * 
 * @author Doða Oruç, Unsal Ozturk
 * @version 01.05.2017
 */
public class Player extends Creature {

	private boolean facingRight;
	private Handler handler;
	private InteractionTrigger stealDesignChoices, firstMapTrigger, secondMapTrigger, thirdMapTrigger, fourthMapTrigger,
			fifthMapTrigger, timeAnimTrigger;
	private UnmoveableArea timeMachineLeft, timeMachineRight, secretBoxes, coffeeTable, oldComputerBoxes;
	private TransitionTrigger secondMapDoor;
	// Animation time
	private Animation animRight, animLeft;
	private Animation shadowAnimRight, shadowAnimLeft;

	// Player progression and state properties START
	private boolean[] puzzleCompletion;
	private String playerName;
	private int skinSelection;
	private boolean hasTriggeredOnce;
	private boolean hasTriggeredOnceTM;
	private boolean hasTriggeredOnceEaster;
	private boolean instructionsOneFired;
	private boolean instructionsTwoFired;
	private boolean instructionsFiveFired;
	private boolean dialogueFired;
	// Player progression and state properties END

	// puzzle
	private static FallingBalloons puzzle1 = new FallingBalloons(GameState.game.getDisplay().getFrame());
	private static UserSidedPuzzle puzzle2 = new UserSidedPuzzle(GameState.game.getDisplay().getFrame(),
			"System.out.println(\"Hello World!\");");
	private static ReorderableJListPuzzle puzzle3 = new ReorderableJListPuzzle(GameState.game.getDisplay().getFrame());
	private static BlankPanel puzzle4 = new BlankPanel(GameState.game.getDisplay().getFrame());
	private static FindMistakePuzzle puzzle5 = new FindMistakePuzzle(GameState.game.getDisplay().getFrame(),
			"randomText.txt");
	private static EasterEggPopup easter = new EasterEggPopup(GameState.game.getDisplay().getFrame());
	Game game;

	/**
	 * Constructor
	 * 
	 * @param game
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 */
	public Player(Game game, float x, float y, ID id, Handler handler) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, id);
		this.game = game;
		this.handler = handler;

		// InteractionTriggers
		stealDesignChoices = new InteractionTrigger(game, 150 * 6, 75 * 65 / 12, 5 * 6, 5 * 65 / 12,
				ID.StealDesignChoices);
		firstMapTrigger = new InteractionTrigger(game, 160, 160, 240, 240, ID.FirstMapInteractionTrigger);
		secondMapTrigger = new InteractionTrigger(game, 160, 160, 240, 240, ID.SecondMapInteractionTrigger);
		thirdMapTrigger = new InteractionTrigger(game, 160, 160, 240, 240, ID.ThirdMapInteractionTrigger);
		fourthMapTrigger = new InteractionTrigger(game, 240, 240, 320, 320, ID.FourthMapInteractionTrigger);
		fifthMapTrigger = new InteractionTrigger(game, 400, 400, 450, 450, ID.FifthMapInteractionTrigger);
		timeAnimTrigger = new InteractionTrigger(game, 26 * 6, 51 * 65 / 12, 26 * 6, 43 * 65 / 12,
				ID.TimeMachineAnimation);

		// Unmoveable Areas
		secretBoxes = new UnmoveableArea(game, 157 * 6, 68 * 65 / 12, 29 * 6, 34 * 65 / 12, ID.UnmoveableArea);
		timeMachineLeft = new UnmoveableArea(game, 22 * 6, 51 * 65 / 12, 4 * 6, 43 * 65 / 12, ID.UnmoveableArea);
		timeMachineRight = new UnmoveableArea(game, 52 * 6, 51 * 65 / 12, 59 * 6, 43 * 65 / 12, ID.UnmoveableArea);
		coffeeTable = new UnmoveableArea(game, 30 * 6, 96 * 65 / 12, 66 * 6, 24 * 65 / 12, ID.UnmoveableArea);
		oldComputerBoxes = new UnmoveableArea(game, 120 * 6, 68 * 65 / 12, 29 * 6, 34 * 65 / 12, ID.UnmoveableArea);

		// Transition Trigger
		secondMapDoor = new TransitionTrigger(game, 135 * 6, 84 * 65 / 12, 18 * 6, 6 * 65 / 12,
				ID.TransitionTriggerRight);

		// Animation
		animRight = new Animation(Animation.DEFAULT_ANIMATION_TRANSITION_CHARACTERS, Assets.player_right);
		animLeft = new Animation(Animation.DEFAULT_ANIMATION_TRANSITION_CHARACTERS, Assets.player_left);
		shadowAnimRight = new Animation(Animation.DEFAULT_ANIMATION_TRANSITION_CHARACTERS, Assets.shadow_right);
		shadowAnimLeft = new Animation(Animation.DEFAULT_ANIMATION_TRANSITION_CHARACTERS, Assets.shadow_left);

		parsePlayer();
		System.out.println(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.entities.Entity#tick()
	 */
	@Override
	public void tick() {

		// Animations
		animRight.tick();
		animLeft.tick();
		shadowAnimRight.tick();
		shadowAnimLeft.tick();

		// Movement
		getInput();
		move();

		collision();
	}

	/**
	 * Gets input from the keyManager object inside the Game class and acts
	 * accordingly
	 */
	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (game.getKeyManager().up) {
			yMove = -speed;
		}

		if (game.getKeyManager().down) {
			yMove = speed;
		}

		if (game.getKeyManager().left) {
			xMove = -speed;
			facingRight = false;
		}

		if (game.getKeyManager().right) {
			xMove = speed;
			facingRight = true;
		}

		// Unsal Ozturk: I added this method so that the game goes invisible and
		// the menu pops up, this is just for demonstration.
		if (game.getKeyManager().escape) {
			Launcher.game.getDisplay().getFrame().setAlwaysOnTop(false);
			game.getDisplay().getFrame().setVisible(false);
			Launcher.menu.setVisible(true);
			game.getKeyManager().disableEscape();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.entities.Entity#getBounds()
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 75, 200);
	}

	/**
	 * @return The players feet's bounds
	 */
	public Rectangle getFeetBounds() {
		return new Rectangle((int) x, (int) y + 170, 75, 30);
	}

	/**
	 * Collision Detection System. Copyright Doða Oruç 2017. All Rights
	 * Reserved.
	 */
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {

			Entity tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.David) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (!dialogueFired && game.getKeyManager().use) {
						JOptionPane instructions;
						instructions = new JOptionPane(null);
						UIManager.put("OptionPane.messageFont", new Font("sugoi ui", Font.PLAIN, 14));
						UIManager.put("OptionPane.buttonFont", new Font("sugoi ui", Font.PLAIN, 12));
						instructions.showMessageDialog(null, "What are you doing here? Get out!", "Denver:",
								JOptionPane.WARNING_MESSAGE);
						dialogueFired = true;
					}
				}
			} else if (tempObject.getId() == ID.TimeMachineAnimation) {
				if (getFeetBounds().intersects(tempObject.getBounds())) {
					Assets.maps[4].startAnim();
					SoundLoader.spark2.loop(0);
				} else {
					Assets.maps[4].stopAnim();
				}
				if (game.getKeyManager().use && !hasTriggeredOnceTM) {
					for (int o = 0; o < 240; o++) {
						GameState.game.getKeyManager().disableE();
					}
					GameState.setIndex(5);
					SoundLoader.buttonError.loop(1);
					MoveableArea.flash();
					hasTriggeredOnceTM = true;
				}
			} else if (tempObject.getId() == ID.MoveableArea) {
				if (getBounds().intersects(tempObject.getBounds())) {
					int index = GameState.getIndex();
					System.out.println(index);
					GameState.getHandler().addObject(stealDesignChoices);
					GameState.getHandler().addObject(firstMapTrigger);
					GameState.getHandler().addObject(secondMapTrigger);
					GameState.getHandler().addObject(thirdMapTrigger);
					GameState.getHandler().addObject(fourthMapTrigger);
					GameState.getHandler().addObject(fifthMapTrigger);
					GameState.getHandler().addObject(timeAnimTrigger);
					GameState.getHandler().addObject(secretBoxes);
					GameState.getHandler().addObject(timeMachineLeft);
					GameState.getHandler().addObject(timeMachineRight);
					GameState.getHandler().addObject(coffeeTable);
					GameState.getHandler().addObject(oldComputerBoxes);
					GameState.getHandler().addObject(secondMapDoor);
					GameState.getHandler().addObject(GameState.rightTrigger);
					GameState.getHandler().addObject(GameState.leftTrigger);
					if (GameState.getHandler().object.indexOf(GameState.david) == -1) {
						GameState.getHandler().addObject(GameState.david);
					}

					if (index == 0) {
						for (int k = 0; k < 3; k++) {
							GameState.getHandler().removeObject(stealDesignChoices);
							// GameState.getHandler().removeObject(firstMapTrigger);
							GameState.getHandler().removeObject(secondMapTrigger);
							GameState.getHandler().removeObject(thirdMapTrigger);
							GameState.getHandler().removeObject(fourthMapTrigger);
							GameState.getHandler().removeObject(fifthMapTrigger);
							GameState.getHandler().removeObject(timeAnimTrigger);
							GameState.getHandler().removeObject(secretBoxes);
							GameState.getHandler().removeObject(timeMachineLeft);
							GameState.getHandler().removeObject(timeMachineRight);
							GameState.getHandler().removeObject(coffeeTable);
							GameState.getHandler().removeObject(oldComputerBoxes);
							GameState.getHandler().removeObject(secondMapDoor);
							GameState.getHandler().removeObject(GameState.david);
						}
					} else if (index == 1) {
						for (int k = 0; k < 3; k++) {
							GameState.getHandler().removeObject(stealDesignChoices);
							GameState.getHandler().removeObject(firstMapTrigger);
							// GameState.getHandler().removeObject(secondMapTrigger);
							GameState.getHandler().removeObject(thirdMapTrigger);
							GameState.getHandler().removeObject(fourthMapTrigger);
							GameState.getHandler().removeObject(fifthMapTrigger);
							GameState.getHandler().removeObject(timeAnimTrigger);
							GameState.getHandler().removeObject(secretBoxes);
							GameState.getHandler().removeObject(timeMachineLeft);
							GameState.getHandler().removeObject(timeMachineRight);
							GameState.getHandler().removeObject(coffeeTable);
							GameState.getHandler().removeObject(oldComputerBoxes);
							for (int l = 0; l < 3; l++) {
								GameState.getHandler().removeObject(GameState.rightTrigger);
							}

							GameState.getHandler().removeObject(GameState.david);
						}
					} else if (index == 2) {
						for (int k = 0; k < 3; k++) {
							GameState.getHandler().removeObject(firstMapTrigger);
							GameState.getHandler().removeObject(secondMapTrigger);
							// GameState.getHandler().removeObject(thirdMapTrigger);
							GameState.getHandler().removeObject(fourthMapTrigger);
							GameState.getHandler().removeObject(fifthMapTrigger);
							GameState.getHandler().removeObject(timeAnimTrigger);
							GameState.getHandler().removeObject(secretBoxes);
							GameState.getHandler().removeObject(timeMachineLeft);
							GameState.getHandler().removeObject(timeMachineRight);
							GameState.getHandler().removeObject(coffeeTable);
							GameState.getHandler().removeObject(oldComputerBoxes);
							GameState.getHandler().removeObject(secondMapDoor);
							for (int l = 0; l < 3; l++) {
								GameState.getHandler().removeObject(GameState.rightTrigger);
							}

							GameState.getHandler().removeObject(GameState.david);
						}
					} else if (index == 3) {
						for (int k = 0; k < 3; k++) {
							GameState.getHandler().removeObject(stealDesignChoices);
							GameState.getHandler().removeObject(firstMapTrigger);
							GameState.getHandler().removeObject(secondMapTrigger);
							GameState.getHandler().removeObject(thirdMapTrigger);
							// GameState.getHandler().removeObject(fourthMapTrigger);
							GameState.getHandler().removeObject(fifthMapTrigger);
							GameState.getHandler().removeObject(timeAnimTrigger);
							GameState.getHandler().removeObject(secretBoxes);
							GameState.getHandler().removeObject(timeMachineLeft);
							GameState.getHandler().removeObject(timeMachineRight);
							GameState.getHandler().removeObject(coffeeTable);
							GameState.getHandler().removeObject(oldComputerBoxes);
							GameState.getHandler().removeObject(secondMapDoor);
							for (int l = 0; l < 3; l++) {
								GameState.getHandler().removeObject(GameState.leftTrigger);
							}
						}
					} else if (index == 4) {
						for (int k = 0; k < 3; k++) {
							GameState.getHandler().removeObject(stealDesignChoices);
							GameState.getHandler().removeObject(firstMapTrigger);
							GameState.getHandler().removeObject(secondMapTrigger);
							GameState.getHandler().removeObject(thirdMapTrigger);
							GameState.getHandler().removeObject(fourthMapTrigger);
							// GameState.getHandler().removeObject(fifthMapTrigger);
							GameState.getHandler().removeObject(coffeeTable);
							GameState.getHandler().removeObject(oldComputerBoxes);
							GameState.getHandler().removeObject(secondMapDoor);
							for (int l = 0; l < 3; l++) {
								GameState.getHandler().removeObject(GameState.rightTrigger);
							}
							GameState.getHandler().removeObject(GameState.david);
						}
					} else if (index == 5) {
						for (int k = 0; k < 3; k++) {
							GameState.getHandler().removeObject(stealDesignChoices);
							GameState.getHandler().removeObject(firstMapTrigger);
							GameState.getHandler().removeObject(secondMapTrigger);
							GameState.getHandler().removeObject(thirdMapTrigger);
							GameState.getHandler().removeObject(fourthMapTrigger);
							GameState.getHandler().removeObject(fifthMapTrigger);
							GameState.getHandler().removeObject(timeAnimTrigger);
							GameState.getHandler().removeObject(secretBoxes);
							GameState.getHandler().removeObject(timeMachineLeft);
							GameState.getHandler().removeObject(timeMachineRight);
							GameState.getHandler().removeObject(secondMapDoor);
							for (int l = 0; l < 3; l++) {
								GameState.getHandler().removeObject(GameState.leftTrigger);
							}
							GameState.getHandler().removeObject(GameState.david);
						}
					}
				}
			} else if (tempObject.getId() == ID.TransitionTriggerLeft) {
				if (getFeetBounds().intersects(tempObject.getBounds())) {
					if (GameState.setIndex(GameState.getIndex() - 1)) {
						int index = GameState.getIndex();
						System.out.println(index);
						GameState.teleport(tempObject.getId());
						MoveableArea.flash();
					} else if (!hasTriggeredOnceEaster && GameState.getIndex() == 0) {
						easter.drawPanel();
						hasTriggeredOnceEaster = true;
					}
				}
			} else if (tempObject.getId() == ID.TransitionTriggerRight) {
				if (getFeetBounds().intersects(tempObject.getBounds())) {
					if (GameState.setIndex(GameState.getIndex() + 1)) {
						int index = GameState.getIndex();
						System.out.println(index);
						GameState.teleport(tempObject.getId());
						MoveableArea.flash();
					}
				}
			} else if (tempObject.getId() == ID.UnmoveableArea) {
				if (getFeetBounds().intersects(tempObject.getBounds())) {
					if (getX() > tempObject.getX() + 10) {
						setX(getX() + getSpeed());
					}
					if (getX() < tempObject.getX() - 10) {
						setX(getX() - getSpeed());
					}
					if (getY() > tempObject.getY() + 10) {
						setY(getY() + getSpeed());
					}
					if (getY() < tempObject.getY() - 10) {
						setY(getY() - getSpeed());
					}
				}
			} else if (tempObject.getId() == ID.StealDesignChoices) {
				if (getBounds().intersects(tempObject.getBounds()) && !hasTriggeredOnce) {
					if (game.getKeyManager().use) {
						for (int o = 0; o < 240; o++) {
							GameState.game.getKeyManager().disableE();
						}
						GameState.setIndex(3);
						MoveableArea.flash();
						hasTriggeredOnce = true;
					}
				}
			} else if (tempObject.getId() == ID.FirstMapInteractionTrigger) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (game.getKeyManager().use) {
						GameState.game.getKeyManager().disableE();
						puzzle1.drawPanel();
						if (!instructionsOneFired) {
							puzzle1.showInstructions();
							instructionsOneFired = true;
						}
						puzzle1.checkCompletion();
					}
				}
			} else if (tempObject.getId() == ID.SecondMapInteractionTrigger) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (game.getKeyManager().use) {
						GameState.game.getKeyManager().disableE();
						puzzle2.drawPanel();
						if (!instructionsTwoFired) {
							puzzle1.showInstructions();
							instructionsTwoFired = true;
						}
						puzzle2.checkCompletion();
					}
				}
			} else if (tempObject.getId() == ID.ThirdMapInteractionTrigger) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (game.getKeyManager().use) {
						GameState.game.getKeyManager().disableE();
						puzzle3.drawPanel();
						puzzle3.checkCompletion();
					}
				}
			} else if (tempObject.getId() == ID.FourthMapInteractionTrigger) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (game.getKeyManager().use) {
						GameState.game.getKeyManager().disableE();
						puzzle4.drawPanel();

						puzzle4.checkCompletion();
					}
				}
			} else if (tempObject.getId() == ID.FifthMapInteractionTrigger) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (game.getKeyManager().use) {
						GameState.game.getKeyManager().disableE();
						puzzle5.drawPanel();
						if (!instructionsFiveFired) {
							puzzle5.showInstructions();
							instructionsFiveFired = true;
						}
						puzzle5.checkCompletion();
					}
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cec.chronicles.entities.Entity#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		if (GameState.getIndex() > 2) {
			if (xMove == 0 && yMove == 0) {
				if (facingRight) {
					g.drawImage(Assets.playerStillR, (int) x, (int) y, 75, 200, null);
				} else {
					g.drawImage(Assets.playerStillL, (int) x, (int) y, 75, 200, null);
				}

			} else if (xMove <= 0 && !facingRight) {
				g.drawImage(getCurrentAnimationFrame(animLeft), (int) x, (int) y, 75, 200, null);
			} else {
				g.drawImage(getCurrentAnimationFrame(animRight), (int) x, (int) y, 75, 200, null);
			}
		} else {
			if (xMove == 0 && yMove == 0) {
				if (facingRight) {
					g.drawImage(Assets.shadowStillR, (int) x, (int) y, 75, 200, null);
				} else {
					g.drawImage(Assets.shadowStillL, (int) x, (int) y, 75, 200, null);
				}

			} else if (xMove <= 0 && !facingRight) {
				g.drawImage(getCurrentAnimationFrame(shadowAnimLeft), (int) x, (int) y, 75, 200, null);
			} else {
				g.drawImage(getCurrentAnimationFrame(shadowAnimRight), (int) x, (int) y, 75, 200, null);
			}
		}
	}

	/**
	 * Returns the current animation frame
	 * 
	 * @param a
	 * @return The current animation frame
	 */
	private BufferedImage getCurrentAnimationFrame(Animation a) {
		return a.getCurrentFrame();
	}

	// Human readable serialization START
	/**
	 * 
	 */
	public void writePlayer() {
		try {
			PrintWriter writer = new PrintWriter("save.txt");
			writer.print(this.toString());
			writer.println();
			writer.println(this.puzzleCompletionToString());
			writer.println(GameState.getIndex());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the players properties from a file(save file)
	 */
	private void parsePlayer() {
		try {
			Scanner scan = new Scanner(new File("save.txt"));
			setX(scan.nextInt());
			setY(scan.nextInt());
			skinSelection = scan.nextInt();
			playerName = scan.next();
			scan.nextLine();
			String tmp = scan.next();
			scan.nextLine();
			GameState.setIndex(scan.nextInt());
			System.out.println(GameState.getIndex());
			puzzleCompletion = new boolean[tmp.charAt(0)];

			for (int i = 1; i < tmp.length(); i++) {
				if (tmp.charAt(i) == '1') {
					puzzleCompletion[i - 1] = true;
				} else {
					puzzleCompletion[i - 1] = false;
				}
			}
			scan.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = (int) getX() + " " + (int) getY() + " " + skinSelection + " " + playerName;
		return str;
	}

	/**
	 * @return Puzzle completion as a string
	 */
	public String puzzleCompletionToString() {
		StringBuffer str = new StringBuffer();
		str.append(8);
		for (int i = 0; i < 8; i++) {
			if (puzzleCompletion[i]) {
				str.append(1);
			} else {
				str.append(0);
			}
		}
		return str.toString();
	}

	/**
	 * @param i
	 * @return The puzzle completion array
	 */
	public boolean getPuzzleCompletion(int i) {
		return puzzleCompletion[i];
	}

	/**
	 * @param s
	 */
	public void setName(String s) {
		playerName = s;
	}

	/**
	 * @return The players name
	 */
	public String getName() {
		return playerName;
	}

	/**
	 * @return THe skin of the player
	 */
	public int getSkinSelection() {
		return skinSelection;
	}

	/**
	 * @param i
	 */
	public void setSkinSelection(int i) {
		skinSelection = i;
	}

	/**
	 * @param i
	 */
	public void completePuzzle(int i) {
		puzzleCompletion[i] = true;
	}
	// Human readable serialization END
}