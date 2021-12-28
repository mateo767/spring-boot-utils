package com.github.mateo767.springbootutils.loggedmethod;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring-boot-utils.logged-method")
@Getter
@Setter
class LoggedMethodProperties {

    private Level defaultLevel = Level.INFO;
}
