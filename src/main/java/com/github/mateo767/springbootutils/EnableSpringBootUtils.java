package com.github.mateo767.springbootutils;

import com.github.mateo767.springbootutils.loggedmethod.EnableLoggedMethod;
import com.github.mateo767.springbootutils.threadname.EnableThreadName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Enables all Spring Boot Utils features.</p>
 * <p>Use it on any Spring Boot configuration class to take effect.</p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableThreadName
@EnableLoggedMethod
public @interface EnableSpringBootUtils {
}
