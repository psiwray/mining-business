package net.blackhamm3rjack.mining_business.engine.interfaces;

import java.awt.Graphics2D;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Tells that the object can be drawn in the canvas with offset
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2)
public interface Renderable {
	/**
	 * Draw the object on the canvas
	 * 
	 * @param graphics
	 *            The painter
	 */
	public abstract void render(Graphics2D graphics);
}
