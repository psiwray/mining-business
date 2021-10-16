package net.blackhamm3rjack.mining_business.configuration;

public class Game {
	/** The main language */
	private String language;
	/** Show the informational panel or not? */
	private boolean showInfo;

	/**
	 * Create a new game settings object
	 * 
	 * @param language
	 *            The main language
	 * @param showInfo
	 *            The show info state
	 */
	public Game(String language, boolean showInfo) {
		this.language = language;
		this.showInfo = showInfo;
	}

	/**
	 * Get the main language
	 * 
	 * @return The main language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Set the main language
	 * 
	 * @param language
	 *            The main language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Get the show info state
	 * 
	 * @return The show info state
	 */
	public boolean isShowInfo() {
		return showInfo;
	}

	/**
	 * Set the show info state
	 * 
	 * @param showInfo
	 *            The show info state
	 */
	public void setShowInfo(boolean showInfo) {
		this.showInfo = showInfo;
	}
}
