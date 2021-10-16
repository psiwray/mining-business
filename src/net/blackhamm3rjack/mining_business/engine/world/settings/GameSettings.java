package net.blackhamm3rjack.mining_business.engine.world.settings;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Class for every game setting
 * 
 * @author lucac
 *
 */
@Versioning(major = 1, minor = 2, working = true)
public class GameSettings {
	/** Days cycle in nanoseconds */
	private long daysCycleValue;
	/** Cash withdrawal cycle in nanoseconds */
	private long cashCycleValue;

	/** Company initial cash */
	private double initialCash;
	/** Company failure debt value */
	private double failureCash;

	/**
	 * Create a new game settings object with every property
	 * 
	 * @param daysCycleValue
	 *            Days cycle in nanoseconds
	 * @param cashCycleValue
	 *            Cash withdrawal cycle in nanoseconds
	 * @param initialCash
	 *            Company initial cash
	 * @param failureCash
	 *            Company failure debt value
	 */
	public GameSettings(long daysCycleValue, long cashCycleValue, double initialCash, double failureCash) {
		this.daysCycleValue = daysCycleValue;
		this.cashCycleValue = cashCycleValue;
		this.initialCash = initialCash;
		this.failureCash = failureCash;
	}

	/**
	 * Get the days cycle in nanoseconds
	 * 
	 * @return The days cycle in nanoseconds
	 */
	public long getDaysCycleValue() {
		return daysCycleValue;
	}

	/**
	 * Get the cash withdrawal cycle in nanoseconds
	 * 
	 * @return The cash withdrawal cycle in nanoseconds
	 */
	public long getCashCycleValue() {
		return cashCycleValue;
	}

	/**
	 * Get the company initial cash
	 * 
	 * @return The company initial cash
	 */
	public double getInitialCash() {
		return initialCash;
	}

	/**
	 * Get the company failure debt value
	 * 
	 * @return The company failure debt value
	 */
	public double getFailureCash() {
		return failureCash;
	}
}
