package net.blackhamm3rjack.mining_business.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Versioning utility for classes
 * 
 * @author Luca
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Versioning {
	/**
	 * The major version code
	 * 
	 * @return The major version code
	 */
	public abstract int major() default 1;

	/**
	 * The minor version code
	 * 
	 * @return The minor version code
	 */
	public abstract int minor() default 0;

	/**
	 * The patch version code
	 * 
	 * @return The patch version code
	 */
	public abstract int patch() default 0;

	/**
	 * Does the class work?
	 * 
	 * @return The class state
	 */
	public abstract boolean working() default true;
}
