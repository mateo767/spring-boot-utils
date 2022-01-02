package io.github.mateo767.springbootutils.loggedmethod;

import org.slf4j.Logger;

import java.util.Arrays;

class ProxyLogger {

    private final Logger targetLogger;
    private final Level level;

    private ProxyLogger(Logger targetLogger, Level level) {
        this.targetLogger = targetLogger;
        this.level = level;
    }

    void log(String format, Object... arguments) {
        switch (level) {
            case ERROR:
                targetLogger.error(format, arguments);
                break;
            case WARN:
                targetLogger.warn(format, arguments);
                break;
            case TRACE:
                targetLogger.trace(format, arguments);
                break;
            case DEBUG:
                targetLogger.debug(format, arguments);
                break;
            case INFO:
            default:
                targetLogger.info(format, arguments);
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

    static class ProxyLoggerFactory {

        private final Logger targetLogger;

        public ProxyLoggerFactory(Logger targetLogger) {
            this.targetLogger = targetLogger;
        }

        public ProxyLogger create(Level level) {
            return new ProxyLogger(targetLogger, level);
        }
    }
}
