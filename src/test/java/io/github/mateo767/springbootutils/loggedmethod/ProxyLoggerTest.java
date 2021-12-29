package io.github.mateo767.springbootutils.loggedmethod;

import io.github.mateo767.springbootutils.testutils.LoggerAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.slf4j.Logger;

import static org.mockito.Mockito.mock;

class ProxyLoggerTest {

    @ParameterizedTest
    @EnumSource(Level.class)
    void createProxyLoggerWihLevel_andInvoke_shouldPrintToProperLevel(Level level) {
        // given
        var logger = mock(Logger.class);

        // when
        new ProxyLogger(logger, level).log("message");

        // then
        LoggerAssertions.create(logger)
                .assertLevel(level)
                .noMoreInteractions();
    }
}