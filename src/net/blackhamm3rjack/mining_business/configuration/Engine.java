package net.blackhamm3rjack.mining_business.configuration;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Engine configuration data
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2)
public class Engine {
	/** Should output debug messages? */
	private boolean outputDebug;
	/** Should stop on error? */
	private boolean stopOnError;
	/** Does the game require to have page flipping? */
	private boolean requiresPageFlipping;
	/** Does the game require to have the multi buffer capability? */
	private boolean requiresMultiBuffer;

	/** The in-game zoom factor */
	private double zoomFactor;

	/**
	 * Create a new engine settings object
	 * 
	 * @param outputDebug
	 *            Should output debug messages?
	 * @param stopOnError
	 *            Should stop on error?
	 * @param requiresPageFlipping
	 *            Does the game require to have page flipping?
	 * @param requiresMultiBuffer
	 *            Does the game require to have the multi buffer capability?
	 * @param zoomFactor
	 *            The in-game zoom factor
	 */
	public Engine(boolean outputDebug, boolean stopOnError, boolean requiresPageFlipping, boolean requiresMultiBuffer,
			double zoomFactor) {
		this.outputDebug = outputDebug;
		this.stopOnError = stopOnError;
		this.requiresPageFlipping = requiresPageFlipping;
		this.requiresMultiBuffer = requiresMultiBuffer;
		this.zoomFactor = zoomFactor;
	}

	/**
	 * Are debug strings outputted?
	 * 
	 * @return The debug strings output state
	 */
	public boolean shouldOutputDebug() {
		return outputDebug;
	}

	/**
	 * Set the debug strings output state
	 * 
	 * @param outputDebug
	 *            The debug strings output state
	 */
	public void setOutputDebug(boolean outputDebug) {
		this.outputDebug = outputDebug;
	}

	/**
	 * Get the game error message stop state
	 * 
	 * @return The game error message stop state
	 */
	public boolean shouldStopOnError() {
		return stopOnError;
	}

	/**
	 * Set the game error message stop state
	 * 
	 * @param stopOnError
	 *            The game error message stop state
	 */
	public void setStopOnError(boolean stopOnError) {
		this.stopOnError = stopOnError;
	}

	/**
	 * Get the page flipping requirement
	 * 
	 * @return The page flipping requirement
	 */
	public boolean doesRequirePageFlipping() {
		return requiresPageFlipping;
	}

	/**
	 * Set the page flipping requirement state
	 * 
	 * @param requiresPageFlipping
	 *            The page flipping requirement state
	 */
	public void setRequiresPageFlipping(boolean requiresPageFlipping) {
		this.requiresPageFlipping = requiresPageFlipping;
	}

	/**
	 * Get the multi buffer capability requirement state
	 * 
	 * @return The multi buffer capability requirement state
	 */
	public boolean doesRequireMultiBuffer() {
		return requiresMultiBuffer;
	}

	/**
	 * Set the multi buffer capability requirement state
	 * 
	 * @param requiresMultiBuffer
	 *            The multi buffer capability requirement state
	 */
	public void setRequiresMultiBuffer(boolean requiresMultiBuffer) {
		this.requiresMultiBuffer = requiresMultiBuffer;
	}

	/**
	 * Get the game display zoom factor
	 * 
	 * @return The game display zoom factor
	 */
	public double getZoomFactor() {
		return zoomFactor;
	}

	/**
	 * Set the game display zoom factor
	 * 
	 * @param zoomFactor
	 *            The game display zoom factor
	 */
	public void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
	}
}
