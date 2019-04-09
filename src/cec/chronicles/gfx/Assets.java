package cec.chronicles.gfx;

import java.awt.image.BufferedImage;

import cec.chronicles.input.ImageLoader;
import cec.chronicles.map.Map;

/**
 * Takes and crops spritesheets coming from SpriteSheet.java.
 * 
 * @author Doða Oruç
 * @version 01.05.2017
 */
public class Assets {

	public static BufferedImage playerStillR, playerStillL, shadowStillR, shadowStillL, back1, back2;
	public static Map[] maps;
	public static BufferedImage[] player_right, player_left, david_right, david_left, shadow_right, shadow_left;
	public static final int NUM_OF_MAPS = 6;

	/**
	 * Inýtializes the assets
	 */
	public static void init() {
		SpriteSheet animatedPlayerR = new SpriteSheet(ImageLoader.LoadImage("/textures/playerR.png"));
		SpriteSheet animatedPlayerL = new SpriteSheet(ImageLoader.LoadImage("/textures/playerL.png"));
		SpriteSheet animatedDavidR = new SpriteSheet(ImageLoader.LoadImage("/textures/davidR.png"));
		SpriteSheet animatedDavidL = new SpriteSheet(ImageLoader.LoadImage("/textures/davidL.png"));
		SpriteSheet animatedShadowR = new SpriteSheet(ImageLoader.LoadImage("/textures/shadowR.png"));
		SpriteSheet animatedShadowL = new SpriteSheet(ImageLoader.LoadImage("/textures/shadowL.png"));

		// Time mac frames
		BufferedImage[] timeFrames = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			timeFrames[i] = ImageLoader.LoadImage("/textures/maps/timeAnim/tm" + (i + 1) + ".png");
		}

		maps = new Map[NUM_OF_MAPS];
		maps[0] = new Map(ImageLoader.LoadImage("/textures/maps/intro1.png"));
		maps[1] = new Map(ImageLoader.LoadImage("/textures/maps/intro2.png"));
		maps[2] = new Map(ImageLoader.LoadImage("/textures/maps/present_david_room_dark.png"));
		maps[3] = new Map(ImageLoader.LoadImage("/textures/maps/present_david_room.png"));
		maps[4] = new Map(timeFrames);
		maps[5] = new Map(ImageLoader.LoadImage("/textures/maps/past_david_room.png"));

		player_right = new BufferedImage[6];
		player_left = new BufferedImage[6];
		david_right = new BufferedImage[6];
		david_left = new BufferedImage[6];
		shadow_right = new BufferedImage[6];
		shadow_left = new BufferedImage[6];

		player_right[0] = animatedPlayerR.crop(103, 0, 15, 40);
		player_right[1] = animatedPlayerR.crop(86, 0, 15, 40);
		player_right[2] = animatedPlayerR.crop(69, 0, 15, 40);
		player_right[3] = animatedPlayerR.crop(52, 0, 15, 40);
		player_right[4] = animatedPlayerR.crop(35, 0, 15, 40);
		player_right[5] = animatedPlayerR.crop(18, 0, 15, 40);

		player_left[0] = animatedPlayerL.crop(1, 0, 15, 40);
		player_left[1] = animatedPlayerL.crop(18, 0, 15, 40);
		player_left[2] = animatedPlayerL.crop(35, 0, 15, 40);
		player_left[3] = animatedPlayerL.crop(52, 0, 15, 40);
		player_left[4] = animatedPlayerL.crop(69, 0, 15, 40);
		player_left[5] = animatedPlayerL.crop(86, 0, 15, 40);

		david_right[0] = animatedDavidR.crop(103, 0, 15, 40);
		david_right[1] = animatedDavidR.crop(86, 0, 15, 40);
		david_right[2] = animatedDavidR.crop(69, 0, 15, 40);
		david_right[3] = animatedDavidR.crop(52, 0, 15, 40);
		david_right[4] = animatedDavidR.crop(35, 0, 15, 40);
		david_right[5] = animatedDavidR.crop(18, 0, 15, 40);

		david_left[0] = animatedDavidL.crop(1, 0, 15, 40);
		david_left[1] = animatedDavidL.crop(18, 0, 15, 40);
		david_left[2] = animatedDavidL.crop(35, 0, 15, 40);
		david_left[3] = animatedDavidL.crop(52, 0, 15, 40);
		david_left[4] = animatedDavidL.crop(69, 0, 15, 40);
		david_left[5] = animatedDavidL.crop(86, 0, 15, 40);

		shadow_right[0] = animatedShadowR.crop(103, 0, 15, 40);
		shadow_right[1] = animatedShadowR.crop(86, 0, 15, 40);
		shadow_right[2] = animatedShadowR.crop(69, 0, 15, 40);
		shadow_right[3] = animatedShadowR.crop(52, 0, 15, 40);
		shadow_right[4] = animatedShadowR.crop(35, 0, 15, 40);
		shadow_right[5] = animatedShadowR.crop(18, 0, 15, 40);

		shadow_left[0] = animatedShadowL.crop(1, 0, 15, 40);
		shadow_left[1] = animatedShadowL.crop(18, 0, 15, 40);
		shadow_left[2] = animatedShadowL.crop(35, 0, 15, 40);
		shadow_left[3] = animatedShadowL.crop(52, 0, 15, 40);
		shadow_left[4] = animatedShadowL.crop(69, 0, 15, 40);
		shadow_left[5] = animatedShadowL.crop(86, 0, 15, 40);

		playerStillR = animatedPlayerR.crop(103, 0, 15, 40);
		playerStillL = animatedPlayerL.crop(1, 0, 15, 40);
		
		shadowStillR = animatedShadowR.crop(103, 0, 15, 40);
		shadowStillL = animatedShadowL.crop(1, 0, 15, 40);

		back1 = maps[0].crop(0, 0, 200, 120);
		back2 = maps[1].crop(0, 0, 200, 120);
	}
}