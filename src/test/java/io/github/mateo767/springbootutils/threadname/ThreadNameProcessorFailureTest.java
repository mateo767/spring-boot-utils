package io.github.mateo767.springbootutils.threadname;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests of the impossible scenarios, just to ensure code coverage.
 */
class ThreadNameProcessorFailureTest {

    private final ThreadNameProcessor instance = new ThreadNameProcessor(0);

    @Test
    void invokeWithWrongJoinPointKind_shouldThrowException() {
        // given
        var joinPoint = mock(ProceedingJoinPoint.class);
        when(joinPoint.getKind())
                .thenReturn("some-kind");

        // when
        assertThrows(IllegalArgumentException.class,
                () -> instance.processThreadName(joinPoint));
    }
}