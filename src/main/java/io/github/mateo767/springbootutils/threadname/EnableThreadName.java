package io.github.mateo767.springbootutils.threadname;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Enables the Thread Name feature.</p>
 * <p>Use it on any Spring Boot configuration class to take effect.</p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ThreadNameConfiguration.class, ThreadNameProperties.class})
public @interface EnableThreadName {
}
