package com.example.controller.localization;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LocalizationAdvice {

    private String[] parameterNames(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        return signature.getParameterNames();
    }

    @Around("execution(* com.example.controller.localization.controller.*.*(..))")
    public Object argumentPrint(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object[] args=joinPoint.getArgs();
        Object[] params=parameterNames(joinPoint);
        for (int i=0; i<params.length;i++)
        {
            if(params[i].equals("lang"))
            {
                Object raw=args[i] ==null ?"" :args[i];
                if(!raw.toString().equals("NP") && !raw.toString().equals("EN"))
                {
                    args[i]="NP";
                }

            }
            else if(params[i].equals("acceptLanguage"))
            {
                Object raw=args[i] ==null ?"" :args[i];
                if(!raw.toString().equals("NP") && !raw.toString().equals("EN"))
                {
                    args[i]="NP";
                }

            }

        }

    return   joinPoint.proceed(args);
    }

}
