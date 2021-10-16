package net.blackhamm3rjack.mining_business.engine.translations;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;

/**
 * Set of languages
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2)
public class LanguageSet {
	private static HashMap<String, Language> languages;
	private static String current;

	static {
		LanguageSet.languages = new HashMap<>();
		LanguageSet.current = null;
	}

	public static boolean load(String source) {
		try {
			TranslationsFile file = new Gson().fromJson(new FileReader(source), TranslationsFile.class);

			for (String name : file.getLanguages())
				languages.put(name, new Language(name));

			for (TranslationsFile.Name name : file.getNames()) {
				for (String translation : name.getDefinitions().keySet()) {
					languages.get(translation).addDefinition(name.getValue(), name.getDefinitions().get(translation));
				}

				Logger.print(Tag.DEBUG, LanguageSet.class, "Loaded definitions for " + name.getValue());
			}

			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static String get(String name) {
		return languages.get(current).getDefinition(name);
	}

	public static String get(String language, String name) {
		return languages.get(language).getDefinition(name);
	}

	public static String getCurrent() {
		return current;
	}

	public static void setCurrent(String current) {
		LanguageSet.current = current;
	}
}
