package mathium.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import mathium.game.entity.mob.Player;
import mathium.game.graphics.Screen;
import mathium.game.graphics.Sprite;
import mathium.game.graphics.SpriteSheet;
import mathium.game.input.Keyboard;
import mathium.game.input.Mouse;
import mathium.game.level.Level;
import mathium.game.level.RandomLevel;
import mathium.game.level.SpawnLevel;
import mathium.game.level.TileCoord;

public class game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	/**
	 * Game frame variables
	 */
	private static int width = 300;
	private static int height = width / 16 * 9; // 168.75
	private static int scale = 3;
	public static String title = "Game";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// Constructor
	public game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();

		key = new Keyboard();
		addKeyListener(key);
		Mouse mouse = new Mouse();
		;
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		level = new SpawnLevel("/levels/spawn.png");
		TileCoord playerSpawn = new TileCoord(19, 60);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);

	}
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	int x = 0, y = 0;

	public void update() {
		key.update();
		player.update();
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
			
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		game game = new game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}
}
