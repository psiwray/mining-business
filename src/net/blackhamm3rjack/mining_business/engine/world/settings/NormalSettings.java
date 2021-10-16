package net.blackhamm3rjack.mining_business.engine.world.settings;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * The configuration for the normal game settings
 * 
 * @author lucac
 *
 */
@Versioning(major = 1, working = true)
public class NormalSettings extends GameSettings {
	/**
	 * Static access attribute, refer to <code>GameSettings</code> for the
	 * meaning of the attribute
	 */
	public static final long DAYS_CYCLE_VALUE = 600_000_000_000L;
	/**
	 * Static access attribute, refer to <code>GameSettings</code> for the
	 * meaning of the attribute
	 */
	public static final long CASH_CYCLE_VALUE = 12_000_000_000_000L;
	/**
	 * Static access attribute, refer to <code>GameSettings</code> for the
	 * meaning of the attribute
	 */
	public static final double INITIAL_CASH = 50_000;
	/**
	 * Static access attribute, refer to <code>GameSettings</code> for the
	 * meaning of the attribute
	 */
	public static final double FAILURE_CASH = -50_000;

	/** Create a new normal settings object */
	public NormalSettings() {
		super(DAYS_CYCLE_VALUE, CASH_CYCLE_VALUE, INITIAL_CASH, FAILURE_CASH);
	}
}
