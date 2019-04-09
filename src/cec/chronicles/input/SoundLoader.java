package cec.chronicles.input;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound Loader for game
 * 
 * @author Doða Oruç, Kaan Ünlü
 * @version 12.5.2017
 */
public class SoundLoader {

	public static SoundLoader buttonError = loadSound("/sounds/buttonError.wav");
	public static SoundLoader david_past_schubert = loadSound("/sounds/david_past_schubert.wav");
	public static SoundLoader david_present_bach = loadSound("/sounds/david_present_bach.wav");
	public static SoundLoader menu_theme = loadSound("/sounds/menu_theme.wav");
	public static SoundLoader menuClick = loadSound("/sounds/menuClick.wav");
	public static SoundLoader step = loadSound("/sounds/step.wav");
	public static SoundLoader spark1 = loadSound("/sounds/spark1.wav");
	public static SoundLoader spark2 = loadSound("/sounds/spark2.wav");

	private Clip clip;

	/**
	 * @param fileName
	 * @return The sound to be played
	 */
	public static SoundLoader loadSound(String fileName) {
		SoundLoader sound = new SoundLoader();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(SoundLoader.class.getResource(fileName));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			sound.clip = clip;
		} catch (Exception e) {
			System.out.println(e);
		}
		return sound;
	}

	/**
	 * Loops (OR PLAYS) the sound file .wav
	 * 
	 * @param count
	 */
	public void loop(final int count) {
		if (clip != null)
			new Thread() {
				public void run() {
					synchronized (clip) {
						clip.stop();
						clip.setFramePosition(0);
						clip.loop(count);
					}
				}
			}.start();
	}

	/**
	 * Convention
	 */
	public void stop() {
		if (clip != null)
			new Thread() {
				public void run() {
					synchronized (clip) {
						clip.stop();
					}
				}
			}.start();
	}

	/**
	 * Convention
	 */
	public void play() {
		if (clip != null)
			new Thread() {
				public void run() {
					synchronized (clip) {
						clip.stop();
						clip.setFramePosition(0);
						clip.start();
					}
				}
			}.start();
	}
}