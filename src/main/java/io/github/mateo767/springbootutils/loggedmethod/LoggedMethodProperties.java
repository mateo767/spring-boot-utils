package io.github.mateo767.springbootutils.loggedmethod;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring-boot-utils.logged-method")
class LoggedMethodProperties {

    private Level defaultLevel = Level.DEFAULT;
    private Level defaultExceptionLevel = Level.DEFAULT;

    public Level getDefaultLevel() {
        return defaultLevel;
    }

    public void setDefaultLevel(Level defaultLevel) {
        this.defaultLevel = defaultLevel;
    }

    public Level getDefaultExceptionLevel() {
        return defaultExceptionLevel;
    }

    public void setDefaultExceptionLevel(Level defaultExceptionLevel) {
        this.defaultExceptionLevel = defaultExceptionLevel;
    }
}
