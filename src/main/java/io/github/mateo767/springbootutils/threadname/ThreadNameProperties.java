package io.github.mateo767.springbootutils.threadname;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring-boot-utils.thread-name")
@Getter
@Setter
class ThreadNameProperties {

    private long initialThreadId = 0;
}
