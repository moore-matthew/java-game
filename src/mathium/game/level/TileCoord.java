package mathium.game.level;

public class TileCoord {
	
	private int x,y;
	private final int TILE_SIZE = 16;
	
	public TileCoord(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public int[] xy() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
}
