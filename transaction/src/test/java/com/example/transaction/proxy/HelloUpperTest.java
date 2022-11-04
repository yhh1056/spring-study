package com.example.transaction.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HelloUpperTest {

    @Test
    void hello() {
        Hello hello = new HelloUpper(new HelloTarget());

        assertThat(hello.sayHello("hoho")).isEqualTo("HELLO HOHO");
        assertThat(hello.sayHi("hoho")).isEqualTo("HI HOHO");
        assertThat(hello.sayThankYou("hoho")).isEqualTo("THANK YOO HOHO");
    }
}
