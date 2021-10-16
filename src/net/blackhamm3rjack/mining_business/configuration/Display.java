package net.blackhamm3rjack.mining_business.configuration;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Display configuration data
 * 
 * @author Luca
 *
 */
@Versioning()
public class Display {
	/** Display width */
	private int width;
	/** Display height */
	private int height;
	/** Frames per second for the engine */
	private int framesPerSecond;
	/** Number of display buffers */
	private int buffersCount;

	/** Should the front buffer be accelerated? */
	private boolean frontBufferAccelerated;
	/** Should the back buffer be accelerated? */
	private boolean backBufferAccelerated;
	/** Should there be some buffer state checks? */
	private boolean bufferChecks;
	/** Should there be some optional engine checks? */
	private boolean optionalChecks;

	/** Should the engine try to use the dedicated graphics card? */
	private boolean dedicatedGraphics;

	/**
	 * Create a new display settings object
	 * 
	 * @param width
	 *            Display width
	 * @param height
	 *            Display height
	 * @param framesPerSecond
	 *            Frames per second for the engine
	 * @param buffersCount
	 *            Number of display buffers
	 * @param frontBufferAccelerated
	 *            Should the front buffer be accelerated?
	 * @param backBufferAccelerated
	 *            Should the back buffer be accelerated?
	 * @param bufferChecks
	 *            Should there be some buffer state checks?
	 * @param optionalChecks
	 *            Should there be some optional engine checks?
	 * @param dedicatedGraphics
	 *            Should the engine try to use the dedicated graphics card?
	 */
	public Display(int width, int height, int framesPerSecond, int buffersCount, boolean frontBufferAccelerated,
			boolean backBufferAccelerated, boolean bufferChecks, boolean optionalChecks, boolean dedicatedGraphics) {
		this.width = width;
		this.height = height;
		this.framesPerSecond = framesPerSecond;
		this.buffersCount = buffersCount;
		this.frontBufferAccelerated = frontBufferAccelerated;
		this.backBufferAccelerated = backBufferAccelerated;
		this.bufferChecks = bufferChecks;
		this.optionalChecks = optionalChecks;
		this.dedicatedGraphics = dedicatedGraphics;
	}

	/**
	 * Get the display width
	 * 
	 * @return The display width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set the display width
	 * 
	 * @param width
	 *            The display width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Get the display height
	 * 
	 * @return The display height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the display height
	 * 
	 * @param height
	 *            The display height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get the frames per second for the engine
	 * 
	 * @return The frames per second for the engine
	 */
	public int getFramesPerSecond() {
		return framesPerSecond;
	}

	/**
	 * Set the frames per second for the engine
	 * 
	 * @param framesPerSecond
	 *            The frames per second for the engine
	 */
	public void setFramesPerSecond(int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}

	/**
	 * Get the number of display buffers
	 * 
	 * @return The number of display buffers
	 */
	public int getBuffersCount() {
		return buffersCount;
	}

	/**
	 * Set the number of display buffers
	 * 
	 * @param buffersCount
	 *            The number of display buffers
	 */
	public void setBuffersCount(int buffersCount) {
		this.buffersCount = buffersCount;
	}

	/**
	 * Is the front buffer accelerated
	 * 
	 * @return The front buffer acceleration state
	 */
	public boolean isFrontBufferAccelerated() {
		return frontBufferAccelerated;
	}

	/**
	 * Set the front buffer acceleration state
	 * 
	 * @param frontBufferAccelerated
	 *            The front buffer acceleration state
	 */
	public void setFrontBufferAccelerated(boolean frontBufferAccelerated) {
		this.frontBufferAccelerated = frontBufferAccelerated;
	}

	/**
	 * Is the back buffer accelerated
	 * 
	 * @return The back buffer acceleration state
	 */
	public boolean isBackBufferAccelerated() {
		return backBufferAccelerated;
	}

	/**
	 * Set the back buffer acceleration state
	 * 
	 * @param backBufferAccelerated
	 *            The back buffer acceleration state
	 */
	public void setBackBufferAccelerated(boolean backBufferAccelerated) {
		this.backBufferAccelerated = backBufferAccelerated;
	}

	/**
	 * Are there buffer checks?
	 * 
	 * @return The buffer checks operational state
	 */
	public boolean shouldDoBufferChecks() {
		return bufferChecks;
	}

	/**
	 * Set the buffer checks operational state
	 * 
	 * @param bufferChecks
	 *            The buffer checks operational state
	 */
	public void setBufferChecks(boolean bufferChecks) {
		this.bufferChecks = bufferChecks;
	}

	/**
	 * Are there optional engine checks?
	 * 
	 * @return The optional engine checks operational state
	 */
	public boolean shouldDoOptionalChecks() {
		return optionalChecks;
	}

	/**
	 * Set the optional engine checks operational state
	 * 
	 * @param optionalChecks
	 *            The optional engine checks operational state
	 */
	public void setOptionalChecks(boolean optionalChecks) {
		this.optionalChecks = optionalChecks;
	}

	/**
	 * Is the dedicated graphics card used?
	 * 
	 * @return The dedicated graphics card usage state
	 */
	public boolean shouldUseDedicatedGraphics() {
		return dedicatedGraphics;
	}

	/**
	 * Set the dedicated graphics card usage state
	 * 
	 * @param dedicatedGraphics
	 *            The dedicated graphics card usage state
	 */
	public void setDedicatedGraphics(boolean dedicatedGraphics) {
		this.dedicatedGraphics = dedicatedGraphics;
	}

}
