package mathium.game.entity;

import java.util.Random;

import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;
import mathium.game.level.Level;

public abstract class Entity {

	public int x,y;
	protected Sprite sprite;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public Entity() {
		
	}
	
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		if (sprite!= null) screen.renderSprite(x,y,sprite,true);
	}
	
	public void remove() {
		removed = true;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
}
