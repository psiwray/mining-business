package net.blackhamm3rjack.mining_business.engine.translations;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for the whole translations file
 * 
 * @author Luca
 *
 */
public class TranslationsFile {
	public class Name {
		private String value;
		private HashMap<String, String> definitions;

		public Name(String value) {
			this.value = value;
			this.definitions = new HashMap<String, String>();
		}

		public String getValue() {
			return value;
		}

		public HashMap<String, String> getDefinitions() {
			return definitions;
		}
	}

	private ArrayList<String> languages;
	private ArrayList<Name> names;

	public TranslationsFile() {
		this.names = new ArrayList<>();
	}

	public void addName(Name name) {
		names.add(name);
	}

	public ArrayList<Name> getNames() {
		return names;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}
}
