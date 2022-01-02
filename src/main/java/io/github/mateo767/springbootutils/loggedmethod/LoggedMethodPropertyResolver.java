package io.github.mateo767.springbootutils.loggedmethod;

import java.lang.annotation.Annotation;

class LoggedMethodPropertyResolver {

    private final Level defaultLevel;

    private LoggedMethodPropertyResolver(Level defaultLevel) {
        this.defaultLevel = defaultLevel;
    }

    public static LoggedMethodPropertyResolver create(Level level) {
        return new LoggedMethodPropertyResolver(level);
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
