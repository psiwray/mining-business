package net.blackhamm3rjack.mining_business.engine.minerals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Mineral descriptor super class
 * 
 * @author Luca
 *
 */
@Versioning(major = 2, minor = 6, patch = 32, working = true)
public class Mineral {
	private byte id;
	private String name;
	private BufferedImage texture;
	private Color color;

	private float hardness;
	private float rarity;
	private float radioactivity;
	private float gramPrice;

	private static HashMap<Byte, Mineral> minerals;
	private static HashMap<String, BufferedImage> textures;

	public static final byte TEXTURE_WIDTH = 16;
	public static final byte TEXTURE_HEIGHT = TEXTURE_WIDTH;
	public static final byte DAMAGE_STAGES = 5;

	private static void loadTexture(String dir, String source) throws IOException {
		BufferedImage image = ImageIO.read(new File(dir + source + ".png"));

		assert image.getWidth() == TEXTURE_WIDTH;
		assert image.getHeight() == TEXTURE_HEIGHT;

		textures.put(source, image);

		// Simple existence check
		if (textures.containsKey(source))
			Logger.print(Tag.DEBUG, Mineral.class, "Loaded mineral texture \"%s\" from \"%s\"", source, dir);
	}

	private static void loadTexture(String dir, String source, String name) throws IOException {
		BufferedImage image = ImageIO.read(new File(dir + source + ".png"));

		assert image.getWidth() == TEXTURE_WIDTH;
		assert image.getHeight() == TEXTURE_HEIGHT;

		textures.put(name, image);

		// Simple existence check
		if (textures.containsKey(name))
			Logger.print(Tag.DEBUG, Mineral.class, "Loaded mineral texture \"%s\" from \"%s\"", source, dir);
	}

	public static float globalFactor = 0.88f;
	public static float rarityAdder = 1.7f;
	public static float radioactivityAdder = 3.0f;

	static {
		Mineral.minerals = new HashMap<>();
		Mineral.textures = new HashMap<>();

		try {
			// Load block shades
			loadTexture("res/textures/blocks/shades/", "mineral");
			loadTexture("res/textures/blocks/shades/", "empty");

			// Load special blocks
			loadTexture("res/textures/blocks/", "grass_light");
			loadTexture("res/textures/blocks/", "grass_dark");
			loadTexture("res/textures/blocks/", "no_grass_light");
			loadTexture("res/textures/blocks/", "no_grass_dark");
			loadTexture("res/textures/blocks/", "stone");

			// Load backgrounds
			loadTexture("res/textures/backgrounds/", "background_dirt", "bg_dirt");
			loadTexture("res/textures/backgrounds/", "background_stone", "bg_stone");

			// Load damages
			loadTexture("res/textures/damages/", "stage_0", "d_stage_0");
			loadTexture("res/textures/damages/", "stage_1", "d_stage_1");
			loadTexture("res/textures/damages/", "stage_2", "d_stage_2");
			loadTexture("res/textures/damages/", "stage_3", "d_stage_3");
			loadTexture("res/textures/damages/", "stage_4", "d_stage_4");
		} catch (IOException e) {
			Logger.setStopOnError(true);
			Logger.print(Tag.ERROR, Mineral.class, "Failed to load the mineral textures");
		}

		// Load up the minerals
		minerals.put((byte) 0, new MineralAluminium());
		minerals.put((byte) 1, new MineralCoal());
		minerals.put((byte) 2, new MineralCopper());
		minerals.put((byte) 3, new MineralDiamond());
		minerals.put((byte) 4, new MineralGold());
		minerals.put((byte) 5, new MineralIron());
		minerals.put((byte) 6, new MineralPlatinum());
		minerals.put((byte) 7, new MineralSilver());
		minerals.put((byte) 8, new MineralTitanium());
		minerals.put((byte) 9, new MineralUranium());

		// Load up special minerals
		minerals.put((byte) (Byte.MAX_VALUE - 1), new MineralDirt());
		minerals.put((byte) (Byte.MAX_VALUE - 0), new MineralStone());
	}

	private void processColor() {
		// Copy texture
		BufferedImage shade = textures.get("mineral");

		for (byte x = 0; x < TEXTURE_WIDTH; x++)
			for (byte y = 0; y < TEXTURE_HEIGHT; y++)
				texture.setRGB(x, y, shade.getRGB(x, y));

		for (byte x = 0; x < TEXTURE_WIDTH; x++) {
			for (byte y = 0; y < TEXTURE_HEIGHT; y++) {
				int color = texture.getRGB(x, y);

				// Switch color to desired one from mineral shade
				if (((color >> 24) & 0xff) != 0)
					texture.setRGB(x, y, (this.color.getRGB() & 0x00ffffff) + (color & 0xff000000));
			}
		}

		Logger.print(Tag.DEBUG, Mineral.class, "Color applied for mineral \"%s\"", name);
	}

	private void processColor(String shadeSource) {
		// Copy texture
		BufferedImage shade = textures.get(shadeSource);

		for (byte x = 0; x < TEXTURE_WIDTH; x++)
			for (byte y = 0; y < TEXTURE_HEIGHT; y++)
				texture.setRGB(x, y, shade.getRGB(x, y));

		for (byte x = 0; x < TEXTURE_WIDTH; x++) {
			for (byte y = 0; y < TEXTURE_HEIGHT; y++) {
				int color = texture.getRGB(x, y);

				// Switch color to desired one from mineral shade
				if (((color >> 24) & 0xff) != 0)
					texture.setRGB(x, y, (this.color.getRGB() & 0x00ffffff) + (color & 0xff000000));
			}
		}

		Logger.print(Tag.DEBUG, Mineral.class, "Color applied for mineral \"%s\"", name);
	}

	public Mineral(byte id, String name, Color color, float hardness, float rarity, float radioactivity) {
		this.id = id;
		this.name = name;
		this.texture = new BufferedImage(TEXTURE_WIDTH, TEXTURE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		this.color = color;

		// Special cases for dirt & stone, skip rendering
		if (name.equals("dirt") || name.equals("stone"))
			processColor("empty");
		else
			processColor();

		this.hardness = hardness;
		this.rarity = rarity;
		this.radioactivity = radioactivity;

		// Calculate the price
		this.gramPrice = getCalculatedGramPrice();
	}

	private float getCalculatedGramPrice() {
		return (hardness + rarity * rarityAdder + radioactivity * radioactivityAdder) * globalFactor;
	}

	public byte getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public Color getColor() {
		return color;
	}

	public float getHardness() {
		return hardness;
	}

	public float getRarity() {
		return rarity;
	}

	public float getRadioactivity() {
		return radioactivity;
	}

	public float getGramPrice() {
		return gramPrice;
	}

	public static Mineral getMineral(Byte key) {
		return minerals.get(key);
	}

	public static BufferedImage getTexture(String key) {
		return textures.get(key);
	}
}
