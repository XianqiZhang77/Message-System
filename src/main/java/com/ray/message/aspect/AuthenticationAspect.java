package com.ray.message.aspect;


import javax.servlet.http.HttpServletRequest;

import com.ray.message.model.User;
import com.ray.message.sevice.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(1)
public class AuthenticationAspect {

    @Autowired
    UserService userService;

    @Around("@annotation(com.ray.message.annotations.NeedAuth) && execution(* com.ray.message.controller.*Controller.*(Integer,..))")
    public Object authenticate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        int userId = userService.verifyToken(token);

        Object[] args = proceedingJoinPoint.getArgs();
        args[0] = userId;
        System.out.println(args[1]);

        return proceedingJoinPoint.proceed(args);
    }
}
