package mathium.game.entity.particle;

import mathium.game.entity.Entity;
import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;

public class Particle extends Entity {

	private Sprite sprite;
	private int life;
	private int time = 0;
	protected double xa,ya, za; 
	protected double xd, yd, zd;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xd = x;
		this.yd = y;
		this.life = life + (random.nextInt(40) - 10);
		sprite = Sprite.particle_test;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zd = random.nextFloat() + 2.0;
	}


	public void update() {
		time++;
		if (time >= 10000) time = 0;
		if (time > life) remove();
		za -= 0.1;
		
		if (zd < 0) {
			zd = 0;
			za *= -.6;
			xa *= .4;
			ya *= .4;
		}
		move(xd + xa, (yd + ya) + (zd + za));
	}
	
	private void move(double x, double y) {
		if(collision(x, y)){
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xd += xa;
		this.yd += ya;
		this.zd += za;
	}


	public boolean collision(double x,double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16 ) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}

		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int)xd , (int)yd - (int)zd, sprite, true);
	}
}

