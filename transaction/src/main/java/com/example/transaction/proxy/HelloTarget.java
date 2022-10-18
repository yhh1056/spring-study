package com.example.transaction.proxy;

public class HelloTarget implements Hello {

    @Override
    public String sayHello(final String name) {
        return "hello " + name;
    }

    @Override
    public String sayHi(final String name) {
        return "hi " + name;
    }

    @Override
    public String sayThankYou(final String name) {
        return "thank yoo " + name;
    }
}
