package com.larchsoftware.utils.springboot.loggedmethod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoggedMethodConfiguration {

    @Bean
    LoggedMethodProcessor loggedMethodProcessor(LoggedMethodProperties properties) {
        return new LoggedMethodProcessor(
                properties.getDefaultLevel() == Level.DEFAULT
                        ? Level.INFO
                        : properties.getDefaultLevel());
    }
}
