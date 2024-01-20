package miu.edu.demoinclass.aspect;

import miu.edu.demoinclass.entity.aop.Exceptions;
import miu.edu.demoinclass.repo.ExceptionsRepo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class ExceptionsAspect {
    @Autowired
    ExceptionsRepo exceptionsRepo;
    @Pointcut("execution( * miu.edu.demoinclass.service.UserService.*(..))")
    public void serviceMethods(){}

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex){
        // Log exception details
        System.out.println("Exception in method: " + joinPoint.getSignature().toShortString());
        System.out.println("Exception type: " + ex.getClass().getSimpleName());
        System.out.println("Exception message: " + ex.getMessage());

        // Create and save ExceptionEntity
        Exceptions exceptionEntity = new Exceptions(LocalDate.now(),
                LocalTime.now(),"admin",joinPoint.getSignature().getName()
                ,ex.getClass().getSimpleName());
        exceptionsRepo.save(exceptionEntity);


    }
}
