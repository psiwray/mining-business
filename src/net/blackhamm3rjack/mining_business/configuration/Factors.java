package net.blackhamm3rjack.mining_business.configuration;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Minerals default factors configuration
 * 
 * @author Luca
 *
 */
@Versioning()
public class Factors {
	/** Default radioactivity factor */
	private float defaultRadioactivity;
	/** Default rarity factor */
	private float defaultRarity;
	/** Default global factor */
	private float defaultGlobal;

	/**
	 * Create a new factor settings object
	 * 
	 * @param defaultRadioactivity
	 *            Default radioactivity factor
	 * @param defaultRarity
	 *            Default rarity factor
	 * @param defaultGlobal
	 *            Default global factor
	 */
	public Factors(float defaultRadioactivity, float defaultRarity, float defaultGlobal) {
		this.defaultRadioactivity = defaultRadioactivity;
		this.defaultRarity = defaultRarity;
		this.defaultGlobal = defaultGlobal;
	}

	/**
	 * Get the default radioactivity factor
	 * 
	 * @return The default radioactivity factor
	 */
	public float getDefaultRadioactivity() {
		return defaultRadioactivity;
	}

	/**
	 * Set the default radioactivity factor
	 * 
	 * @param defaultRadioactivity
	 *            The default radioactivity factor
	 */
	public void setDefaultRadioactivity(float defaultRadioactivity) {
		this.defaultRadioactivity = defaultRadioactivity;
	}

	/**
	 * Get the default rarity factor
	 * 
	 * @return The default rarity factor
	 */
	public float getDefaultRarity() {
		return defaultRarity;
	}

	/**
	 * Set the default rarity factor
	 * 
	 * @param defaultRadioactivity
	 *            The default rarity factor
	 */
	public void setDefaultRarity(float defaultRarity) {
		this.defaultRarity = defaultRarity;
	}

	/**
	 * Get the default global factor
	 * 
	 * @return The default global factor
	 */
	public float getDefaultGlobal() {
		return defaultGlobal;
	}

	/**
	 * Set the default global factor
	 * 
	 * @param defaultRadioactivity
	 *            The default global factor
	 */
	public void setDefaultGlobal(float defaultGlobal) {
		this.defaultGlobal = defaultGlobal;
	}
}
