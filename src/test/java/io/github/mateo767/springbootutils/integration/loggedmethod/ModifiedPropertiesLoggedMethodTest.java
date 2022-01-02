package io.github.mateo767.springbootutils.integration.loggedmethod;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import io.github.mateo767.springbootutils.integration.SomeObject;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LoggedMethodStarter.class)
@ActiveProfiles("modified-properties")
class ModifiedPropertiesLoggedMethodTest {

    @Autowired
    private LoggedMethodService loggedMethodService;

    static void main(String[] args) {
        SpringApplication.run(LoggedMethodTest.class, args);
    }

    @Test
    void logMethodWithArgumentsAndResultAndTimingToInfo_withDefaultLevelDebug() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithArgumentsAndResultAndTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        // then
        var logs = listAppender.list;
        assertEquals(Level.DEBUG, listAppender.list.get(0).getLevel());
        assertEquals(Level.DEBUG, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndResultAndTimingToInfo(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithArgumentsAndResultAndTimingToInfo returned: res in "));
    }


    @Test
    void logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToDefault_withDefaultLevelsInvocationToDebugAndExceptionToWarn() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        assertThrows(LoggedMethodService.ServiceException.class, () ->
                loggedMethodService.logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToDefault("arg_1", 1, new SomeObject("sF", 0)));

        // then
        var logs = listAppender.list;
        assertEquals(Level.DEBUG, listAppender.list.get(0).getLevel());
        assertEquals(Level.WARN, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToDefault()", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToDefault threw exception in "));
    }
}
