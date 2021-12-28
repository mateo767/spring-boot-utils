package com.larchsoftware.utils.springboot.integration.threadname;

import com.larchsoftware.utils.springboot.threadname.EnableThreadName;
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
