package net.blackhamm3rjack.mining_business.engine.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.apache.commons.math3.util.FastMath;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.Camera;
import net.blackhamm3rjack.mining_business.engine.LayeredSprite;
import net.blackhamm3rjack.mining_business.engine.blocks.Block;
import net.blackhamm3rjack.mining_business.engine.events.BlockDestroyEvent;
import net.blackhamm3rjack.mining_business.engine.events.EventDispatcher;
import net.blackhamm3rjack.mining_business.utils.Configuration;
import net.blackhamm3rjack.mining_business.window.GameLooper;
import net.blackhamm3rjack.mining_business.window.input.PointerManager;
import net.blackhamm3rjack.mining_business.window.input.buttons.ButtonInput;
import net.blackhamm3rjack.mining_business.window.input.keys.KeyInput;

/**
 * Main character
 * 
 * @author lucac
 *
 */
@Versioning(major = 2, minor = 5, patch = 4, working = true)
public class Player extends LayeredSprite {
	/**
	 * The collision data for the player, changes every time
	 * 
	 * @author lucac
	 *
	 */
	private class CollisionData {
		public Block[] blocks;

		public int bw = (int) (Block.WIDTH * Configuration.engine.getZoomFactor());
		public int bh = (int) (Block.HEIGHT * Configuration.engine.getZoomFactor());

		public int x;
		public int y;
		public int w;
		public int h;
	}

	/**
	 * The block damager information
	 * 
	 * @author lucac
	 *
	 */
	private class BlockDamager {
		public double interval = 250_000_000;
		public double passedTime = 0;
		public boolean destroy = false;
	}

	private String name;
	private World world;
	private Point2D.Double speed;
	private CollisionData colData;
	private BlockDamager damager;
	private KeyInput keyInput;
	private ButtonInput buttonInput;

	private static final int WIDTH = 20;
	private static final int HEIGHT = 50;
	private static final int COL_OFFSET = 5;

	public Player(Point2D.Double position, String name, World world, GameLooper looper) {
		super(new Rectangle2D.Double(position.x, position.y, WIDTH, HEIGHT));

		this.name = name;
		this.world = world;
		this.keyInput = looper.getKeyInput();
		this.buttonInput = looper.getButtonInput();
		this.speed = new Point2D.Double(0, 0);
		this.colData = new CollisionData();
		this.damager = new BlockDamager();

		colData.blocks = new Block[6];
		colData.w = (int) getWidth();
		colData.h = (int) getHeight();
	}

	public void setSpeed(Point2D.Double speed) {
		this.speed.x = speed.x;
		this.speed.y = speed.y;
	}

	public void setSpeed(double speedX, double speedY) {
		this.speed.x = speedX;
		this.speed.y = speedY;
	}

	public void setSpeedX(double speed) {
		this.speed.x = speed;
	}

	public void setSpeedY(double speed) {
		this.speed.y = speed;
	}

	public String getName() {
		return name;
	}

	/**
	 * Check the collision by moving over the X axis
	 * 
	 * @param speed
	 *            The movement speed
	 * @return True if didn't collide
	 */
	private boolean checkCollisionX(double speed) {
		moveX(speed);

		for (Block block : colData.blocks)
			if (block != null && block.isSolid() && block.getBounds().intersects(getBounds()))
				return false;

		return true;
	}

	/**
	 * Check the collision by moving over the Y axis
	 * 
	 * @param speed
	 *            The movement speed
	 * @return True if didn't collide
	 */
	private boolean checkCollisionY(double speed) {
		moveY(speed);

		for (Block block : colData.blocks)
			if (block != null && block.isSolid() && block.getBounds().intersects(getBounds()))
				return false;

		return true;
	}

	@Override
	public void update(long deltaTime) {
		if (keyInput.isKeyDown(KeyEvent.VK_A))
			speed.x = -1;
		if (keyInput.isKeyDown(KeyEvent.VK_D))
			speed.x = +1;
		if (keyInput.isKeyDown(KeyEvent.VK_W))
			speed.y = -1;
		if (keyInput.isKeyDown(KeyEvent.VK_S))
			speed.y = +1;

		// Clicked the mouse button
		if (buttonInput.isButtonDown(MouseEvent.BUTTON1)) {
			damager.passedTime += deltaTime;

			if (damager.passedTime >= damager.interval)
				damager.destroy = true;
			else
				damager.destroy = false;

			if (damager.destroy) {
				int blockX = FastMath.floorDiv((int) (PointerManager.getPositionX() + Camera.getPositionX()), (int) (Block.WIDTH * Configuration.engine.getZoomFactor()));
				int blockY = FastMath.floorDiv((int) (PointerManager.getPositionY() + Camera.getPositionY()), (int) (Block.HEIGHT * Configuration.engine.getZoomFactor()));

				Block block = world.getBlock(blockX, blockY);

				if (block != null) {
					block.damage();

					// Reset the time and flag
					damager.destroy = false;
					damager.passedTime = 0;

					// Call the block destroy event
					if (block.isDestroyed())
						EventDispatcher.call(new BlockDestroyEvent(this, block, world));
				}
			}
		}

		colData.x = (int) getPositionX();
		colData.y = (int) getPositionY();

		// Upper-left
		colData.blocks[0] = world.getBlock(new Point(FastMath.round((colData.x - COL_OFFSET) / colData.bw), FastMath.round((colData.y - COL_OFFSET) / colData.bh)));
		// Upper-right
		colData.blocks[1] = world.getBlock(new Point(FastMath.round((colData.x + colData.w + COL_OFFSET) / colData.bw), FastMath.round((colData.y - COL_OFFSET) / colData.bh)));
		// Middle-left
		colData.blocks[2] = world.getBlock(new Point(FastMath.round((colData.x - COL_OFFSET) / colData.bw), FastMath.round((colData.y + colData.h / 2) / colData.bh)));
		// Middle-right
		colData.blocks[3] = world.getBlock(new Point(FastMath.round((colData.x + colData.w + COL_OFFSET) / colData.bw), FastMath.round((colData.y + colData.h / 2) / colData.bh)));
		// Lower-left
		colData.blocks[4] = world.getBlock(new Point(FastMath.round((colData.x - COL_OFFSET) / colData.bw), FastMath.round((colData.y + colData.h + COL_OFFSET) / colData.bh)));
		// Lower-right
		colData.blocks[5] = world.getBlock(new Point(FastMath.round((colData.x + colData.w + COL_OFFSET) / colData.bw), FastMath.round((colData.y + colData.h + COL_OFFSET) / colData.bh)));

		if (!checkCollisionX(speed.x))
			moveX(-speed.x);
		else
			// Move the camera too
			Camera.moveX(speed.x);
		if (!checkCollisionY(speed.y))
			moveY(-speed.y);
		else
			// Move the camera too
			Camera.moveY(speed.y);

		// Reset the speed
		setSpeed(0, 0);
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.setColor(Color.black);
		graphics.drawRect((int) (bounds.x - Camera.getPositionX()), (int) (bounds.y - Camera.getPositionY()), (int) (bounds.width - 1), (int) (bounds.height - 1));
	}
}
