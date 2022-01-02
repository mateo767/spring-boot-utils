package io.github.mateo767.springbootutils.loggedmethod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoggedMethodConfiguration {

    @Bean
    LoggedMethodProcessor loggedMethodProcessor(LoggedMethodProperties properties) {
        return new LoggedMethodProcessor(
                resolveLevel(properties.getDefaultLevel(), Level.INFO),
                resolveLevel(properties.getDefaultExceptionLevel(), Level.ERROR));
    }

    private static Level resolveLevel(Level level, Level defaultLevel) {
        return level == Level.DEFAULT
                ? defaultLevel
                : level;
    }
}
