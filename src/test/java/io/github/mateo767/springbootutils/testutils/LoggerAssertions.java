package io.github.mateo767.springbootutils.testutils;

import io.github.mateo767.springbootutils.loggedmethod.Level;
import org.mockito.Mockito;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class LoggerAssertions {

    private final Logger loggerMock;

    private LoggerAssertions(Logger loggerMock) {
        assertTrue(Mockito.mockingDetails(loggerMock).isMock());
        this.loggerMock = loggerMock;
    }

    public static LoggerAssertions create(Logger loggerMock) {
        return new LoggerAssertions(loggerMock);
    }

    public LoggerAssertions assertLevel(Level level) {
        switch (level) {
            case INFO:
            case DEFAULT:
                verify(loggerMock).info(anyString(), any(Object[].class));
                break;
            case ERROR:
                verify(loggerMock).error(anyString(), any(Object[].class));
                break;
            case WARN:
                verify(loggerMock).warn(anyString(), any(Object[].class));
                break;
            case DEBUG:
                verify(loggerMock).debug(anyString(), any(Object[].class));
                break;
            case TRACE:
                verify(loggerMock).trace(anyString(), any(Object[].class));
                break;
        }
        return this;
    }

    public void noMoreInteractions() {
        verifyNoMoreInteractions(loggerMock);
    }
}
