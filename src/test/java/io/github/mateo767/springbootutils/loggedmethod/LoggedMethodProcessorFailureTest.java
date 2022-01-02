package io.github.mateo767.springbootutils.loggedmethod;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests of the impossible scenarios, just to ensure code coverage.
 */
class LoggedMethodProcessorFailureTest {

    private final LoggedMethodProcessor instance = new LoggedMethodProcessor(Level.DEFAULT);

    @Test
    void invokeWithWrongJoinPointKind_shouldThrowException() {
        // given
        var joinPoint = mock(ProceedingJoinPoint.class);
        when(joinPoint.getKind())
                .thenReturn("some-kind");

        // when
        assertThrows(IllegalArgumentException.class,
                () -> instance.processLoggedMethod(joinPoint));
    }
}