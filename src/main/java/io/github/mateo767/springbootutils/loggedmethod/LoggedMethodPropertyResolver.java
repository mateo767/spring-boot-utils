package io.github.mateo767.springbootutils.loggedmethod;

import java.lang.annotation.Annotation;

class LoggedMethodPropertyResolver {

    private final Level defaultLevel;
    private final Level exceptionLevel;

    private LoggedMethodPropertyResolver(Level defaultLevel, Level exceptionLevel) {
        this.defaultLevel = defaultLevel;
        this.exceptionLevel = exceptionLevel;
    }

    public static LoggedMethodPropertyResolver create(Level level, Level exceptionLevel) {
        return new LoggedMethodPropertyResolver(level, exceptionLevel);
    }

    LoggedMethod resolveProperties(LoggedMethod methodAnnotation) {
        return new LoggedMethod() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return LoggedMethod.class;
            }

            @Override
            public Level level() {
                return methodAnnotation.level() == Level.DEFAULT
                        ? defaultLevel
                        : methodAnnotation.level();
            }

            @Override
            public Level exceptionLevel() {
                return methodAnnotation.exceptionLevel() == Level.DEFAULT
                        ? exceptionLevel
                        : methodAnnotation.exceptionLevel();
            }

            @Override
            public boolean logArguments() {
                return methodAnnotation.logArguments();
            }

            @Override
            public boolean logResult() {
                return methodAnnotation.logResult();
            }

            @Override
            public boolean timing() {
                return methodAnnotation.timing();
            }

            @Override
            public String[] exclude() {
                return methodAnnotation.exclude();
            }
        };
    }
}
