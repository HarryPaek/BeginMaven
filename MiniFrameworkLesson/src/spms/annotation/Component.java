/**
 * 
 */
package spms.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
/**
 * @author HarryPaek
 *
 */
public @interface Component {
	String value() default "";
}
