package io.github.mateo767.springbootutils.integration.loggedmethod;

import io.github.mateo767.springbootutils.integration.SomeObject;
import io.github.mateo767.springbootutils.loggedmethod.Level;
import io.github.mateo767.springbootutils.loggedmethod.LoggedMethod;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unused", "SameParameterValue", "UnusedReturnValue"})
@Service
class LoggedMethodService {

    @LoggedMethod
    String logMethodWithArgumentsAndResultAndTimingToInfo(String arg1, int arg2, SomeObject arg3) {
        return "res";
    }

    @LoggedMethod(logArguments = false, logResult = false, timing = false)
    String logMethodWithoutArgumentsAndWithoutResultAndWithoutTimingToInfo(String arg1, int arg2, SomeObject arg3) {
        return "res";
    }

    @LoggedMethod(timing = false)
    String logMethodWithArgumentsAndResultAndWithoutTimingToInfo(String arg1, int arg2, SomeObject arg3) {
        return "res";
    }

    @LoggedMethod(timing = false, exclude = {"arg1"})
    String logMethodWithSecretArgumentAndResultAndWithoutTimingToInfo(String arg1, int arg2, SomeObject arg3) {
        return "res";
    }

    @LoggedMethod(timing = false)
    void logMethodWithNoArgumentsAndVoidResultAndWithoutTimingToInfo() {
        // do nothing
    }

    @LoggedMethod(timing = false)
    void logMethodWithNullArgumentsAndVoidResultAndWithoutTimingToInfo(String arg1, int arg2, SomeObject arg3) {
        // do nothing
    }

    @LoggedMethod(timing = false)
    void logMethodWithNullArgumentFieldAndVoidResultAndWithoutTimingToInfo(String arg1, int arg2, SomeObject arg3) {
        // do nothing
    }

    @LoggedMethod(level = Level.DEBUG, exceptionLevel = Level.WARN)
    String logMethodWithArgumentsAndThrowableAndTimingInvocationToDebugAndExceptionToWarn(String arg1, int arg2, SomeObject arg3) {
        throw new ServiceException("message");
    }

    @LoggedMethod(level = Level.ERROR, timing = false)
    String logMethodWithArgumentsAndThrowableAndWithoutTimingToError(String arg1, int arg2, SomeObject arg3) {
        throw new ServiceException("message");
    }

    @LoggedMethod(logArguments = false, logResult = false)
    String logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToDefault(String arg1, int arg2, SomeObject arg3) {
        throw new ServiceException("message");
    }

    @LoggedMethod(logArguments = false, logResult = false, timing = false)
    String logMethodWithoutArgumentsAndWithoutThrowableAndWithoutTimingToDefault(String arg1, int arg2, SomeObject arg3) {
        throw new ServiceException("message");
    }

    @LoggedMethod(logArguments = false, logResult = false)
    String logMethodWithoutArgumentsAndWithoutResultAndWithTimingToInfo(String arg1, int arg2, SomeObject arg3) {
        return "res";
    }

    static class ServiceException extends RuntimeException {

        public ServiceException(String message) {
            super(message);
        }
    }
}