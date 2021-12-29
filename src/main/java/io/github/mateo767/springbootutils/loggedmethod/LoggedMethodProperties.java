package io.github.mateo767.springbootutils.loggedmethod;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring-boot-utils.logged-method")
class LoggedMethodProperties {

    private Level defaultLevel = Level.INFO;

    public Level getDefaultLevel() {
        return defaultLevel;
    }

    public void setDefaultLevel(Level defaultLevel) {
        this.defaultLevel = defaultLevel;
    }
}
