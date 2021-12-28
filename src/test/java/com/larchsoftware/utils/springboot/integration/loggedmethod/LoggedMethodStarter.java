package com.larchsoftware.utils.springboot.integration.loggedmethod;

import com.larchsoftware.utils.springboot.loggedmethod.EnableLoggedMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLoggedMethod
class LoggedMethodStarter {

    static void main(String[] args) {
        SpringApplication.run(LoggedMethodStarter.class, args);
    }
}
