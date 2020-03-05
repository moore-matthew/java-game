package mathium.game.entity.spawner;

import mathium.game.entity.Entity;
import mathium.game.entity.particle.Particle;
import mathium.game.level.Level;

public class Spawner extends Entity{
	
	public enum Type {
		MOB, PARTICLE
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
