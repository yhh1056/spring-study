package com.example.transaction.proxy.factoryBean;

public class Message {

    private String text;

    private Message(final String text) {
        this.text = text;
    }

    public static Message newMessage(final String text) {
        return new Message(text);
    }

    public String getText() {
        return text;
    }
}
