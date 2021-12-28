package com.github.mateo767.springbootutils.integration.loggedmethod;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.github.mateo767.springbootutils.integration.SomeObject;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LoggedMethodStarter.class)
class LoggedMethodProcessorTest {

    @Autowired
    private LoggedMethodService loggedMethodService;

    static void main(String[] args) {
        SpringApplication.run(LoggedMethodProcessorTest.class, args);
    }

    @Test
    void logMethodWithArgumentsAndResultAndTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        loggedMethodService.logMethodWithArgumentsAndResultAndTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndResultAndTimingToInfo(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithArgumentsAndResultAndTimingToInfo returned: res in "));
    }

    @Test
    void logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        loggedMethodService.logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo()", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo finished", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithArgumentsAndResultAndWithoutTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        loggedMethodService.logMethodWithArgumentsAndResultAndWithoutTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndResultAndWithoutTimingToInfo(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithArgumentsAndResultAndWithoutTimingToInfo returned: res", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        loggedMethodService.logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo(●●●●, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo returned: res", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        loggedMethodService.logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo();

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo()", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo finished", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        loggedMethodService.logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo(null, 1, new SomeObject("sF", 0));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo(null, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo finished", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        loggedMethodService.logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo("arg_1", 1, new SomeObject(null, 0));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo(arg_1, 1, SomeObject(sField=null, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo finished", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithArgumentsAndThrowableAndTimingToDebug() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        assertThrows(LoggedMethodService.ServiceException.class, () ->
                loggedMethodService.logMethodWithArgumentsAndThrowableAndTimingToDebug("arg_1", 1, new SomeObject("sF", 0)));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.DEBUG, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.DEBUG, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndThrowableAndTimingToDebug(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithArgumentsAndThrowableAndTimingToDebug threw ServiceException(message=message) in "));
    }

    @Test
    void logMethodWithArgumentsAndThrowableAndWithoutTimingToError() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        assertThrows(LoggedMethodService.ServiceException.class, () ->
                loggedMethodService.logMethodWithArgumentsAndThrowableAndWithoutTimingToError("arg_1", 1, new SomeObject("sF", 0)));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.ERROR, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.ERROR, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithArgumentsAndThrowableAndWithoutTimingToError(arg_1, 1, SomeObject(sField=sF, iField=0))", logs.get(0).getFormattedMessage());
        assertEquals("Method logMethodWithArgumentsAndThrowableAndWithoutTimingToError threw ServiceException(message=message)", logs.get(1).getFormattedMessage());
    }

    @Test
    void logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        assertThrows(LoggedMethodService.ServiceException.class, () ->
                loggedMethodService.logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo("arg_1", 1, new SomeObject("sF", 0)));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo()", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo threw exception in "));
    }

    @Test
    void logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo() {
        var logger = (Logger) LoggerFactory.getLogger(LoggedMethodService.class);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        logger.addAppender(listAppender);

        loggedMethodService.logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo("arg_1", 1, new SomeObject("sF", 0));

        var logs = listAppender.list;
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(0).getLevel());
        assertEquals(ch.qos.logback.classic.Level.INFO, listAppender.list.get(1).getLevel());
        assertEquals("Invoked logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo()", logs.get(0).getFormattedMessage());
        assertTrue(logs.get(1).getFormattedMessage().startsWith("Method logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo finished in "));
    }
}
