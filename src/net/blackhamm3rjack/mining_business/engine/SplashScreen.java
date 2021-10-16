package net.blackhamm3rjack.mining_business.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.utils.Configuration;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Splash screen that shows the studio's logo
 * 
 * @author Luca
 *
 */
@Versioning(minor = 3, patch = 1)
public class SplashScreen {
	/** Small size logo */
	@SuppressWarnings("unused")
	private static BufferedImage small;
	/** Medium size logo */
	@SuppressWarnings("unused")
	private static BufferedImage medium;
	/** Big size logo */
	private static BufferedImage big;

	/** Is the splash screen currently visible? */
	private static boolean visible;
	/** Is the game fully loaded? */
	private static boolean loaded;
	/** The time passed since the start */
	private static long time;
	/**
	 * The time that has to pass to hide the splash screen, if the loading took
	 * less time
	 */
	private static long timeout;
	/** The position of the splash screen */
	private static Rectangle2D.Double bounds;

	static {
		try {
			SplashScreen.small = ImageIO.read(new File("res/splashscreen/59x59.png"));
			SplashScreen.medium = ImageIO.read(new File("res/splashscreen/118x118.png"));
			SplashScreen.big = ImageIO.read(new File("res/splashscreen/236x236.png"));
		} catch (IOException e) {
			Logger.setStopOnError(true);
			Logger.print(Tag.ERROR, SplashScreen.class, "Failed to load splash screens");
		}

		SplashScreen.visible = true;
		SplashScreen.loaded = false;
		SplashScreen.time = 0;
		SplashScreen.timeout = 3_000_000_000L; // Three seconds as timeout
		SplashScreen.bounds = new Rectangle2D.Double(0, 0, Configuration.display.getWidth(), Configuration.display.getHeight());
	}

	/**
	 * Update the object (Interface substitution for static method)
	 * 
	 * @param deltaTime
	 *            The time passed
	 */
	public static void update(long deltaTime) {
		if (visible && loaded && (time += deltaTime) > timeout)
			visible = false;
	}

	/**
	 * Render the object on the canvas (Interface substitution for static
	 * method)
	 * 
	 * @param graphics
	 *            The painter
	 */
	public static void render(Graphics2D graphics) {
		graphics.setColor(Color.lightGray);
		graphics.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		graphics.drawImage(big, (int) (bounds.width / 2 - big.getWidth() / 2), (int) (bounds.height / 2 - big.getHeight() / 2), null);
	}

	/**
	 * Is the splash screen visible?
	 * 
	 * @return The splash screen visibility state
	 */
	public static boolean isVisible() {
		return visible;
	}

	/**
	 * Set the game load state
	 * 
	 * @param loaded
	 *            The game load state
	 */
	public static void setLoaded(boolean loaded) {
		SplashScreen.loaded = loaded;
	}
}
