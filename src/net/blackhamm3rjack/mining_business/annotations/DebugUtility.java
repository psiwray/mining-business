package net.blackhamm3rjack.mining_business.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Debug annotation utility
 * 
 * @author Luca
 *
 */
@Versioning(working = true)
public class DebugUtility {
	/**
	 * Get the debug methods for a particular class
	 * 
	 * @param c
	 *            The class to analyze
	 * @return The found methods
	 */
	public static <T> ArrayList<Method> getDebugMethods(Class<T> c) {
		ArrayList<Method> methods = new ArrayList<>();

		for (Method m : c.getMethods()) {
			if (m.isAnnotationPresent(Debug.class))
				methods.add(m);
		}

		return methods;
	}

	/**
	 * Get the debug fields for a particular class
	 * 
	 * @param c
	 *            The class to analyze
	 * @return The found fields
	 */
	public static <T> ArrayList<Field> getDebugFields(Class<T> c) {
		ArrayList<Field> fields = new ArrayList<>();

		for (Field f : c.getFields()) {
			if (f.isAnnotationPresent(Debug.class))
				fields.add(f);
		}

		return fields;
	}

	/**
	 * Is a particular class a debug class?
	 * 
	 * @param c
	 *            The class to check
	 * @return The result
	 */
	public static <T> boolean isDebugClass(Class<T> c) {
		return c.isAnnotationPresent(Debug.class);
	}
}
