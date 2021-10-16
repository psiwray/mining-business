package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.LayeredSprite;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.utils.Configuration;

/**
 * Simple block super class
 * 
 * @author Luca
 *
 */
@Versioning(major = 3, minor = 2, patch = 13, working = true)
public class Block extends LayeredSprite {
	/** Can the block be damaged? */
	private boolean damageable;
	/** Can the block receive collisions? */
	private boolean solid;

	/** The current block damage */
	private byte damage;
	/** The mineral ID */
	private byte id;

	/** The block default width */
	public static final byte WIDTH = Mineral.TEXTURE_WIDTH;
	/** The block default height */
	public static final byte HEIGHT = Mineral.TEXTURE_HEIGHT;

	/**
	 * The background type of the block
	 * 
	 * @author Luca
	 *
	 */
	public enum Background {
		/** Dirt with no grass, light color */
		NO_GRASS_LIGHT(Mineral.getTexture("no_grass_light"), (byte) 0),
		/** Dirt with grass, light color */
		GRASS_LIGHT(Mineral.getTexture("grass_light"), (byte) 1),
		/** Dirt with no grass, dark color */
		NO_GRASS_DARK(Mineral.getTexture("no_grass_dark"), (byte) 2),
		/** Dirt with grass, dark color */
		GRASS_DARK(Mineral.getTexture("grass_dark"), (byte) 3),
		/** Plain stone */
		STONE(Mineral.getTexture("stone"), (byte) 4),
		/** An uniform dirt background */
		BACKGROUND_DIRT(Mineral.getTexture("bg_dirt")),
		/** An uniform stone background */
		BACKGROUND_STONE(Mineral.getTexture("bg_stone"));

		/** The related texture */
		private BufferedImage image;
		/** The dump index (used by I/O classes) */
		private byte dumpIndex;

		/**
		 * Create a new background type
		 * 
		 * @param image
		 *            The related texture
		 */
		Background(BufferedImage image) {
			this.image = image;
		}

		/**
		 * Create a new background type
		 * 
		 * @param image
		 *            The related texture
		 * @param dumpIndex
		 *            The dump index
		 */
		Background(BufferedImage image, byte dumpIndex) {
			this.image = image;
			this.dumpIndex = dumpIndex;
		}

		/**
		 * Get the related texture
		 * 
		 * @return The related texture
		 */
		public BufferedImage getImage() {
			return image;
		}

		/**
		 * Get the dump index
		 * 
		 * @return The dump index
		 */
		public byte getDumpIndex() {
			return dumpIndex;
		}

		/**
		 * Get the background type form its index
		 * 
		 * @param dumpIndex
		 *            The dump index
		 * @return The background or null if invalid
		 */
		public static Background getFromDump(byte dumpIndex) {
			for (Background background : values())
				if (background.getDumpIndex() == dumpIndex)
					return background;

			return null;
		}
	}

	/** The block foreground */
	private Background foreground;
	/** The block background */
	private Background background;

	/**
	 * Create a new block
	 * 
	 * @param position
	 *            The block position, in blocks
	 * @param data
	 *            The block data (dirt, stone, mineral...)
	 * @param foreground
	 *            The block foreground. Automatically deduce the block
	 *            background
	 */
	public Block(Point position, Mineral data, Background foreground) {
		super(new Rectangle2D.Double(position.x * WIDTH, position.y * HEIGHT, WIDTH, HEIGHT));

		assert foreground != Background.BACKGROUND_DIRT;
		assert foreground != Background.BACKGROUND_STONE;

		this.name = String.format("block[0x%08x]", this.hashCode());
		this.damageable = true;
		this.solid = true;
		this.damage = 0;
		this.foreground = foreground;
		this.id = data.getId();

		// Automatically deduce the background type
		if (foreground == Background.STONE)
			this.background = Background.BACKGROUND_STONE;
		else
			this.background = Background.BACKGROUND_DIRT;

		double zoom = Configuration.engine.getZoomFactor();

		// Gather settings from the configuration
		setWidth(zoom * WIDTH);
		setHeight(zoom * HEIGHT);

		setPositionX((zoom * WIDTH) * position.x);
		setPositionY((zoom * HEIGHT) * position.y);

		// Set layers
		setLayers(new BufferedImage[Mineral.DAMAGE_STAGES + 3]);
		setLayer((byte) 0, background.getImage());
		setLayer((byte) 1, foreground.getImage());
		setLayer((byte) 2, data.getTexture());

		for (byte i = 0; i < Mineral.DAMAGE_STAGES; i++)
			setLayer((byte) (i + 3), Mineral.getTexture("d_stage_" + i));

		// Set visibility
		setLayersVisibility(new boolean[Mineral.DAMAGE_STAGES + 3]);
		setLayerVisible((byte) 0, false);
		setLayerVisible((byte) 1, true);
		setLayerVisible((byte) 2, true);

		for (byte i = 0; i < Mineral.DAMAGE_STAGES; i++)
			setLayerVisible((byte) (i + 3), false);
	}

	/**
	 * Can the block be damaged?
	 * 
	 * @return The block damage permission state
	 */
	public boolean isDamageable() {
		return damageable;
	}

	/**
	 * Set the block damage permission
	 * 
	 * @param damageable
	 *            The block damage permission
	 */
	public void setDamageable(boolean damageable) {
		this.damageable = damageable;
	}

	/**
	 * Can the block receive collisions?
	 * 
	 * @return The block collision detection state
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * Set the block collision detection state
	 * 
	 * @param solid
	 *            The block collision detection state
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	/** Add a unit of damage to the block */
	public void damage() {
		// if (damageable && damage < Mineral.DAMAGE_STAGES) {
		// setDamage((byte) (damage + 1));
		// Logger.print(Tag.DEBUG, Block.class, "Block damaged @[%d, %d]: " +
		// damage, getBlockPositionX(), getBlockPositionY());
		// } else {
		// solid = false;
		//
		// // Show the background
		// for (byte i = 0; i < Mineral.DAMAGE_STAGES + 3; i++)
		// if (i == 0)
		// setLayerVisible(i, true);
		// else
		// setLayerVisible(i, false);
		// }

		if (damage <= Mineral.DAMAGE_STAGES)
			setDamage((byte) (damage + 1));
	}

	/** Remove a unit of damage from the block */
	public void repair() {
		// if (damageable && damage > 0) {
		// setDamage((byte) (damage - 1));
		// Logger.print(Tag.DEBUG, Block.class, "Block repaired @[%d, %d]: " +
		// damage, getBlockPositionX(), getBlockPositionY());
		//
		// // Bring back the mineral to life
		// if (isLayerVisible((byte) 0)) {
		// setLayerVisible((byte) 0, false);
		// setLayerVisible((byte) 1, true);
		// setLayerVisible((byte) 2, true);
		// }
		// }

		if (damage > 0)
			setDamage((byte) (damage - 1));
	}

	/**
	 * Get the current damage units
	 * 
	 * @return The current damage units
	 */
	public byte getDamage() {
		return damage;
	}

	/**
	 * Set the current damage units
	 * 
	 * @param damage
	 *            The current damage units
	 */
	public void setDamage(byte damage) {
		// assert damage >= 0;
		// assert damage <= Mineral.DAMAGE_STAGES;
		//
		// this.damage = damage;
		//
		// for (byte i = 0; i < Mineral.DAMAGE_STAGES; i++)
		// if (i < damage)
		// setLayerVisible((byte) (i + 3), true);
		// else
		// setLayerVisible((byte) (i + 3), false);

		assert damage >= 0;
		assert damage <= Mineral.DAMAGE_STAGES + 1;

		this.damage = damage;

		// TODO: Affects performance
		if (damage != Mineral.DAMAGE_STAGES + 1) {
			// Show block face
			setLayerVisible((byte) 0, false);
			setLayerVisible((byte) 1, true);
			setLayerVisible((byte) 2, true);

			for (byte l = 0; l < damage; l++)
				setLayerVisible((byte) (l + 3), true);

			this.solid = true;
		} else {
			// Hide block face
			setLayerVisible((byte) 0, true);
			setLayerVisible((byte) 1, false);
			setLayerVisible((byte) 2, false);

			for (byte l = 0; l < Mineral.DAMAGE_STAGES; l++)
				setLayerVisible((byte) (l + 3), false);

			this.solid = false;
		}
	}

	/**
	 * Get the block position
	 * 
	 * @return The block position
	 */
	public Point getBlockPosition() {
		return new Point((int) (getPositionX() / getWidth()), (int) (getPositionY() / getHeight()));
	}

	/**
	 * Get the block position along X axis
	 * 
	 * @return The block position along X axis
	 */
	public int getBlockPositionX() {
		return (int) (getPositionX() / getWidth());
	}

	/**
	 * Get the block position along Y axis
	 * 
	 * @return The block position along Y axis
	 */
	public int getBlockPositionY() {
		return (int) (getPositionY() / getHeight());
	}

	/**
	 * Get the block foreground
	 * 
	 * @return The block foreground
	 */
	public Background getForeground() {
		return foreground;
	}

	/**
	 * Get the block background
	 * 
	 * @return The block background
	 */
	public Background getBackground() {
		return background;
	}

	/**
	 * Get the block mineral ID
	 * 
	 * @return The block mineral ID
	 */
	public byte getId() {
		return id;
	}

	/**
	 * Set the block position
	 * 
	 * @param x
	 *            The position along X axis
	 * @param y
	 *            The position along Y axis
	 */
	public void setBlockPosition(int x, int y) {
		setPositionX(x * getWidth());
		setPositionY(y * getHeight());
	}

	/**
	 * Set the block position along X axis
	 * 
	 * @param x
	 *            The block position along X axis
	 */
	public void setBlockPositionX(int x) {
		setPositionX(x * getWidth());
	}

	/**
	 * Set the block position along Y axis
	 * 
	 * @param y
	 *            The block position along Y axis
	 */
	public void setBlockPositionY(int y) {
		setPositionX(y * getHeight());
	}

	/**
	 * Is the block destroyed?
	 * 
	 * @return The block destruction state
	 */
	public boolean isDestroyed() {
		return damage == Mineral.DAMAGE_STAGES + 1;
	}
}
