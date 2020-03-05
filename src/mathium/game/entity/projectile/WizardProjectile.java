package mathium.game.entity.projectile;

import mathium.game.entity.spawner.ParticleSpawner;
import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 10;

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 2.5;
		damage = 20;
		sprite = Sprite.projectile_wizard;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		if (level.tileCollision((int)(x + nx), (int)(y + ny), 8, 4, 4)) {
			level.add(new ParticleSpawner((int)x, (int)y, 100, 45, level));
			remove();
		}
		move();
	}

	public void move() {
			x += nx;
			y += ny;
		if (distance() > range) remove();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 10, (int) y - 5, this);
	}
}
