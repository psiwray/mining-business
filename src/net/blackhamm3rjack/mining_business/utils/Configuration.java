package net.blackhamm3rjack.mining_business.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.configuration.Display;
import net.blackhamm3rjack.mining_business.configuration.Engine;
import net.blackhamm3rjack.mining_business.configuration.Factors;
import net.blackhamm3rjack.mining_business.configuration.Game;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Manage the system configuration
 * 
 * @author Luca
 *
 */
@Versioning(major = 3, minor = 3, patch = 6)
public class Configuration {
	private static String source;
	private static boolean onlyDefaults = false;

	public static Display display;
	public static Engine engine;
	public static Factors factors;
	public static Game game;

	static {
		Configuration.display = new Display(640, 480, 60, 2, true, false, true, true, true);
		Configuration.engine = new Engine(true, true, true, false, 1);
		Configuration.factors = new Factors(3, 19.7f, 0.88f);
		Configuration.game = new Game("en_US", false);
	}

	public static boolean load() {
		if (onlyDefaults)
			return false;

		try {
			Gson instance = new Gson();

			display = instance.fromJson(new FileReader(source + "display.json"), Display.class);
			engine = instance.fromJson(new FileReader(source + "engine.json"), Engine.class);
			factors = instance.fromJson(new FileReader(source + "factors.json"), Factors.class);
			game = instance.fromJson(new FileReader(source + "game.json"), Game.class);
		} catch (JsonParseException | IOException e) {
			Logger.print(Tag.ERROR, Configuration.class,
					"Failed to load the configuration: " + e.getMessage().toLowerCase());
			return false;
		}

		Logger.print(Tag.DEBUG, Configuration.class, "Configuration loaded from " + source);
		return true;
	}

	public static boolean save() {
		if (onlyDefaults)
			return false;

		Gson instance = new Gson();

		if (!write(instance, display, source + "display.json")) {
			Logger.print(Tag.ERROR, Configuration.class, "Failed to save the configuration for: display");
			return false;
		}
		if (!write(instance, engine, source + "engine.json")) {
			Logger.print(Tag.ERROR, Configuration.class, "Failed to save the configuration for: engine");
			return false;
		}
		if (!write(instance, factors, source + "factors.json")) {
			Logger.print(Tag.ERROR, Configuration.class, "Failed to save the configuration for: factors");
			return false;
		}
		if (!write(instance, game, source + "game.json")) {
			Logger.print(Tag.ERROR, Configuration.class, "Failed to save the configuration for: game");
			return false;
		}

		Logger.print(Tag.DEBUG, Configuration.class, "Configuration saved to " + source);
		return true;
	}

	private static boolean write(Gson instance, Object value, String destination) {
		try (FileWriter writer = new FileWriter(destination)) {
			writer.write(instance.toJson(value));
		} catch (IOException e) {
			Logger.print(Tag.ERROR, Configuration.class,
					"Failed to write the node: " + destination + e.getMessage().toLowerCase());
			return false;
		}

		return true;
	}

	public static String getSource() {
		return source;
	}

	public static void setSource(String source) {
		Configuration.source = source;
	}

	public static void setOnlyDefaults(boolean onlyDefaults) {
		Configuration.onlyDefaults = onlyDefaults;
	}

	public static boolean isOnlyDefaults() {
		return onlyDefaults;
	}
}
