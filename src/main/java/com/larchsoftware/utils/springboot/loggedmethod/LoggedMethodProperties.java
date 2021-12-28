package com.larchsoftware.utils.springboot.loggedmethod;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("larchsoftware.utils.logged-method")
@Getter
@Setter
class LoggedMethodProperties {

    private Level defaultLevel = Level.INFO;
}
