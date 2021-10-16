package net.blackhamm3rjack.mining_business.engine.world;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.Entity;
import net.blackhamm3rjack.mining_business.engine.blocks.Block;
import net.blackhamm3rjack.mining_business.engine.interfaces.Renderable;
import net.blackhamm3rjack.mining_business.utils.Configuration;

/**
 * A group of block, with a dynamic position. It is almost like an entity but it
 * can't be moved
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2, patch = 6)
public class Chunk extends Entity implements Renderable {
	private Block[][] blocks;

	public static final byte WIDTH = 8;
	public static final byte HEIGHT = WIDTH;

	public Chunk(Point position) {
		// TODO: Cryptic constructor
		super(new Rectangle2D.Double(position.x * Block.WIDTH * Configuration.engine.getZoomFactor() * WIDTH, position.y * Block.HEIGHT * Configuration.engine.getZoomFactor() * HEIGHT, WIDTH * Block.WIDTH * Configuration.engine.getZoomFactor(), HEIGHT * Block.HEIGHT * Configuration.engine.getZoomFactor()), String.format("chunk[%d, %d]", position.x, position.y));

		this.blocks = new Block[WIDTH][HEIGHT];
	}

	public Chunk(int positionX, int positionY) {
		// TODO: Cryptic constructor
		super(new Rectangle2D.Double(positionX * Block.WIDTH * Configuration.engine.getZoomFactor() * WIDTH, positionY * Block.HEIGHT * Configuration.engine.getZoomFactor() * HEIGHT, WIDTH * Block.WIDTH * Configuration.engine.getZoomFactor(), HEIGHT * Block.HEIGHT * Configuration.engine.getZoomFactor()), String.format("chunk[%d, %d]", positionX, positionY));

		this.blocks = new Block[WIDTH][HEIGHT];
	}

	public Chunk(Point position, Block[][] blocks) {
		// TODO: Cryptic constructor
		super(new Rectangle2D.Double(position.x * Block.WIDTH * Configuration.engine.getZoomFactor() * WIDTH, position.y * Block.HEIGHT * Configuration.engine.getZoomFactor() * HEIGHT, WIDTH * Block.WIDTH * Configuration.engine.getZoomFactor(), HEIGHT * Block.HEIGHT * Configuration.engine.getZoomFactor()), String.format("chunk[%d, %d]", position.x, position.y));

		this.blocks = blocks;
	}

	public Chunk(int positionX, int positionY, Block[][] blocks) {
		// TODO: Cryptic constructor
		super(new Rectangle2D.Double(positionX * Block.WIDTH * Configuration.engine.getZoomFactor() * WIDTH, positionY * Block.HEIGHT * Configuration.engine.getZoomFactor() * HEIGHT, WIDTH * Block.WIDTH * Configuration.engine.getZoomFactor(), HEIGHT * Block.HEIGHT * Configuration.engine.getZoomFactor()), String.format("chunk[%d, %d]", positionX, positionY));

		this.blocks = blocks;
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public void setBlocks(Block[][] blocks) {
		assert blocks.length == WIDTH;
		assert blocks[0].length == HEIGHT;

		this.blocks = blocks;
	}

	public Point getChunkPosition() {
		return new Point((int) (getPositionX() / getWidth()), (int) (getPositionY() / getHeight()));
	}

	public int getChunkPositionX() {
		return (int) (getPositionX() / getWidth());
	}

	public int getChunkPositionY() {
		return (int) (getPositionY() / getHeight());
	}

	public Block getBlock(byte x, byte y) {
		assert x >= 0 && x < WIDTH;
		assert y >= 0 && y < HEIGHT;

		return blocks[x][y];
	}

	public void setBlock(byte x, byte y, Block block) {
		assert x >= 0 && x < WIDTH;
		assert y >= 0 && y < HEIGHT;

		if (block != null) {
			blocks[x][y] = block;
			blocks[x][y].setBlockPosition((int) (x + getChunkPositionX() * WIDTH), (int) (y + getChunkPositionY() * HEIGHT));
		} else
			blocks[x][y] = null;
	}

	@Override
	public void render(Graphics2D graphics) {
		for (byte x = 0; x < WIDTH; x++)
			for (byte y = 0; y < HEIGHT; y++)
				if (blocks[x][y] != null)
					blocks[x][y].render(graphics);
	}
}
