package com.example.transaction.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UppercaseAdvice implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        String proceed = (String) invocation.proceed();
        System.out.println("UPPP");
        return proceed.toUpperCase();
    }
}
