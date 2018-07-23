/**
 * 
 */
package com.wrobin.common.annotations;

import java.lang.annotation.*;

/**
 * @author robin.wu
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface HttpAuthentication {
	
	/**
     * Flag to determine whether to ignore current request or not
     * 
     * @return
     */
    boolean ignore() default false;

}
