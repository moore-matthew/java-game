package mathium.game.graphics;

import java.awt.Color;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width,height;
	public int[] pixels;
	protected SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 3, 1, SpriteSheet.tiles);
	public static Sprite grassTall = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0xff0d96ff);
	
	/*****
	 * Spawn Sprites
	 */
	
	public static Sprite spawn_grass = new Sprite(16,3,0, SpriteSheet.spawn_level);
	public static Sprite spawn_tall_grass = new Sprite(16,3,1, SpriteSheet.spawn_level);
	public static Sprite spawn_bush = new Sprite(16,1,0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16,2,0, SpriteSheet.spawn_level);
	public static Sprite spawn_stonewall = new Sprite(16,0,0, SpriteSheet.spawn_level);
	public static Sprite spawn_mosswall = new Sprite(16,0,1, SpriteSheet.spawn_level);
	public static Sprite spawn_dirt = new Sprite(16,1,1, SpriteSheet.spawn_level);
	public static Sprite spawn_wood = new Sprite(16,2,1, SpriteSheet.spawn_level);
	
	/*****
	 * Player Sprites
	 *****/
	
	public static Sprite player_north = new Sprite(32, 5, 7, SpriteSheet.tiles);
	public static Sprite player_south = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 6, 6, SpriteSheet.tiles);

	public static Sprite player_north_1 = new Sprite(32, 6, 7, SpriteSheet.tiles);
	public static Sprite player_north_2 = new Sprite(32, 4, 7, SpriteSheet.tiles);

	public static Sprite player_south_1 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_south_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

	public static Sprite player_side_1 = new Sprite(32, 5, 6, SpriteSheet.tiles);
	public static Sprite player_side_2 = new Sprite(32, 7, 6, SpriteSheet.tiles);
	
	public static Sprite mob_test = new Sprite(32,0,0,SpriteSheet.mob_down);
	
	/*****
	 * Projectile Sprites
	 *****/
	public static Sprite projectile_wizard = new Sprite(16,0,0,SpriteSheet.projectile_wizard);
	
	/*****
	 * Particles
	 *****/
	
	public static Sprite particle_test = new Sprite(1, 0xffffff);
	
	
	/****************************************************************************************************/
	
	protected Sprite( SpriteSheet sheet, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}

	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}

}
