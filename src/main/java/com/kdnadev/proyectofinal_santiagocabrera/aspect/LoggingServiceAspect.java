package com.kdnadev.proyectofinal_santiagocabrera.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class LoggingServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingServiceAspect.class);

    @Before("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)")
    public void logBeforeControllerMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        logger.info("BEGIN: Controller [{}] Method [{}] with arguments:",
            className, 
            methodName);
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> logger.info(arg.toString()));
    }

    @AfterReturning(
        pointcut = "@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)",
        returning = "result"
    )
    public void logAfterControllerMethods(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        logger.info("END: Controller [{}] Method [{}] returned: {}",
            className,
            methodName,
            result
        );
    }
}
