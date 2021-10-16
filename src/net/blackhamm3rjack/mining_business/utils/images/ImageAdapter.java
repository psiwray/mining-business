package net.blackhamm3rjack.mining_business.utils.images;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Utility to create resized and flipped images
 * 
 * @author Luca
 *
 */
@Versioning(working = false)
public class ImageAdapter {
	public static BufferedImage resize(BufferedImage image, Point size) {
		BufferedImage resized = new BufferedImage(size.x, size.y, image.getType());
		Graphics graphics = resized.createGraphics();

		if (graphics != null) {
			graphics.drawImage(image, 0, 0, size.x, size.y, null);
			graphics.dispose();

			return resized;
		}

		return null;
	}

	public enum FlipMode {
		VERTICAL, HORIZONTAL;
	}

	public static BufferedImage flip(BufferedImage image, FlipMode mode) {
		BufferedImage flipped = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		if (mode.equals(FlipMode.HORIZONTAL)) {
			for (int x = 0; x < image.getWidth(); x++)
				for (int y = 0; y < image.getHeight(); y++)
					flipped.setRGB(x, y, image.getRGB(x, image.getHeight() - y - 1));

			return flipped;
		}

		if (mode.equals(FlipMode.VERTICAL)) {
			for (int x = 0; x < image.getWidth(); x++)
				for (int y = 0; y < image.getHeight(); y++)
					flipped.setRGB(x, y, image.getRGB(image.getWidth() - x - 1, y));

			return flipped;
		}

		return null;
	}
}
