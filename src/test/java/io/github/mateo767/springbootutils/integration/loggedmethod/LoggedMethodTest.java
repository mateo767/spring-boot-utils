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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LoggedMethodStarter.class)
class LoggedMethodTest {

    @Autowired
    private LoggedMethodService loggedMethodService;

    static void main(String[] args) {
        SpringApplication.run(LoggedMethodTest.class, args);
    }

    @Test
    void logMethodWithArgumentsAndResultAndTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithArgumentsAndResultAndTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndResultAndTimingToInfo(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithArgumentsAndResultAndTimingToInfo returned: res in "));
    }

    @Test
    void logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo()", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo finished", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithArgumentsAndResultAndWithoutTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithArgumentsAndResultAndWithoutTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndResultAndWithoutTimingToInfo(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithArgumentsAndResultAndWithoutTimingToInfo returned: res", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo(●●●●, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo returned: res", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo();

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo()", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo finished", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo(null, 1, new SomeObject("sF", 0));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo(null, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo finished", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo("arg_1", 1, new SomeObject(null, 0));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo(arg_1, 1, SomeObject(sField=null, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo finished", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithArgumentsAndThrowableAndTimingToDebug() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        assertThrows(LoggedMethodService.ServiceException.class, () ->
                loggedMethodService.logMethodWithArgumentsAndThrowableAndTimingToDebug("arg_1", 1, new SomeObject("sF", 0)));

        // then
        var logs = listAppender.list;
        assertEquals(Level.DEBUG, listAppender.list.get(0).getLevel());
        assertEquals(Level.DEBUG, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndThrowableAndTimingToDebug(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithArgumentsAndThrowableAndTimingToDebug threw ServiceException(message=message) in "));
    }

    @Test
    void logMethodWithArgumentsAndThrowableAndWithoutTimingToError() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        assertThrows(LoggedMethodService.ServiceException.class, () ->
                loggedMethodService.logMethodWithArgumentsAndThrowableAndWithoutTimingToError("arg_1", 1, new SomeObject("sF", 0)));

        // then
        var logs = listAppender.list;
        assertEquals(Level.ERROR, listAppender.list.get(0).getLevel());
        assertEquals(Level.ERROR, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndThrowableAndWithoutTimingToError(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithArgumentsAndThrowableAndWithoutTimingToError threw ServiceException(message=message)", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        assertThrows(LoggedMethodService.ServiceException.class, () ->
                loggedMethodService.logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo("arg_1", 1, new SomeObject("sF", 0)));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo()", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo threw exception in "));
    }

    @Test
    void logMethodWithoutArgumentsAndWithoutThrowableAndWithoutTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        assertThrows(LoggedMethodService.ServiceException.class, () ->
                loggedMethodService.logMethodWithoutArgumentsAndWithoutThrowableAndWithoutTimingToInfo("arg_1", 1, new SomeObject("sF", 0)));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithoutArgumentsAndWithoutThrowableAndWithoutTimingToInfo()", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithoutArgumentsAndWithoutThrowableAndWithoutTimingToInfo threw exception"));
    }

    @Test
    void logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo() {
        // given
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        // when
        loggedMethodService.logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        // then
        var logs = listAppender.list;
        assertEquals(Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo()", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo finished in "));
    }
}
