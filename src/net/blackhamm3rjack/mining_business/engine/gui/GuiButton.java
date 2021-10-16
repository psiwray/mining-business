package net.blackhamm3rjack.mining_business.engine.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Simple GUI button with a caption (GUI label) over it and some customization
 * options
 * 
 * @author lucac
 *
 */
@Versioning(patch = 2, working = false)
public class GuiButton extends GuiComponent {
	/** The button GUI caption */
	private GuiLabel caption;
	/** The button foreground color */
	private Color foreground;
	/** The button background color */
	private Color background;

	/** The padding between the button border and the caption, horizontally */
	private static final int CAPTION_OFFSET_WIDTH = 8;
	/** The padding between the button border and the caption, vertically */
	private static final int CAPTION_OFFSET_HEIGHT = CAPTION_OFFSET_WIDTH;

	/**
	 * Create a new GUI button
	 * 
	 * @param bounds
	 *            The button bounds
	 * @param name
	 *            The button internal name
	 * @param caption
	 *            The button caption GUI label
	 * @param foreground
	 *            The button foreground color
	 * @param background
	 *            The button background color
	 * @param visible
	 *            The button visibility state
	 */
	public GuiButton(Rectangle2D.Double bounds, String name, GuiLabel caption, Color foreground, Color background) {
		super(bounds, name);

		this.caption = caption;
		this.foreground = foreground;
		this.background = background;

		centerCaption();
	}

	/**
	 * Create a new GUI button and automatically calculate the size from the
	 * caption length
	 * 
	 * @param position
	 *            The button position
	 * @param name
	 *            The button internal name
	 * @param caption
	 *            The button caption GUI label
	 * @param foreground
	 *            The button foreground color
	 * @param background
	 *            The button background color
	 * @param visible
	 *            The button visibility state
	 */
	public GuiButton(Point position, String name, GuiLabel caption, Color foreground, Color background) {
		super(new Rectangle2D.Double(position.x, position.y, 0, 0), name);

		this.caption = caption;
		this.foreground = foreground;
		this.background = background;

		setWidth(caption.getWidth() + CAPTION_OFFSET_WIDTH * 2);
		setHeight(caption.getHeight() + CAPTION_OFFSET_HEIGHT * 2);

		centerCaption();
	}

	/** Center the caption with the button */
	private void centerCaption() {
		caption.setPositionX(getPositionX() + (getWidth() / 2 - caption.getWidth() / 2));
		caption.setPositionY(getPositionY() + (getHeight() / 2 - caption.getHeight() / 2));
	}

	public GuiLabel getCaption() {
		return caption;
	}

	public Color getForeground() {
		return foreground;
	}

	public Color getBackground() {
		return background;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	@Override
	public void render(Graphics2D graphics) {
		if (!visible)
			return;

		graphics.setColor(background);
		graphics.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		graphics.setColor(foreground);
		graphics.drawRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);

		// Draw the caption only if visible
		caption.render(graphics);
	}

	@Override
	public void update(long deltaTime) {
		
	}
}
