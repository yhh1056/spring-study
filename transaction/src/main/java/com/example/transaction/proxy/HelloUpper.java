package com.example.transaction.proxy;

public class HelloUpper implements Hello {

    private final Hello hello;

    public HelloUpper(final Hello hello) {
        this.hello = hello;
    }

    @Override
    public String sayHello(final String name) {
        return hello.sayHello(name).toUpperCase();
    }

    @Override
    public String sayHi(final String name) {
        return hello.sayHi(name).toUpperCase();
    }

    @Override
    public String sayThankYou(final String name) {
        return hello.sayThankYou(name).toUpperCase();
    }
}
