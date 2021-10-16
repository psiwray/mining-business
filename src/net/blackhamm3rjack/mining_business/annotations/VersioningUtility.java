package net.blackhamm3rjack.mining_business.annotations;

/**
 * Versioning annotation utility
 * 
 * @author Luca
 *
 */
@Versioning(working = true)
public class VersioningUtility {
	/**
	 * Does the specified class work?
	 * 
	 * @param c
	 *            The class to check
	 * @return The result
	 */
	public static <T> boolean doesClassWork(Class<T> c) {
		if (c.isAnnotationPresent(Versioning.class))
			return c.getAnnotation(Versioning.class).working();

		return false;
	}

	/**
	 * Get the formatted version string from a class
	 * 
	 * @param c
	 *            The class to analyze
	 * @return The formatted version string
	 */
	public static <T> String getVersionString(Class<T> c) {
		if (c.isAnnotationPresent(Versioning.class)) {
			Versioning annotation = c.getAnnotation(Versioning.class);

			int major = annotation.major();
			int minor = annotation.minor();
			int patch = annotation.patch();

			return String.format("%d.%d.%d", major, minor, patch);
		}

		return null;
	}

	/**
	 * Enumeration for version components
	 * 
	 * @author Luca
	 *
	 */
	public enum Number {
		/** The major version code */
		MAJOR,
		/** The minor version code */
		MINOR,
		/** The patch version code */
		PATCH;
	}

	/**
	 * Get the version component from a class
	 * 
	 * @param c
	 *            The class to analyze
	 * @param number
	 *            The component type
	 * @return The version component or -1 if the component is invalid
	 */
	public static <T> int getVersion(Class<T> c, Number number) {
		if (c.isAnnotationPresent(Versioning.class)) {
			switch (number) {
			case MAJOR:
				return c.getAnnotation(Versioning.class).major();
			case MINOR:
				return c.getAnnotation(Versioning.class).minor();
			case PATCH:
				return c.getAnnotation(Versioning.class).patch();
			}
		}

		return -1;
	}
}
