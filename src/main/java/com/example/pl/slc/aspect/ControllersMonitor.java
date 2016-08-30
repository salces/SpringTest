package com.example.pl.slc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
@Component
public class ControllersMonitor {

    private static final Logger log = LoggerFactory.getLogger(ControllersMonitor.class);

    @Pointcut("execution(* com.example.pl.slc.controller.*.*(..))")
    public void anyControllerMethodExecution() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void anyMappedControllerMethod() {
    }

    @Pointcut("anyControllerMethodExecution() && anyMappedControllerMethod()")
    public void anyMappedControllerMethodExecution() {
    }

    @Around("anyMappedControllerMethodExecution()")
    public Object logTimeOfExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch(nameOf(joinPoint));
        stopWatch.start();

        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info(stopWatch.shortSummary());
        }
    }

    private String nameOf(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        return signature.getDeclaringType().getSimpleName() + "." + signature.getName();
    }

}
