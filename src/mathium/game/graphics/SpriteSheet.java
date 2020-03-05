package mathium.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	public final int WIDTH, HEIGHT;

	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheets/spritesheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/textures/spritesheets/spawnlevel.png", 64);
	public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/spritesheets/projectiles/wizardprojectiles.png", 48);

	public static SpriteSheet player = new SpriteSheet("/textures/spritesheets/player.png", 128, 96);
	public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 1, 3, 32);
	public static SpriteSheet player_up = new SpriteSheet(player, 1, 0, 1, 3, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 2, 0, 1, 3, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 3, 0, 1, 3, 32);
	
	public static SpriteSheet mob_test = new SpriteSheet("/textures/spritesheets/mob_test.png",128,96);
	public static SpriteSheet mob_down = new SpriteSheet(mob_test, 0, 0, 1, 3, 32);
	public static SpriteSheet mob_up = new SpriteSheet(mob_test, 1, 0, 1, 3, 32);
	public static SpriteSheet mob_left = new SpriteSheet(mob_test, 2, 0, 1, 3, 32);
	public static SpriteSheet mob_right = new SpriteSheet(mob_test, 3, 0, 1, 3, 32);

	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) {
			SIZE = width;
		} else {
			SIZE = -1;
		}

		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		
		for (int yO = 0; yO < h; yO++) {
			int yp = yy + yO;
			for (int xO = 0; xO < w; xO++) {
				int xp = xx + xO;
				pixels[xO + yO * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int yO = 0; yO < spriteSize; yO++) {
					for (int xO = 0; xO < spriteSize; xO++) {
						spritePixels[xO + yO * spriteSize] = pixels[(xO + xa * spriteSize) + (yO + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}

	public Sprite[] getSprites() {
		return sprites;

	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
