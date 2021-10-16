package net.blackhamm3rjack.mining_business.utils;

import java.awt.Font;
import java.util.HashMap;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Collection of every loaded font
 * 
 * @author lucac
 *
 */
@Versioning(minor = 3, working = true)
public class FontManager {
	/** The font cache */
	private static HashMap<String, Font> fonts;

	static {
		FontManager.fonts = new HashMap<>();
		FontManager.fonts.put("Arial", new Font("Arial", Font.PLAIN, 12));
		FontManager.fonts.put("Consolas", new Font("Consolas", Font.PLAIN, 12));
		FontManager.fonts.put("Times New Roman", new Font("Times New Roman", Font.PLAIN, 12));
	}

	/**
	 * Get the font cache
	 * 
	 * @return The font cache
	 */
	public static HashMap<String, Font> getFonts() {
		return fonts;
	}

	/**
	 * Get a specific font
	 * 
	 * @param name
	 *            The font name
	 * @return The font object
	 */
	public static Font getFont(String name) {
		return fonts.get(name);
	}

	/**
	 * Load a new font inside the cache
	 * 
	 * @param name
	 *            The font name
	 */
	public static void loadFont(String name) {
		fonts.put(name, new Font(name, Font.PLAIN, 12));
	}

	/**
	 * Load an existing font inside the cache
	 * 
	 * @param font
	 *            The font object
	 */
	public static void loadFont(Font font) {
		fonts.put(font.getFontName(), font);
	}

	/**
	 * Remove a font from the cache
	 * 
	 * @param name
	 *            The font name
	 */
	public static void unloadFont(String name) {
		fonts.remove(name);
	}

	/**
	 * Is a particular font loaded?
	 * 
	 * @param name
	 *            The font name
	 * @return The font presence
	 */
	public static boolean isFontLoaded(String name) {
		return fonts.containsKey(name);
	}
}
