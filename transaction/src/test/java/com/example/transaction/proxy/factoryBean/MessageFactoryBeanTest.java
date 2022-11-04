package com.example.transaction.proxy.factoryBean;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.transaction.proxy.factoryBean.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class MessageFactoryBeanTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void getMessageFactoryBean() {
        Object bean = applicationContext.getBean("messageFactoryBean");

        Message message = (Message) bean;
        assertThat(bean).isExactlyInstanceOf(Message.class);
        assertThat(message.getText()).isEqualTo("Factory Bean");
    }

}
