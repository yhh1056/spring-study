package com.example.transaction.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

class HelloHandlerTest {

    @Test
    void dynamicHelloHandler() {
        HelloHandler handler = new HelloHandler(new HelloUpper(new HelloTarget()));

        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class<?>[]{Hello.class},
                handler
        );

        assertThat(hello.sayHello("hoho")).isEqualTo("HELLO HOHO");
        assertThat(hello.sayHi("hoho")).isEqualTo("HI HOHO");
        assertThat(hello.sayThankYou("hoho")).isEqualTo("THANK YOU HOHO");
    }

    @Test
    void proxyFactoryBean() {

        /**
         * InvocationHandler의 invoke() 메서드는 타깃 오브젝틍에 대한 정보를 제공하지 않는다.
         * 반면에 MethodInterceptor의 invoke() 메서드는 ProxyFactoryBean으로부터 타깃 오브젝트에 대한 정보까지도 제공받는다.
         * 그 차이덕분에 타깃 오브젝트와 상관없이 독립적으로 만들어질 수 있다.
         * 여러 프록시에서 함께 사용할 수 있고 싱글톤 빈으로 등록이 가능하다.
         */
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());
        pfBean.addAdvice(new LowercaseAdvice());

        Hello proxiedHello = (Hello) pfBean.getObject();

        assertThat(proxiedHello.sayHello("hoho")).isEqualTo("HELLO HOHO");
        assertThat(proxiedHello.sayHi("hoho")).isEqualTo("HI HOHO");
        assertThat(proxiedHello.sayThankYou("hoho")).isEqualTo("THANK YOU HOHO");
    }

    @Test
    void pointCutAdvisor() {
        ProxyFactoryBean factoryBean = new ProxyFactoryBean();
        factoryBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        factoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) factoryBean.getObject();

        assertThat(proxiedHello.sayHello("hoho")).isEqualTo("HELLO HOHO");
        assertThat(proxiedHello.sayHi("hoho")).isEqualTo("HI HOHO");
        assertThat(proxiedHello.sayThankYou("hoho")).isEqualTo("thank you hoho");
    }

    @Test
    @Disabled
    void classNamePointcutAdvisor() {
        NameMatchMethodPointcut methodPointcut = new NameMatchMethodPointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return clazz -> clazz.getSimpleName().startsWith("HelloT");
            }
        };

        methodPointcut.setMappedName("SayH");

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new HelloToby());
//        proxyFactoryBean.setTarget(new HelloHoHo());
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(methodPointcut, new UppercaseAdvice()));

        HelloToby helloToby = (HelloToby) proxyFactoryBean.getObject();
        assertThat(helloToby.sayHello("hoho")).isEqualTo("HELLO HOHO");
        assertThat(helloToby.sayHi("hoho")).isEqualTo("HI HOHO");
        assertThat(helloToby.sayThankYou("hoho")).isEqualTo("thank you hoho");
//
//        HelloHoHo helloHoHo = (HelloHoHo) proxyFactoryBean.getObject();
//        assertThat(helloHoHo.sayHello("hoho")).isEqualTo("hello hoho");
//        assertThat(helloHoHo.sayHi("hoho")).isEqualTo("hi hoho");
//        assertThat(helloHoHo.sayThankYou("hoho")).isEqualTo("thank you hoho");
    }

    static class HelloToby extends HelloTarget {}
    static class HelloHoHo extends HelloTarget {}

}
