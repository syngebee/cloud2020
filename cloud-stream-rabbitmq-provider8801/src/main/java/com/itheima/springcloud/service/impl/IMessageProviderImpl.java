package com.itheima.springcloud.service.impl;

import com.itheima.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.UUID;

@EnableBinding(Source.class) //定义消息的推送管道,他是Source
public class IMessageProviderImpl implements IMessageProvider {

    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel output;  //消息发送管道.

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString(); //生成一个流水号
        Message<String> message = MessageBuilder.withPayload(serial).build();
        output.send(message);
        System.out.println("**********serial: "+serial);
        return serial;
    }
}
