package net.blackhamm3rjack.mining_business.engine.events;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Super class for each event
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public abstract class Event {
	/** The event source */
	protected Object source;

	/**
	 * Create a new general event
	 * 
	 * @param source
	 *            The event source
	 */
	public Event(Object source) {
		this.source = source;
	}

	/**
	 * Get the event source
	 * 
	 * @return The event source
	 */
	public Object getSource() {
		return source;
	}
}
