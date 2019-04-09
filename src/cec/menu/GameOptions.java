package cec.menu;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Unsal Ozturk
 * @version 01.05.2017 Defines GameOptions
 */
public class GameOptions {
	// constants start
	// mute states
	public static final int NOT_MUTED = 0;
	public static final int MUTED = 1;

	// screen states
	public static final int FULLSCREEN = 0;
	public static final int WINDOWED = 1;
	public static final int BORDERLESS_FULLSCREEN = 2;

	// difficulty
	public static final int EASY = 0;
	public static final int MEDIUM = 1;
	public static final int HARD = 2;
	// constants end

	// fields
	private int isMute;
	private int soundLevel;
	private Resolution resolution;
	private int screenState;
	private int difficulty;

	/**
	 * Constructs a GameOptions object. Assigns default values if options.txt
	 * doesn't exist. Else, reads from options.txt.
	 */
	public GameOptions() {
		try {
			Scanner scan = new Scanner(new File("options.txt"));

			isMute = scan.nextInt();
			if (isMute != NOT_MUTED || isMute != MUTED) {
				isMute = NOT_MUTED;
				soundLevel = 50;
			}

			soundLevel = scan.nextInt();
			if (!(soundLevel <= 100 && soundLevel >= 0)) {
				soundLevel = 50;
			}

			resolution = new Resolution(scan.nextInt(), scan.nextInt());

			screenState = scan.nextInt();
			if (screenState != FULLSCREEN && screenState != WINDOWED && screenState != BORDERLESS_FULLSCREEN) {
				screenState = WINDOWED;
			}

			difficulty = scan.nextInt();
			if (difficulty != EASY && difficulty != MEDIUM && difficulty != HARD) {
				difficulty = EASY;
			}

			if (soundLevel > 0) {
				isMute = NOT_MUTED;
			}
			scan.close();
		} catch (Exception ex) {
			isMute = NOT_MUTED;
			soundLevel = 50;
			resolution = new Resolution(1200, 650);
			screenState = WINDOWED;
			difficulty = EASY;
		}
		writeGameOptionFile();
	}

	/**
	 * Writes the current option configuration to a file called options.txt.
	 */
	private void writeGameOptionFile() {
		try {
			PrintWriter writer = new PrintWriter("options.txt", "UTF-8");
			writer.print(toString());
			writer.close();
		} catch (Exception ex) {
		}
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(isMute + " ");
		str.append(soundLevel + " ");
		str.append(resolution.getX() + " ");
		str.append(resolution.getY() + " ");
		str.append(screenState + " ");
		str.append(difficulty + " ");
		return str.toString();
	}

	// getters
	/**
	 * Gets the mute state.
	 * 
	 * @return 0 If muted, 1 if not muted.
	 */
	public int isMute() {
		return isMute;
	}

	/**
	 * Gets the sound level.
	 * 
	 * @return Sound level, an integer from 0 to 100.
	 */
	public int getSoundLevel() {
		return soundLevel;
	}

	/**
	 * Gets the resolution.
	 * 
	 * @return A reference to the resolution object of the GameOptions object.
	 */
	public Resolution getResolution() {
		return resolution;
	}

	/**
	 * Gets the ScreenState.
	 * 
	 * @return 0 if fullscreen, 1 if windowed, 2 if borderless fullscreen.
	 */
	public int getScreenState() {
		return screenState;
	}

	/**
	 * Gets the difficulty.
	 * 
	 * @return 0 if easy, 1 if medium, 2 if hard.
	 */
	public int getDifficulty() {
		return difficulty;
	}

	// setters
	/**
	 * Sets the mute state.
	 * 
	 * @param i
	 *            Pass 0 to mute, 1 to unmute. If an undefined integer is
	 *            passed, sets sound level to 50.
	 */
	public void setMute(int i) {
		isMute = i;
		if (isMute != NOT_MUTED && isMute != MUTED) {
			isMute = NOT_MUTED;
			soundLevel = 50;
		}
		writeGameOptionFile();
	}

	/**
	 * Sets the sound level.
	 * 
	 * @param i
	 *            Pass an integer between 0 and 100. If an undefined integer is
	 *            passed, sets sound level to 50.
	 */
	public void setSoundLevel(int i) {
		soundLevel = i;
		if (!(soundLevel <= 100 && soundLevel >= 0)) {
			soundLevel = 50;
		}
		if (soundLevel == 0) {
			isMute = MUTED;
		}
		writeGameOptionFile();
	}

	/**
	 * Sets the resolution.
	 * 
	 * @param x
	 *            Size of the x axis.
	 * @param y
	 *            Size of the y axis.
	 */
	private void setResolution(int x, int y) {
		resolution = new Resolution(x, y);
		writeGameOptionFile();
	}

	/**
	 * Sets the resolution.
	 * 
	 * @param str
	 *            Resolution in String form. E.g. 640x840
	 */
	public void setResolution(String str) {
		String[] numbers = str.split("x");
		setResolution(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
	}

	/**
	 * Sets the screen state.
	 * 
	 * @param i
	 *            Pass an integer between 0 and 2. If an undefined integer is
	 *            passed, sets screen state to windowed.
	 */
	public void setScreenState(int i) {
		screenState = i;
		if (screenState != FULLSCREEN && screenState != WINDOWED && screenState != BORDERLESS_FULLSCREEN) {
			screenState = WINDOWED;
		}
		writeGameOptionFile();
	}

	/**
	 * Sets the difficulty.
	 * 
	 * @param i
	 *            Pass an integer between 0 and 2. If an undefined integer is
	 *            passed, sets difficulty to easy.
	 */
	public void setDifficulty(int i) {
		difficulty = i;
		if (difficulty != EASY && difficulty != MEDIUM && difficulty != HARD) {
			difficulty = EASY;
		}
		writeGameOptionFile();
	}

	/**
	 * @author Unsal Ozturk
	 * @version 01052017 Defines the resolution object, which is a very similar
	 *          class to Java's Dimension class, but with a different toString()
	 *          method.
	 */
	public class Resolution {
		private int x;
		private int y;

		/**
		 * Constructs a resolution object with a given width and height.
		 * 
		 * @param x
		 *            Width
		 * @param y
		 *            Height
		 */
		public Resolution(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * Gets the width of the resolution.
		 * 
		 * @return Width
		 */
		public int getX() {
			return x;
		}

		/**
		 * Gets the height of the resolution.
		 * 
		 * @return Height
		 */
		public int getY() {
			return y;
		}

		@Override
		public String toString() {
			return x + "x" + y;
		}
	}
}