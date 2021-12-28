package com.github.mateo767.springbootutils.threadname;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ThreadNameConfiguration {

    @Bean
    @Autowired
    ThreadNameProcessor threadNameProcessor(ThreadNameProperties properties) {
        return new ThreadNameProcessor(properties.getInitialThreadId());
    }
}
