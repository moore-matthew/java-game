package mathium.game.level.tile;

import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;
import mathium.game.level.tile.spawn_level.SpawnBushTile;
import mathium.game.level.tile.spawn_level.SpawnDirtTile;
import mathium.game.level.tile.spawn_level.SpawnGrassTile;
import mathium.game.level.tile.spawn_level.SpawnTallGrassTile;
import mathium.game.level.tile.spawn_level.SpawnWallTile;
import mathium.game.level.tile.spawn_level.SpawnWaterTile;
import mathium.game.level.tile.spawn_level.SpawnWoodTile;

public class Tile {

	public int x,y;
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile grassTall = new GrassTallTile(Sprite.grassTall);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawn_grass_tile = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_tall_grass = new SpawnTallGrassTile(Sprite.spawn_tall_grass);
	public static Tile spawn_bush = new SpawnBushTile(Sprite.spawn_bush);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_stonewall = new SpawnWallTile(Sprite.spawn_stonewall);
	public static Tile spawn_mosswall = new SpawnWallTile(Sprite.spawn_mosswall);
	public static Tile spawn_dirt = new SpawnDirtTile(Sprite.spawn_dirt);
	public static Tile spawn_wood = new SpawnWoodTile(Sprite.spawn_wood);
	
	public final static int col_spawn_grass = 0xff00ff00;
	public final static int col_spawn_tall_grass = 0xff00a651;
	public final static int col_spawn_bush = 0xff005952;
	public final static int col_spawn_water = 0xff00bff3;
	public final static int col_spawn_stonewall = 0xff000000;
	public final static int col_spawn_mosswall = 0xff406618;
	public final static int col_spawn_dirt = 0xff603913;
	public final static int col_spawn_wood = 0xff754c24;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
}
