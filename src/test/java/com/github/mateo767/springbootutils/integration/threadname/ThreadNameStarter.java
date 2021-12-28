package com.github.mateo767.springbootutils.integration.threadname;

import com.github.mateo767.springbootutils.threadname.EnableThreadName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableThreadName
@Slf4j
class ThreadNameStarter {

    static void main(String[] args) {
        SpringApplication.run(ThreadNameStarter.class, args);
    }
}
