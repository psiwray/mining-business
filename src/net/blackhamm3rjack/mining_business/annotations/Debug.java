package net.blackhamm3rjack.mining_business.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Debug flag for any identifier
 * 
 * @author Luca
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Debug {
	/**
	 * The debug item description
	 * 
	 * @return The item description
	 */
	public abstract String description() default "";

	/**
	 * The debug item permission to use
	 * 
	 * @return The item permission to use
	 */
	public abstract boolean permitted() default true;
}
