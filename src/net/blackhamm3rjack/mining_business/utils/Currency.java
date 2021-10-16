package net.blackhamm3rjack.mining_business.utils;

import java.text.NumberFormat;
import java.util.Locale;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.translations.LanguageSet;

/**
 * Currency utilities for the game
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class Currency {
	private static Locale locale;

	// TODO: Depends on class loading order, better modify
	static {
		switch (LanguageSet.getCurrent()) {
		case "it_IT":
			locale = Locale.ITALY;
			break;
		case "en_US":
			locale = Locale.US;
			break;
		default:
			// No translation set, or error
			locale = Locale.US;
			break;
		}
	}

	/**
	 * Get the formatted string for the given amount of money
	 * 
	 * @param money
	 *            The given amount of money
	 * @return The formatted string
	 */
	public static String formatMoney(double money) {
		return NumberFormat.getCurrencyInstance(locale).format(money).replace(" ", "");
	}
}
