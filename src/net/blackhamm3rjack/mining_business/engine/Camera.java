package net.blackhamm3rjack.mining_business.engine;

import java.awt.geom.Point2D;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Static viewport class
 * 
 * @author Luca
 *
 */
@Versioning(working = true)
public class Camera {
	/** The current position */
	private static Point2D.Double position = new Point2D.Double(0, 0);
	/** Is the camera fixed? */
	private static boolean fixed = false;

	/**
	 * Get the current position
	 * 
	 * @return The current position
	 */
	public static Point2D.Double getPosition() {
		return position;
	}

	/**
	 * Get the current X axis position
	 * 
	 * @return The current X axis position
	 */
	public static double getPositionX() {
		return position.x;
	}

	/**
	 * Get the current Y axis position
	 * 
	 * @return The current Y axis position
	 */
	public static double getPositionY() {
		return position.y;
	}

	/**
	 * Set the current X axis position
	 * 
	 * @param x
	 *            The current X axis position
	 */
	public static void setPositionX(double x) {
		Camera.position.x = x;
	}

	/**
	 * Set the Y axis position
	 * 
	 * @param y
	 *            The Y axis position
	 */
	public static void setPositionY(double y) {
		Camera.position.y = y;
	}

	/**
	 * Set the current position
	 * 
	 * @param position
	 *            The current position
	 */
	public static void setPosition(Point2D.Double position) {
		Camera.position = position;
	}

	/**
	 * Set the current position
	 * 
	 * @param positionX
	 *            The current X axis position
	 * @param positionY
	 *            The current Y axis position
	 */
	public static void setPosition(double positionX, double positionY) {
		Camera.position.x = positionX;
		Camera.position.y = positionY;
	}

	/** Reset the viewport */
	public static void reset() {
		Camera.position.x = 0;
		Camera.position.y = 0;
	}

	/**
	 * Move the viewport. The camera won't move if it's fixed
	 * 
	 * @param offset
	 *            The motion offset
	 */
	public static void move(Point2D.Double offset) {
		if (fixed)
			return;

		Camera.position.x += offset.x;
		Camera.position.y += offset.y;
	}

	/**
	 * Move the viewport. The camera won't move if it's fixed
	 * 
	 * @param offsetX
	 *            The motion X axis offset
	 * @param offsetY
	 *            The motion Y axis offset
	 */
	public static void move(double offsetX, double offsetY) {
		if (fixed)
			return;

		Camera.position.x += offsetX;
		Camera.position.y += offsetY;
	}

	/**
	 * Move the viewport along X axis. The camera won't move if it's fixed
	 * 
	 * @param offset
	 *            The motion X axis offset
	 */
	public static void moveX(double offset) {
		if (fixed)
			return;

		Camera.position.x += offset;
	}

	/**
	 * Move the viewport along Y axis. The camera won't move if it's fixed
	 * 
	 * @param offset
	 *            The motion Y axis offset
	 */
	public static void moveY(double offset) {
		if (fixed)
			return;

		Camera.position.y += offset;
	}

	/**
	 * Set the camera fixed state
	 * 
	 * @param fixed
	 *            The camera fixed state
	 */
	public static void setFixed(boolean fixed) {
		Camera.fixed = fixed;
	}

	/**
	 * Is the camera fixed?
	 * 
	 * @return The camera fixed state
	 */
	public static boolean isFixed() {
		return fixed;
	}
}
