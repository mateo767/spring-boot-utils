package com.larchsoftware.utils.springboot.loggedmethod;

import org.slf4j.Logger;

import java.util.Arrays;

class ProxyLogger {

    private final Logger targetLogger;
    private final Level level;

    ProxyLogger(Logger targetLogger, Level level) {
        this.targetLogger = targetLogger;
        this.level = level;
    }

    void log(String format, Object... arguments) {
        switch (level) {
            case ERROR -> targetLogger.error(format, arguments);
            case WARN -> targetLogger.warn(format, arguments);
            case INFO -> targetLogger.info(format, arguments);
            case TRACE -> targetLogger.trace(format, arguments);
            case DEBUG -> targetLogger.debug(format, arguments);
            default -> targetLogger.debug(format, arguments);
        }
    }

    static class ArgumentsWrapper {

        private final Object[] arguments;

        private ArgumentsWrapper(Object... arguments) {
            this.arguments = arguments;
        }

        static ArgumentsWrapper of(Object... arguments) {
            return new ArgumentsWrapper(arguments);
        }

        @Override
        public String toString() {
            return Arrays.toString(arguments).replaceAll("(^\\[)|(]$)", "");
        }
    }
}
