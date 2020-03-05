package mathium.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mathium.game.entity.mob.Follower;
import mathium.game.entity.mob.Test;
import mathium.game.level.tile.Tile;

public class SpawnLevel extends Level {
	
	public SpawnLevel(String path) {
		super(path);
	}
	
	
	protected void loadLevel(String path)	{
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error Loading Level");
		}
		for(int i = 0; i < 1; i++) {
			add(new Follower(20, 58));			
		}
	}
	
	
	// Grass = 0xFFFF00
	// Tall Grass = 0xFF00A651
	// Flower = 0xFFFFFF00
	// Rock = 0xFF754C24
	
	protected void generateLevel() {
		
	}
	
}
