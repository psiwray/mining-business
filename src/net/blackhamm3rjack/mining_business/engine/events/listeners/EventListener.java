package net.blackhamm3rjack.mining_business.engine.events.listeners;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.events.Event;

/**
 * Super class for every event listener
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public abstract class EventListener {
	/** The listener internal name */
	private String name;
	/** Is the listener enabled? */
	protected boolean enabled;

	/**
	 * Create a new listener
	 * 
	 * @param name
	 *            The listener internal name
	 */
	public EventListener(String name) {
		this.name = name;
		this.enabled = true;
	}

	/**
	 * Get the listener enabled state
	 * 
	 * @return The listener enabled state
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Set the listener enabled state
	 * 
	 * @param enabled
	 *            The listener enabled state
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Get the listener internal name
	 * 
	 * @return The listener internal name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Call the defined action
	 * 
	 * @param event
	 *            The event that triggered the action
	 */
	public abstract void action(Event event);
}
