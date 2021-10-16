package net.blackhamm3rjack.mining_business.engine.world.settings;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * The configuration for the hard game settings
 * 
 * @author lucac
 *
 */
@Versioning(major = 1, working = true)
public class HardSettings extends GameSettings {
	/**
	 * Static access attribute, refer to <code>GameSettings</code> for the
	 * meaning of the attribute
	 */
	public static final long DAYS_CYCLE_VALUE = 600_000_000_000L;
	/**
	 * Static access attribute, refer to <code>GameSettings</code> for the
	 * meaning of the attribute
	 */
	public static final long CASH_CYCLE_VALUE = 24_000_000_000_000L;
	/**
	 * Static access attribute, refer to <code>GameSettings</code> for the
	 * meaning of the attribute
	 */
	public static final double INITIAL_CASH = 25_000;
	/**
	 * Static access attribute, refer to <code>GameSettings</code> for the
	 * meaning of the attribute
	 */
	public static final double FAILURE_CASH = -25_000;

	/** Create a new hard settings object */
	public HardSettings() {
		super(DAYS_CYCLE_VALUE, CASH_CYCLE_VALUE, INITIAL_CASH, FAILURE_CASH);
	}
}
