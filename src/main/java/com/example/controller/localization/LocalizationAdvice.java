package com.example.controller.localization;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LocalizationAdvice {

    private String defValue="NP";
    private List<String> supportedLanguage= new ArrayList();
    private String finalParam=null;
    private int langIndex=-1;
    private int acceptLanguageIndex=-1;


    private String[] parameterNames(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        return signature.getParameterNames();
    }

    @Around("execution(* com.example.controller.localization.controller.*.*(..))")
    public Object argumentPrint(ProceedingJoinPoint joinPoint) throws Throwable
    {
        supportedLanguage.add("NP");
        supportedLanguage.add("EN");

        Object[] args=joinPoint.getArgs();
        Object[] params=parameterNames(joinPoint);
        for (int i=0; i<params.length;i++)
        {
            if(params[i].equals("lang") )
            {
                langIndex=i;
            }
            else if(params[i].equals("acceptLanguage") )
            {
                acceptLanguageIndex=i;
            }

        }
        if (args[langIndex]==null )
        {
            finalParam=defValue;
        }
        else if(args[acceptLanguageIndex]!=null)
        {
            finalParam=args[acceptLanguageIndex].toString();
        }
        if (args[acceptLanguageIndex]==null && args[langIndex]==null )
        {
            finalParam=defValue;
        }
        else if (args[acceptLanguageIndex]!=null && args[langIndex]==null)
        {
            finalParam=args[acceptLanguageIndex].toString();
        }
        else {
            if(args[langIndex]!=null)
            {
                finalParam=args[langIndex].toString();
            }
        }
        if(!supportedLanguage.contains(finalParam))
        {
            finalParam=defValue;
        }
        args[langIndex]=finalParam;
        args[acceptLanguageIndex]=finalParam;

    return   joinPoint.proceed(args);
    }

}
