package io.github.mateo767.springbootutils.threadname;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring-boot-utils.thread-name")
class ThreadNameProperties {

    private long initialThreadId = 0;

    public long getInitialThreadId() {
        return initialThreadId;
    }

    public void setInitialThreadId(long initialThreadId) {
        this.initialThreadId = initialThreadId;
    }
}
