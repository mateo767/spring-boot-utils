package com.github.mateo767.springbootutils.loggedmethod;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Slf4j
class LoggedMethodProcessor {

    private static final String METHOD_EXECUTION_JOIN_KIND = "method-execution";
    private static final String BEFORE_WO_ARGS_PATTERN = "Invoked {}()";
    private static final String BEFORE_W_ARGS_PATTERN = "Invoked {}({})";
    private static final String AFTER_W_RETURN_PATTERN = "Method {} returned: {}";
    private static final String AFTER_WO_RETURN_PATTERN = "Method {} finished";
    private static final String AFTER_W_THROWABLE_PATTERN = "Method {} threw {}(message={})";
    private static final String AFTER_WO_THROWABLE_PATTERN = "Method {} threw exception";
    private static final String TIMING_PATTERN = " in {} ms";
    private static final String EXCLUDED_ARGUMENT_REPLACEMENT = "●●●●";
    private final Level defaultLevel;

    LoggedMethodProcessor(Level defaultLevel) {
        this.defaultLevel = defaultLevel;
    }

    @Around("@annotation(com.github.mateo767.springbootutils.loggedmethod.LoggedMethod)")
    Object processLoggedMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!METHOD_EXECUTION_JOIN_KIND.equals(joinPoint.getKind())) {
            log.error("unsupported joinPoint of kind {}", joinPoint.getKind());
            throw new IllegalArgumentException("joinPoint of kind " + joinPoint.getKind() + " is not supported");
        }

        var methodSignature = (MethodSignature) joinPoint.getSignature();
        var lmAnnotation = Optional.ofNullable(
                        AnnotatedElementUtils.findMergedAnnotation(
                                methodSignature.getMethod(),
                                LoggedMethod.class))
                .orElseThrow(() -> {
                    log.error("Annotation cannot be null - possible wrong pointcut expression");
                    return new NullPointerException("annotation cannot be null");
                });

        var logger = new ProxyLogger(
                LoggerFactory.getLogger(methodSignature.getDeclaringType()),
                lmAnnotation.level() == Level.DEFAULT ? defaultLevel : lmAnnotation.level());

        logMethodInvocation(lmAnnotation, logger, joinPoint, methodSignature);
        var start = System.currentTimeMillis();
        try {
            var result = joinPoint.proceed();
            logMethodFinished(lmAnnotation, logger, methodSignature, result, start);
            return result;
        } catch (Throwable t) {
            logThrowable(lmAnnotation, logger, methodSignature, t, start);
            throw t;
        }
    }

    private void logMethodInvocation(
            LoggedMethod lmAnnotation,
            ProxyLogger logger,
            JoinPoint joinPoint,
            MethodSignature methodSignature) {
        if (lmAnnotation.logArguments() && methodSignature.getMethod().getParameterCount() > 0) {
            logger.log(BEFORE_W_ARGS_PATTERN, methodSignature.getName(),
                    getFilteredArguments(methodSignature, joinPoint, lmAnnotation.exclude()));
        } else {
            logger.log(BEFORE_WO_ARGS_PATTERN, methodSignature.getName());
        }
    }

    private void logMethodFinished(
            LoggedMethod lmAnnotation,
            ProxyLogger logger,
            MethodSignature methodSignature,
            Object result,
            long start) {
        if (lmAnnotation.logResult() && !methodSignature.getMethod().getReturnType().equals(Void.TYPE)) {
            if (lmAnnotation.timing()) {
                logger.log(AFTER_W_RETURN_PATTERN + TIMING_PATTERN, methodSignature.getName(), result, calculateDuration(start));
            } else {
                logger.log(AFTER_W_RETURN_PATTERN, methodSignature.getName(), result);
            }
        } else {
            if (lmAnnotation.timing()) {
                logger.log(AFTER_WO_RETURN_PATTERN + TIMING_PATTERN, methodSignature.getName(), calculateDuration(start));
            } else {
                logger.log(AFTER_WO_RETURN_PATTERN, methodSignature.getName());
            }
        }
    }

    private void logThrowable(
            LoggedMethod lmAnnotation,
            ProxyLogger logger,
            MethodSignature methodSignature,
            Throwable throwable,
            long start) {
        if (lmAnnotation.logResult()) {
            if (lmAnnotation.timing()) {
                logger.log(AFTER_W_THROWABLE_PATTERN + TIMING_PATTERN, methodSignature.getName(), throwable.getClass().getSimpleName(), throwable.getMessage(), calculateDuration(start));
            } else {
                logger.log(AFTER_W_THROWABLE_PATTERN, methodSignature.getName(), throwable.getClass().getSimpleName(), throwable.getMessage());
            }
        } else {
            if (lmAnnotation.timing()) {
                logger.log(AFTER_WO_THROWABLE_PATTERN + TIMING_PATTERN, methodSignature.getName(), calculateDuration(start));
            } else {
                logger.log(AFTER_WO_THROWABLE_PATTERN, methodSignature.getName());
            }
        }
    }

    private static long calculateDuration(long start) {
        return System.currentTimeMillis() - start;
    }

    private static ProxyLogger.ArgumentsWrapper getFilteredArguments(
            MethodSignature methodSignature,
            JoinPoint joinPoint,
            String[] excludedNames) {
        var names = methodSignature.getParameterNames();
        if (names.length != joinPoint.getArgs().length) {
            throw new IllegalArgumentException("argument names count differs from argument values count");
        }
        var exclusions = Arrays.asList(excludedNames);
        var filteredValues = new ArrayList<>();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            if (exclusions.contains(methodSignature.getParameterNames()[i])) {
                filteredValues.add(EXCLUDED_ARGUMENT_REPLACEMENT);
            } else {
                filteredValues.add(joinPoint.getArgs()[i]);
            }
        }
        return ProxyLogger.ArgumentsWrapper.of(filteredValues.toArray());
    }
}
