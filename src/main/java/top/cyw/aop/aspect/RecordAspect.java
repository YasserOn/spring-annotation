package top.cyw.aop.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class RecordAspect {

    @Pointcut("@annotation(top.cyw.aop.annotation.Record)")
    public void annotationPointCut() {
        System.out.println("annotationPointCut");
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("方法前织入");
        Object result = joinPoint.proceed();
        System.out.println("方法后织入");
        return result;
    }


}
