package net.blackhamm3rjack.mining_business.utils.images;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Small utility to change image color levels
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2)
public class ImageLevelsEditor {
	public static BufferedImage setOffsetRGB(BufferedImage image, byte offsetR, byte offsetG, byte offsetB) {
		for (int x = 0; x < image.getWidth(); x++)
			for (int y = 0; y < image.getHeight(); y++) {
				int rgb = image.getRGB(x, y);
				byte r = (byte) ((rgb >> 24) & 0xff);
				byte g = (byte) ((rgb >> 16) & 0xff);
				byte b = (byte) ((rgb >> 8) & 0xff);

				image.setRGB(x, y, new Color(r + offsetR, g + offsetG, b + offsetB).getRGB());
			}

		return image;
	}

	public static BufferedImage setOffsetR(BufferedImage image, byte offset) {
		return setOffsetRGB(image, offset, (byte) 0, (byte) 0);
	}

	public static BufferedImage setOffsetG(BufferedImage image, byte offset) {
		return setOffsetRGB(image, (byte) 0, offset, (byte) 0);
	}

	public static BufferedImage setOffsetB(BufferedImage image, byte offset) {
		return setOffsetRGB(image, (byte) 0, (byte) 0, offset);
	}
}
