package com.github.mateo767.springbootutils.loggedmethod;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Enables the Logged Method feature.</p>
 * <p>Use it on any Spring Boot configuration class to take effect.</p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({LoggedMethodConfiguration.class, LoggedMethodProperties.class})
public @interface EnableLoggedMethod {
}
