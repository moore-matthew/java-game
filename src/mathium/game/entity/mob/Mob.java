package mathium.game.entity.mob;

import mathium.game.entity.Entity;
import mathium.game.entity.projectile.Projectile;
import mathium.game.entity.projectile.WizardProjectile;
import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;

public abstract class Mob extends Entity {

	protected boolean moving = false;
	protected boolean walking = false;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	// xa / ya = x-axis / y-axis

	public void move(int xa, int ya) {
		System.out.println(level.getProjectiles().size());
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if (ya < 0) dir = Direction.UP; // North
		if (xa > 0) dir = Direction.RIGHT; // East
		if (ya > 0) dir = Direction.DOWN; // South
		if (xa < 0) dir = Direction.LEFT; // West

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}

	}

	public abstract void update();

	public abstract void render(Screen screen);

	protected void shoot(int x, int y, double dir) {
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 12 - 7) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}

		return solid;
	}
}
