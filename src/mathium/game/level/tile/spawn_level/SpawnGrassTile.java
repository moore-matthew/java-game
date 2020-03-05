package mathium.game.level.tile.spawn_level;

import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;
import mathium.game.level.tile.Tile;

public class SpawnGrassTile extends Tile {

	public SpawnGrassTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
