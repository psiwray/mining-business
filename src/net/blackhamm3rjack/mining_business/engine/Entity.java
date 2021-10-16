package net.blackhamm3rjack.mining_business.engine;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.interfaces.Updatable;

/**
 * Updatable entity living in the current world
 * 
 * @author Luca
 *
 */
@Versioning(major = 2, minor = 2, working = true)
public class Entity implements Updatable {
	/** The entity bounds in the world */
	protected Rectangle2D.Double bounds;
	/** Is the entity visible? */
	protected boolean visible = true;
	/** The entity internal name */
	protected String name;

	/**
	 * Create a new abstract entity
	 * 
	 * @param bounds
	 *            The entity bounds in the world
	 * @param name
	 *            The entity internal name
	 */
	public Entity(Rectangle2D.Double bounds, String name) {
		this.bounds = bounds;
		this.name = name;
	}

	/**
	 * Create a new abstract entity
	 * 
	 * @param bounds
	 *            The entity bounds in the world
	 * @param name
	 *            The entity internal name
	 * @param visible
	 *            The entity visibility state
	 */
	public Entity(Rectangle2D.Double bounds, String name, boolean visible) {
		this.bounds = bounds;
		this.visible = visible;
		this.name = name;
	}

	/**
	 * Get the entity bounds in the world
	 * 
	 * @return The entity bounds in the world
	 */
	public Rectangle2D.Double getBounds() {
		return bounds;
	}

	/**
	 * Set the entity bounds in the world
	 * 
	 * @param bounds
	 *            The entity bounds in the world
	 */
	public void setBounds(Rectangle2D.Double bounds) {
		this.bounds = bounds;
	}

	/**
	 * Is the entity visible?
	 * 
	 * @return The entity visibility state
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Set the entity visibility state
	 * 
	 * @param visible
	 *            The entity visibility state
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Set the entity position
	 * 
	 * @param position
	 *            The entity position
	 */
	public void setPosition(Point2D.Double position) {
		bounds.x = position.x;
		bounds.y = position.y;
	}

	/**
	 * Set the entity position
	 * 
	 * @param x
	 *            The X axis position
	 * @param y
	 *            The Y axis position
	 */
	public void setPosition(double x, double y) {
		bounds.x = x;
		bounds.y = y;
	}

	/**
	 * Get the entity position
	 * 
	 * @return The entity position
	 */
	public Point2D.Double getPosition() {
		return new Point2D.Double(bounds.x, bounds.y);
	}

	/**
	 * Get the entity position along X axis
	 * 
	 * @return The entity position along X axis
	 */
	public double getPositionX() {
		return bounds.x;
	}

	/**
	 * Get the entity position along Y axis
	 * 
	 * @return The entity position along Y axis
	 */
	public double getPositionY() {
		return bounds.y;
	}

	/**
	 * Set the entity position along X axis
	 * 
	 * @param x
	 *            The entity position along X axis
	 */
	public void setPositionX(double x) {
		bounds.x = x;
	}

	/**
	 * Set the entity position along Y axis
	 * 
	 * @param y
	 *            The entity position along Y axis
	 */
	public void setPositionY(double y) {
		bounds.y = y;
	}

	/**
	 * Move the entity
	 * 
	 * @param offset
	 *            The entity motion offset
	 */
	public void move(Point2D.Double offset) {
		bounds.x += offset.x;
		bounds.y += offset.y;
	}

	/**
	 * Move the entity
	 * 
	 * @param offsetX
	 *            The entity motion offset along X axis
	 * @param offsetY
	 *            The entity motion offset along Y axis
	 */
	public void move(double offsetX, double offsetY) {
		bounds.x += offsetX;
		bounds.y += offsetY;
	}

	/**
	 * Move the entity along X axis
	 * 
	 * @param offset
	 *            The motion offset along X axis
	 */
	public void moveX(double offset) {
		bounds.x += offset;
	}

	/**
	 * Move the entity along Y axis
	 * 
	 * @param offset
	 *            The motion offset along Y axis
	 */
	public void moveY(double offset) {
		bounds.y += offset;
	}

	/**
	 * Get the entity size
	 * 
	 * @return The entity size
	 */
	public Point2D.Double getSize() {
		return new Point2D.Double(bounds.width, bounds.height);
	}

	/**
	 * Get the entity width
	 * 
	 * @return The entity width
	 */
	public double getWidth() {
		return bounds.width;
	}

	/**
	 * Get the entity height
	 * 
	 * @return The entity height
	 */
	public double getHeight() {
		return bounds.height;
	}

	/**
	 * Set the entity size
	 * 
	 * @param size
	 *            The entity size
	 */
	public void setSize(Point2D.Double size) {
		bounds.width = size.x;
		bounds.height = size.y;
	}

	/**
	 * Set the entity width
	 * 
	 * @param width
	 *            The entity width
	 */
	public void setWidth(double width) {
		bounds.width = width;
	}

	/**
	 * Set the entity height
	 * 
	 * @param width
	 *            The entity height
	 */
	public void setHeight(double height) {
		bounds.height = height;
	}

	/**
	 * Set the entity scale factor
	 * 
	 * @param factor
	 *            The entity scale factor
	 */
	public void scale(Point2D.Double factor) {
		bounds.width += factor.x;
		bounds.height += factor.y;
	}

	/**
	 * Set the entity scale factor for its width
	 * 
	 * @param factor
	 *            The entity scale factor for its width
	 */
	public void scaleWidth(double factor) {
		bounds.width += factor;
	}

	/**
	 * Set the entity scale factor for its height
	 * 
	 * @param factor
	 *            The entity scale factor for its height
	 */
	public void scaleHeight(double factor) {
		bounds.height += factor;
	}

	@Override
	public void update(long deltaTime) {
	}

	/**
	 * Get the entity internal name
	 * 
	 * @return The entity internal name
	 */
	public String getName() {
		return name;
	}
}
