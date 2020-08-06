package com.jrx.logger.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Lazy(false)
public class AnytxnLogAspect {

    private final static Logger log = LoggerFactory.getLogger(AnytxnLogAspect.class);

    @Pointcut(value = "@annotation(com.jrx.logger.config.AnytxnLog)")
    public void cutMethod() {
    }

    @Pointcut(value = "execution(public * context*(..))")
    public void excute(){}

    @Before("excute()")
    public void logBefore(JoinPoint jointPoint) {
        MethodSignature signature = (MethodSignature) jointPoint.getSignature();
        String methodName = signature.getMethod().getName();

        log.info("methodName = {} start 日志操作", methodName);
    }

    @Around("cutMethod()")
    public Object arround(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("log arround{}", proceedingJoinPoint.getSignature().getName());

        return "10000";
    }

    @After("cutMethod()")
    public void endBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        AnytxnLog anytxnLog = signature.getMethod().getAnnotation(AnytxnLog.class);
        String simpleName = anytxnLog.className().getSimpleName();
        String old = anytxnLog.old();
        String now = anytxnLog.now();
        String logInfo = anytxnLog.logInfo();

        log.info("方法{}执行日志操作:类对象[{}]旧值=[{}],新值=[{}],日志信息={}", methodName, simpleName, old, now, logInfo);
    }

}
