package net.blackhamm3rjack.mining_business.engine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.utils.FontManager;

/**
 * A simple text drawn as a GUI, with static position relative to the screen and
 * not to the player
 * 
 * @author lucac
 *
 */
@Versioning(minor = 3, patch = 5, working = true)
public class GuiLabel extends GuiComponent {
	/** The label text */
	private String text;
	/** The label vectorial font */
	private Font font;
	/** The label color */
	private Color color;
	/** Is the font antialiased? */
	private boolean antialiased;

	/**
	 * Create a new non antialiased GUI label
	 * 
	 * @param position
	 *            The label position
	 * @param name
	 *            The label internal name
	 * @param text
	 *            The label text
	 * @param color
	 *            The label color
	 * @param font
	 *            The label font name
	 * @param style
	 *            The label font style
	 * @param size
	 *            The label font size
	 */
	public GuiLabel(Point position, String name, String text, Color color, String font, int style, int size) {
		super(new Rectangle2D.Double(position.x, position.y, 0, 0), name);

		this.text = text;
		this.color = color;

		// Add a font or load it from cache
		if (FontManager.isFontLoaded(font))
			this.font = FontManager.getFont(font).deriveFont(style, size);
		else
			FontManager.loadFont(this.font = new Font(font, style, size));

		// Calculate the font height
		FontMetrics fontMetrics = gamePanel.getFontMetrics(this.font);
		this.bounds.width = fontMetrics.stringWidth(text);
		this.bounds.height = fontMetrics.getMaxDescent();
	}

	/**
	 * Create a new GUI label
	 * 
	 * @param position
	 *            The label position
	 * @param name
	 *            The label internal name
	 * @param text
	 *            The label text
	 * @param color
	 *            The label color
	 * @param font
	 *            The label font name
	 * @param style
	 *            The label font style
	 * @param size
	 *            The label font size
	 * @param antialiased
	 *            Should the font be antialiased?
	 */
	public GuiLabel(Point position, String name, String text, Color color, String font, int style, int size, boolean antialiased) {
		super(new Rectangle2D.Double(position.x, position.y, 0, 0), name);

		this.text = text;
		this.color = color;
		this.antialiased = antialiased;

		// Add a font or load it from cache
		if (FontManager.isFontLoaded(font))
			this.font = FontManager.getFont(font).deriveFont(style, size);
		else
			FontManager.loadFont(this.font = new Font(font, style, size));

		// Calculate the font height
		FontMetrics metrics = gamePanel.getFontMetrics(this.font);
		this.bounds.width = metrics.stringWidth(text);
		this.bounds.height = metrics.getHeight();
	}

	/**
	 * Get the label text
	 * 
	 * @return The label text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Set the label text
	 * 
	 * @param text
	 *            The label text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Get the label color
	 * 
	 * @return The label color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set the label color
	 * 
	 * @param color
	 *            The label color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Get the label font
	 * 
	 * @return The label font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Is the font antialised?
	 * 
	 * @return The font antialiasing state
	 */
	public boolean isAntialiased() {
		return antialiased;
	}

	/**
	 * Set the font antialiasing state
	 * 
	 * @param antialiased
	 *            The font antialiasing state
	 */
	public void setAntialiased(boolean antialiased) {
		this.antialiased = antialiased;
	}

	@Override
	public void render(Graphics2D graphics) {
		if (!visible)
			return;

		graphics.setColor(color);
		// Turn on antialiasing
		if (antialiased)
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setFont(font);
		graphics.drawString(text, (int) bounds.x, (int) (bounds.y + bounds.height - 3));
		// Turn off antialiasing
		if (antialiased)
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}
}
