package com.larchsoftware.utils.springboot.threadname;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("larchsoftware.utils.thread-name")
@Getter
@Setter
class ThreadNameProperties {

    private long initialThreadId = 0;
}
