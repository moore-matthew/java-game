package mathium.game.level.tile;

import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;

public class GrassTallTile extends Tile {

	public GrassTallTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
