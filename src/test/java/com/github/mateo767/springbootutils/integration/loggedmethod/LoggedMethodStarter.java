package com.github.mateo767.springbootutils.integration.loggedmethod;

import com.github.mateo767.springbootutils.loggedmethod.EnableLoggedMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLoggedMethod
class LoggedMethodStarter {

    static void main(String[] args) {
        SpringApplication.run(LoggedMethodStarter.class, args);
    }
}
