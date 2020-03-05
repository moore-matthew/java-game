package mathium.game.entity.mob;

import mathium.game.graphics.AnimatedSprite;
import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;
import mathium.game.graphics.SpriteSheet;

public class Follower extends Mob {
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.mob_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.mob_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.mob_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.mob_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	private int xa = 0;
	private int ya = 0;
	private int time = 0;
	
	public Follower(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.mob_test;
	}

	@Override
	public void update() {
		time++;
		
		if (time % (random.nextInt(50) + 30) == 0) {
			if (random.nextBoolean() == true) {
				xa = random.nextInt(3) - 1;
			} else
				ya = random.nextInt(3) - 1;
			if (random.nextInt(3) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		if (walking) animSprite.update();
		else
			animSprite.setFrame(0);
		if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		} else if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

	}

	@Override
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x, y, this);
		
	}
}
