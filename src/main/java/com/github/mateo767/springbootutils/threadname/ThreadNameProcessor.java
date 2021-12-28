package com.github.mateo767.springbootutils.threadname;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.Collectors.joining;

@Aspect
@Slf4j
class ThreadNameProcessor {

    private static final String METHOD_EXECUTION_JOIN_KIND = "method-execution";
    private static final String SEPARATOR = "-";
    private static final SpelExpressionParser expressionParser = new SpelExpressionParser();
    private final AtomicLong threadId;

    ThreadNameProcessor(long initialThreadId) {
        this.threadId = new AtomicLong(initialThreadId);
    }

    @Around("@annotation(com.github.mateo767.springbootutils.threadname.ThreadName)")
    Object processThreadName(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!METHOD_EXECUTION_JOIN_KIND.equals(joinPoint.getKind())) {
            log.error("unsupported joinPoint of kind {}", joinPoint.getKind());
            throw new IllegalArgumentException("joinPoint of kind " + joinPoint.getKind() + " is not supported");
        }
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        var tnAnnotation = Optional.ofNullable(
                        AnnotatedElementUtils.findMergedAnnotation(
                                methodSignature.getMethod(),
                                ThreadName.class))
                .orElseThrow(() -> {
                    log.error("Annotation cannot be null - possible wrong pointcut expression");
                    return new NullPointerException("annotation cannot be null");
                });

        var oldName = Thread.currentThread().getName();
        var name = prepareEvaluatedName(joinPoint, methodSignature, tnAnnotation) + threadId.getAndIncrement();
        Thread.currentThread().setName(name);
        try {
            return joinPoint.proceed();
        } finally {
            if (tnAnnotation.restoreName()) {
                Thread.currentThread().setName(oldName);
            }
        }
    }

    private static String prepareEvaluatedName(
            JoinPoint joinPoint,
            MethodSignature methodSignature,
            ThreadName tnAnnotation) {
        var evaluationCtx = new MethodBasedEvaluationContext(
                joinPoint.getTarget(),
                methodSignature.getMethod(),
                joinPoint.getArgs(),
                new DefaultParameterNameDiscoverer());
        return Arrays.stream(tnAnnotation.expressions())
                .map(expressionParser::parseExpression)
                .map(e -> "" + e.getValue(evaluationCtx))
                .collect(joining(SEPARATOR, tnAnnotation.prefix() + SEPARATOR, SEPARATOR))
                .replace(SEPARATOR + SEPARATOR, SEPARATOR);
    }
}