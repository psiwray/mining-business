package net.blackhamm3rjack.mining_business.engine;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.interfaces.Renderable;

/**
 * Simple sprite class with single texture
 * 
 * @author Luca
 *
 */
@Versioning(major = 3, minor = 2, patch = 4)
public class Sprite extends Entity implements Renderable {
	/** The sprite texture */
	protected BufferedImage texture;
	/** Is the sprite fully loaded? */
	private boolean okay = false;

	/**
	 * Create a new sprite by providing only its position. Flag it as not fully
	 * loaded
	 * 
	 * @param position
	 *            The sprite position
	 */
	public Sprite(Point2D.Double position) {
		super(new Rectangle2D.Double(position.x, position.y, 0, 0), null);

		this.name = String.format("sprite[%x]", this.hashCode());
		this.texture = null;
		this.okay = false;
	}

	/**
	 * Create a new sprite. Deduce the sprite size from the texture
	 * 
	 * @param position
	 *            The sprite position
	 * @param texture
	 *            The sprite texture
	 */
	public Sprite(Point2D.Double position, BufferedImage texture) {
		super(new Rectangle2D.Double(position.x, position.y, texture.getWidth(), texture.getHeight()), null);

		this.name = String.format("sprite[%x]", this.hashCode());
		this.texture = texture;
		this.okay = true;
	}

	/**
	 * Get the sprite texture
	 * 
	 * @return The sprite texture
	 */
	public BufferedImage getTexture() {
		return texture;
	}

	/**
	 * Set the sprite texture and update its size
	 * 
	 * @param texture
	 *            The sprite texture
	 */
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
		this.bounds = new Rectangle2D.Double(bounds.x, bounds.y, texture.getWidth(), texture.getHeight());

		// Setup of the sprite done
		if (!okay)
			okay = true;
	}

	@Override
	public void render(Graphics2D graphics) {
		if (visible && okay)
			graphics.drawImage(texture, (int) Math.round(bounds.x - Camera.getPositionX()), (int) Math.round(bounds.y - Camera.getPositionY()), (int) Math.round(bounds.width), (int) Math.round(bounds.height), null);
	}
}
