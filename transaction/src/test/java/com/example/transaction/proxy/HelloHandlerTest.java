package com.example.transaction.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;

class HelloHandlerTest {

    @Test
    void hello() {
        HelloHandler handler = new HelloHandler(new HelloUpper(new HelloTarget()));

        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class<?>[]{Hello.class},
                handler
        );

        assertThat(hello.sayHello("hoho")).isEqualTo("HELLO HOHO");
        assertThat(hello.sayHi("hoho")).isEqualTo("HI HOHO");
        assertThat(hello.sayThankYou("hoho")).isEqualTo("THANK YOO HOHO");
    }
}
