package com.ray.message.aspect;


import com.ray.message.sevice.UserService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
@Order(0)
public class LogAspect {

    @Autowired
    UserService userService;

    @Around("execution(* com.ray.message.controller.*Controller.*(..))")
    public Object authenticate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
            String methodName = proceedingJoinPoint.getSignature().getName();
            log.info("Executed {}.{} in {} ms.", className, methodName, System.currentTimeMillis() - startTime);
        }

    }
}
