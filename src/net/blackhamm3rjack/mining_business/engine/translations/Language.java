package net.blackhamm3rjack.mining_business.engine.translations;

import java.util.HashMap;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Simple set of translations for a particular language
 * 
 * @author Luca
 *
 */
@Versioning(minor = 2, working = true)
public class Language {
	private String name;
	private HashMap<String, String> definitions;

	public Language(String name) {
		this.name = name;
		this.definitions = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(HashMap<String, String> definitions) {
		this.definitions = definitions;
	}

	public void addDefinition(String name, String definition) {
		definitions.put(name, definition);
	}

	public void removeDefinition(String name) {
		definitions.remove(name);
	}

	public void setDefinition(String name, String definition) {
		definitions.put(name, definition);
	}

	public boolean doesDefinitionExist(String name) {
		return definitions.containsKey(name);
	}

	public String getDefinition(String name) {
		return definitions.get(name);
	}
}
