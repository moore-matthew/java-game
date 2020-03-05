package mathium.game.level.tile.spawn_level;

import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;
import mathium.game.level.tile.Tile;

public class SpawnWoodTile extends Tile {

	public SpawnWoodTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
