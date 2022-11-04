package com.example.transaction.proxy.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MessageFactoryBean implements FactoryBean<Message> {

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage("Factory Bean");
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
