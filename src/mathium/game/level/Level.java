package mathium.game.level;

import java.util.ArrayList;
import java.util.List;

import mathium.game.entity.Entity;
import mathium.game.entity.particle.Particle;
import mathium.game.entity.projectile.Projectile;
import mathium.game.entity.spawner.Spawner;
import mathium.game.graphics.Screen;
import mathium.game.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();

	public static Level spawn = new Level("/levels/spawn.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();

	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {

	}

	protected void loadLevel(String path) {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	private void time() {

	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}

		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {

		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else {
			entities.add(e);
		}
	}

	// Grass = 0xFFFF00
	// Tall Grass = 0xFF00A651
	// Flower = 0xFFFFFF00
	// Rock = 0xFF754C24

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass_tile;
		if (tiles[x + y * width] == Tile.col_spawn_tall_grass) return Tile.spawn_tall_grass;
		if (tiles[x + y * width] == Tile.col_spawn_bush) return Tile.spawn_bush;
		if (tiles[x + y * width] == Tile.col_spawn_water) return Tile.spawn_water;
		if (tiles[x + y * width] == Tile.col_spawn_stonewall) return Tile.spawn_stonewall;
		if (tiles[x + y * width] == Tile.col_spawn_mosswall) return Tile.spawn_mosswall;
		if (tiles[x + y * width] == Tile.col_spawn_dirt) return Tile.spawn_dirt;
		if (tiles[x + y * width] == Tile.col_spawn_wood) return Tile.spawn_wood;

		if (tiles[x + y * width] == 0xFFFFFF00) return Tile.flower;
		if (tiles[x + y * width] == 0xFF754C24) return Tile.rock;

		return Tile.voidTile;
	}

}
