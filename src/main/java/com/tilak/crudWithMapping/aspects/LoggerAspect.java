package com.tilak.crudWithMapping.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class LoggerAspect {

    private static final Logger logger= LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(* com.tilak.crudWithMapping.services.EmployeeService.*(..))")
    public void serviceMethods(){}

    @Pointcut("execution(* com.tilak.crudWithMapping.controllers.EmployeeController.*(..))")
    public void controllerMethods() {}


    @Before(value = "execution(* com.tilak.crudWithMapping.controllers.EmployeeController.createEmployee(..))")
    public void generateLog(JoinPoint joinPoint){

        System.out.println("name is Tilak");
        logger.info("Before method: " + joinPoint.getSignature().getName());
    }
    @Before("serviceMethods()")
    public void logBeforeService(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature() + " method is about to execute.");
    }
    @Before("controllerMethods()")
    public void logBeforeController(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature() + " method is about to execute.");
    }

    @After("serviceMethods()")
    public void logAfterService(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature() + " method has executed.");
    }
    @After("controllerMethods()")
    public void logAfterController(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature() + " method has executed.");
    }
    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start =System.currentTimeMillis();
        Object process=joinPoint.proceed();
        long executionTime=System.currentTimeMillis()-start;
        logger.info("Method {} executed in {} ms",joinPoint.getSignature(),executionTime);

        return process;
    }

}
