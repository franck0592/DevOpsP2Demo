package com.revature.P1DemoBackend.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect //this spring that this class is an Aspect
@Component
public class AuthAspect {
    @Order(1) //this advice will always run first
    @Before("within(com.revature.P1DemoBackend.controller.*)"+"&& !within(com.revature.P1DemoBackend.controller.*")
    public void checkLoggedIn(){}
}
