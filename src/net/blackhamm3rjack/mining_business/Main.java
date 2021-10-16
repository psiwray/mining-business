package net.blackhamm3rjack.mining_business;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.translations.LanguageSet;
import net.blackhamm3rjack.mining_business.utils.Configuration;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;
import net.blackhamm3rjack.mining_business.window.GameFrame;

/**
 * Main class that executes engine & game code
 * 
 * @author Luca
 *
 */
@Versioning()
public class Main {
	public Main() {
		try {
			Logger.setFileOutputStream(new PrintStream("res/logs/" + new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS").format(new Date()) + ".log"));
		} catch (IOException e) {
			Logger.print(Tag.ERROR, Main.class, "Cannot set the file output stream for the logger: " + e.getMessage().toLowerCase());
		}

		Configuration.setSource("res/config/");
		Configuration.load();

		// Load the translations
		LanguageSet.load("res/config/translations.json");
		LanguageSet.setCurrent(Configuration.game.getLanguage());

		// Activate the dedicated graphics by using OpenGL
		if (Configuration.display.shouldUseDedicatedGraphics()) {
			System.setProperty("sun.java2d.opengl", "true");
			System.setProperty("sun.java2d.ddforcevram", "true");
		}

		Logger.setOutputDebug(Configuration.engine.shouldOutputDebug());
		Logger.setStopOnError(Configuration.engine.shouldStopOnError());
		Logger.setDateFormat(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss:SSS"));

		new GameFrame(Configuration.display.getFramesPerSecond(), Configuration.display.getBuffersCount());
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            The program arguments
	 */
	public static void main(String[] args) {
		new Main();
	}
}
