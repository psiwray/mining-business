package net.blackhamm3rjack.mining_business.engine;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Animated sprite with multiple frames
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2, patch = 4)
public class AnimatedSprite extends Sprite {
	/** Every frame for the sprite */
	private BufferedImage[] frames;
	/** The index of the current frame */
	private byte index;

	/**
	 * Create a new animated sprite
	 * 
	 * @param position
	 *            Every frame for the sprite
	 * @param frames
	 *            The index of the current frame
	 */
	public AnimatedSprite(Point2D.Double position, BufferedImage[] frames) {
		super(position);

		// Check frames count
		assert frames.length > 0;
		assert frames.length <= Byte.MAX_VALUE;

		this.frames = frames;
		setTexture(frames[0]);
	}

	/**
	 * Create a new animated sprite
	 * 
	 * @param position
	 *            The position of the sprite
	 * @param frames
	 *            Every frame for the sprite
	 * @param index
	 *            The index of the current frame
	 */
	public AnimatedSprite(Point2D.Double position, BufferedImage[] frames, byte index) {
		super(position, null);

		// Check frames count
		assert frames.length > 0;
		assert frames.length <= Byte.MAX_VALUE;

		this.frames = frames;
		setTexture(frames[index]);
	}

	/**
	 * Get every frame for the sprite
	 * 
	 * @return Every frame for the sprite
	 */
	public BufferedImage[] getFrames() {
		return frames;
	}

	/**
	 * Get a single frame by index
	 * 
	 * @param index
	 *            The frame index
	 * @return The desired frame
	 */
	public BufferedImage getFrame(byte index) {
		assert index >= 0;
		assert index < frames.length;

		return frames[index];
	}

	/**
	 * Set a frame image by index
	 * 
	 * @param index
	 *            The frame index
	 * @param frame
	 *            The desired frame image
	 */
	public void setFrame(byte index, BufferedImage frame) {
		assert frame != null;
		assert index >= 0;
		assert index < frames.length;

		frames[index] = frame;
	}

	/**
	 * Get the index for the current frame
	 * 
	 * @return The index for the current frame
	 */
	public byte getIndex() {
		return index;
	}

	/**
	 * Set the index for the current frame
	 * 
	 * @param index
	 *            The index for the current frame
	 */
	public void setIndex(byte index) {
		assert index >= 0;
		assert index < frames.length;

		// Set index and texture
		this.index = index;
		setTexture(frames[index]);
	}

	/**
	 * Set every frame for the sprite
	 * 
	 * @param frames
	 *            Every frame for the sprite
	 */
	public void setFrames(BufferedImage[] frames) {
		// Check new length
		assert frames != null;
		assert frames.length == this.frames.length;

		this.frames = frames;
	}

	/**
	 * Move to the next frame in the array, go back to the first is the index is
	 * the last
	 */
	public void nextFrame() {
		if (index + 1 < frames.length)
			setIndex((byte) (index + 1));
		else
			setIndex((byte) 0);
	}

	/**
	 * Move to the previous frame in the array, go back to the last if the index
	 * is the first
	 */
	public void previousFrame() {
		if (index - 1 >= 0)
			setIndex((byte) (index - 1));
		else
			setIndex((byte) (frames.length - 1));
	}
}
