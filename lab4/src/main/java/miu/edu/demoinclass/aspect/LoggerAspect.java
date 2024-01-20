package miu.edu.demoinclass.aspect;

import miu.edu.demoinclass.entity.aop.Exceptions;
import miu.edu.demoinclass.entity.aop.Logger;
import miu.edu.demoinclass.repo.LoggerAspectRepo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class LoggerAspect {

    @Autowired
    LoggerAspectRepo loggerAspectRepo;

    @Pointcut("@annotation(miu.edu.demoinclass.aspect.annotation.LogMe)")
    public void logMeAnnotation(){}

    @Pointcut("execution( * miu.edu.demoinclass.controller.UserController.*(..))")
    public void logMe(){}

    @After("logMe()")
//    @After("logMeAnnotation()")
    public void logAfter(JoinPoint joinPoint){
        System.out.println("Log after the method: " + joinPoint.getSignature().getName());
        Logger logger = new Logger(LocalDate.now(), LocalTime.now(),"admin",joinPoint.getSignature().getName());
        loggerAspectRepo.save(logger);

    }
//    @AfterThrowing(pointcut = "logMe()", throwing = "ex")
//    public void logAfterThrow(JoinPoint joinPoint, Exceptions ex){
//        System.out.println("Log after the method: " + joinPoint.getSignature().getName());
//        Exceptions exceptions = new Exceptions(LocalDate.now(), LocalTime.now(),"admin",joinPoint.getSignature().getName(),ex.getMessage());
//     //   loggerAspectRepo.save(exceptions);
//    }
}
