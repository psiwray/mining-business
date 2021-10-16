package net.blackhamm3rjack.mining_business.engine.interfaces;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Tells that the object can be updated, depending on time
 * 
 * @author Luca
 *
 */
@Versioning()
public interface Updatable {
	/**
	 * Update the object state
	 * 
	 * @param deltaTime
	 *            The passed time in nanoseconds
	 */
	public abstract void update(long deltaTime);
}
