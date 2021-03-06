package io.github.mateo767.springbootutils.loggedmethod;

import java.lang.annotation.*;

/**
 * <p>Helper annotation that allows to log method invocation.</p>
 * <p><b>By default it creates log messages:</b></p>
 * <ul>
 * <li><code>Invoked methodName(argument, values)</code> - before method invocation;</li>
 * <li><code>Method methodName returned: returnObjectStringRepresentation in 1 ms</code> - after method with non-void return type;</li>
 * <li><code>Method methodName finished in 1 ms</code> - after method with void return type;</li>
 * <li><code>Method methodName threw ExceptionName(message=exception message) in 1 ms</code> - after method which threw any Throwable.</li>
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LoggedMethod {

    /**
     * Default level denotes that logging level should be taken from configuration property. If unchanged it's INFO.
     *
     * @return the level to write non-exception logs at
     */
    Level level() default Level.DEFAULT;

    /**
     * Default exception level denotes that logging level should be taken from configuration property. If unchanged it's ERROR.
     *
     * @return the level to write exception logs at
     */
    Level exceptionLevel() default Level.DEFAULT;

    /**
     * Allows controlling whether arguments values should be logged.
     *
     * @return whether arguments should be logged
     */
    boolean logArguments() default true;

    /**
     * Allows controlling whether result value should be logged.
     *
     * @return whether result should be logged
     */
    boolean logResult() default true;

    /**
     * Allows controlling whether the duration of method execution should be logged.
     *
     * @return whether execution time should be measured and logged
     */
    boolean timing() default true;

    /**
     * <p>ź
     * Allows pointing method arguments to exclude in the logger message. With logArguments=false, it's meaningless.
     * Excluded argument values will be replaced with ●●●● to keep argument order and count.
     * </p>
     * <p>
     * It is not inherited from class annotation.
     * </p>
     *
     * @return the array of method arguments to exclude
     */
    String[] exclude() default {};

}
