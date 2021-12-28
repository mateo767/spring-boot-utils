package com.larchsoftware.utils.springboot.integration.loggedmethod;

import com.larchsoftware.utils.springboot.integration.SomeObject;
import com.larchsoftware.utils.springboot.loggedmethod.Level;
import com.larchsoftware.utils.springboot.loggedmethod.LoggedMethod;
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

    @LoggedMethod(level = Level.DEBUG)
    String logMethodWithArgumentsAndThrowableAndTimingToDebug(String arg1, int arg2, SomeObject arg3) {
        throw new ServiceException("message");
    }

    @LoggedMethod(level = Level.ERROR, timing = false)
    String logMethodWithArgumentsAndThrowableAndWithoutTimingToError(String arg1, int arg2, SomeObject arg3) {
        throw new ServiceException("message");
    }

    @LoggedMethod(logArguments = false, logResult = false)
    String logMethodWithoutArgumentsAndWithoutThrowableAndWithTimingToInfo(String arg1, int arg2, SomeObject arg3) {
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