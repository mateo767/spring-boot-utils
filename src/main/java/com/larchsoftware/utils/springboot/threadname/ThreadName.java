package com.larchsoftware.utils.springboot.threadname;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>
 * Helper annotation that changes name of the thread for the method invocation.
 * Old thread name is stored and recovered after annotated method invocation ends.
 * </p>
 * <p>
 * It supports 2 types of thread names:
 * <ol>
 * <li>
 * With expressions.
 * <p><code>prefix + '-' + "expressions values separated by dash" + '-' + "thread id starting with 1000000"</code></p>
 * </li>
 * <li>
 * Without expressions.
 * <p><code>prefix + '-' + "thread id starting with 1000000"</code>
 * </li>
 * </ol>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ThreadName {

    /**
     * @return the prefix for the thread name
     */
    @AliasFor("prefix")
    String value() default "Thread";

    /**
     * @return the prefix for the thread name
     */
    @AliasFor("value")
    String prefix() default "Thread";

    /**
     * @return the array of SpEL expressions to evaluate as thread name parts
     */
    String[] expressions() default {};

    /**
     * Allows controlling whether the old thread name should be restored.
     * Can be useful if new thread name should be preserved for out-of-method executions, i.e. message interceptors.
     *
     * @return whether the old thread name should be restored
     */
    boolean restoreName() default true;
}