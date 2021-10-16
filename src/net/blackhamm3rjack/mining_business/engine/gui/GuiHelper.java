package net.blackhamm3rjack.mining_business.engine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Help in the creation of GUI components
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class GuiHelper {
	/** Default font */
	private static final String FONT = "Arial";
	/** Default font size */
	private static final int FONT_SIZE = 14;
	/** Default font style */
	private static final int FONT_STYLE = Font.PLAIN;

	/**
	 * Create a new GUI label
	 * 
	 * @param position
	 *            The label position
	 * @param text
	 *            The label text
	 * @param color
	 *            The label color
	 * @return The newly created label
	 */
	public static GuiLabel newLabel(Point position, String text, Color color) {
		return new GuiLabel(position, null, text, color, FONT, FONT_STYLE, FONT_SIZE, true);
	}

	/**
	 * Create a new GUI button with the relative GUI label serving as a caption
	 * 
	 * @param position
	 *            The button position
	 * @param captionText
	 *            The caption text
	 * @param captionColor
	 *            The caption color
	 * @param foreground
	 *            The button foreground color
	 * @param background
	 *            The button background color
	 * @return The newly created button
	 */
	public static GuiButton newButton(Point position, String captionText, Color captionColor, Color foreground, Color background) {
		return new GuiButton(position, null, newLabel(new Point(0, 0), captionText, captionColor), foreground, background);
	}
}
