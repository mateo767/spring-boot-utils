package io.github.mateo767.springbootutils.integration.threadname;

import io.github.mateo767.springbootutils.threadname.EnableThreadName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableThreadName
class ThreadNameStarter {

    static void main(String[] args) {
        SpringApplication.run(ThreadNameStarter.class, args);
    }
}
