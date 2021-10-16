package net.blackhamm3rjack.mining_business.engine;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.interfaces.Renderable;

/**
 * Layered sprite, with multiple textures visible at once
 * 
 * @author Luca
 *
 */
@Versioning(minor = 3, patch = 3, working = true)
public class LayeredSprite extends Entity implements Renderable {
	/** Every sprite layer */
	private BufferedImage[] layers;
	/** Every sprite layer visibility flag */
	private boolean[] layersVisibility;

	/**
	 * Create a new layered sprite by only specifying the position and flagging
	 * it as with no layers
	 * 
	 * @param bounds
	 *            The sprite bounds
	 */
	public LayeredSprite(Rectangle2D.Double bounds) {
		super(bounds, null);

		this.name = String.format("layered_sprite[%x]", this.hashCode());
		this.layers = new BufferedImage[0];
		this.layersVisibility = new boolean[0];
	}

	/**
	 * Create a new layered sprite and flag it as invisible. The size is deduced
	 * from the sprite first layer
	 * 
	 * @param position
	 *            The sprite position
	 * @param layers
	 *            The sprite layers
	 */
	public LayeredSprite(Point2D.Double position, BufferedImage[] layers) {
		super(null, null);

		assert layers.length > 0;
		assert layers.length <= Byte.MAX_VALUE;

		this.name = String.format("layered_sprite[%x]", this.hashCode());
		this.layers = layers;
		this.layersVisibility = new boolean[layers.length];
		this.bounds = new Rectangle2D.Double(position.x, position.y, layers[0].getWidth(), layers[0].getHeight());
	}

	/**
	 * Create a new layered sprite
	 * 
	 * @param position
	 *            The sprite position
	 * @param layers
	 *            The sprite layers
	 * @param layersVisibility
	 *            The sprite layers visibility flags
	 */
	public LayeredSprite(Point2D.Double position, BufferedImage[] layers, boolean[] layersVisibility) {
		super(null, null);

		assert layers.length > 0;
		assert layers.length <= Byte.MAX_VALUE;

		// Check even visibility array length
		assert layersVisibility.length == layers.length;

		this.name = String.format("layered_sprite[%x]", this.hashCode());
		this.layers = layers;
		this.layersVisibility = layersVisibility;
		this.bounds = new Rectangle2D.Double(position.x, position.y, layers[0].getWidth(), layers[0].getHeight());
	}

	/**
	 * Get every sprite layer
	 * 
	 * @return The sprite layers
	 */
	public BufferedImage[] getLayers() {
		return layers;
	}

	/**
	 * Set every sprite layer. The layers count can be even different
	 * 
	 * @param layers
	 *            The sprite layers
	 */
	public void setLayers(BufferedImage[] layers) {
		// Check new length
		assert layers != null;

		// Copy the contents and expand/reduce
		this.layers = layers;
		this.layersVisibility = Arrays.copyOf(layersVisibility, layers.length);
	}

	/**
	 * Get a single layer by index
	 * 
	 * @param index
	 *            The layer index
	 * @return The desired layer
	 */
	public BufferedImage getLayer(byte index) {
		assert index >= 0;
		assert index < layers.length;

		return layers[index];
	}

	/**
	 * Set a single layer image by index
	 * 
	 * @param index
	 *            The layer index
	 * @param layer
	 *            The desired layer image
	 */
	public void setLayer(byte index, BufferedImage layer) {
		assert index >= 0;
		assert index < layers.length;

		layers[index] = layer;
	}

	/**
	 * Get the layer visibility flags
	 * 
	 * @return The layer visibility flags
	 */
	public boolean[] getLayersVisibility() {
		return layersVisibility;
	}

	/**
	 * Set the layer visibility flags. The flags count can even differ from the
	 * 
	 * @param layersVisibility
	 *            The layer visibility flags
	 */
	public void setLayersVisibility(boolean[] layersVisibility) {
		// Check new length
		assert layersVisibility != null;

		// Copy the contents and expand/reduce
		this.layers = Arrays.copyOf(layers, layersVisibility.length);
		this.layersVisibility = layersVisibility;
	}

	@Override
	public void render(Graphics2D graphics) {
		if (visible) {
			for (byte i = 0; i < layers.length; i++)
				if (layersVisibility[i]) {
					// Draw only if the layer is visible
					graphics.drawImage(layers[i], (int) Math.round(bounds.x - Camera.getPositionX()), (int) Math.round(bounds.y - Camera.getPositionY()), (int) Math.round(bounds.width), (int) Math.round(bounds.height), null);
				}
		}
	}

	/**
	 * Get the flag for a single layer
	 * 
	 * @param index
	 *            The layer index
	 * @return The layer flag
	 */
	public boolean isLayerVisible(byte index) {
		assert index >= 0;
		assert index < layers.length;

		return layersVisibility[index];
	}

	/**
	 * Set the flag for a single layer
	 * 
	 * @param index
	 *            The layer index
	 * @param visible
	 *            The layer flag
	 */
	public void setLayerVisible(byte index, boolean visible) {
		assert index >= 0;
		assert index < layers.length;

		layersVisibility[index] = visible;
	}
}
