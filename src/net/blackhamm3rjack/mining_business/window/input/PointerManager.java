package net.blackhamm3rjack.mining_business.window.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.window.GamePanel;

/**
 * Manager the position of the pointer by extending the class from a mouse
 * motion adapter to track the current cursor position relative to the window of
 * the game
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class PointerManager extends MouseMotionAdapter {
	/** The motion component */
	private static PointerManager motion;
	/** The current pointer position */
	private static Point position;

	static {
		// Initialize the pointer manager
		PointerManager.motion = new PointerManager();
		PointerManager.position = new Point(0, 0);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		synchronized (position) {
			// Put the position
			position.x = e.getX();
			position.y = e.getY();
		}
	}

	/**
	 * Attach the motion listener to the right component
	 * 
	 * @param instance
	 *            The right component
	 */
	public static void attach(GamePanel instance) {
		instance.addMouseMotionListener(motion);
	}

	/**
	 * Get the current pointer position
	 * 
	 * @return The current pointer position
	 */
	public static synchronized Point getPosition() {
		return position;
	}

	/**
	 * Get the current pointer position along X axis
	 * 
	 * @return The current pointer position along X axis
	 */
	public static synchronized int getPositionX() {
		return position.x;
	}

	/**
	 * Get the current pointer position along Y axis
	 * 
	 * @return The current pointer position along Y axis
	 */
	public static synchronized int getPositionY() {
		return position.y;
	}
}
