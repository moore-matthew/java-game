package mathium.game.entity.mob;

import mathium.game.game;
import mathium.game.entity.projectile.Projectile;
import mathium.game.entity.projectile.WizardProjectile;
import mathium.game.graphics.AnimatedSprite;
import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;
import mathium.game.graphics.SpriteSheet;
import mathium.game.input.Keyboard;
import mathium.game.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim;
	private boolean walking = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int fireRate = 0;
	Projectile p;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_north;
		animSprite = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_north;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void update() {
		if (walking) animSprite.update();
		else
			animSprite.setFrame(0);

		int xa = 0;
		int ya = 0;

		if (fireRate > 0) fireRate--;

		if (anim < 10000) anim++;
		else
			anim = 0;

		if (input.up) {
			animSprite = up;
			ya--;
		} else if (input.down) {
			animSprite = down;
			ya++;
		} else if (input.left) {
			animSprite = left;
			xa--;

		}
		if (input.right) {
			animSprite = right;
			xa++;

		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		clear();
		updateShooting();

	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = (Mouse.getX() - (game.getWindowWidth()) / 2);
			double dy = (Mouse.getY() - (game.getWindowHeight()) / 2);
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, sprite, flip);
	}

}
